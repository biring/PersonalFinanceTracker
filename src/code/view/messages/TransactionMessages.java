package view.messages;

public class TransactionMessages {

    // PROMPT_* for input prompts or messages requesting user action
    public static final String PROMPT_FOR_MENU_SELECTION = "Enter your choice by number: ";
    // TITLE_* for titles of menu, tables, etc.
    public static final String TITLE_MENU = "Transaction Menu";
    public static final String TITLE_TRANSACTION_TABLE = "List of transaction";
    // INFO_* for general information messages
    public static final String INFO_SELECTION_NOT_SUPPORTED = "Selection not supported.";
    public static final String INFO_TRANSACTIONS_NOT_AVAILABLE = "No transaction available.";
    public static final String INFO_CREATION_SUCCESS = "Transaction creation successful.";
    public static final String INFO_MODIFICATION_SUCCESS = "Transaction modification successful.";
    public static final String INFO_DELETION_SUCCESS = "Transaction deletion successful.";
    // WARNING_* for warning messages
    public static final String WARNING_INVALID_SELECTION = "Invalid selection.";
    public static final String WARNING_CREATION_FAILED = "Transaction creation failed.";
    public static final String WARNING_MODIFICATION_FAILED = "Transaction modification failed.";
    public static final String WARNING_DELETION_FAILED = "Transaction deletion failed.";

    // Prevent instantiation
    private TransactionMessages() {
        throw new UnsupportedOperationException("Cannot instantiate this class.");
    }
}

