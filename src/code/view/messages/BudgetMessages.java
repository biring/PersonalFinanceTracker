package view.messages;

public class BudgetMessages {

    // PROMPT_* for input prompts or messages requesting user action
    public static final String PROMPT_FOR_MENU_SELECTION = "Enter your choice by number: ";
    public static final String PROMPT_FOR_SELECTION_BY_NO = "Enter category number: ";
    public static final String PROMPT_FOR_CATEGORY_BUDGET = "Enter category budget amount: ";
    // TITLE_* for titles of menu, tables, etc.
    public static final String TITLE_MENU = "Budget Menu";
    public static final String TITLE_NO_BUDGET_TABLE = "List of categories without budgets";
    public static final String TITLE_BUDGET_TABLE = "List of categories with budgets";
    // INFO_* for general information messages
    public static final String INFO_CATEGORY_NOT_AVAILABLE = "No Category available.";
    public static final String INFO_CREATION_SUCCESS = "Budget creation successful.";
    public static final String INFO_MODIFICATION_SUCCESS = "Budget modification successful.";
    public static final String INFO_DELETION_SUCCESS = "Budget deletion successful.";
    // WARNING_* for warning messages
    public static final String WARNING_INVALID_SELECTION = "Invalid selection.";
    public static final String WARNING_CREATION_FAILED = "Budget creation failed.";
    public static final String WARNING_MODIFICATION_FAILED = "Budget modification failed.";
    public static final String WARNING_DELETION_FAILED = "Budget deletion failed.";

    // Prevent instantiation
    private BudgetMessages() {
        throw new UnsupportedOperationException("Cannot instantiate this class.");
    }
}
