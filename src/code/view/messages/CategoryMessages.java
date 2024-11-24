package view.messages;

public class CategoryMessages {

    // PROMPT_* for input prompts or messages requesting user action
    public static final String PROMPT_FOR_MENU_SELECTION = "Enter your choice by number: ";
    public static final String PROMPT_FOR_SELECTION_BY_NO = "Enter category number: ";
    public static final String PROMPT_FOR_NAME = "Enter category name: ";
    // TITLE_* for titles of menu, tables, etc.
    public static final String TITLE_MENU = "Category Menu";
    public static final String TITLE_TABLE = "List of categories";
    // INFO_* for general information messages
    public static final String INFO_NAME_VALID = "Category name is valid.";
    public static final String INFO_CREATION_SUCCESS = "Category creation successful.";
    public static final String INFO_MODIFICATION_SUCCESS = "Category modification successful.";
    public static final String INFO_DELETION_SUCCESS = "Category deletion successful.";
    // WARNING_* for warning messages
    public static final String WARNING_NAME_INVALID = "Category name is invalid.";
    public static final String WARNING_CREATION_FAILED = "Category creation failed.";
    public static final String WARNING_MODIFICATION_FAILED = "Category modification failed.";
    public static final String WARNING_DELETION_FAILED = "Category deletion failed.";
    public static final String WARNING_DB_READ_FAILED = "Category read from database failed.";
    public static final String WARNING_DB_WRITE_FAILED = "Category write to Database failed.";

    // Prevent instantiation
    private CategoryMessages() {
        throw new UnsupportedOperationException("Cannot instantiate this class.");
    }


}
