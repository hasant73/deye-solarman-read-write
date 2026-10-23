package tuncay.deye;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Modbus TCP connection.
 */
public class ModbusConnection implements AutoCloseable {

  private final static Logger log = LoggerFactory.getLogger(ModbusConnection.class);
  private final int mSlaveId;
  private final Socket mSocket;
  private final InputStream mInputStream;
  private final OutputStream mOutputStream;
  private final Integer mMaxRetries;

  private final AtomicInteger mNextTransactionId = new AtomicInteger(1);

  public ModbusConnection(String pHost, int pPort, int pSlaveId, int pMaxRetries) throws IOException {
    mSocket = new Socket(pHost, pPort);
    mInputStream = mSocket.getInputStream();
    mOutputStream = mSocket.getOutputStream();
    mSlaveId = pSlaveId;
    mMaxRetries = pMaxRetries;
  }

  public synchronized byte[] execute(ModbusRequest pRequest) throws IOException, InterruptedException {
    for (int retry = 0; retry < mMaxRetries; retry++) {
      // create request frame
      int txRequest = mNextTransactionId.incrementAndGet();

      // create frame
      byte[] requestBytes = createFrame(pRequest, txRequest);

      // send
      mOutputStream.write(requestBytes);
      mOutputStream.flush();

      // receive response
      byte[] modbusAppProtoHeader = mInputStream.readNBytes(7);
      log.debug("RECV MBAP: " + hex(modbusAppProtoHeader));
      int responseLength = ((modbusAppProtoHeader[4] & 0xff) << 8) | (modbusAppProtoHeader[5] & 0xff);
      int txResponse = ((modbusAppProtoHeader[0] & 0xff) << 8) | (modbusAppProtoHeader[1] & 0xff);

      if (txResponse != txRequest) {
        log.warn("Transaction ID mismatch: expected " + txRequest + " but got " + txResponse + ". Retrying...");
        TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(2, 9));
        continue;
      }

      // read rest of the response
      return mInputStream.readNBytes(responseLength - 1);
    }
    throw new IOException("Transaction ID mismatch after 10 retries: expected " + mNextTransactionId);
  }

  private byte[] createFrame(ModbusRequest pRequest, int pTransaction) throws IOException {
    int requestLength = 2 + pRequest.payload().length;
    ByteArrayOutputStream frame = new ByteArrayOutputStream();
    frame.write(pTransaction >> 8);
    frame.write(pTransaction);
    frame.write(0);
    frame.write(0);
    frame.write(requestLength >> 8);
    frame.write(requestLength);
    frame.write(mSlaveId);
    frame.write(pRequest.functionCode());
    frame.write(pRequest.payload());

    // copy bytes to byte array and log
    byte[] requestBytes = frame.toByteArray();
    log.debug("SEND tx=" + pTransaction + " fc=" + pRequest.functionCode() + " bytes=" + requestBytes.length);
    log.debug("SEND DATA: " + hex(requestBytes));

    return requestBytes;
  }

  @Override
  public void close() throws IOException {
    mSocket.close();
  }

  private static String hex(byte[] data) {
    StringBuilder sb = new StringBuilder();
    for (byte b : data) {
      sb.append(String.format("%02X ", b));
    }
    return sb.toString();
  }

}