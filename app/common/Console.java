package common;

import java.util.Scanner;

/**
 * The {@code Console} class contains methods for interacting with the console.
 * It provides static methods to read and write to console.
 * This class is designed as a utility class and cannot be instantiated.
 */
public class Console {

    /**
     * Private constructor prevents instantiation of the Console class.
     */
    private Console() {
        throw new UnsupportedOperationException("Cannot instantiate Console class.");
    }

    /**
     * Retrieves a non-empty string input from the user using the provided Scanner object.
     *
     * @param scanner the Scanner object to use for input
     * @return a non-empty string input provided by the user
     */
    public static String getStringInput(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine();
            if (input.isBlank()) {
                Console.printPrompt("Invalid input. Please enter a string: ");
            } else {
                return input;
            }
        }
    }

    /**
     * Retrieves an integer input from the user using the provided Scanner object.
     *
     * @param scanner the Scanner object to use for input
     * @return the integer input provided by the user
     */
    public static int getIntInput(Scanner scanner) {
        while (true) {
            try {
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                Console.printPrompt("Invalid input. Please enter a number: ");
            }
        }
    }

    /**
     * Prints the given message as a prompt without a new line.
     *
     * @param message the message to be displayed as a prompt
     */
    public static void printPrompt(String message) {
        System.out.print(message);
    }

    /**
     * Prints the given message to the console followed by a new line.
     *
     * @param message the message to be printed to the console
     */
    public static void printMessage(String message) {
        System.out.println(message);
    }
}
