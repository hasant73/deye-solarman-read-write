package tuncay.deye;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tuncay.deye.modules.Inverter;
import tuncay.deye.modules.Production;
import tuncay.deye.modules.TimeOfUse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {

  private static final Logger log = LoggerFactory.getLogger(Main.class);

  // set your own IP address of the Deye inverter here
  private static final String IP_ADDRESS = "192.168.178.55";

  // set your own port of the Deye inverter here
  private static final int PORT = 8899;

  static void main(String[] args) throws Exception {

    try (DeyeClient deye = new DeyeClient(IP_ADDRESS, PORT)) {

      // write multiple registers at once, for example to set the SOC values for the Time of Use module
      deye.write(TimeOfUse.SOC_1.getRegister(), 40, 40, 40, 40, 40, 40);

      // wait a second
      TimeUnit.SECONDS.sleep(1);

      // read multiple registers at once, for example to read the SOC values for the Time of Use module
      List<RegisterValue> values = deye.read(TimeOfUse.SOC_1.getRegister(), 6);
      values.forEach(l -> log.info(l.toString()));

      String serialNumber = deye.readAsciiString(3,5);
      log.info(serialNumber);

      LocalDateTime time = deye.read3ByteInverterTime(Inverter.TIME_STAMP.getRegister(), 3);
      log.info("Inverter time: " + time);

      while (!Thread.interrupted()) {
        try {
          List<RegisterValue> pvs = deye.read(Production.PV1_POWER.getRegister(), 2);
          log.info("PV1: " + pvs.get(0) + " PV2: " + pvs.get(1));
          TimeUnit.MINUTES.sleep(1);
        } catch (Exception e) {
          log.error("Error reading PV power", e);
        }
      }
    }

  }
}