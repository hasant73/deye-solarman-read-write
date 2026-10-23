package tuncay.deye;

/**
 * Modbus request interface.
 */
public interface ModbusRequest {

  byte functionCode();

  byte[] payload();

}