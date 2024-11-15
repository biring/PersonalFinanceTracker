package service;

public class AccountService {

    private final String[] accountTypes = {"Credit", "Debit"};

    public AccountService() {
    }

    public boolean isAccountNameValid(String accountName) {
        return accountName != null && !accountName.isEmpty();
    }

    public String[] getAccountTypes() {
        return accountTypes;
    }

    public boolean createAccount(String accountName, String accountType) {
        //TODO: Implement account creation logic
        return true;
    }
}
