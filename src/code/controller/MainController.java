package controller;

import dao.AccountDAO;
import dao.CategoryDAO;
import service.AccountService;
import view.MainView;

public class MainController extends BaseClass<MainView> {
    private final AccountDAO accountDAO = new AccountDAO();
    private final CategoryDAO categoryDAO = new CategoryDAO();
    private final AccountService accountService = new AccountService(accountDAO);

    private final AccountController accountController = new AccountController(accountService);
    private final CategoryController categoryController = new CategoryController(categoryDAO);
    private final BudgetController budgetController = new BudgetController(categoryDAO);
    private final LinkController linkController = new LinkController();
    private final TransactionController transactionController = new TransactionController(accountService);
    private final ReportController reportController = new ReportController();

    // Constructor to initialize the shared Scanner object
    public MainController() {
        super(new MainView());
    }

    @Override
    public void start() {
        transactionController.start();
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
