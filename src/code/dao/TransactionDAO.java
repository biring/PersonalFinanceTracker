package dao;

import model.TransactionModel;

import java.util.Date;
import java.util.List;

public class TransactionDAO extends BaseDAO<TransactionModel> {

    private final List<TransactionModel> transactions = super.items;

    public TransactionDAO(){
        super(TransactionModel.class);
    }

    @Override
    protected int extractID(TransactionModel transaction) {
        return transaction.getID();
    }

    // Create a new transaction
    public boolean create(int id, String name, Date date, double amount, int accountId) {
        TransactionModel transaction = new TransactionModel(id, date, name, amount, accountId);
        return createItem(transaction);
    }

    // Delete a transaction by account ID
    public boolean deleteTransactionsByAccountId(int accountId) {
        try {
            transactions.removeIf(transaction -> transaction.getAccountId() == accountId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // get transactions with name containing the search string
    public List<String> getTransactionsMatchingString(String searchString) {
        return transactions.stream()
                .filter(transaction -> transaction.getName().toLowerCase().contains(searchString.toLowerCase()))
                .map(TransactionModel::toString)
                .toList();
    }

    // Check if a transaction exists
    public boolean isValidTransactionId(int id) {
        if (transactions == null) {
            return false; // Handle null safely
        }
        return transactions.stream().anyMatch(transaction -> transaction.getID() == id);
    }
}
