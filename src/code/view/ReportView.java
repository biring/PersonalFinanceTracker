package view;

import controller.MenuOption;
import utility.Console;

import java.lang.reflect.Array;
import java.util.List;

import static messages.ReportMessages.*;

public class ReportView  extends BaseClass {

    private final int ACCOUNT_REPORT_COLUMN_1_WIDTH = 25;
    private final int ACCOUNT_REPORT_COLUMN_2_WIDTH = 10;
    private final int ACCOUNT_REPORT_COLUMN_3_WIDTH = 10;
    private final int ACCOUNT_REPORT_COLUMN_4_WIDTH = 10;
    private final int ACCOUNT_REPORT_COLUMN_5_WIDTH = 10;

    private final int BUDGET_REPORT_COLUMN_1_WIDTH = 25;
    private final int BUDGET_REPORT_COLUMN_2_WIDTH = 10;
    private final int BUDGET_REPORT_COLUMN_3_WIDTH = 10;
    private final int BUDGET_REPORT_COLUMN_4_WIDTH = 10;

    private final int ELLIPSIS_WIDTH = STRING_ELLIPSIS.length();

    public ReportView() {
        super();
    }

    public <T extends Enum<T> & MenuOption> T promptForEnumMenuSelection(Class<T> enumType) {
        return super.getEnumMenuSelection(enumType, TITLE_MENU, PROMPT_FOR_MENU);
    }

    public void showAccountReportTitle() {
        tableHelper.displayTableTitle(TITLE_ACCOUNT_REPORT);
        String ACCOUNT_REPORT_HEADER_FORMAT =
                "%-" + ACCOUNT_REPORT_COLUMN_1_WIDTH + "s" +
                "%-" + ACCOUNT_REPORT_COLUMN_2_WIDTH + "s" +
                "%-" + ACCOUNT_REPORT_COLUMN_3_WIDTH + "s" +
                "%-" + ACCOUNT_REPORT_COLUMN_4_WIDTH + "s" +
                "%-" + ACCOUNT_REPORT_COLUMN_5_WIDTH + "s";
        String msg = String.format(
                ACCOUNT_REPORT_HEADER_FORMAT,
                ACCOUNT_REPORT_COLUMN_1,
                ACCOUNT_REPORT_COLUMN_2,
                ACCOUNT_REPORT_COLUMN_3,
                ACCOUNT_REPORT_COLUMN_4,
                ACCOUNT_REPORT_COLUMN_5);
        Console.printMessage(msg);
    }

    public void showAccountReportData(List<Object> data) {
        String ACCOUNT_REPORT_FORMAT =
                "%-" + ACCOUNT_REPORT_COLUMN_1_WIDTH + "s" +
                        "%-" + ACCOUNT_REPORT_COLUMN_2_WIDTH + "s" +
                        "%-" + ACCOUNT_REPORT_COLUMN_3_WIDTH + ".0f" +
                        "%-" + ACCOUNT_REPORT_COLUMN_4_WIDTH + ".0f" +
                        "%-" + ACCOUNT_REPORT_COLUMN_5_WIDTH + ".0f";
        String name = data.get(0).toString();
        name = truncateString(name, ACCOUNT_REPORT_COLUMN_1_WIDTH);
        String type = data.get(1).toString();
        type = truncateString(type, ACCOUNT_REPORT_COLUMN_2_WIDTH);
        double credits = (double) data.get(2);
        double debits = (double) data.get(3);
        double net = (double) data.get(4);

        String msg = String.format(
                ACCOUNT_REPORT_FORMAT, name, type, credits, debits, net);
        Console.printMessage(msg);
    }

    public void showAccountReportSummary(List<Double> number) {
        String ACCOUNT_REPORT_FORMAT =
                "%-" + ACCOUNT_REPORT_COLUMN_1_WIDTH + "s" +
                        "%-" + ACCOUNT_REPORT_COLUMN_2_WIDTH + "s" +
                        "%-" + ACCOUNT_REPORT_COLUMN_3_WIDTH + ".0f" +
                        "%-" + ACCOUNT_REPORT_COLUMN_4_WIDTH + ".0f" +
                        "%-" + ACCOUNT_REPORT_COLUMN_5_WIDTH + ".0f";
        String msg = String.format(
                ACCOUNT_REPORT_FORMAT,STRING_TOTAL, STRING_DASH, number.get(0), number.get(1), number.get(2));
        Console.printMessage(msg);
    }

    public void showBudgetReportTitle() {
        tableHelper.displayTableTitle(TITLE_BUDGET_REPORT);
        String BUDGET_REPORT_HEADER_FORMAT =
                "%-" + BUDGET_REPORT_COLUMN_1_WIDTH + "s" +
                "%-" + BUDGET_REPORT_COLUMN_2_WIDTH + "s" +
                "%-" + BUDGET_REPORT_COLUMN_3_WIDTH + "s" +
                "%-" + BUDGET_REPORT_COLUMN_4_WIDTH + "s";
        String msg = String.format(
                BUDGET_REPORT_HEADER_FORMAT,
                BUDGET_REPORT_COLUMN_1,
                BUDGET_REPORT_COLUMN_2,
                BUDGET_REPORT_COLUMN_3,
                BUDGET_REPORT_COLUMN_4);
        Console.printMessage(msg);
    }

    public void showBudgetReportData(List<Object> data) {
        String BUDGET_REPORT_FORMAT =
                "%-" + BUDGET_REPORT_COLUMN_1_WIDTH + "s" +
                "%-" + BUDGET_REPORT_COLUMN_2_WIDTH + ".0f" +
                "%-" + BUDGET_REPORT_COLUMN_3_WIDTH + ".0f" +
                "%-" + BUDGET_REPORT_COLUMN_4_WIDTH + "s";
        String name = data.get(0).toString();
        name = truncateString(name, BUDGET_REPORT_COLUMN_1_WIDTH);
        double credits = (double) data.get(1);
        double debits = (double) data.get(2);
        String status = data.get(3).toString();
        status = truncateString(status, BUDGET_REPORT_COLUMN_4_WIDTH);
        String msg = String.format(
                BUDGET_REPORT_FORMAT, name, credits, debits, status);

        Console.printMessage(msg);
    }

    private String truncateString(String str, int width) {
        if (str.length() > width) {
            return str.substring(0, width - ELLIPSIS_WIDTH) + STRING_ELLIPSIS;
        }
        return str;
    }

}