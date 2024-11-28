package view;

import controller.MenuOption;

import java.util.List;

import static messages.ReportMessages.*;

public class ReportView  extends BaseClass {

    public ReportView() {
        super();
    }

    public <T extends Enum<T> & MenuOption> T promptForEnumMenuSelection(Class<T> enumType) {
        return super.getEnumMenuSelection(enumType, TITLE_MENU, PROMPT_FOR_MENU);
    }

    public void showAccountReport(List<String> report) {
        super.displayTableData(report, TITLE_ACCOUNT_REPORT);
    }

    public void showBudgetReport(List<String> report) {
        super.displayTableData(report, TITLE_BUDGET_REPORT);
    }
}