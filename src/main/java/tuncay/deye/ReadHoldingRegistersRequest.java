package tuncay.deye;

public class ReadHoldingRegistersRequest implements ModbusRequest {

  private final int mStartRegister;
  private final int mNumberOfRegisters;

  public ReadHoldingRegistersRequest(int pStartRegister, int pNumberOfRegisters) {
    this.mStartRegister = pStartRegister;
    this.mNumberOfRegisters = pNumberOfRegisters;
  }

  @Override
  public byte functionCode() {
    return 0x03;
  }

  @Override
  public byte[] payload() {

    return new byte[]{
        (byte) (mStartRegister >> 8),
        (byte) mStartRegister,
        (byte) (mNumberOfRegisters >> 8),
        (byte) mNumberOfRegisters
    };
  }
}