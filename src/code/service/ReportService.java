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

    private final int ACCOUNT_REPORT_COLUMN_1_WIDTH = 25;
    private final int ACCOUNT_REPORT_COLUMN_2_WIDTH = 10;
    private final int ACCOUNT_REPORT_COLUMN_3_WIDTH = 10;
    private final int ACCOUNT_REPORT_COLUMN_4_WIDTH = 10;
    private final int ACCOUNT_REPORT_COLUMN_5_WIDTH = 10;

    private final int BUDGET_REPORT_COLUMN_1_WIDTH = 25;
    private final int BUDGET_REPORT_COLUMN_2_WIDTH = 10;
    private final int BUDGET_REPORT_COLUMN_3_WIDTH = 10;
    private final int BUDGET_REPORT_COLUMN_4_WIDTH = 10;


    public ReportService(AccountDAO accountDAO, CategoryDAO categoryDAO,
                         LinkDAO linkDAO, TransactionDAO transactionDAO) {
        this.accountDAO = accountDAO;
        this.categoryDAO = categoryDAO;
        this.linkDAO = linkDAO;
        this.transactionDAO = transactionDAO;
    }

    public List<String> getAccountReport() {
        List<String> report = new ArrayList<>();
        report.add(generateAccountReportHeader());
        report.add(getAccountTotals());
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
            report.add(addCategoryReportDataLine(categoryName, budget, actual));
        }
        return report;
    }

    private String getAccountTotals() {
        List<String> report = new ArrayList<>();
        double netDebits = 0;
        double netCredits = 0;
        // iterate over each account
        List<Integer> accountIds = accountDAO.getIDs();
        for (int accountId : accountIds) {
            String accountName = accountDAO.getNameById(accountId);
            AccountType accountType = accountDAO.getTypeById(accountId);
            double debits = 0;
            double credits = 0;
            //  iterate over transactions in the account
            List<Integer> transactionIds = transactionDAO.getIDs();
            for (Integer transactionId : transactionIds) {
                // calculate the debit and credit for the transaction
                int transactionAccountId = transactionDAO.getAccountId(transactionId);
                if (transactionAccountId == accountId) {
                    double amount = transactionDAO.getAmount(transactionId);
                    debits += getDebitForAccount(accountType, amount);
                    credits += getCreditForAccount(accountType, amount);
                }
            }
            // add the account data line to the report
            report.add(addAccountReportDataLine(accountName, accountType.toString(), credits, debits));
            // update the net debits and credits
            netDebits += debits;
            netCredits += credits;
        }
        // add the total line to the report
        report.add(addAccountReportDataLine("TOTAL", "na", netCredits, netDebits));
        return String.join("\n", report);
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
        final String STATUS_NA = "-";
        if (budget == 0) {
            return STATUS_NA;
        } else if (Math.abs(actual) > budget) {
            return STATUS_OVER;
        } else {
            return STATUS_OK;
        }
    }

    private String generateAccountReportHeader() {

        String BUDGET_REPORT_COLUMN_1 = "[Account]";
        String BUDGET_REPORT_COLUMN_2 = "[Type]";
        String BUDGET_REPORT_COLUMN_3 = "[Credits]";
        String BUDGET_REPORT_COLUMN_4 = "[Debits]";
        String BUDGET_REPORT_COLUMN_5 = "[Net]";

        String BUDGET_REPORT_HEADER_FORMAT =
                "%-" + ACCOUNT_REPORT_COLUMN_1_WIDTH + "s" +
                        "%-" + ACCOUNT_REPORT_COLUMN_2_WIDTH + "s" +
                        "%-" + ACCOUNT_REPORT_COLUMN_3_WIDTH + "s" +
                        "%-" + ACCOUNT_REPORT_COLUMN_4_WIDTH + "s" +
                        "%-" + ACCOUNT_REPORT_COLUMN_5_WIDTH + "s";

        return String.format(
                BUDGET_REPORT_HEADER_FORMAT,
                BUDGET_REPORT_COLUMN_1,
                BUDGET_REPORT_COLUMN_2,
                BUDGET_REPORT_COLUMN_3,
                BUDGET_REPORT_COLUMN_4,
                BUDGET_REPORT_COLUMN_5);
    }

    private String generateBudgetReportHeader() {

        String BUDGET_REPORT_COLUMN_1 = "[Category]";
        String BUDGET_REPORT_COLUMN_2 = "[Budget]";
        String BUDGET_REPORT_COLUMN_3 = "[Actual]";
        String BUDGET_REPORT_COLUMN_4 = "[Status]";

        String BUDGET_REPORT_HEADER_FORMAT =
                "%-" + BUDGET_REPORT_COLUMN_1_WIDTH + "s" +
                        "%-" + BUDGET_REPORT_COLUMN_2_WIDTH + "s" +
                        "%-" + BUDGET_REPORT_COLUMN_3_WIDTH + "s" +
                        "%-" + BUDGET_REPORT_COLUMN_4_WIDTH + "s";

        return String.format(
                BUDGET_REPORT_HEADER_FORMAT,
                BUDGET_REPORT_COLUMN_1,
                BUDGET_REPORT_COLUMN_2,
                BUDGET_REPORT_COLUMN_3,
                BUDGET_REPORT_COLUMN_4);
    }

    private String addAccountReportDataLine(String accountName, String accountType, double credits, double debits) {
        String ACCOUNT_REPORT_FORMAT =
                "%-" + ACCOUNT_REPORT_COLUMN_1_WIDTH + "s" +
                        "%-" + ACCOUNT_REPORT_COLUMN_2_WIDTH + "s" +
                        "%-" + ACCOUNT_REPORT_COLUMN_3_WIDTH + ".0f" +
                        "%-" + ACCOUNT_REPORT_COLUMN_4_WIDTH + ".0f" +
                        "%-" + ACCOUNT_REPORT_COLUMN_5_WIDTH + ".0f";

        return String.format(
                ACCOUNT_REPORT_FORMAT,
                accountName, accountType, credits, debits, credits + debits);
    }

    private String addCategoryReportDataLine(String categoryName, double budget, double actual) {
        String BUDGET_REPORT_LINE_FORMAT =
                "%-" + BUDGET_REPORT_COLUMN_1_WIDTH + "s" +
                        "%-" + BUDGET_REPORT_COLUMN_2_WIDTH + ".0f" +
                        "%-" + BUDGET_REPORT_COLUMN_3_WIDTH + ".0f" +
                        "%-" + BUDGET_REPORT_COLUMN_4_WIDTH + "s";

        String status = getStatus(budget, actual);

        return String.format(BUDGET_REPORT_LINE_FORMAT, categoryName, budget, actual, status);
    }
}
