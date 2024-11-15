package view.messages;

public class AccountMessages {

    // Prevent instantiation
    private AccountMessages() {
        throw new UnsupportedOperationException("Cannot instantiate this class.");
    }

    // PROMPT_* for input prompts or messages requesting user action
    public static final String PROMPT_MENU_SELECTION = "Enter your choice: ";
    public static final String PROMPT_ACCOUNT_NAME = "Enter account name: ";
    public static final String PROMPT_ACCOUNT_TYPE = "Select account type: ";

    // INFO_* for general information messages
    public static final String INFO_ACCOUNT_NAME_INVALID = "Account name is invalid.";
    public static final String INFO_ACCOUNT_NAME_VALID = "Account name is valid.";
    public static final String INFO_ACCOUNT_CREATED_SUCCESS = "Account created successfully.";
    public static final String INFO_ACCOUNT_CREATION_FAILED = "Account creation failed.";

    // ERROR_* for error messages

    // ACTION_STATUS_* for success/failure messages
}