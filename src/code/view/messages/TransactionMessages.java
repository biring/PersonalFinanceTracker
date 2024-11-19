package view.messages;

public class TransactionMessages {

    // PROMPT_* for input prompts or messages requesting user action
    public static final String PROMPT_FOR_MENU = "Enter menu choice by number: ";
    public static final String PROMPT_FOR_ACCOUNT = "Enter account choice by number: ";
    public static final String PROMPT_FOR_FILE = "Enter csv file choice by number: ";
    // TITLE_* for titles of menu, tables, etc.
    public static final String TITLE_MENU = "Transaction Menu";
    public static final String TITLE_ACCOUNT = "List of available accounts";
    public static final String TITLE_FILE = "List of csv files";
    // INFO_* for general information messages
    public static final String INFO_MENU_SELECTION_NOT_SUPPORTED = "Selection not supported.";
    public static final String INFO_NO_ACCOUNTS_FOUND = "No accounts found.";
    public static final String INFO_NO_CSV_FILE_FOUND = "No csv data file found.";
    public static final String INFO_CSV_FILE_READ_SUCCESS = "CSV file read successful.";
    // WARNING_* for warning messages
    public static final String WARNING_INVALID_SELECTION = "Invalid selection.";
    public static final String WARNING_CSV_FILE_READ_FAILED = "CSV file read failed.";

    // Prevent instantiation
    private TransactionMessages() {
        throw new UnsupportedOperationException("Cannot instantiate this class.");
    }
}

