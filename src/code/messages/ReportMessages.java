package messages;

public class ReportMessages {

    // PROMPT_* for input prompts or messages requesting user action
    public static final String PROMPT_FOR_MENU = "Enter menu choice by number: ";
    // TITLE_* for titles of menu, tables, etc.
    public static final String TITLE_MENU = "Report Menu";
    public static final String TITLE_ACCOUNT_REPORT = "Account Report";
    public static final String TITLE_BUDGET_REPORT = "Budget Report";
    // INFO_* for general information messages
    // WARNING_* for warning messages
    // Prevent instantiation
    private ReportMessages() {
        throw new UnsupportedOperationException("Cannot instantiate this class.");
    }
}

