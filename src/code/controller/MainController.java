package controller;

import dao.AccountDAO;
import dao.CategoryDAO;
import dao.LinkDAO;
import dao.TransactionDAO;
import service.*;
import view.MainView;

public class MainController extends BaseClass<MainView> {
    private final AccountDAO accountDAO = new AccountDAO();
    private final CategoryDAO categoryDAO = new CategoryDAO();
    private final LinkDAO linkDAO = new LinkDAO();
    private final TransactionDAO transactionDAO = new TransactionDAO();

    private final AccountService accountService = new AccountService(accountDAO, transactionDAO);
    private final CategoryService categoryService = new CategoryService(categoryDAO, linkDAO);
    private final LinkService linkService = new LinkService(categoryDAO, linkDAO, transactionDAO);
    private final TransactionService transactionService = new TransactionService(accountDAO, transactionDAO);
    private final ReportService reportService = new ReportService(accountDAO, categoryDAO, linkDAO, transactionDAO);

    private final AccountController accountController = new AccountController(accountService);
    private final CategoryController categoryController = new CategoryController(categoryService);
    private final BudgetController budgetController = new BudgetController(categoryService);
    private final LinkController linkController = new LinkController(linkService);
    private final TransactionController transactionController = new TransactionController(transactionService);
    private final ReportController reportController = new ReportController(reportService);

    // Constructor to initialize the shared Scanner object
    public MainController() {
        super(new MainView());
    }

    public void start() {
        accountController.start();
        categoryController.start();
        transactionController.start();
        linkController.start();
    }

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

    public void stop() {
        accountController.stop();
        categoryController.stop();
        transactionController.stop();
        linkController.stop();
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
