package tuncay.deye.modules;

/**
 * Data type for registers.
 * @author hasant73
 */
public enum DataType {

  INT,

  WATT,
  KW,
  KWH,

  VOLT,
  AMPERE,
  AMPERE_HOUR,
  HERTZ,

  TEMP,
  PERCENT,

  ASCII,

  TIMESTAMP, BOOLEAN; // 3 byte register, 1/2 bytes for year, month, day, hour, minute, second, use read3ByteInverterTime

}
