package events;

import domain.GameLog;
import domain.ShipState;
import exceptions.CriticalStatusException;
import exceptions.InvalidTradeException;
import io.ConsoleIO;
import io.Printer;
import service.GameRules;

public class TradeEvent {

    private final Printer printer = new Printer();
    private final GameRules rules = new GameRules();

    public void run(ShipState state, GameLog log, ConsoleIO io)
            throws CriticalStatusException, InvalidTradeException {

        log.add("EVENT 2: HANDEL STARTET");

        System.out.println("=== EVENT 2: HANDEL ===");
        printer.printStatus(state);

        System.out.println("1) Trade reservedele -> brændstof");
        System.out.println("2) Køb shield (4 reservedele)");
        System.out.println("3) Skip");

        int choice = io.readInt("Vælg (1-3): ");

        switch (choice) {

            case 1: // TRADE
                int x = io.readInt("Hvor mange reservedele vil du trade?: ");

                if (x <= 0)
                    throw new IllegalArgumentException("Antal skal være > 0");

                if (x > state.getSpareParts())
                    throw new InvalidTradeException("Du har ikke nok reservedele");

                state.addSpareParts(-x);
                state.addFuel(x * 5);

                log.add("EVENT 2: Trade " + x +
                        " reservedele -> +" + (x * 5) + " brændstof");
                break;

            case 2: // SHIELD
                int cost = 4;

                if (state.getSpareParts() < cost)
                    throw new InvalidTradeException("Ikke nok reservedele til shield");

                state.addSpareParts(-cost);
                state.setShieldLevel(1);

                log.add("EVENT 2: Købte shield for " + cost + " reservedele");
                break;

            case 3: // SKIP
                log.add("EVENT 2: Spilleren sprang handel over");
                break;

            default:
                throw new IllegalArgumentException("Ugyldigt valg");
        }

        // Tjek kritisk status efter event
        rules.checkCriticalStatus(state);
    }
}