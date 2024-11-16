package controller;

import service.AccountService;
import view.AccountView;

import java.util.Scanner;

public class AccountController extends BaseClass<AccountView> {
    private final AccountService accountService = new AccountService();

    public AccountController(Scanner scanner) {
        super(scanner, new AccountView(scanner));
    }

    // Method to start the application flow
    public void run() {
        enumMenuOptions selectedOption;
        do {
            selectedOption = view.promptForEnumMenuSelection(enumMenuOptions.class);
            switch (selectedOption) {
                case CREATE:
                    createAccount();
                    break;
                case MODIFY:
                    modifyAccount();
                    break;
                case VIEW:
                    viewAccounts();
                    break;
                case DELETE:
                    deleteAccount();
                    break;
                case EXIT:
                    System.out.println("Exit");
                    break;
                default:
                    throw new IllegalStateException("Invalid value for selection.");
            }
        }
        while (selectedOption != enumMenuOptions.EXIT);
    }

    private void createAccount() {
        String accountName = getAccountName();
        boolean success = accountService.createAccount(accountName);
        view.showAccountCreationResult(success);
    }

    private void modifyAccount() {
        int accountId = view.promptForAccountId(accountService.getAllAccountsAsString());
        String accountName = getAccountName();
        boolean success = accountService.updateAccount(accountId, accountName);
        view.showAccountModificationResult(success);
    }

    private void viewAccounts() {
        view.showAccounts(accountService.getAllAccountsAsString());
    }

    private void deleteAccount() {
        int id = view.promptForAccountId(accountService.getAllAccountsAsString());
        boolean success = accountService.deleteAccount(id);
        view.showAccountDeletionResult(success);
    }


    private String getAccountName() {
        boolean isAccountNameValid;
        String accountName;
        do {
            accountName = view.promptForAccountName();
            isAccountNameValid = accountService.isAccountNameValid(accountName);
            view.showAccountValidResult(isAccountNameValid);
        } while (!isAccountNameValid);
        return accountName;
    }


    private enum enumMenuOptions implements MenuOption {
        CREATE("Create Account"),
        MODIFY("Modify Account"),
        VIEW("View Account"),
        DELETE("Delete Account"),
        EXIT("Exit");

        private final String text;

        enumMenuOptions(String text) {
            this.text = text;
        }

        @Override
        public String getText() {
            return text;
        }
    }
}
