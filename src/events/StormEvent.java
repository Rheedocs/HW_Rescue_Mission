package events;

import domain.GameLog;
import domain.ShipState;
import exceptions.CriticalStatusException;
import io.ConsoleIO;

public class StormEvent {
    public void run(ShipState state, GameLog log, ConsoleIO io) throws CriticalStatusException {
        System.out.println("EVENT – STORM (TODO)");
        log.add("Event 1: TODO");
    }
}
