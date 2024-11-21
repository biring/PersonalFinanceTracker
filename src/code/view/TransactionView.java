package view;

import controller.MenuOption;
import utility.Console;

import java.io.IOException;
import java.util.List;

import static view.messages.CategoryMessages.TITLE_TABLE;
import static view.messages.TransactionMessages.*;

public class TransactionView extends BaseClass {

    public TransactionView() {
        super();
    }

    public <T extends Enum<T> & MenuOption> T promptForEnumMenuSelection(Class<T> enumType) {
        return super.getEnumMenuSelection(enumType, TITLE_MENU, PROMPT_FOR_MENU);
    }

    public void showMenuOptionNotSupported() {
        Console.printMessage(INFO_MENU_SELECTION_NOT_SUPPORTED);
    }

    public void showNoAccountsFound() {
        Console.printMessage(INFO_NO_ACCOUNTS_FOUND);
    }

    public void showNoCSVFilesFound() {
        Console.printMessage(INFO_NO_CSV_FILE_FOUND);
    }

    public int promptForCSVFileSelection(List<String> files) {
        return super.getTableSelection(files, TITLE_FILE, PROMPT_FOR_FILE);
    }

    public int promptForAccountSelection(List<String> accounts) {
        return super.getTableSelection(accounts, TITLE_ACCOUNT, PROMPT_FOR_ACCOUNT);
    }

    public int promptForAccountSelection() {
        return super.promptForIntInput(PROMPT_FOR_ACCOUNT);
    }

    public void showInvalidSelection() {
        Console.printMessage(WARNING_INVALID_SELECTION);
    }

    public void showNoTransactionsFound() {
        Console.printMessage(INFO_NO_TRANSACTIONS_FOUND);
    }

    public void showDbReadError() {
        Console.printMessage(WARNING_DB_READ_FAILED);
    }

    public void showDbWriteError() {
        Console.printMessage(WARNING_DB_WRITE_FAILED);
    }

    public void showTransactions(List<String> transactions) {
        super.displayTableData(transactions, TITLE_TRANSACTION_TABLE);
    }

    public int promptForCSVFileSelection() {
        return super.promptForIntInput(PROMPT_FOR_FILE);
    }

    public void showCSVFileReadResult(boolean success) {
        super.displaySuccessFailureMessage(success, INFO_CSV_FILE_READ_SUCCESS, WARNING_CSV_FILE_READ_FAILED);
    }

}
