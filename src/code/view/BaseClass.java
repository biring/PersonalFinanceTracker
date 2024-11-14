package view;

import java.util.Scanner;
import utility.Console;

public class BaseClass implements Interface {
    protected Scanner scanner;
    protected int menuOptionMax;

    public BaseClass(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void displayMenu(String[] menuOptions) {
        this.menuOptionMax = menuOptions.length;
        String formattedMenu = Console.formatList(menuOptions);
        printMessageLine(formattedMenu);
    }

    @Override
    public int promptForMenuSelection(String message) {
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

    protected void printMessage(String message) {
        System.out.print(message);
    }

    protected void printMessageLine(String message) {
        System.out.println(message);
    }
}
