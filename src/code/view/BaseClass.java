package view;

import java.util.Scanner;

import controller.MenuOption;
import utility.Console;

public class BaseClass implements Interface {
    protected Scanner scanner;
    protected int menuOptionMax;

    public BaseClass(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public final void displayMenu(String[] menuOptions) {
        this.menuOptionMax = menuOptions.length;
        String formattedMenu = Console.formatList(menuOptions);
        printMessageLine(formattedMenu);
    }

    @Override
    public final int promptForMenuSelection(String message) {
        while (true) {
            printMessage(message);
            String input = Console.getInput(scanner);
            int selection = Console.stringToInt(input);
            if (Console.isValidSelection(selection, menuOptionMax)) {
                return selection;
            } else {
                printMessageLine("Invalid selection. Please enter a number between 1 and " + menuOptionMax);
            }
        }
    }

    @Override
    public final String promptForStringInput(String message) {
        while (true) {
            printMessage(message);
            String input = Console.getInput(scanner);
            if (input.isBlank()) {
                printMessageLine("Input cannot be blank.");
            } else {
                return input;
            }
        }
    }

    @Override
    public final int promptForIntInput(String message) {
        while (true) {
            printMessage(message);
            String input = Console.getInput(scanner);
            int selection = Console.stringToInt(input);
            if (selection > 0) {
                return selection;
            } else {
                printMessageLine("Invalid selection. Please try again.");
            }
        }
    }

    @Override
    public final void printMenuTitle(String title) {
        String text = "\n==== " + title + " ====";
        printMessageLine(text);
    }

    protected void printMessage(String message) {
        System.out.print(message);
    }

    protected void printMessageLine(String message) {
        System.out.println(message);
    }

    public final <T extends Enum<T> & MenuOption> T getEnumMenuSelection(Class<T> enumType, String title, String message) {
        printMenuTitle(title);

        // Get all enum constants
        T[] enumValues = enumType.getEnumConstants();
        // Convert enum to string array
        String[] menuOptions = new String[enumValues.length];
        for (int i = 0; i < enumValues.length; i++) {
            menuOptions[i] = (enumValues[i]).getText();
        }
        // Display the menu options
        printEnumMenu(menuOptions);
        // Get the user's choice (number selection)
        int selectedIndex = promptForEnumMenuSelection(message, menuOptions.length);
        // Return the enum constant based on the index
        return enumValues[selectedIndex - 1];
    }

    // Formats an enum of menu options into a numbered list
    private void printEnumMenu(String[] items) {
        if (items == null) {
            throw new IllegalArgumentException("List is null");
        }

        StringBuilder formattedString = new StringBuilder();
        for (int i = 0; i < items.length; i++) {
            formattedString.append("[").append(i + 1).append("] ").append(items[i]).append("\n");
        }
        printMessageLine(formattedString.toString().trim());
    }

    private int promptForEnumMenuSelection(String message, int menuOptionMax) {
        while (true) {
            printMessage(message);
            String input = Console.getInput(scanner);
            int selection = Console.stringToInt(input);
            if (isSelectionValid(selection, menuOptionMax)) {
                return selection;
            } else {
                printMessageLine("Invalid selection. Please enter a number between 1 and " + menuOptionMax);
            }
        }
    }

    // Validates if the selection is within the valid range
    private boolean isSelectionValid(int selection, int menuOptionMax) {
        return selection > 0 && selection <= menuOptionMax;
    }

}
