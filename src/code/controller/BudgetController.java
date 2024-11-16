package controller;

import view.BudgetView;

import java.util.Scanner;

public class BudgetController extends BaseClass<BudgetView> {

    public BudgetController(Scanner scanner) {
        super(scanner, new BudgetView(scanner));
    }

    public void run() {
    }
}
