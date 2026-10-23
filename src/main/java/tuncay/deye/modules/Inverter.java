package tuncay.deye.modules;

/**
 * Inverter registers.
 * @author hasant73
 */
public enum Inverter implements Register {

  DEVICE_TYPE(0, 1, DataType.INT, 1.0, 0, 10),
  MODBUS_ADDRESS(1, 1, DataType.INT, 1.0, 0, 10),
  PROTOCOL(2, 1, DataType.INT, 1.0, 0, 10),
  SERIAL_NUMBER(3, 5, DataType.ASCII, 1.0, 100000000, 999999999),
  POWER_RATE(20, 1, DataType.KW, 0.0001, 0, 50000),
  TIME_STAMP(62,  3, DataType.TIMESTAMP, 0, 0, 0),

  // 0 standby
  // 1 self-check selfcheck
  // 2 normal
  // 3 alarm alarm
  // 4 Fault fault
  STATUS(500, 1, DataType.INT, 1.0, 0, 10);

  private final int mRegister;
  private final boolean mWriteable;
  private final int mLength;
  private final DataType mDataType;
  private final double mMultiplier;
  private final double mMax;
  private final double mMin;

  Inverter(int pRegister, int pLength, DataType pDataType, double pMultiplier, double pMin, double pMax) {
    mRegister = pRegister;
    mWriteable = false;
    mLength = pLength;
    mDataType = pDataType;
    this.mMultiplier = pMultiplier;
    this.mMax = pMax;
    this.mMin = pMin;
  }

  public int getRegister() {
    return mRegister;
  }

  public boolean isWriteable() {
    return mWriteable;
  }

  @Override
  public boolean isDoubleRegister() {
    return false;
  }

  @Override
  public int getLength() {
    return mLength;
  }

  @Override
  public DataType getDataType() {
    return mDataType;
  }

  @Override
  public double getMultiplier() {
    return mMultiplier;
  }

  @Override
  public double getMax() {
    return mMax;
  }

  @Override
  public double getMin() {
    return mMin;
  }
}
