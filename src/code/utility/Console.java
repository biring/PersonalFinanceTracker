package utility;

import java.util.Scanner;

public class Console {

    public static String getStringInput(Scanner scanner) {
        return scanner.nextLine();
    }

    public static int getIntInput(Scanner scanner) {
        return stringToInt(getStringInput(scanner));
    }

    public static void printPrompt(String message) {
        System.out.print(message);
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }

    private static int stringToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1; // Invalid input
        }
    }
}
