package dao;

import model.TransactionModel;

import java.util.ArrayList;
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

    public List<Integer> getIDs() {
        List<Integer> ids = new ArrayList<>();
        for (TransactionModel transaction: transactions) {
            ids.add(transaction.getID());
        }
        return ids;
    }

    // Get account ID by transaction ID
    public int getAccountId(int id) {
        try {
            return transactions.stream()
                    .filter(transaction -> transaction.getID() == id)
                    .findFirst()
                    .map(TransactionModel::getAccountId)
                    .orElseThrow(() -> new IndexOutOfBoundsException("Transaction ID not found"));
        } catch (Exception e) {
            throw new IndexOutOfBoundsException("Transaction ID not found");
        }
    }

    // Get amount by transaction ID
    public double getAmount(int id) {
        try {
            return transactions.stream()
                    .filter(transaction -> transaction.getID() == id)
                    .findFirst()
                    .map(TransactionModel::getAmount)
                    .orElseThrow(() -> new IndexOutOfBoundsException("Transaction ID not found"));
        } catch (Exception e) {
            throw new IndexOutOfBoundsException("Transaction ID not found");
        }
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

    // Get calendar days of transaction history
    public int getDaysOfHistory() {
        // get minimum date
        Date minDate = transactions.stream()
                .map(TransactionModel::getDate)
                .min(Date::compareTo)
                .orElse(new Date());
        // get maximum date
        Date maxDate = transactions.stream()
                .map(TransactionModel::getDate)
                .max(Date::compareTo)
                .orElse(new Date());
        // get difference in days
        int DaysOfHistory = (int) ((maxDate.getTime() - minDate.getTime()) / (1000 * 60 * 60 * 24));
        if (DaysOfHistory < 0) {
            return 0;
        }
        return DaysOfHistory;
    }

    public long getMinDate() {
        return transactions.stream()
                .map(TransactionModel::getDate)
                .min(Date::compareTo)
                .orElse(new Date()).getTime();
    }

    public long getMaxDate() {
        return transactions.stream()
                .map(TransactionModel::getDate)
                .max(Date::compareTo)
                .orElse(new Date()).getTime();
    }


    // Check if a transaction exists
    public boolean isValidTransactionId(int id) {
        if (transactions == null) {
            return false; // Handle null safely
        }
        return transactions.stream().anyMatch(transaction -> transaction.getID() == id);
    }
}
