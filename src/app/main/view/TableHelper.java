package main.view;

import common.Console;

import java.util.List;
import java.util.Scanner;

public class TableHelper {

    private final Scanner scanner;

    public final String NO_DATA_FOUND = "No data found.";

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
        String text = "\n--- " + title + " ---";
        Console.printMessage(text);
    }

    protected final void displayTableData(List<String> data) {
        if (data.isEmpty()) {
            Console.printMessage(NO_DATA_FOUND);
            return;
        }
        for (String row : data) {
            Console.printMessage(row);
        }
    }

    // Prompt for the user's menu selection and validate the input
    private int getUserSelection(String prompt) {
        Console.printPrompt(prompt);
        return Console.getIntInput(scanner);
    }
}
