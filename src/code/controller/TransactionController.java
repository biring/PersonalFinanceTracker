package controller;

import view.TransactionView;

import java.util.Scanner;

public class TransactionController extends BaseClass<TransactionView> {

    public TransactionController(Scanner scanner) {
        super(scanner, new TransactionView(scanner));
    }

    public void run() {

    }
}