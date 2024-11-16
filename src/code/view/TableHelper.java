package view;

import utility.Console;

import java.util.List;
import java.util.Scanner;

public class TableHelper {
    private final Scanner scanner;

    public TableHelper(Scanner scanner) {
        this.scanner = scanner;
    }

    public int  getTableSelection(List<String> data, String title, String prompt) {
        displayTableTitle(title);
        displayTableData(data);
        return getUserSelection(prompt);
    }

    public void displayTable(List<String> data, String title) {
        displayTableTitle(title);
        displayTableData(data);
    }

    public final void displayTableTitle(String title) {
        String text = "\n---" + title + "---";
        Console.printMessage(text);
    }

    protected final void displayTableData(List<String> data) {
        for (String row : data) {
            Console.printMessage(row);
        }
    }

    // Prompt for the user's menu selection and validate the input
    private int getUserSelection(String prompt) {
        int selection = -1;
        while (selection < 1) {
            Console.printPrompt(prompt);
            selection = Console.getIntInput(scanner);
            if (selection < 1) {
                System.out.println("Invalid selection.");
            }
        }
        return selection;
    }
}
