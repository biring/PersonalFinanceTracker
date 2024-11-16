package view.messages;

public class MainMessages {

    // Prevent instantiation
    private MainMessages() {
        throw new UnsupportedOperationException("Cannot instantiate this class.");
    }

    // PROMPT_* for input prompts or messages requesting user action
    public static final String PROMPT_MENU_SELECTION = "Enter your choice: ";

    // INFO_* for general information messages
    public static final String INFO_MAIN_MENU_TITLE = "Main Menu";

    // ERROR_* for error messages

    // ACTION_STATUS_* for success/failure messages
}