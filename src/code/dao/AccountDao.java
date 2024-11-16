package dao;

import model.AccountModel;
import model.AccountType;

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
    public boolean addAccount(int id, String name, int idx) {
        AccountType type = AccountType.values()[idx];
        AccountModel account = new AccountModel(id, name, type);
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

    // Check if account name exists
    public boolean isAccountNameExists(String name) {
        for (AccountModel account : accounts) {
            if (account.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    // get account type as a table of strings
    public List<String> getAccountTypes() {
        List<String> accountTypes = new ArrayList<>();
        for (AccountType type : AccountType.values()) {
            accountTypes.add(type.toString());
        }
        return accountTypes;
    }

    // validate account type
    public boolean isAccountTypeValid(int index) {
        try {
            // Attempt to access the enum by index
            AccountType value = AccountType.values()[index];
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            // Handle invalid index
            return false;
            }
        }
}
