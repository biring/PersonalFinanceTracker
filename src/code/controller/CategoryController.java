package controller;

import view.CategoryView;

import java.util.Scanner;

public class CategoryController extends BaseClass<CategoryView> {
    public CategoryController(Scanner scanner) {
        super(scanner, new CategoryView(scanner));
    }

    // Method to start the application flow
    public void run() {
    }
}
