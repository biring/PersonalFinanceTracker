package account.service;

import account.dao.AccountDAO;
import transaction.dao.TransactionDAO;

import java.io.IOException;
import java.util.List;

public class AccountService {

    private static final int MIN_ACCOUNT_NAME_LENGTH = 3;
    private static final int MAX_ACCOUNT_NAME_LENGTH = 20;

    private final AccountDAO accountDAO;
    private final TransactionDAO transactionDAO;

    public AccountService(AccountDAO accountDAO, TransactionDAO transactionDAO) {
        this.accountDAO = accountDAO;
        this.transactionDAO = transactionDAO;
    }


    public boolean createAccount(String name, int typeIndex) {
        int id = accountDAO.getNextID();
        return accountDAO.create(id, name, typeIndex);
    }

    public boolean updateAccount(int id, String name) {
        return accountDAO.updateName(id, name);
    }

    public boolean deleteAccount(int id) {
        boolean success = accountDAO.delete(id);
        success = success && transactionDAO.deleteTransactionsByAccountId(id);
        return success;
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

    public void loadFromDb() throws IOException {
        accountDAO.deserialize();
    }

    public void storeToDb() throws IOException {
        accountDAO.serialize();
    }
}

