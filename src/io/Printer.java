package io;

import domain.GameLog;
import domain.ShipState;

public class Printer {
    public void printStatus(ShipState s) {
        System.out.println("----------------------------------------");
        System.out.println("STATUS");
        System.out.println("Brændstof : " + s.getFuel());
        System.out.println("Integritet : " + s.getIntegrity());
        System.out.println("Reservedele : " + s.getSpareParts());
        System.out.println("Shield : " + s.getShieldLevel());
        System.out.println("Repair kit : " + (s.isRepairKitUsed() ? "brugt" : "ikke brugt"));
        System.out.println("----------------------------------------");
    }

    public void printLog(GameLog log) {

        if (log == null) return;

        System.out.println("----------------------------------------");
        System.out.println("EVENT LOG");
        for (String line : log.all()) {
            System.out.println("- " + line);
        }
        System.out.println("----------------------------------------");
    }
}
