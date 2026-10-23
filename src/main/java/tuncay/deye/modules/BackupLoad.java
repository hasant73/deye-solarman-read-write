package tuncay.deye.modules;

/**
 * Load registers
 * All registers are only readable.
 */
public enum BackupLoad implements Register {

  L1_BACKUP_POWER(640, 1, DataType.WATT, 1.0, 0, 10000),
  L2_BACKUP_POWER(641, 1, DataType.WATT, 1.0, 0, 10000),
  L3_BACKUP_POWER(642, 1, DataType.WATT, 1.0, 0, 10000),

  BACKUP_TOTAL_POWER(643, 1, DataType.WATT, 1.0, 0, 30000),

  L1_VOLT(644, 1, DataType.VOLT, 1.0, 0, 250),
  L2_VOLT(645, 1, DataType.VOLT, 1.0, 0, 250),
  L3_VOLT(646, 1, DataType.VOLT, 1.0, 0, 250),

  L1_CURRENT(647, 1, DataType.AMPERE, 0.01, 0, 100),
  L2_FREQ(648, 1, DataType.AMPERE, 0.01, 0, 100),
  L3_FREQ(649, 1, DataType.AMPERE, 0.01, 0, 100),

  L1_POWER(650, 1, DataType.WATT, 1.0, 0, 10000),
  L2_POWER(651, 1, DataType.WATT, 1.0, 0, 10000),
  L3_POWER(652, 1, DataType.WATT, 1.0, 0, 10000),

  TOTAL_POWER(653, 1, DataType.WATT, 1.0, 0, 30000),
  LOAD_FREQUENCY(655, 1, DataType.HERTZ, 0.01, 45,55),
  PERCENTAGE(657, 1, DataType.PERCENT, 1.0, 0, 100);

  private final int mRegister;
  private final boolean mWriteable;
  private final int mLength;
  private final DataType mDataType;
  private final double mMultiplier;
  private final double mMax;
  private final double mMin;

  BackupLoad(int pRegister, int pLength, DataType pDataType, double pMultiplier, double pMin, double pMax) {
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
