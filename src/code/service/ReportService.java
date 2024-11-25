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
        return report;
    }

}
