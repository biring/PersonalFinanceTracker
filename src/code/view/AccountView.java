package view;

import controller.MenuOption;
import utility.Console;

import java.util.List;
import java.util.Scanner;

import static view.messages.AccountMessages.*;

public class AccountView extends BaseClass {

    public AccountView(Scanner scanner) {
        super(scanner);
    }

    public <T extends Enum<T> & MenuOption> T promptForEnumMenuSelection(Class<T> enumType) {
        return super.getEnumMenuSelection(enumType, TITLE_ACCOUNT_MENU, PROMPT_MENU_SELECTION);
    }

    public String promptForAccountName() {
        return super.promptForStringInput(PROMPT_ACCOUNT_NAME);
    }

    public int promptForAccountId(List<String> accounts) {
        return super.getTableSelection(accounts, TITLE_ACCOUNT_TABLE, PROMPT_ACCOUNT_ID);
    }

    public void showAccounts(List<String> accounts) {
        super.displayTableData(accounts, TITLE_ACCOUNT_TABLE);
    }

    public void showAccountValidResult(boolean success) {
        super.displaySuccessFailureMessage(success, INFO_ACCOUNT_NAME_VALID, INFO_ACCOUNT_NAME_INVALID);
    }

    public void showAccountCreationResult(boolean success) {
        super.displaySuccessFailureMessage(success, INFO_ACCOUNT_CREATION_SUCCESS, WARNING_ACCOUNT_CREATION_FAILED);
    }

    public void showAccountModificationResult(boolean success) {
        super.displaySuccessFailureMessage(success, INFO_ACCOUNT_MODIFIED_SUCCESS, WARNING_ACCOUNT_MODIFICATION_FAILED);
    }

    public void showAccountDeletionResult(boolean success) {
        super.displaySuccessFailureMessage(success, INFO_ACCOUNT_DELETED_SUCCESS, WARNING_ACCOUNT_DELETION_FAILED);
    }
}

