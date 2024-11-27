package service;

import dao.AccountDAO;
import dao.CategoryDAO;
import dao.LinkDAO;
import dao.TransactionDAO;
import model.AccountType;

import java.util.ArrayList;
import java.util.List;


public class ReportService {

    private final AccountDAO accountDAO;
    private final CategoryDAO categoryDAO;
    private final LinkDAO linkDAO;
    private final TransactionDAO transactionDAO;

    public ReportService(AccountDAO accountDAO, CategoryDAO categoryDAO, LinkDAO linkDAO, TransactionDAO transactionDAO) {
        this.accountDAO = accountDAO;
        this.categoryDAO = categoryDAO;
        this.linkDAO = linkDAO;
        this.transactionDAO = transactionDAO;
    }

    public List<String> getAccountReport() {
        double netDebits = 0;
        double netCredits = 0;
        double netTotal = 0;
        String ACCOUNT_REPORT_HEADER = "[Accounts]     [Type]    [Debits]   [Credits]  [Net]";
        String ACCOUNT_REPORT_FORMAT = "%-15s%-10s$%-10.0f$%-10.0f$%-10.0f";
        List<String> report = new ArrayList<>();
        report.add(ACCOUNT_REPORT_HEADER);
        List<Integer> accountIds = accountDAO.getIDs();
        for (Integer accountId : accountIds) {
            String accountName = accountDAO.getNameById(accountId);
            AccountType accountType = accountDAO.getTypeById(accountId);
            double debits = 0;
            double credits = 0;
            double total = 0;
            List<Integer> transactionIds = transactionDAO.getIDs();
            for (Integer transactionId : transactionIds) {
                int transactionAccountId = transactionDAO.getAccountId(transactionId);
                if (transactionAccountId == accountId) {
                    double amount = transactionDAO.getAmount(transactionId);
                    if (amount < 0) {
                        debits += amount;
                    } else if (amount > 0) {
                        credits += amount;
                    }
                }
                total = debits + credits;
            }
            netDebits += debits;
            netCredits += credits;
            netTotal += total;
            String summary = String.format(ACCOUNT_REPORT_FORMAT, accountName, accountType.toString(), debits, credits, total);
            report.add(summary);
        }
        String summary = String.format(ACCOUNT_REPORT_FORMAT, "Total", "n/a", netDebits, netCredits, netTotal);
        report.add(summary);
        return report;
    }

    public List<String> getBudgetReport() {
        List<String> report = new ArrayList<>();
        report.add(generateBudgetReportHeader());
        List<Integer> categoryIds = categoryDAO.getIDs();
        for (Integer categoryId : categoryIds) {
            String categoryName = categoryDAO.getNameById(categoryId);
            double budget = categoryDAO.getBudgetById(categoryId);
            double total = getTotalExpenseForCategory(categoryId);
            double actual = calculateMonthlyAverageExpense(total);
            String status = getStatus(budget, actual);
            report.add(addCategoryReportDataLine(categoryName, budget, actual, status));
        }
        return report;
    }

    private double getTotalExpenseForCategory(int categoryId) {
        double actual = 0;
        List<Integer> linkIds = linkDAO.getLinkIDsForCategory(categoryId);
        actual = 0;
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

        long days = 0;
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
        final String STATUS_NA = "N/A";
        if (budget == 0) {
            return STATUS_NA;
        } else if (Math.abs(actual) > budget) {
            return STATUS_OVER;
        } else {
            return STATUS_OK;
        }
    }

    private String generateBudgetReportHeader() {
        String BUDGET_REPORT_COLUMN_1 = "[Category]";
        String BUDGET_REPORT_COLUMN_2 = "[Budget/Month]";
        String BUDGET_REPORT_COLUMN_3 = "[Actual/Month]";
        String BUDGET_REPORT_COLUMN_4 = "[Status]";
        String BUDGET_REPORT_HEADER_FORMAT = "%-30s%-21s%-21s%-15s"; // 1 more to account for the $ sign
        return String.format(
                BUDGET_REPORT_HEADER_FORMAT,
                BUDGET_REPORT_COLUMN_1,
                BUDGET_REPORT_COLUMN_2,
                BUDGET_REPORT_COLUMN_3,
                BUDGET_REPORT_COLUMN_4);
    }


    private String addCategoryReportDataLine(String categoryName, double budget, double actual, String status) {
        String BUDGET_REPORT_LINE_FORMAT = "%-30s$%-20.0f$%-20.0f%-15s";
        return String.format(
                BUDGET_REPORT_LINE_FORMAT,
                categoryName, budget, actual, status);
    }
}
