package service;

import dao.AccountDAO;
import dao.TransactionDAO;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TransactionService {

    private final AccountDAO accountDAO;
    private final TransactionDAO transactionDAO;

    public TransactionService(AccountDAO accountDAO, TransactionDAO transactionDAO) {
        this.accountDAO = accountDAO;
        this.transactionDAO = transactionDAO;
    }

    public boolean createTransactions(int accountId, List<String[]> data) {
        boolean success = true;
        // remove the first row of the data as it contains the column names
        data.removeFirst();
        for (String[] transactionData : data) {
            int id = transactionDAO.getNextID();
            Date date = parseDate(transactionData[0]);
            String name = parseName(transactionData[1]);
            double amount = parseAmount(transactionData[2]);
            success = success && transactionDAO.create(id, name, date, amount, accountId);
        }
        return success;
    }

    public List<String> showTransactions() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");  // Specify your desired format here
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);  // Format for USD currency (can change the locale)
        return transactionDAO.readAll().stream()
                .map(transaction -> {
                    String accountName = accountDAO.getNameById(transaction.getAccountId());
                    return "["
                            + transaction.getID() + "] (a/c :"
                            + accountName + ") "
                            + dateFormat.format(transaction.getDate()) + " "
                            + currencyFormat.format(transaction.getAmount()) + " "
                            + transaction.getName();
                })
                .toList();
    }

    public void loadFromDb() throws IOException {
        transactionDAO.deserialize();
    }

    public void storeToDb() throws IOException {
        transactionDAO.serialize();
    }

    public List<String> getAccounts() {
        return accountDAO.readAll().stream()
                .map(account -> "[" + account.getID() + "] " + account.getName()+ " (" + account.getAccountType() + ")")
                .toList();
    }

    public boolean isValidAccountId(int accountId) {
        return accountDAO.isValidAccountId(accountId);
    }

    private Date parseDate(String date) {
        String[] supportedFormats = {"yyyy-MM-dd", "MM/dd/yyyy"};
        for (String format : supportedFormats) {
            try {
                return new SimpleDateFormat(format).parse(date);
            } catch (Exception ignored) {
                // Continue trying next format
                }
            }
            throw new IllegalArgumentException("Invalid date format: " + date + ". Expected formats: " + String.join(", ", supportedFormats));
        }

    private String parseName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        return name;
    }

    private double parseAmount(String amount) {
        try {
            return Double.parseDouble(amount);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid amount format");
        }
    }
}
