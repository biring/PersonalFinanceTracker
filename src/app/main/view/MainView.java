package main.view;

import base.BaseView;
import interfaces.MenuInterface;

import static main.message.MainMessages.*;

public class MainView extends BaseView {

    public MainView() {
        super();
    }

    public <T extends Enum<T> & MenuInterface> T promptForEnumMenuSelection(Class<T> enumType) {
        return super.getEnumMenuSelection(enumType, TITLE_MAIN_MENU, PROMPT_MENU_SELECTION);
    }
}
