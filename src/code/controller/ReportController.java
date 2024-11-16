package controller;

import view.ReportView;

import java.util.Scanner;

public class ReportController extends BaseClass<ReportView> {

    public ReportController(Scanner scanner) {
        super(scanner, new ReportView(scanner));
    }

    @Override
    public void run() {
    }
}
