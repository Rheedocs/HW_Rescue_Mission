package events;

import domain.GameLog;
import domain.ShipState;
import exceptions.CriticalStatusException;
import exceptions.InvalidTradeException;
import io.ConsoleIO;
import io.Printer;

import java.util.Random;

public class EngineEvent {
    private static boolean resolved = false;


    private final Random random = new Random();

    public void run(ShipState state, GameLog log, ConsoleIO io) throws CriticalStatusException, InvalidTradeException {

        while (!resolved) {
            System.out.println("EVENT – ENGINE");
            System.out.println("Motoren begynder at lyde ustabil...");

            // Viser muligheder, og når playeren har brugt repair-kittet, så kan playeren kun ignorer rumlen, da han ikke har et repair kit længere.
            if (!state.isRepairKitUsed()) {
                System.out.println("1. Brug repair kit?");
                System.out.println("2. Ignorér rumlen?");
            } else {
                System.out.println("Kan ikke længere fikse motoren. Repair kit brugt!");
                System.out.println("2. Ignorér rumlen?");
            }


            boolean success = Math.random() < 0.4;
            int choice = io.readChoice("> ", 1, 2);

            if (choice == 1) {
                if (state.isRepairKitUsed()) {
                    System.out.println("Repair kit er allerede brugt tidligere");
                    log.add("Event 3: Repair kit mislykkedes!");
                    resolved = true;
                }

                System.out.println("Du forsøger at reparere motoren...");

                state.setRepairKitUsed(true);

                if (success) {
                    System.out.println("Repair kit brugt! +20 integritet.");
                    state.addIntegrity(20);
                    state.setRepairKitUsed(true);
                    state.resetEngineFailures();
                    log.add("Event 3: Motor repareret (+20) " + "integrity -> " + state.getIntegrity());

                    resolved = true;
                } else {
                    System.out.println("Repair kit mislykkedes... Motoren rumler stadig.");
                    state.addEngineFailure();
                    state.addIntegrity(-10);
                    log.add("Event 3: Reparation mislykkedes (-10 integrity)");
                }
                continue;
            }

            try {
                if (choice == 2) {
                    if (success) {
                        System.out.println("Motoren starter korrekt!");
                        System.out.println("Motoren er nu igang igen!");
                        state.resetEngineFailures();
                        log.add("Event 3: Motor genstartet succesfuldt");
                        resolved = true;
                        break;
                    } else {
                        System.out.println("Genstart mislykkedes! -15 integritet");
                        state.addIntegrity(-15);
                        state.addEngineFailure();
                        log.add("Event 3: Motor genstart fejlede (-15) integritet");

                        if (state.getEngineFailures() >= 2) {
                            throw new CriticalStatusException("Motoren er permanent ødelagt efter to fejl i træk!");
                        }
                    }
                }
            } catch (CriticalStatusException e) {
                throw new CriticalStatusException(e.getMessage());
            } finally {
                    System.out.println("Forsøger at genstarte...");
                }

            }

        }
    }








