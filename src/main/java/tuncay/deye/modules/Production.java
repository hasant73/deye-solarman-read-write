package tuncay.deye.modules;

/**
 * Time of use registers.
 * All registers are only readable.
 */
public enum Production implements Register {

  PV1_POWER(672, 1, DataType.WATT, 1.0, 0, 10000),
  PV2_POWER(673, 1, DataType.WATT, 1.0, 0, 10000),
  PV3_POWER(674, 1, DataType.WATT, 1.0, 0, 10000),
  PV4_POWER(675, 1, DataType.WATT, 1.0, 0, 10000),

  PV1_VOLT(676, 1, DataType.VOLT, 0.1, 0, 5000),
  PV2_VOLT(678, 1, DataType.VOLT, 0.1, 0, 5000),
  PV3_VOLT(680, 1, DataType.VOLT, 0.1, 0, 5000),
  PV4_VOLT(682, 1, DataType.VOLT, 0.1, 0, 5000),

  PV1_CURRENT(677,1, DataType.AMPERE, 0.1, 0, 50),
  PV2_CURRENT(679,1, DataType.AMPERE, 0.1, 0, 50),
  PV3_CURRENT(681,1, DataType.AMPERE, 0.1, 0, 50),
  PV4_CURRENT(683,1, DataType.AMPERE, 0.1, 0, 50);

  private final int mRegister;
  private final boolean mWriteable;
  private final int mLength;
  private final DataType mDataType;
  private final double mMultiplier;
  private final double mMax;
  private final double mMin;

  Production(int pRegister, int pLength, DataType pDataType, double pMultiplier, double pMin, double pMax) {
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
