package service;

import domain.ShipState;
import exceptions.CriticalStatusException;

public class GameRules {
    public void checkCriticalStatus(ShipState state) throws CriticalStatusException {
        if (state.getFuel() < 10) throw new CriticalStatusException("Brændstof er under 10 (fuel = " + state.getFuel() + ")");
        if (state.getIntegrity() < 20) throw new CriticalStatusException("Integritet er under 20");

    }
}
