package service;

import java.util.List;

public class TransactionService {

    private final AccountService accountService;

    public TransactionService(AccountService accountService) {
        this.accountService = accountService;
    }

    public List<String> getAccounts() {
        return accountService.showAccounts();
    }

    public boolean isValidAccountId(int accountId) {
        return accountService.isValidAccountId(accountId);
    }
}
