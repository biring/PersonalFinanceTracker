package dao;

import model.AccountModel;
import model.AccountType;

import java.util.ArrayList;
import java.util.List;

public class AccountDAO extends BaseDAO<AccountModel> {

    private final List<AccountModel> accounts = super.items;

    public AccountDAO(){
        super();
    }

    @Override
    protected int extractID(AccountModel account) {
        return account.getID();
    }

    // Create a new account
    public boolean create(int id, String name, int typeIndex) {
        AccountType type = AccountType.values()[typeIndex];
        AccountModel account = new AccountModel(id, name, type);
        return createItem(account);
    }

    // Update account name
    public boolean updateName(int id, String name) {
        for (AccountModel account : accounts) {
            if (account.getID() == id) {
                account.setName(name);
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
    public boolean isAccountTypeValid(int typeIndex) {
        try {
            // Attempt to access the enum by index
            AccountType value = AccountType.values()[typeIndex];
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            // Handle invalid index
            return false;
            }
        }
}