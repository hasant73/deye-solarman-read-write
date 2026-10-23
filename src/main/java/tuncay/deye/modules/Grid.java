package tuncay.deye.modules;

/**
 * Grid registers.
 * All registers are only readable.
 * @author hasant73
 */
public enum Grid implements Register {

  L1_VOLT(598, 1, DataType.VOLT, 0.1, 0, 250),
  L2_VOLT(599, 1, DataType.VOLT, 0.1, 0, 250),
  L3_VOLT(600, 1, DataType.VOLT, 0.1, 0, 250),
  AB_VOLT(601, 1, DataType.VOLT, 0.1, 0, 250),
  BC_VOLT(602, 1, DataType.VOLT, 0.1, 0, 250),
  CA_VOLT(603, 1, DataType.VOLT, 0.1, 0, 250),
  L1_CT_INTERNAL(604, 1, DataType.WATT, 1.0, 0, 10000),
  L2_CT_INTERNAL(605, 1, DataType.WATT, 1.0, 0, 10000),
  L3_CT_INTERNAL(606, 1, DataType.WATT, 1.0, 0, 10000),
  ACTIVE_SIDE_TO_SIDE_POWER(607, 1, DataType.WATT, 1.0, 0, 10000),
  INSIDE_TOTAL_POWER(608, 1, DataType.WATT, 0.1, 0, 10000),
  FREQUENCY(609, 1, DataType.HERTZ, 0.01, 45, 55),
  L1_INTERNAL_CURRENT(610, 1, DataType.AMPERE, 0.01, -100, 100),
  L2_INTERNAL_CURRENT(611,1, DataType.AMPERE, 0.01, -100, 100),
  L3_INTERNAL_CURRENT(612, 1, DataType.AMPERE, 0.01, -100, 100),
  L1_EXTERNAL_CURRENT(613, 1, DataType.AMPERE, 0.01, -100, 100),
  L2_EXTERNAL_CURRENT(614, 1, DataType.AMPERE, 0.01, -100, 100),
  L3_EXTERNAL_CURRENT(615, 1, DataType.AMPERE, 0.01, -100, 100),
  L1_EXTERNAL_CT(616, 1, DataType.WATT, 1.0, -10000, 10000),
  L2_EXTERNAL_CT(617, 1, DataType.WATT, 1.0, -10000, 10000),
  L3_EXTERNAL_CT(618, 1, DataType.WATT, 1.0, -10000, 10000),
  OUT_TOTAL_POWER(619, 1, DataType.WATT, 1.0, -10000, 10000),
  TOTAL_GRID_POWER(625, 1, DataType.WATT, 1.0,-10000, 10000);

  private final int mRegister;
  private final boolean mWriteable;
  private final int mLength;
  private final DataType mDataType;
  private final double mMultiplier;
  private final double mMax;
  private final double mMin;

  Grid(int pRegister, int pLength, DataType pDataType, double pMultiplier, double pMin, double pMax) {
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
