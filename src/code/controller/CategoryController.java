package controller;

import view.CategoryView;

import java.util.Scanner;

public class CategoryController extends BaseClass<CategoryView> {
    private final String[] menuOptions = {"Create", "Modify", "View", "Delete", "Exit"};
    private final int EXIT_OPTION = menuOptions.length;

    public CategoryController(Scanner scanner) {
        super(scanner, new CategoryView(scanner));
    }

    // Method to start the application flow
    public void run() {
        int selection = 0;
        do {
            view.displayMenu(menuOptions);
            selection = view.promptForMenuSelection("Enter your choice: ");
            switch (selection) {
                case 1:
                    System.out.println("Create");
                    break;
                case 2:
                    System.out.println("Modify");
                    break;
                case 3:
                    System.out.println("View");
                    break;
                case 4:
                    System.out.println("Delete");
                    break;
                case 5:
                    System.out.println("Exit");
                    break;
                default:
                    throw new IllegalStateException("Unexpected value for account menu selection: " + selection);
            }
        } while (selection != EXIT_OPTION);
    }
}
