package dao;

import model.TransactionModel;

import java.util.Date;
import java.util.List;

public class TransactionDAO extends BaseDAO<TransactionModel> {

    private final List<TransactionModel> transactions = super.items;

    public TransactionDAO(){
        super();
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


    // Check if a transaction exists
    public boolean isValidTransactionId(int id) {
        if (transactions == null) {
            return false; // Handle null safely
        }
        return transactions.stream().anyMatch(transaction -> transaction.getID() == id);
    }
}
