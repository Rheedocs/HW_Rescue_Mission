package domain;

public class ShipState {
    private int fuel = 100;
    private int integrity = 100;
    private int spareParts = 10;
    private int shieldLevel = 0;
    private boolean repairKitUsed = false;

    private final String captainName;
    private final String shipName;

    public ShipState(String captainName, String shipName) {
        this.captainName = captainName;
        this.shipName = shipName;
    }

    public int getFuel() { return fuel; }
    public int getIntegrity() { return integrity; }
    public int getSpareParts() { return spareParts; }
    public int getShieldLevel() { return shieldLevel; }
    public boolean isRepairKitUsed() { return repairKitUsed; }
    public String getCaptainName() { return captainName; }
    public String getShipName() { return shipName; }

    public void addFuel(int delta) { fuel += delta; }
    public void addIntegrity(int delta) { integrity += delta; }
    public void addSpareParts(int delta) { spareParts += delta; }
    public void setShieldLevel(int level) { shieldLevel = level; }
    public void setRepairKitUsed(boolean used) { repairKitUsed = used; }
}
