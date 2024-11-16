package view;

import java.util.List;
import java.util.Scanner;

import controller.MenuOption;
import utility.Console;

public class BaseClass implements Interface {
    protected Scanner scanner;
    protected MenuHelper menuHelper;
    protected TableHelper tableHelper;

    public BaseClass(Scanner scanner) {
        this.scanner = scanner;
        this.menuHelper = new MenuHelper(scanner);
        this.tableHelper = new TableHelper(scanner);
    }

    @Override
    public final String promptForStringInput(String message) {
        while (true) {
            Console.printPrompt(message);
            String input = Console.getStringInput(scanner);
            if (input.isBlank()) {
                Console.printMessage("Input cannot be blank.");
            } else {
                return input;
            }
        }
    }

    protected final <T extends Enum<T> & MenuOption> T getEnumMenuSelection(Class<T> enumType, String title, String message) {
        return menuHelper.getEnumMenuSelection(enumType, title, message);
    }

    protected final int getTableSelection(List<String> data, String title, String prompt) {
        return tableHelper.getTableSelection(data, title, prompt);
    }

    protected final void displayTableData(List<String> data, String title) {
        tableHelper.displayTable(data, title);
    }
}