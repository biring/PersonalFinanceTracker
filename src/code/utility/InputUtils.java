package utils;

import java.util.Scanner;

public class InputUtils {

    // Converts a string input to an integer
    public static int stringToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // Reads user input from the console using a given scanner
    public static String getInput(Scanner scanner) {
        return scanner.nextLine();
    }

    // Validates if the selection is within the valid range
    public static boolean isValidSelection(int selection, int menuOptionMax) {
        return selection > 0 && selection <= menuOptionMax;
    }

    // Formats a list of menu options into a numbered list
    public static String formatList(String[] items) {
        if (items == null) {
            throw new IllegalArgumentException("List is null");
        }

        StringBuilder formattedString = new StringBuilder();
        for (int i = 0; i < items.length; i++) {
            formattedString.append("[").append(i + 1).append("] ").append(items[i]).append("\n");
        }
        return formattedString.toString().trim();
    }
}
