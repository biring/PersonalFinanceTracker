package report.service;

import account.dao.AccountDAO;
import category.dao.CategoryDAO;
import link.dao.LinkDAO;
import transaction.dao.TransactionDAO;
import account.model.AccountType;

import java.util.ArrayList;
import java.util.List;

public class ReportService {

    private final AccountDAO accountDAO;
    private final CategoryDAO categoryDAO;
    private final LinkDAO linkDAO;
    private final TransactionDAO transactionDAO;

    private double netDebits = 0;
    private double netCredits = 0;

    public ReportService(AccountDAO accountDAO, CategoryDAO categoryDAO,
                         LinkDAO linkDAO, TransactionDAO transactionDAO) {
        this.accountDAO = accountDAO;
        this.categoryDAO = categoryDAO;
        this.linkDAO = linkDAO;
        this.transactionDAO = transactionDAO;
    }

    public List<List<Object>> getAccountReportLines() {
        this.netDebits = 0;
        this.netCredits = 0;
        List<List<Object>> report = new ArrayList<>();
        List<Integer> accountIds = accountDAO.getIDs();
        for (int accountId : accountIds) {
            String accountName = accountDAO.getNameById(accountId);
            AccountType accountType = accountDAO.getTypeById(accountId);
            double debits = 0;
            double credits = 0;
            List<Integer> transactionIds = transactionDAO.getIDs();
            for (Integer transactionId : transactionIds) {
                int transactionAccountId = transactionDAO.getAccountId(transactionId);
                if (transactionAccountId == accountId) {
                    double amount = transactionDAO.getAmount(transactionId);
                    debits += getDebitForAccount(accountType, amount);
                    credits += getCreditForAccount(accountType, amount);
                }
            }
            List<Object> line = List.of(accountName,accountType.toString(),credits,debits, credits + debits);
            report.add(line);
            this.netDebits += debits;
            this.netCredits += credits;
        }
        return report;
    }

    public List<Double> getAccountReportSummary() {
        List<Double> report = new ArrayList<>();
        report.add(netCredits);
        report.add(netDebits);
        report.add(netCredits + netDebits);
        return report;
    }

    public List<List<Object>> getBudgetReportLines() {
        List<List<Object>> report = new ArrayList<>();
        List<Integer> categoryIds = categoryDAO.getIDs();
        for (Integer categoryId : categoryIds) {
            String categoryName = categoryDAO.getNameById(categoryId);
            double budget = categoryDAO.getBudgetById(categoryId);
            double total = getTotalExpenseForCategory(categoryId);
            double actual = calculateMonthlyAverageExpense(total);
            List<Object> line = new ArrayList<>();
            line.add(categoryName);
            line.add(budget);
            line.add(actual);
            line.add(getStatus(budget, actual));
            report.add(line);
        }
        return report;
    }


    public double getDebitForAccount(AccountType accountType, double amount) {
        return calculateTransaction(accountType, amount, true);
    }

    public double getCreditForAccount(AccountType accountType, double amount) {
        return calculateTransaction(accountType, amount, false);
    }

    private double calculateTransaction(AccountType accountType, double amount, boolean isDebit) {
        if (accountType == AccountType.CREDIT) {
            // For CREDIT accounts, debits are negative, credits are positive
            return isDebit ? (amount < 0 ? amount : 0) : (amount > 0 ? amount : 0);
        } else if (accountType == AccountType.DEBIT) {
            // For DEBIT accounts, debits are positive, credits are negative
            return isDebit ? (amount > 0 ? amount : 0) : (amount < 0 ? amount : 0);
        }
        return 0; // Fallback case (if needed)
    }

    private double getTotalExpenseForCategory(int categoryId) {
        List<Integer> linkIds = linkDAO.getLinkIDsForCategory(categoryId);
        double actual = 0;
        for (Integer linkId : linkIds) {
            List<Integer> transactionIds = transactionDAO.getIDs();
            for (Integer transactionId : transactionIds) {
                String transactionName = transactionDAO.getNameById(transactionId);
                String linkName = linkDAO.getNameById(linkId);
                if (transactionName.toLowerCase().contains(linkName.toLowerCase())) {
                    actual += transactionDAO.getAmount(transactionId);
                }
            }
        }
        return actual;
    }

    private double getDaysOfHistory() {
        long min = transactionDAO.getMinDate();
        long max = transactionDAO.getMaxDate();

        long days;
        try {
            days = (max - min) / (1000 * 60 * 60 * 24);
        } catch (Exception e) {
            return 0;
        }
        return (double) days;
    }

    private double calculateMonthlyAverageExpense(double expense) {
        try {
            expense = (expense / getDaysOfHistory()) * 30;
        } catch (ArithmeticException e) {
            return -1;
        }
        return expense;
    }

    private String getStatus(double budget, double actual) {
        final String STATUS_OK = "OK";
        final String STATUS_OVER = "OVER";
        final String STATUS_NA = ".";
        if (budget == 0) {
            return STATUS_NA;
        } else if (Math.abs(actual) > budget) {
            return STATUS_OVER;
        } else {
            return STATUS_OK;
        }
    }
}
