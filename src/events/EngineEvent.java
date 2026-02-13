package events;

import domain.GameLog;
import domain.ShipState;
import exceptions.CriticalStatusException;
import io.ConsoleIO;

import java.util.Random;

public class EngineEvent {
    private static final int REPAIR_BONUS = 20;
    private static final int RESTART_FAIL_DAMAGE = 15;

    private final Random random = new Random();

    public void run(ShipState state, GameLog log, ConsoleIO io) throws CriticalStatusException {

        // Future-safe: hvis samme instance genbruges, virker eventet stadig.
        boolean resolved = false;

        while (!resolved) {
            System.out.println("EVENT 3 – MOTOR FEJL");
            System.out.println("Motoren begynder at lyde ustabil...");

            // Viser muligheder, og når playeren har brugt repair-kittet,
            // så kan playeren kun forsøge genstart.
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
                continue;
            }

            // Repair kit: kan bruges en gang i hele spillet, giver +20 integritet og logges
            if (choice == 1) {
                System.out.println("----------------------------------------");
                System.out.println("Du bruger repair kit...");
                state.setRepairKitUsed(true);

                System.out.println("Repair kit brugt! (+" + REPAIR_BONUS + ") integritet.");
                state.addIntegrity(REPAIR_BONUS);
                state.resetEngineFailures();
                log.add("Event 3: Repair kit brugt (+" + REPAIR_BONUS + ") integrity -> " + state.getIntegrity());
                System.out.println("----------------------------------------");

                // Efter repair kit: vi går direkte videre til genstartforsøg i samme event
                choice = 2;
            }

            if (choice == 2) {
                boolean printedRestartLine = false;

                try {
                    // Vi skriver den her før forsøget, så output “læses rigtigt”
                    System.out.println("Forsøger at genstarte...");
                    log.add("Forsøger at genstarte...");
                    printedRestartLine = true;

                    boolean success = random.nextDouble() < 0.4;

                    System.out.println("----------------------------------------");
                    if (success) {
                        System.out.println("Motoren starter korrekt!");
                        System.out.println("----------------------------------------");
                        state.resetEngineFailures();
                        log.add("Event 3: Motor genstartet succesfuldt");
                        resolved = true;
                    } else {
                        System.out.println("Genstart mislykkedes! -" + RESTART_FAIL_DAMAGE + " integritet");
                        System.out.println("----------------------------------------");

                        state.addIntegrity(-RESTART_FAIL_DAMAGE);
                        state.addEngineFailure();
                        log.add("Event 3: Motor genstart fejlede (-" + RESTART_FAIL_DAMAGE + ") integritet");

                        if (state.getEngineFailures() >= 2) {
                            throw new CriticalStatusException("Motoren er permanent ødelagt efter to fejl i træk!");
                        }
                    }
                } finally {
                    // Krav: finally skal altid udskrive denne tekst
                    // (men undgå dobbelt tekst hvis vi allerede gjorde det)
                    if (!printedRestartLine) {
                        System.out.println("Forsøger at genstarte...");
                        log.add("Forsøger at genstarte...");
                    }
                }
            }
        }
    }
}
