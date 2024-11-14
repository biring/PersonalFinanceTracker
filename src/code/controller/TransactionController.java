package controller;

import view.TransactionView;

import java.util.Scanner;

public class TransactionController extends BaseClass<TransactionView> {
    private final String[] transactionMenuOption = {"Add Transaction", "Edit Transaction", "Delete Transaction", "View Transaction", "Exit"};
    private final int EXIT_OPTION = transactionMenuOption.length;

    public TransactionController(Scanner scanner) {
        super(scanner, new TransactionView(scanner));
    }

    public void run() {
        int selection = 0;
        do {
            view.displayMenu(transactionMenuOption);
            selection = view.promptForMenuSelection("Enter your choice: ");
            switch (selection) {
                case 1:
                    System.out.println("Add Transaction");
                    break;
                case 2:
                    System.out.println("Edit Transaction");
                    break;
                case 3:
                    System.out.println("Delete Transaction");
                    break;
                case 4:
                    System.out.println("View Transaction");
                    break;
                case 5:
                    System.out.println("Exiting Transaction Menu...");
                    break;
                default:
                    System.out.println("Invalid selection. Please try again.");
                    break;
            }
        } while (selection != EXIT_OPTION);
    }
}