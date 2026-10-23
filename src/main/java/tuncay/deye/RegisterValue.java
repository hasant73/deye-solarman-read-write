package tuncay.deye;

/**
 * Register value with address and value.
 */
public record RegisterValue(
        int address,
        int value) {

    public String hex() {
        return String.format("0x%04X", value);
    }

    @Override
    public String toString() {
        return address + " = " + value + " (" + hex() + ")";
    }
}