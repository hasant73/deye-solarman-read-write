package tuncay.deye.modules;

/**
 * Time of use registers.
 * All registers are writeable.
 * @author hasant73
 */
public enum TimeOfUse implements Register {

  TIME_1(148, 1, DataType.INT, 1.0, 0, 2400),
  TIME_2(149, 1, DataType.INT, 1.0, 0, 2400),
  TIME_3(150, 1, DataType.INT, 1.0, 0, 2400),
  TIME_4(151, 1, DataType.INT, 1.0, 0, 2400),
  TIME_5(152, 1, DataType.INT, 1.0, 0, 2400),
  TIME_6(153, 1, DataType.INT, 1.0, 0, 2400),

  MAX_DISCHARGE_CURRENT_1(154, 1, DataType.AMPERE, 1, 0, 80),
  MAX_DISCHARGE_CURRENT_2(155, 1, DataType.AMPERE, 1, 0, 80),
  MAX_DISCHARGE_CURRENT_3(156, 1, DataType.AMPERE, 1, 0, 80),
  MAX_DISCHARGE_CURRENT_4(157, 1, DataType.AMPERE, 1, 0, 80),
  MAX_DISCHARGE_CURRENT_5(158, 1, DataType.AMPERE, 1, 0, 80),
  MAX_DISCHARGE_CURRENT_6(159, 1, DataType.AMPERE, 1, 0, 80),

  BATTERY_VOLTAGE_1(160, 1, DataType.VOLT, 0.01, 0, 63),
  BATTERY_VOLTAGE_2(161, 1, DataType.VOLT, 0.01, 0, 63),
  BATTERY_VOLTAGE_3(162, 1, DataType.VOLT, 0.01, 0, 63),
  BATTERY_VOLTAGE_4(163, 1, DataType.VOLT, 0.01, 0, 63),
  BATTERY_VOLTAGE_5(164, 1, DataType.VOLT, 0.01, 0, 63),
  BATTERY_VOLTAGE_6(165, 1, DataType.VOLT, 0.01, 0, 63),

  SOC_1(166, 1, DataType.PERCENT, 1.0, 0, 100),
  SOC_2(167, 1, DataType.PERCENT, 1.0, 0, 100),
  SOC_3(168, 1, DataType.PERCENT, 1.0, 0, 100),
  SOC_4(169, 1, DataType.PERCENT, 1.0, 0, 100),
  SOC_5(170, 1, DataType.PERCENT, 1.0, 0, 100),
  SOC_6(171, 1, DataType.PERCENT, 1.0, 0, 100),

  CHARGING_ENABLED_1(172, 1, DataType.BOOLEAN, 1.0, 0, 1),
  CHARGING_ENABLED_2(173, 1, DataType.BOOLEAN, 1.0, 0, 1),
  CHARGING_ENABLED_3(174, 1, DataType.BOOLEAN, 1.0, 0, 1),
  CHARGING_ENABLED_4(175, 1, DataType.BOOLEAN, 1.0, 0, 1),
  CHARGING_ENABLED_5(176, 1, DataType.BOOLEAN, 1.0, 0, 1),
  CHARGING_ENABLED_6(177, 1, DataType.BOOLEAN, 1.0, 0, 1);

  private final int mRegister;
  private final boolean mWriteable;
  private final int mLength;
  private final DataType mDataType;
  private final double mMultiplier;
  private final double mMax;
  private final double mMin;

  TimeOfUse(int pRegister, int pLength, DataType pDataType, double pMultiplier, double pMin, double pMax) {
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
