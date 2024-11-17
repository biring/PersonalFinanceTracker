package view;

import java.util.List;
import java.util.Scanner;

import controller.MenuOption;
import utility.Console;

public class BaseClass implements Interface {
    protected Scanner scanner;
    protected MenuHelper menuHelper;
    protected TableHelper tableHelper;

    public BaseClass() {
        this.scanner = new Scanner(System.in);
        this.menuHelper = new MenuHelper(this.scanner);
        this.tableHelper = new TableHelper(this.scanner);
    }

    @Override
    public final String promptForStringInput(String message) {
        Console.printPrompt(message);
        return Console.getStringInput(scanner);
    }

    @Override
    public final int promptForIntInput(String message) {
        Console.printPrompt(message);
        return Console.getIntInput(scanner);
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

    protected final void displaySuccessFailureMessage(boolean result, String success, String failure) {
        if (result) {
            Console.printMessage(success);
        } else {
            Console.printMessage(failure);
        }
    }
}