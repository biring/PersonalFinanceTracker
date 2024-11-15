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
                    System.out.println("Modify");
                    break;
                case VIEW:
                    System.out.println("View");
                    break;
                case DELETE:
                    System.out.println("Delete");
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
        String accountType = getAccountType();
        boolean isAccountCreated = accountService.createAccount(accountName, accountType);
        view.showAccountCreationResult(isAccountCreated);
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

    private String getAccountType() {
        String[] accountTypes = accountService.getAccountTypes();
        return view.promptForAccountTypeSelection(accountTypes);
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
