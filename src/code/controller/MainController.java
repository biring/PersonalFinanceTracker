package controller;

import view.MainView;

import java.util.Scanner;

public class MainController extends BaseClass<MainView> {
    private final String[] mainMenuOption = {"Account", "Category", "Budget", "Link", "Transaction", "Report", "Exit"};
    private final int EXIT_OPTION = mainMenuOption.length;

    private AccountController accountController;
    private CategoryController categoryController;
    private BudgetController budgetController;
    private LinkController linkController;
    private TransactionController transactionController;
    private ReportController reportController;

    // Constructor to initialize the shared Scanner object
    public MainController(Scanner scanner) {
        super(scanner, new MainView(scanner));
    }

    // Method to initialize the application
    @Override
    public void start() {
        this.accountController = new AccountController(scanner);
        this.categoryController = new CategoryController(scanner);
        this.budgetController = new BudgetController(scanner);
        this.reportController = new ReportController(scanner);
        this.transactionController = new TransactionController(scanner);
        this.linkController = new LinkController(scanner);
    }

    // Method to start the application flow
    @Override
    public void run() {
        int selection = 0;
        do {
            view.displayMenu(mainMenuOption);
            selection = view.promptForMenuSelection("Enter your choice: ");
            switch (selection) {
                case 1:
                    accountController.run();
                    break;
                case 2:
                    categoryController.run();
                    break;
                case 3:
                    budgetController.run();
                    break;
                case 4:
                    linkController.run();
                    break;
                case 5:
                    transactionController.run();
                    break;
                case 6:
                    reportController.run();
                    break;
                case 7:
                    break;
                default:
                    throw new IllegalStateException("Unexpected value for main menu selection: " + selection);
            }
        } while (selection != EXIT_OPTION);
    }
}
