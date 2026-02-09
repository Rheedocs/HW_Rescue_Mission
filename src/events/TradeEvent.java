package events;

import domain.GameLog;
import domain.ShipState;
import exceptions.CriticalStatusException;
import exceptions.InvalidTradeException;
import io.ConsoleIO;

public class TradeEvent {
    public void run(ShipState state, GameLog log, ConsoleIO io)
            throws CriticalStatusException, InvalidTradeException {
        System.out.println("EVENT – TRADE (TODO)");
        log.add("Event 2: TODO");
    }
}
