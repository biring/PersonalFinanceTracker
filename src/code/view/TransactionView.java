package view;

import controller.MenuOption;
import utility.Console;

import static view.messages.TransactionMessages.*;

public class TransactionView extends BaseClass {

    public TransactionView() {
        super();
    }

    public <T extends Enum<T> & MenuOption> T promptForEnumMenuSelection(Class<T> enumType) {
        return super.getEnumMenuSelection(enumType, TITLE_MENU, PROMPT_FOR_MENU_SELECTION);
    }

    public void showMenuOptionNotSupported() {
        Console.printMessage(INFO_SELECTION_NOT_SUPPORTED);
    }

}
