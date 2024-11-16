package dao;

import model.AccountModel;

import java.util.ArrayList;
import java.util.List;

public class AccountDao {
    private final List<AccountModel> accounts = new ArrayList<>();

    // get the next available ID
    public int getNextID() {
        int nextID = 0;
        for (AccountModel account : accounts) {
            if (nextID <= account.getID()) {
                nextID = account.getID();
            }
        }
        return nextID + 1;
    }

    // Create a new account
    public void addAccount(int id, String name) {
        AccountModel account = new AccountModel(id, name);
        accounts.add(account);
    }

    public boolean updateAccount(int id, String name) {
        for (AccountModel account : accounts) {
            if (account.getID() == id) {
                account.setName(name);
                return true;
            }
        }
        return false;
    }

    // Retrieve all accounts
    public List<AccountModel> getAllAccounts() {
        return accounts;
    }

    // delete an account
    public boolean deleteAccount(int id) {
        for (AccountModel account : accounts) {
            if (account.getID() == id) {
                accounts.remove(account);
                return true;
            }
        }
        return false;
    }
}
