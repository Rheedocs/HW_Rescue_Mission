package events;

import domain.GameLog;
import domain.ShipState;
import exceptions.InvalidTradeException;
import io.ConsoleIO;

public class TradeEvent {

    public void run(ShipState state, GameLog log, ConsoleIO io) throws InvalidTradeException {
        System.out.println();
        System.out.println("EVENT 2 – HANDEL OG SHIELD (TODO)");
        log.add("Event 2: TODO");

        // TODO: Menu 1-3 (trade, shield, skip)
        // TODO: Trade:
        //   - readInt antal reservedele
        //   - if <= 0 -> throw IllegalArgumentException
        //   - if > state.getSpareParts() -> throw InvalidTradeException
        //   - state.addSpareParts(-x), state.addFuel(+x*5)
        //   - log vigtige valg + resultat
        // TODO: Shield:
        //   - koster fx 4 reservedele
        //   - hvis ikke nok -> throw InvalidTradeException
        //   - state.setShieldLevel(1)
        //   - log det
    }
}
