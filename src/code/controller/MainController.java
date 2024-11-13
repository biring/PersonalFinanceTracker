package controller;

import view.MainView;

import java.util.Scanner;

public class MainController {

    // Instance variable to store the shared Scanner object
    private final Scanner scanner;
    private final MainView mainView;
    private final String[] mainMenuOption = {"Account", "Category", "Budget", "Link", "Transaction", "Report", "Exit"};

    // Constructor to initialize the shared Scanner object
    public MainController(Scanner scanner) {
        this.scanner = scanner;
        this.mainView = new MainView(scanner);
    }

    // Method to start the application flow
    public void start() {
        int input;
        mainView.displayMenu(mainMenuOption);
        input = mainView.promptForMenuSelection("Enter your choice: ");
        System.out.println("User input: " + input);
    }


}
