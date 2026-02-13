package io;

import java.util.Scanner;

public class ConsoleIO {
    private final Scanner scanner = new Scanner(System.in);

    public String readNonEmptyString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = scanner.nextLine().trim();
            if (!s.isEmpty()) return s;
            System.out.println("FEJL: Feltet må ikke være tomt.");
        }
    }

    public int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = scanner.nextLine().trim();

            if (s.isEmpty()) {
                System.out.println("FEJL: Skriv et tal.");
                continue;
            }

            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("FEJL: Skriv et tal.");
            }
        }
    }

    public int readChoice(String prompt, int min, int max) {
        while (true) {
            int n = readInt(prompt);
            if (n >= min && n <= max) return n;
            System.out.println("FEJL: Vælg et tal mellem " + min + " og " + max + ".");
        }
    }

    public void pause() {
        System.out.println();
        System.out.println(">>> Navigationssystemet afventer din kommando...");
        System.out.print(">>> Tryk ENTER for at fortsætte rejsen");
        scanner.nextLine();
        System.out.println("----------------------------------------");
    }

    public void introPause() {
        System.out.println();
        System.out.println(">>> System online. Tryk ENTER for at initialisere mission...");
        scanner.nextLine();
        System.out.println("----------------------------------------");
    }

}
