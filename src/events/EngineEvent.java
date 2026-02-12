package events;

import domain.GameLog;
import domain.ShipState;
import exceptions.CriticalStatusException;
import io.ConsoleIO;

import java.util.Random;

public class EngineEvent {
    private static final int ENGINE_DAMAGE_MIN = 15;
    private static final int ENGINE_DAMAGE_MAX = 35;

    private final Random random = new Random();

    public void run(ShipState state, GameLog log, ConsoleIO io) throws CriticalStatusException {
        System.out.println("EVENT – ENGINE");
        System.out.println("Motoren begynder at lyde ustabil...");
        System.out.println();

        int integrityBefore = state.getIntegrity();


        int outcome = random.nextInt(100);
        if (outcome < 20) {
            System.out.println("The engine has taken severe damage! -10.");
            log.add("Event 3: Engine failure");

            state.addIntegrity(-10);
            state.addEngineFailure();

            if (state.getEngineFailures() >= 2) {
                throw new CriticalStatusException("The engine failed twice. Engine is now dead. Game over!");
            }

            if (state.getIntegrity() <= 0) {
                throw new CriticalStatusException("The ship's engine failed completely! Game over.");
            }

            if (state.getIntegrity() < 0 && state.isRepairKitUsed()) {
                System.out.println("You can use a spare part to repair the engine. Use one? (Y/N)");
                String input = io.readString("");

                if (input.equalsIgnoreCase("y")) {
                    state.addIntegrity(20);
                    state.addSpareParts(-1);
                    state.setRepairKitUsed(true);
                    System.out.println("Repaired 20 integrity using repair kit!");
                    log.add("Event 3: Player used spare part to repair engine");
                    state.resetEngineFailures();
                } else if (state.getIntegrity() <= 0 && state.getIntegrity() >= 100) {
                    throw new CriticalStatusException("You can't repair ship that is detroyed or fully shielded!");
                }
            } else {
                System.out.println("Motoren kører uden problemer");
                log.add("Event 3: Engine is working fine.");
                state.resetEngineFailures();
            }
        }
    }
}
