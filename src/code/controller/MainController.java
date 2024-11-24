package controller;

import dao.AccountDAO;
import dao.CategoryDAO;
import service.AccountService;
import service.CategoryService;
import view.MainView;

public class MainController extends BaseClass<MainView> {
    private final AccountDAO accountDAO = new AccountDAO();
    private final AccountService accountService = new AccountService(accountDAO);
    private final CategoryService categoryService = new CategoryService();

    private final AccountController accountController = new AccountController(accountService);
    private final CategoryController categoryController = new CategoryController(categoryService);
    private final BudgetController budgetController = new BudgetController(categoryService);
    private final LinkController linkController = new LinkController(categoryService);
    private final TransactionController transactionController = new TransactionController(accountService);
    private final ReportController reportController = new ReportController();

    // Constructor to initialize the shared Scanner object
    public MainController() {
        super(new MainView());
    }

    @Override
    public void start() {
        accountController.start();
        categoryController.start();
        transactionController.start();
        linkController.start();
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

    @Override
    public void stop() {
        accountController.stop();
        categoryController.stop();
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
