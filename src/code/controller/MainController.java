package controller;

import view.MainView;

import java.util.Scanner;

public class MainController extends BaseClass<MainView> {

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
        MenuOptions selectedOption;
        do {
            selectedOption = view.promptForEnumMenuSelection(MenuOptions.class);
            switch (selectedOption) {
                case ACCOUNT:
                    accountController.run();
                    break;
                case CATEGORY:
                    categoryController.run();
                    break;
                case BUDGET:
                    budgetController.run();
                    break;
                case LINK:
                    linkController.run();
                    break;
                case TRANSACTION:
                    transactionController.run();
                    break;
                case REPORT:
                    reportController.run();
                    break;
                case EXIT:
                    break;
                default:
                    throw new IllegalStateException("Invalid value for selection.");
            }
        } while (selectedOption != MenuOptions.EXIT);
    }

    private enum MenuOptions implements MenuOption {
        ACCOUNT("Account"),
        CATEGORY("Category"),
        BUDGET("Budget"),
        LINK("Link"),
        TRANSACTION("Transaction"),
        REPORT("Report"),
        EXIT("Exit Application");

        private final String text;

        MenuOptions(String text) {
            this.text = text;
        }

        @Override
        public String getText() {
            return text;
        }
    }
}
