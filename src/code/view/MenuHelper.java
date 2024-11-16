package view;

import controller.MenuOption;
import utility.Console;

import java.util.Scanner;

public class MenuHelper {
    private final Scanner scanner;

    public MenuHelper(Scanner scanner) {
        this.scanner = scanner;
    }

    // Main method for getting menu selection
    public <T extends Enum<T> & MenuOption> T getEnumMenuSelection(Class<T> enumType, String title, String message) {
        displayMenuTitle(title);
        String[] menuOptions = getMenuOptions(enumType);
        displayMenuItems(menuOptions);
        return getUserSelection(menuOptions, message, enumType);
    }

    // Get the options from an Enum type
    private <T extends Enum<T> & MenuOption> String[] getMenuOptions(Class<T> enumType) {
        T[] enumValues = enumType.getEnumConstants();
        String[] menuOptions = new String[enumValues.length];
        for (int i = 0; i < enumValues.length; i++) {
            menuOptions[i] = enumValues[i].getText();
        }
        return menuOptions;
    }

    // Display the menu title
    private void displayMenuTitle(String title) {
        Console.printMessage("\n==== " + title + " ====");
    }

    // Display the list of menu items with numbers
    private void displayMenuItems(String[] menuOptions) {
        for (int i = 0; i < menuOptions.length; i++) {
            Console.printMessage("[" + (i + 1) + "] " + menuOptions[i]);
        }
    }

    // Handle user input and return the selected menu option
    private <T extends Enum<T> & MenuOption> T getUserSelection(String[] menuOptions, String message, Class<T> enumType) {
        int selectedIndex = promptForMenuSelection(message, menuOptions.length);
        return enumType.getEnumConstants()[selectedIndex - 1];
    }

    // Prompt for the user's menu selection and validate the input
    private int promptForMenuSelection(String message, int menuOptionMax) {
        int selection = -1;
        while (selection < 1 || selection > menuOptionMax) {
            Console.printPrompt(message);
            selection = Console.getIntInput(scanner);
            if (selection < 1 || selection > menuOptionMax) {
                System.out.println("Invalid selection. Please enter a number between 1 and " + menuOptionMax);
            }
        }
        return selection;
    }


}
