package tuncay.deye;

import java.io.ByteArrayOutputStream;

/**
 * Modbus request for writing multiple registers.
 */
public class WriteRegistersRequest implements ModbusRequest {

  private final int mStartRegister;
  private final int[] mValues;

  public WriteRegistersRequest(int pStartRegister, int... pValues) {
    this.mStartRegister = pStartRegister;
    this.mValues = pValues;
  }

  @Override
  public byte functionCode() {
    return 0x10;
  }

  @Override
  public byte[] payload() {
    final ByteArrayOutputStream payload = new ByteArrayOutputStream();

    payload.write(mStartRegister >> 8);   // register high byte
    payload.write(mStartRegister);           // register low byte

    payload.write(mValues.length >> 8);   // register count high byte
    payload.write(mValues.length);           // register count low byte

    payload.write(mValues.length * 2);    // byte count

    for (int value : mValues) {
      payload.write(value >> 8);          // value high byte
      payload.write(value);                  // value low byte
    }

    return payload.toByteArray();
  }
}