package io;

import java.util.Scanner;

public class ConsoleIO {
    private final Scanner scanner = new Scanner(System.in);

    public String readString(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return scanner.nextLine();
            } catch (NumberFormatException e) {
                System.out.println("FEJL: Skriv et tal.");
            }
        }
    }

    public int readInt(String prompt) {
        while (true) {
            String s = readString(prompt).trim();
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
}
