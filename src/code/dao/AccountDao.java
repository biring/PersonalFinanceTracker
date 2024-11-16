package dao;

import model.AccountModel;

import java.util.ArrayList;
import java.util.List;

public class AccountDao {
    private final List<AccountModel> accounts = new ArrayList<>();
    private int nextID = 0;

    // get the next available ID
    public int getNextID() {
        if (this.nextID == 0) {
            for (AccountModel account : accounts) {
                if (this.nextID <= account.getID()) {
                    this.nextID = account.getID();
                }
            }
            this.nextID ++;
        }
        return this.nextID;
    }

    // Create a new account
    public boolean addAccount(int id, String name) {
        AccountModel account = new AccountModel(id, name);
        accounts.add(account);
        this.nextID ++;
        for (AccountModel ac : accounts) {
            if (ac.getID() == id) {
                return true;
            }
        }
        return false;
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
