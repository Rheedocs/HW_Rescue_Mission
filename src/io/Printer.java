package io;

import domain.GameLog;
import domain.ShipState;

public class Printer {

    public void printStatus(ShipState s) {
        System.out.println("----------------------------------------");
        System.out.println("STATUS");
        System.out.println("Brændstof: " + s.getFuel());
        System.out.println("Integritet: " + s.getIntegrity());
        System.out.println("Reservedele: " + s.getSpareParts());
        System.out.println("Shield: " + s.getShieldLevel());
        System.out.println("Repair kit: " + (s.isRepairKitUsed() ? "brugt" : "ikke brugt"));
        System.out.println("----------------------------------------");
        System.out.println();
    }

    public void printLog(GameLog log) {
        System.out.println();
        System.out.println("----------------------------------------");
        System.out.println("EVENT LOG");
        for (String line : log.all()) {
            System.out.println("- " + line);
        }
        System.out.println("----------------------------------------");
    }

    public void printLastLogLine(GameLog log) {
        if (log == null || log.all().isEmpty()) return;

        System.out.println("[SENESTE LOG]");
        System.out.println("• " + log.all().getLast()); // Java 21: SequencedCollection
        System.out.println("----------------------------------------");
    }
}
