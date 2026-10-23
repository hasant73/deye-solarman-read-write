package tuncay.deye;

/**
 * Modbus request interface.
 * @author hasant73
 */
public interface ModbusRequest {

  byte functionCode();

  byte[] payload();

}