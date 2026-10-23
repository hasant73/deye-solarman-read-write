# Deye Inverter Modbus Client

A small Java client for reading and writing Deye inverter registers over Modbus TCP through a Solarman LAN dongle. The project is intended both as a lightweight library and as a practical reference for Modbus communication with Deye inverters.

> **Warning:** This client can change inverter settings. Incorrect register values may damage batteries or connected equipment, shorten battery life, cause inverter faults, or violate local grid requirements. Use it only if you understand the settings you are changing, and at your own risk. The software is provided without warranty; the author is not responsible for resulting damage, loss, or regulatory consequences.

## Features

- Read holding registers and write consecutive registers.
- Use named register constants for selected Time of Use and battery values.
- Manage the TCP connection with `try`-with-resources.

Register availability and meaning can vary by inverter model and firmware. Confirm register addresses and valid values for your device before use.

## Requirements

- Java 25 or newer, but older version will work too with tiny changes. 
- Apache Maven
- A Deye Hybrid inverter reachable over the local network through a compatible Solarman LAN dongle

The client connects to the host and port you provide. Port `8899` is commonly used for Modbus TCP, but check your device configuration. The current client uses Modbus slave ID `1`.

## Tested with

- Deye SUN-12K-SG04LP3-EU (12kW hybrid inverter)
- Solarman LAN dongle LSE-3, not with the Wifi Dongle. 

## Build

From the project root, run:

```sh
mvn clean package
```

## Usage

The project is not currently published as a Maven dependency. Add the project as a module or include its source in your application, then create a `DeyeClient` with the inverter's reachable address:

```java
import tuncay.deye.DeyeClient;
import tuncay.deye.modules.TimeOfUse;

public class ReadInverter {
    public static void main(String[] args) throws Exception {
        String inverterHost = "192.168.1.50";
        int modbusPort = 8899;

        try (DeyeClient client = new DeyeClient(inverterHost, modbusPort)) {
            var socValues = client.read(TimeOfUse.SOC_1.getRegister(), 6);
            socValues.forEach(System.out::println);
        }
    }
}
```

`read(startRegister, count)` returns a list of `RegisterValue` objects containing each register's address and value. For example, write six consecutive Time of Use SOC values with:

```java
client.write(TimeOfUse.SOC_1.getRegister(), 40, 40, 40, 40, 40, 40);
```

Writes take effect on the inverter. Verify the register range and values for your model before running a write operation.

## Register maps included

| Register group | Addresses | Named values |
| --- | --- | --- |
| Time of Use time slots | 148-153 | `TimeOfUse.TIME_1` to `TIME_6` |
| Maximum discharge current | 154-159 | `MAX_DISCHARGE_CURRENT_1` to `_6` |
| Battery voltage | 160-165 | `BATTERY_VOLTAGE_1` to `_6` |
| State of charge (SOC) | 166-171 | `SOC_1` to `SOC_6` |
| Charging enabled | 172-177 | `CHARGING_ENABLED_1` to `_6` |
| Battery telemetry | 514, 515, 586-588, 590-592 | `Battery` enum values |

Each enum value exposes its address with `getRegister()`. These maps are intentionally limited and are not a complete inverter register reference.

## Network and safety

Keep the dongle and inverter on a trusted local network. Do not expose the Modbus TCP port directly to the public internet. Begin with read-only operations, and consult your inverter and battery documentation before changing configuration.

## Project status

This project is primarily an educational Modbus communication example. Register coverage and device compatibility are limited; additional register definitions may be added over time.
