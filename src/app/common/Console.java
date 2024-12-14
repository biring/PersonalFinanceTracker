package common;

import java.util.Scanner;

public class Console {

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void waitForEnter(Scanner scanner) {
        scanner.nextLine();
    }

    public static String getStringInput(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine();
            if (input.isBlank()) {
                Console.printMessage("Invalid input. Please enter a string.");
            } else {
                return input;
            }
        }
    }


    public static int getIntInput(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(getStringInput(scanner));
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    public static void printPrompt(String message) {
        System.out.print(message);
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }
}
