package link.message;

public class LinkMessage {

    // PROMPT_* for input prompts or messages requesting user action
    public static final String PROMPT_FOR_MENU_SELECTION = "Enter menu choice by number: ";
    public static final String PROMPT_FOR_CATEGORY_SELECTION = "Enter category choice by number: ";
    public static final String PROMPT_FOR_LINK_SELECTION = "Enter link choice by number: ";
    public static final String PROMPT_FOR_LINK_STRING = "Enter link search string: ";
    // TITLE_* for titles of menu, tables, etc.
    public static final String TITLE_MENU_TABLE = "Link Menu";
    public static final String TITLE_CATEGORY_TABLE = "List of available categories";
    public static final String TITLE_LINK_TABLE = "List of links";
    // INFO_* for general information messages
    public static final String INFO_NO_CATEGORY_FOUND = "No categories found.";
    public static final String INFO_NO_LINKS_FOUND = "No links found.";
    public static final String INFO_CREATION_SUCCESS = "Link creation successful.";
    public static final String INFO_MODIFICATION_SUCCESS = "Link modification successful.";
    public static final String INFO_DELETION_SUCCESS = "Link deletion successful.";
    // WARNING_* for warning messages
    public static final String WARNING_INVALID_SELECTION = "Invalid selection.";
    public static final String WARNING_INVALID_LINK_STRING = "Invalid link string.";
    public static final String WARNING_CREATION_FAILED = "Link creation failed.";
    public static final String WARNING_MODIFICATION_FAILED = "Link modification failed.";
    public static final String WARNING_DELETION_FAILED = "Link deletion failed.";
    // ERROR_* for error messages
    public static final String ERROR_DB_READ_FAILED = "Link read from database failed.";
    public static final String ERROR_DB_WRITE_FAILED = "Link write to database failed.";

    // Prevent instantiation
    private LinkMessage() {
        throw new UnsupportedOperationException("Cannot instantiate this class.");
    }
}

