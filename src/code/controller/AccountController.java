package controller;

import service.AccountService;
import view.AccountView;

import java.io.IOException;
import java.util.List;

public class AccountController extends BaseClass<AccountView> {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        super(new AccountView());
        this.accountService = accountService;
    }

    public void start(){
        readFromDatabase();
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
                    writeToDatabase();
                    break;
                default:
                    throw new IllegalStateException("Invalid value for selection.");
            }
        }
        while (selectedOption != enumMenuOptions.EXIT);
    }

    public void stop(){
        writeToDatabase();
    }

    private void readFromDatabase() {
        try {
            accountService.loadFromDb();
        } catch (IOException e) {
            view.showDbReadError();
        }
    }

    private void writeToDatabase() {
        try {
            accountService.storeToDb();
        } catch (IOException e) {
            view.showDbWriteError();
        }
    }

    private void createAccount() {
        String accountName = getAccountName();
        int accountIndex = getAccountType();
        boolean success = accountService.createAccount(accountName, accountIndex);
        view.showAccountCreationResult(success);
    }

    private void modifyAccount() {
        List<String> accounts = accountService.showAccounts();
        int accountId = view.promptForAccountId(accounts);
        String accountName = getAccountName();
        boolean success = accountService.updateAccount(accountId, accountName);
        view.showAccountModificationResult(success);
    }

    private void viewAccounts() {
        view.showAccounts(accountService.showAccounts());
    }

    private void deleteAccount() {
        List<String> accounts = accountService.showAccounts();
        int accountId = view.promptForAccountId(accounts);
        boolean success = accountService.deleteAccount(accountId);
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

    private int getAccountType() {
        boolean isAccountTypeValid;
        int accountTypeIndex;
        do {
            accountTypeIndex = view.promptForAccountTypeIndex(accountService.showAccountTypes())-1;
            isAccountTypeValid = accountService.isAccountTypeValid(accountTypeIndex);
            view.showAccountTypeValidResult(isAccountTypeValid);
        } while (!isAccountTypeValid);
        return accountTypeIndex;
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
