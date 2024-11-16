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
        return super.getEnumMenuSelection(enumType, PROMPT_MENU_SELECTION);
    }

    public String promptForAccountName() {
        return promptForStringInput(PROMPT_ACCOUNT_NAME);
    }

    public int promptForAccountId(List<String> accounts) {
        showAccounts(accounts);
        return promptForIntInput(PROMPT_ACCOUNT_ID);
    }

    public void showAccounts(List<String> accounts) {
        for(String account : accounts) {
            printMessageLine(account);
        }
    }

    public String promptForAccountTypeSelection(String[] accountTypes) {
        displayMenu(accountTypes);
        int selection = promptForMenuSelection(PROMPT_ACCOUNT_TYPE);
        return accountTypes[selection - 1];
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
            printMessageLine(INFO_ACCOUNT_CREATED_SUCCESS);
        } else {
            printMessageLine(INFO_ACCOUNT_CREATION_FAILED);
        }
    }
}

