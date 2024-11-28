package view;

import controller.MenuOption;

import static messages.MainMessages.*;

public class MainView extends BaseClass {

    public MainView() {
        super();
    }

    public <T extends Enum<T> & MenuOption> T promptForEnumMenuSelection(Class<T> enumType) {
        return super.getEnumMenuSelection(enumType, TITLE_MAIN_MENU, PROMPT_MENU_SELECTION);
    }
}
