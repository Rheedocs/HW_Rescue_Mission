package events;

import domain.GameLog;
import domain.ShipState;
import exceptions.CriticalStatusException;
import io.ConsoleIO;

public class EngineEvent {

    public void run(ShipState state, GameLog log, ConsoleIO io) throws CriticalStatusException {
        System.out.println();
        System.out.println("EVENT 3 – DEFEKT MOTOR (TODO)");
        log.add("Event 3: TODO");

        // TODO: Repair kit (kan kun bruges én gang)
        //   - spørg før/under event
        //   - hvis allerede brugt: vis pæn besked (ikke exception nødvendigt)
        //   - effect fx +20 integrity, log det
        //
        // TODO: try/catch/finally:
        //   - finally skal ALTID skrive: "Forsøger at genstarte..."
        //   - 40% chance success
        //   - ved fail: integrity -15, log
        //   - fail 2 gange i træk: throw new CriticalStatusException("Motoren er permanent ødelagt")
    }
}
