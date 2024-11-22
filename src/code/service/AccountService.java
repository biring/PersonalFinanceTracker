package service;

import dao.AccountDAO;

import java.util.List;

public class AccountService {

    private static final int MIN_ACCOUNT_NAME_LENGTH = 3;
    private static final int MAX_ACCOUNT_NAME_LENGTH = 20;

    private final AccountDAO accountDAO;

    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }


    public boolean createAccount(String name, int typeIndex) {
        int id = accountDAO.getNextID();
        return accountDAO.create(id, name, typeIndex);
    }

    public boolean updateAccount(int id, String name) {
        return accountDAO.updateName(id, name);
    }

    public boolean deleteAccount(int id) {
        return accountDAO.delete(id);
    }

    public List<String> showAccounts() {
        return accountDAO.readAll().stream()
                .map(account -> "[" + account.getID() + "] " + account.getName()+ " (" + account.getAccountType() + ")")
                .toList();
    }

    public List<String> showAccountTypes() {
        List<String> accountTypesWithIndex = new java.util.ArrayList<>(List.of());

        List<String> accountTypes = accountDAO.getAccountTypes();
            for (int i = 0; i < accountTypes.size(); i++) {
                accountTypesWithIndex.add("[" + (i + 1) + "] " + accountTypes.get(i));
        }
        return accountTypesWithIndex;
    }

    public boolean isValidAccountId(int id) {
        return accountDAO.isValidAccountId(id);
    }

    public boolean isAccountNameValid(String accountName) {
        return (accountName != null)
                && (!accountName.isEmpty())
                && (!accountDAO.isAccountNameExists(accountName))
                && (accountName.length() >= MIN_ACCOUNT_NAME_LENGTH)
                && (accountName.length() <= MAX_ACCOUNT_NAME_LENGTH);
    }

    public boolean isAccountTypeValid(int idx) {
        return accountDAO.isAccountTypeValid(idx);
    }

    // Get account name using account ID
    protected String getNameById(int id) {
        return accountDAO.getNameById(id);
    }
}

