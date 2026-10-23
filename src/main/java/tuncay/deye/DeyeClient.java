package tuncay.deye;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

/**
 * <p>Deye client for reading and writing registers.</p>
 * <b>WARNING:</b>
 * This library allows reading and modifying inverter settings.
 * Incorrect usage can damage batteries, inverters, connected equipment,
 * or cause non-compliance with local regulations.
 *
 * <p>The software is provided AS IS without any warranty.</p>
 *
 * Use at your own risk.
 * @author hasant73
 */
public class DeyeClient implements AutoCloseable {

  private final ModbusConnection mConnection;

  public DeyeClient(String mHost, int mPort, int pMaxRetries) throws IOException {
    mConnection = new ModbusConnection(mHost, mPort, 1, pMaxRetries);
  }

  public Map<Integer, RegisterValue> read(int pRegister, int pCount) throws IOException, InterruptedException {
    var request = new ReadHoldingRegistersRequest(pRegister, pCount);
    byte[] response = mConnection.execute(request);
    int byteCount = response[1] & 0xff;
    Map<Integer, RegisterValue> values = new LinkedHashMap<>(pCount);
    for (int i = 0; i < byteCount / 2; i++) {
      int value = ((response[2 + i * 2] & 0xff) << 8) | (response[3 + i * 2] & 0xff);
      values.put(pRegister + i, new RegisterValue(pRegister + i, value));
    }
    return values;
  }

  /**
   * Reads a ascii string from the inverter registers. This is useful to
   * read the serial number for example.
   * @param pStart register to start
   * @param pCount count of registers to read
   * @return the ascii string read from the registers
   * @throws IOException e
   * @throws InterruptedException e
   */
  public String readAsciiString(int pStart, int pCount) throws IOException, InterruptedException {
    final Map<Integer, RegisterValue> values = read(pStart, pCount);
    StringBuilder result = new StringBuilder();
    for (Integer key : values.keySet()) {
      result.append((char) (values.get(key).value() >> 8));
      result.append((char) (values.get(key).value() & 0xFF));
    }
    return result.toString().trim();
  }

  public LocalDateTime read3ByteInverterTime(int pStart)
      throws IOException, InterruptedException {
    Map<Integer, RegisterValue> values = read(pStart, 3);
    int reg1 = values.get(pStart).value();
    int reg2 = values.get(pStart + 1).value();
    int reg3 = values.get(pStart + 2).value();
    int year = (reg1 >> 8) & 0xFF;
    int month = reg1 & 0xFF;
    int day = (reg2 >> 8) & 0xFF;
    int hour = reg2 & 0xFF;
    int minute = (reg3 >> 8) & 0xFF;
    int second = reg3 & 0xFF;
    return LocalDateTime.of(2000 + year, month, day, hour, minute, second);
  }

  /**
   * Read a integer that is saved into two registers (32 bit integer)
   * @param pStart start register
   * @return the integer value
   * @throws IOException e
   * @throws InterruptedException e
   */
  public int read32BitInteger(int pStart) throws IOException, InterruptedException {
    Map<Integer, RegisterValue> values = read(pStart, 2);
    int highWord = values.get(pStart).value();
    int lowWord = values.get(pStart + 1).value();
    return (highWord << 16) | lowWord;
  }

  public void write(int pStartRegister, int... pValue) throws IOException, InterruptedException {
    mConnection.execute(new WriteRegistersRequest(pStartRegister, pValue));
  }

  @Override
  public void close() throws IOException {
    mConnection.close();
  }


}