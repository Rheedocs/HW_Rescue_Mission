package events;

import domain.GameLog;
import domain.ShipState;
import io.ConsoleIO;

public class StormEvent {

    private static final int FUEL_STORM = 5;
    private static final int FUEL_DETOUR = 10;
    private static final int SHIELD_REDUCTION_PER_LEVEL = 10;

    private static final int STORM_DAMAGE_MIN = 20;
    private static final int STORM_DAMAGE_MAX = 45;
    private static final int DETOUR_DAMAGE_MIN = 8;
    private static final int DETOUR_DAMAGE_MAX = 28;

    public void run(ShipState state, GameLog log, ConsoleIO io) {
        System.out.println("EVENT 1 – RUMSTORM");
        System.out.println("En voldsom rumstorm nærmer sig");
        System.out.println("1) Flyv igennem stormen (høj risiko)");
        System.out.println("2) Tag en omvej (-10 brændstof, lavere skade)");

        int choice = io.readChoice("> ", 1, 2);

        int fuelBefore = state.getFuel();
        int integrityBefore = state.getIntegrity();

        int rawDamage;
        int fuelCost;

        if (choice == 1) {
            System.out.println("Flyver ind i stormen...");
            fuelCost = FUEL_STORM;
            rawDamage = randomInt(STORM_DAMAGE_MIN, STORM_DAMAGE_MAX);
            log.add("Event 1: Valgte storm");
        } else {
            System.out.println("Plotter alternativ rute gennem asteroidefelt...");
            fuelCost = FUEL_DETOUR;
            rawDamage = randomInt(DETOUR_DAMAGE_MIN, DETOUR_DAMAGE_MAX);
            log.add("Event 1: Valgte omvej (-" + FUEL_DETOUR + " fuel)");
        }

        System.out.println("----------------------------------------");
        System.out.println("Stormskade beregnes...");

        int shieldReduction = state.getShieldLevel() * SHIELD_REDUCTION_PER_LEVEL;
        int finalDamage = Math.max(0, rawDamage - shieldReduction);

        state.addFuel(-fuelCost);
        state.addIntegrity(-finalDamage);

        System.out.println("Stormskade: " + rawDamage);

        if (shieldReduction > 0) {
            System.out.println("Shield absorberer " + shieldReduction + " skade");
        } else {
            System.out.println("Ingen aktivt shield – fuld skade mod skroget");
        }

        System.out.println("Integritet -" + finalDamage);
        System.out.println("Brændstof -" + fuelCost);
        System.out.println("----------------------------------------");

        log.add("Event 1: fuel " + fuelBefore + "->" + state.getFuel()
                + ", integrity " + integrityBefore + "->" + state.getIntegrity()
                + ", raw " + rawDamage + ", shield " + shieldReduction + ", final " + finalDamage);
    }

    private int randomInt(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }
}
