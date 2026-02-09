package events;

import domain.GameLog;
import domain.ShipState;
import exceptions.CriticalStatusException;
import io.ConsoleIO;

public class EngineEvent {
    public void run(ShipState state, GameLog log, ConsoleIO io) throws CriticalStatusException {
        System.out.println("EVENT – ENGINE (TODO)");
        log.add("Event 3: TODO");
    }
}
