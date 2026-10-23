package tuncay.deye.modules;

/**
 * Totals registers.
 * The totals are saved in two registers. The first register contains the high word, the second register contains the low word.
 * @author hasant73
 */
public enum Totals implements Register {

  CHARGE_BATTERY_DAY(514, 1, DataType.KWH, 0.1, 0, 50),
  DISCHARGE_BATTERY_DAY(515, 1, DataType.KWH, 0.1, 0, 50),

  CHARGE_BATTERY_TOTAL(516, 2, DataType.KWH, 0.1, 0, 100000),
  DISCHARGE_BATTERY_TOTAL(518, 2, DataType.KWH, 0.1, 0, 100000),

  PURCHASED_ENERGY_DAY(520, 1, DataType.KWH, 0.1, 0.1, 100),
  SELL_ENERGY_DAY(521, 1, DataType.KWH, 0.1, 0.1, 100),

  PURCHASED_ENERGY_TOTAL(522, 2, DataType.KWH, 0.1, 0, 100000),
  SELL_ENERGY_TOTAL(524, 2, DataType.KWH, 0.1, 0, 100000),

  CONSUMPTION_ENERGY_DAY(526, 1, DataType.KWH, 0.1, 0.1, 100),
  CONSUMPTION_ENERGY_TOTAL(527, 2, DataType.KWH, 0.1, 0, 100000),

  ENERGY_GENERATION_DAY(529, 1, DataType.KWH, 0.1, 0, 100),

  ENERGY_GENERATION_PV1(530, 1, DataType.KWH, 0.1, 0, 100),
  ENERGY_GENERATION_PV2(531, 1, DataType.KWH, 0.1, 0, 100),
  ENERGY_GENERATION_PV3(532, 1, DataType.KWH, 0.1, 0, 100),
  ENERGY_GENERATION_PV4(533, 1, DataType.KWH, 0.1, 0, 100),

  ENERGY_GENERATION_TOTAL(534, 2, DataType.KWH, 0.1, 0, 100000);

  private final int mRegister;
  private final boolean mWriteable;
  private final int mLength;
  private final DataType mDataType;
  private final double mMultiplier;
  private final double mMax;
  private final double mMin;

  Totals(int pRegister, int pLength, DataType pDataType, double pMultiplier, double pMin, double pMax) {
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
