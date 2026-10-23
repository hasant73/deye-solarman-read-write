package tuncay.deye.modules;

/**
 * Register interface.
 * @author hasant73
 */
public interface Register {
  int getRegister();
  int getLength();
  boolean isWriteable();
  boolean isDoubleRegister();
  DataType getDataType();
  double getMultiplier();
  double getMax();
  double getMin();

}
