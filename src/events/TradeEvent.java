package events;

import domain.GameLog;
import domain.ShipState;
import exceptions.InvalidTradeException;
import io.ConsoleIO;

public class TradeEvent {

    private static final int FUEL_PER_PART = 5;
    private static final int SHIELD_COST = 4;

    public void run(ShipState state, GameLog log, ConsoleIO io) {
        System.out.println("EVENT 2 – HANDEL OG SHIELD");

        while (true) {
            System.out.println("1) Trade reservedele -> brændstof");
            System.out.println("2) Køb shield level 1 (" + SHIELD_COST + " reservedele)");
            System.out.println("3) Skip");

            int choice = io.readChoice("> ", 1, 3);

            try {
                switch (choice) {
                    case 1 -> {
                        log.add("Event 2: Valgte trade");
                        int parts = io.readInt("Hvor mange reservedele vil du trade?: ");

                        if (parts <= 0) {
                            throw new IllegalArgumentException("Antal skal være > 0");
                        }
                        if (parts > state.getSpareParts()) {
                            throw new InvalidTradeException("Du har ikke nok reservedele");
                        }

                        state.addSpareParts(-parts);
                        state.addFuel(parts * FUEL_PER_PART);

                        log.add("Event 2: Trade " + parts + " dele -> +" + (parts * FUEL_PER_PART) + " fuel");
                        return;
                    }

                    case 2 -> {
                        log.add("Event 2: Valgte shield");

                        if (state.getShieldLevel() >= 1) {
                            System.out.println("Shield er allerede aktivt.");
                            log.add("Event 2: Shield var allerede aktivt");
                            return;
                        }
                        if (state.getSpareParts() < SHIELD_COST) {
                            throw new InvalidTradeException("Ikke nok reservedele til shield");
                        }

                        state.addSpareParts(-SHIELD_COST);
                        state.setShieldLevel(1);

                        log.add("Event 2: Købte shield level 1 for " + SHIELD_COST + " dele");
                        return;
                    }

                    case 3 -> {
                        log.add("Event 2: Spilleren sprang handel over");
                        return;
                    }
                }
            } catch (IllegalArgumentException e) {
                System.out.println("FEJL: " + e.getMessage());
                log.add("Event 2: Ugyldigt input (" + e.getMessage() + ")");

            } catch (InvalidTradeException e) {
                System.out.println("FEJL: " + e.getMessage());
                log.add("Event 2: Trade-fejl (" + e.getMessage() + ")");
            }
        }
    }
}