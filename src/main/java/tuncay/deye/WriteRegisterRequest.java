package tuncay.deye;

public class WriteRegisterRequest implements ModbusRequest {

  private final int mRegister;
  private final int mValue;

  public WriteRegisterRequest(int pRegister, int pValue) {
    this.mRegister = pRegister;
    this.mValue = pValue;
  }

  @Override
  public byte functionCode() {
    return 0x10;
  }

  @Override
  public byte[] payload() {
    return new byte[]{
        (byte) (mRegister >> 8),
        (byte) mRegister,
        0,
        1,
        2,
        (byte) (mValue >> 8),
        (byte) mValue
    };
  }
}