package controller;

import service.ReportService;
import view.ReportView;

import java.util.ArrayList;
import java.util.List;

public class ReportController extends BaseClass<ReportView> {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        super(new ReportView());
        this.reportService = reportService;
    }

    public void start() {
    }

    public void run() {
        enumMenuOptions selectedOption;
        do {
            selectedOption = view.promptForEnumMenuSelection(enumMenuOptions.class);
            switch (selectedOption) {
                case ACCOUNT_REPORT:
                    showAccountReport();
                    break;
                case BUDGET_REPORT:
                    showBudgetReport();
                    break;
                case EXIT:
                    break;
                default:
                    throw new IllegalStateException("Invalid value for selection.");
            }
        }
        while (selectedOption != enumMenuOptions.EXIT);
    }

    public void stop() {
    }

    private void showAccountReport() {
        List<String> report = reportService.getAccountReport();
        view.showAccountReport(report);
    }

    private void showBudgetReport() {
        List<String> report = reportService.getBudgetReport();
        view.showBudgetReport(report);
    }

    private enum enumMenuOptions implements MenuOption {
        ACCOUNT_REPORT("Account report"),
        BUDGET_REPORT("Budget report"),
        EXIT("Exit");

        private final String text;

        enumMenuOptions(String text) {
            this.text = text;
        }

        @Override
        public String getText() {
            return text;
        }
    }
}
