package dao;

import model.AccountModel;
import model.AccountType;

import java.util.ArrayList;
import java.util.List;

public class AccountDAO extends BaseDAO<AccountModel> {

    private final List<AccountModel> accounts = super.items;

    public AccountDAO(){
        super(AccountModel.class);
    }

    @Override
    protected int extractID(AccountModel account) {
        return account.getID();
    }

    public List<Integer> getIDs() {
        List<Integer> ids = new ArrayList<>();
        for (AccountModel account : accounts) {
            ids.add(account.getID());
        }
        return ids;
    }

    // Get account type by ID
    public AccountType getTypeById(int id) {
        try {
            return accounts.stream()
                    .filter(account -> account.getID() == id)
                    .findFirst()
                    .map(AccountModel::getAccountType)
                    .orElseThrow(() -> new IndexOutOfBoundsException("Account ID not found"));
        } catch (Exception e) {
            throw new IndexOutOfBoundsException("Account ID not found");
        }
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

    // validate account ID
    public boolean isValidAccountId(int id) {
        if (accounts == null) {
            return false; // Handle null safely
        }
        return accounts.stream().anyMatch(account -> account.getID() == id);
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
