package service;

import dao.AccountDao;

import java.util.List;

public class AccountService {

    private static final int MIN_ACCOUNT_NAME_LENGTH = 3;
    private static final int MAX_ACCOUNT_NAME_LENGTH = 20;

    private final AccountDao accountDao = new AccountDao();

    public AccountService() {
    }

    public boolean isAccountNameValid(String accountName) {
        return (accountName != null)
                && (!accountName.isEmpty())
                && (!accountDao.isAccountNameExists(accountName))
                && (accountName.length() >= MIN_ACCOUNT_NAME_LENGTH)
                && (accountName.length() <= MAX_ACCOUNT_NAME_LENGTH);
    }

    public boolean createAccount(String name) {
        int id = accountDao.getNextID();
        return accountDao.addAccount(id, name);
    }

    public boolean updateAccount(int id, String name) {
        return accountDao.updateAccount(id, name);
    }

    public boolean deleteAccount(int id) {
        return accountDao.deleteAccount(id);
    }

    public List<String> getAllAccountsAsString() {
        return accountDao.getAllAccounts().stream()
                .map(account -> "[" + account.getID() + "] " + account.getName())
                .toList();
    }
}

