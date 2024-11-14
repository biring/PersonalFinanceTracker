package controller;

import view.LinkView;
import java.util.Scanner;

public class LinkController extends BaseClass<LinkView> {
    private final String[] menuOptions = {"Create", "Modify", "View", "Delete", "Exit"};
    private final int EXIT_OPTION = menuOptions.length;

    public LinkController (Scanner scanner) {
        super(scanner, new LinkView(scanner));
    }

    @Override
    public void run() {
        int selection;
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