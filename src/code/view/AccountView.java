package view;

import controller.MenuOption;

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
        return promptForStringInput(PROMPT_ACCOUNT_NAME);
    }

    public int promptForAccountId(List<String> accounts) {
        showAccounts(accounts);
        return promptForIntInput(PROMPT_ACCOUNT_ID);
    }

    public void showAccounts(List<String> accounts) {
        printTable(TITLE_ACCOUNT_TABLE, accounts);
    }


    public void showAccountValidResult(boolean success) {
        if (success) {
            printMessageLine(INFO_ACCOUNT_NAME_VALID);
        } else {
            printMessageLine(INFO_ACCOUNT_NAME_INVALID);
        }
    }

    public void showAccountCreationResult(boolean success) {
        if (success) {
            printMessageLine(INFO_ACCOUNT_CREATION_SUCCESS);
        } else {
            printMessageLine(WARNING_ACCOUNT_CREATION_FAILED);
        }
    }

    public void showAccountModificationResult(boolean success) {
        if (success) {
            printMessageLine(INFO_ACCOUNT_MODIFIED_SUCCESS);
        } else {
            printMessageLine(WARNING_ACCOUNT_MODIFICATION_FAILED);
        }
    }

    public void showAccountDeletionResult(boolean success) {
        if (success) {
            printMessageLine(INFO_ACCOUNT_DELETED_SUCCESS);
        } else {
            printMessageLine(WARNING_ACCOUNT_DELETION_FAILED);
        }
    }
}

