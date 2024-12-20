package account.message;

public class AccountMessage {

    // Prevent instantiation
    private AccountMessage() {
        throw new UnsupportedOperationException("Cannot instantiate this class.");
    }

    // PROMPT_* for input prompts or messages requesting user action
    public static final String PROMPT_MENU_SELECTION = "Enter your choice: ";
    public static final String PROMPT_ACCOUNT_ID = "Enter account ID: ";
    public static final String PROMPT_ACCOUNT_NAME = "Enter account name: ";
    public static final String PROMPT_ACCOUNT_TYPE = "Select account type: ";

    // TITLE_* for titles of menu, tables, etc.
    public static final String TITLE_ACCOUNT_MENU = "Account Menu";
    public static final String TITLE_ACCOUNT_TABLE = "Accounts List";
    public static final String TITLE_ACCOUNT_TYPE = "Account Types";

    // INFO_* for general information messages
    public static final String INFO_ACCOUNT_NAME_INVALID = "Account name is invalid.";
    public static final String INFO_ACCOUNT_NAME_VALID = "Account name is valid.";
    public static final String INFO_ACCOUNT_TYPE_VALID = "Account type is valid.";
    public static final String INFO_ACCOUNT_CREATION_SUCCESS = "Account created successfully.";
    public static final String INFO_ACCOUNT_MODIFIED_SUCCESS = "Account modified successfully.";
    public static final String INFO_ACCOUNT_DELETED_SUCCESS = "Account deleted successfully.";

    // WARNING_* for warning messages
    public static final String WARNING_ACCOUNT_TYPE_INVALID = "Account type is invalid.";
    public static final String WARNING_ACCOUNT_CREATION_FAILED = "Account creation failed.";
    public static final String WARNING_ACCOUNT_MODIFICATION_FAILED = "Account modification failed.";
    public static final String WARNING_ACCOUNT_DELETION_FAILED = "Account deletion failed.";
    public static final String WARNING_DB_READ_FAILED = "Account read from database failed.";
    public static final String WARNING_DB_WRITE_FAILED = "Account write to Database failed.";
    // ERROR_* for error messages

    // ACTION_STATUS_* for success/failure messages
}
