package view;

import controller.MenuOption;

import java.util.Scanner;

import static view.messages.MainMessages.*;

public class MainView extends BaseClass {

    public MainView(Scanner scanner) {
        super(scanner);
    }

    public <T extends Enum<T> & MenuOption> T promptForEnumMenuSelection(Class<T> enumType) {
        return super.getEnumMenuSelection(enumType, INFO_MAIN_MENU_TITLE, PROMPT_MENU_SELECTION);
    }
}
