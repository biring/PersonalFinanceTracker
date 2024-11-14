package controller;

import view.ReportView;

import java.util.Scanner;

public class ReportController extends BaseClass<ReportView> {
    private final String[] menuOptions = {"Type A", "Type B", "Exit"};
    private final int EXIT_OPTION = menuOptions.length;

    public ReportController(Scanner scanner) {
        super(scanner, new ReportView(scanner));
    }

    @Override
    public void run() {
        int selection;
        do {
            view.displayMenu(menuOptions);
            selection = view.promptForMenuSelection("Enter your choice: ");
            switch (selection) {
                case 1:
                    System.out.println("Type A");
                    break;
                case 2:
                    System.out.println("Type B");
                    break;
                case 3:
                    System.out.println("Exit");
                    break;
                default:
                    throw new IllegalStateException("Unexpected value for account menu selection: " + selection);
            }
        } while (selection != EXIT_OPTION);
    }
}
