package app;

import domain.GameLog;
import domain.ShipState;
import events.EngineEvent;
import events.StormEvent;
import events.TradeEvent;
import exceptions.CriticalStatusException;

import io.ConsoleIO;
import io.Printer;
import service.GameRules;

public class Game {

    public void run() {
        ConsoleIO io = new ConsoleIO();
        Printer printer = new Printer();
        GameLog log = new GameLog();

        String captain = io.readString("Indtast dit navn: ");
        String ship = io.readString("Indtast navnet på dit rumskib: ");
        ShipState state = new ShipState(captain, ship);

        log.add("Start: Kaptajn " + captain + " på " + ship);

        try {
            GameRules rules = new GameRules();

            printer.printStatus(state);

            new StormEvent().run(state, log, io);
            rules.checkCriticalStatus(state);
            printer.printStatus(state);
            printer.printLastLogLine(log);
            io.pause();

            new TradeEvent().run(state, log, io);
            rules.checkCriticalStatus(state);
            printer.printStatus(state);
            printer.printLastLogLine(log);
            io.pause();

            new EngineEvent().run(state, log, io);
            rules.checkCriticalStatus(state);
            printer.printStatus(state);
            printer.printLastLogLine(log);

            System.out.println("MISSION FULDFØRT");
            log.add("MISSION FULDFØRT");

        } catch (CriticalStatusException e) {
            System.out.println("GAME OVER: " + e.getMessage());
            log.add("GAME OVER: " + e.getMessage());

        } catch (Exception e) {
            System.out.println("FEJL: Noget gik galt, men spillet crasher ikke.");
            log.add("Uventet fejl: " + e.getClass().getSimpleName() + " - " + e.getMessage());

        } finally {
            printer.printLog(log);
        }
    }
}
