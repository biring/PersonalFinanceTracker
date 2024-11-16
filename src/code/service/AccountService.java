package service;

import dao.AccountDao;

import java.util.List;

public class AccountService {

    private final AccountDao accountDao = new AccountDao();

    public AccountService() {
    }

    public boolean isAccountNameValid(String accountName) {
        return accountName != null && !accountName.isEmpty();
    }

    public void createAccount(String name) {
        int id = accountDao.getNextID();
        accountDao.addAccount(id, name);
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

