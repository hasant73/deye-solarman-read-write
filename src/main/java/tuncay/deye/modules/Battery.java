package tuncay.deye.modules;

/**
 * Battery registers.
 * All registers are only readable.
 * @author hasant73
 */
public enum Battery implements Register {

  TEMPERATURE(586, 1, DataType.TEMP, 0, 0, 70),
  VOLTAGE(587, 1, DataType.VOLT, 0.01, 0, 60),
  SOC(588, 1, DataType.PERCENT, 1.0, 0, 100),
  POWER(590, 1, DataType.WATT, 1.0, -10000, 10000),
  CURRENT(591, 1, DataType.AMPERE, 0.01, -100, 100),
  CAPACITY(592, 1, DataType.AMPERE_HOUR, 1.0, 0, 200);

  private final int mRegister;
  private final boolean mWriteable;
  private final int mLength;
  private final DataType mDataType;
  private final double mMultiplier;
  private final double mMax;
  private final double mMin;

  Battery(int pRegister, int pLength, DataType pDataType, double pMultiplier, double pMin, double pMax) {
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
