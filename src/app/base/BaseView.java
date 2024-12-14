package base;

import java.util.List;
import java.util.Scanner;

import interfaces.MenuInterface;
import common.Console;
import interfaces.ViewInterface;
import main.view.MenuHelper;
import main.view.TableHelper;

public class BaseView implements ViewInterface {
    protected Scanner scanner;
    protected MenuHelper menuHelper;
    protected TableHelper tableHelper;

    public BaseView() {
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

    protected final <T extends Enum<T> & MenuInterface> T getEnumMenuSelection(Class<T> enumType, String title, String message) {
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