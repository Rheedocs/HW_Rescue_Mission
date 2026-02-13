package events;

import domain.GameLog;
import domain.ShipState;
import exceptions.CriticalStatusException;
import io.ConsoleIO;

import java.util.Random;

public class EngineEvent {
    private static final int REPAIR_BONUS = 20;
    private static final int RESTART_FAIL_DAMAGE = 15;

    private boolean resolved = false;

    private final Random random = new Random();

    public void run(ShipState state, GameLog log, ConsoleIO io) throws CriticalStatusException {

        while (!resolved) {
            System.out.println("----------------------------------------");
            System.out.println("EVENT 3 – MOTOR FEJL");
            System.out.println("Motoren begynder at lyde ustabil...");

            // Viser muligheder, og når playeren har brugt repair-kittet, så kan playeren kun ignorer rumlen, da han ikke har et repair kit længere.
            if (!state.isRepairKitUsed()) {
                System.out.println("1) Brug repair kit");
                System.out.println("2) Forsøg genstart");
            } else {
                System.out.println("Kan ikke længere fikse motoren. Repair kit brugt!");
                System.out.println("2) Forsøg genstart");
            }

            int choice = io.readChoice("> ", 1, 2);

            // Hvis spilleren prøver at bruge repair kit igen, håndter pænt med besked (ingen exception)
            if (choice == 1 && state.isRepairKitUsed()) {
                System.out.println("----------------------------------------");
                System.out.println("Repair kit er allerede brugt tidligere");
                System.out.println("----------------------------------------");
                log.add("Event 3: Repair kit var allerede brugt");
                continue; // Bliv i eventet så spilleren kan vælge (2) bagefter
            }

            // Hvis spilleren bruger repair kit første gang, så giv bonus og gå direkte videre til genstart i samme event (uden at printe event-header igen)
            if (choice == 1) {
                System.out.println("----------------------------------------");
                System.out.println("Du bruger repair kit...");
                state.setRepairKitUsed(true);

                // Repair kit kan bruges én gang i hele spillet og giver +20 integrity og logges
                System.out.println("Repair kit brugt! (+" + REPAIR_BONUS + ") integritet.");
                state.addIntegrity(REPAIR_BONUS);
                state.resetEngineFailures();
                log.add("Event 3: Repair kit brugt (+" + REPAIR_BONUS + ") integrity -> " + state.getIntegrity());
                System.out.println("----------------------------------------");

                // Efter repair kit går vi direkte til genstartforsøget i samme loop
                choice = 2;
            }

            // Regner success chance efter playeren har valgt genstart (valg 2).
            boolean success = random.nextDouble() < 0.4;

            try {
                if (choice == 2) {
                    if (success) {
                        System.out.println("----------------------------------------");
                        System.out.println("Motoren starter korrekt!");
                        System.out.println("----------------------------------------");
                        state.resetEngineFailures();
                        log.add("Event 3: Motor genstartet succesfuldt");
                        resolved = true;
                    } else {
                        System.out.println("----------------------------------------");
                        System.out.println("Genstart mislykkedes! -" + RESTART_FAIL_DAMAGE + " integritet");
                        System.out.println("----------------------------------------");
                        state.addIntegrity(-RESTART_FAIL_DAMAGE);
                        state.addEngineFailure();
                        log.add("Event 3: Motor genstart fejlede (-" + RESTART_FAIL_DAMAGE + ") integritet");

                        if (state.getEngineFailures() >= 2) {
                            throw new CriticalStatusException("Motoren er permanent ødelagt efter to fejl i træk!");
                        }
                    }
                }
            } catch (CriticalStatusException e) {
                throw new CriticalStatusException(e.getMessage());
            } finally {
                // finally skal altid udskrive Forsøger at genstarte
                System.out.println("Forsøger at genstarte...");
            }
        }
    }
}
