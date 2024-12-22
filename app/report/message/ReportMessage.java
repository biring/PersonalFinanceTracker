package report.message;

public class ReportMessage {

    // STRING CONSTANTS
    public static final String BUDGET_REPORT_COLUMN_1 = "[Category]";
    public static final String BUDGET_REPORT_COLUMN_2 = "[Budget]";
    public static final String BUDGET_REPORT_COLUMN_3 = "[Actual]";
    public static final String BUDGET_REPORT_COLUMN_4 = "[Status]";

    public static final String ACCOUNT_REPORT_COLUMN_1 = "[Account]";
    public static final String ACCOUNT_REPORT_COLUMN_2 = "[Type]";
    public static final String ACCOUNT_REPORT_COLUMN_3 = "[Credits]";
    public static final String ACCOUNT_REPORT_COLUMN_4 = "[Debits]";
    public static final String ACCOUNT_REPORT_COLUMN_5 = "[Net]";

    public static final String STRING_TOTAL = "Total";
    public static final String STRING_DASH = "-";
    public static final String STRING_ELLIPSIS = "...";




    // PROMPT_* for input prompts or messages requesting user action
    public static final String PROMPT_FOR_MENU = "Enter menu choice by number: ";
    // TITLE_* for titles of menu, tables, etc.
    public static final String TITLE_MENU = "Report Menu";
    public static final String TITLE_ACCOUNT_REPORT = "Account Report";
    public static final String TITLE_BUDGET_REPORT = "Budget Report";
    // INFO_* for general information messages
    // WARNING_* for warning messages
    // Prevent instantiation
    private ReportMessage() {
        throw new UnsupportedOperationException("Cannot instantiate this class.");
    }
}

