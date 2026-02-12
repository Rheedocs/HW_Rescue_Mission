package service;

import domain.ShipState;
import exceptions.CriticalStatusException;
import exceptions.InvalidTradeException;

public class GameRules {

    //  CRITICAL STATUS

    public void checkCriticalStatus(ShipState state) throws CriticalStatusException {
        if (state.getFuel() < 10) {
            throw new CriticalStatusException("Brændstof er under 10");
        }

        if (state.getIntegrity() < 20) {
            throw new CriticalStatusException("Integritet er under 20");
        }
    }

    // STORM

    public int calculateStormDamage(ShipState state, boolean highRisk) {

        int damage = (int) (Math.random() * 30) + 10;

        if (!highRisk) {
            // Omvej giver halv skade
            damage = damage / 2;
            state.setFuel(state.getFuel() - 10);
        }

        // Shield reducerer skade
        if (state.getShieldLevel() > 0) {
            damage -= 10;
            if (damage < 0) damage = 0;
        }

        state.setIntegrity(state.getIntegrity() - damage);

        return damage;
    }

    // TRADE

    public void tradePartsForFuel(ShipState state, int amount)
            throws InvalidTradeException {

        if (amount <= 0) {
            throw new IllegalArgumentException("Antal skal være over 0");
        }

        if (amount > state.getSpareParts()) {
            throw new InvalidTradeException("Ikke nok reservedele");
        }

        state.addSpareParts(state.getSpareParts() - amount);
        state.setFuel(state.getFuel() + (amount * 5));
    }

    // ================= SHIELD =================

    public void buyShield(ShipState state)
            throws InvalidTradeException {

        int cost = 4;

        if (state.getSpareParts() < cost) {
            throw new InvalidTradeException("Ikke nok reservedele til shield");
        }

        state.addSpareParts(state.getSpareParts() - cost);
        state.setShieldLevel(1);
    }



    public void tradeParts(ShipState state, int amount) {
    }
}