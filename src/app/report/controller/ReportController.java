package report.controller;

import base.BaseController;
import interfaces.MenuInterface;
import report.service.ReportService;
import report.view.ReportView;

import java.util.List;

public class ReportController extends BaseController<ReportView> {

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
        view.showAccountReportTitle();
        List<List<Object>> lines = reportService.getAccountReportLines();
        for (List<Object> line : lines) {
            view.showAccountReportData(line);
        }
        view.showAccountReportSummary(reportService.getAccountReportSummary());
    }

    private void showBudgetReport() {
        view.showBudgetReportTitle();
        List<List<Object>> lines = reportService.getBudgetReportLines();
        for (List<Object> line : lines) {
            view.showBudgetReportData(line);
        }
    }

    private enum enumMenuOptions implements MenuInterface {
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
