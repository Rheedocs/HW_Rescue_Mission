package io;

import domain.GameLog;
import domain.ShipState;

public class Printer {

    public void printStatus(ShipState s) {
        System.out.println("----------------------------------------");
        System.out.println("STATUS");
        System.out.println("Kaptajn: " + s.getCaptainName());
        System.out.println("Skib: " + s.getShipName());
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

    public void printIntro() {
        System.out.println("----------------------------------------");
        System.out.println("      *       .       *        .        *        ");
        System.out.println("  .        *        .      *        .            ");
            System.out.println("""
                          .        *
                                  __|__
                               *   _|_
                                  / _ \\
                     .         __/ (_) \\__        o
                          ____/_ ======= _\\____
                 ________/ _ \\(_)_______(_)/ _ \\________
                <___+____ (_) | /   _   \\ | (_) ____+___>
                  O O O  \\___/ |   (_)   | \\___/  O O O
                             \\__\\_______/__/
                        *                 .
                """);
        System.out.println("        .        *       .         *            ");
        System.out.println("   *        .        *        .                 ");
        System.out.println("----------------------------------------");
        System.out.println("RUMEVENTYR – EXCEPTION RESCUE MISSION");
        System.out.println("Du er kaptajn på et fragtskib fanget i en farlig sektor.");
        System.out.println("Tre hændelser venter forude. Overlev og gennemfør missionen.");
        System.out.println("----------------------------------------");
    }
}
