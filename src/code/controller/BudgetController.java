package controller;

import dao.CategoryDAO;
import service.BudgetService;
import view.BudgetView;

import java.util.List;

public class BudgetController extends BaseClass<BudgetView> {

    private final BudgetService budgetService;

    public BudgetController(CategoryDAO categoryDAO) {
        super(new BudgetView());
        this.budgetService = new BudgetService(categoryDAO);
    }

    // Method to start the application flow


    public void run() {
        enumMenuOptions selectedOption;
        do {
            selectedOption = view.promptForEnumMenuSelection(enumMenuOptions.class);
            switch (selectedOption) {
                case CREATE:
                    createBudget();
                    break;
                case MODIFY:
                    modifyBudget();
                    break;
                case VIEW:
                    viewBudgets();
                    break;
                case DELETE:
                    deleteBudget();
                    break;
                case EXIT:
                    System.out.println("Exit");
                    break;
                default:
                    throw new IllegalStateException("Invalid value for selection.");
            }
        }
        while (selectedOption != enumMenuOptions.EXIT);
    }

    private void createBudget() {
        if (budgetService.showCategoriesWithoutBudgets().isEmpty()) {
            view.showNoCategoriesAvailable();
        }
        else {
            int categoryId = getCategoryWithoutBudgets();
            int categoryBudget = view.promptForCategoryBudget();
            boolean success = budgetService.setBudget(categoryId, categoryBudget);
            view.showBudgetCreationResult(success);
        }
    }

    private void modifyBudget() {
        if (budgetService.showCategoriesWithBudgets().isEmpty()) {
            view.showNoCategoriesAvailable();
        } else {
            int categoryId = getCategoryWithBudgets();
            int categoryBudget = view.promptForCategoryBudget();
            boolean success = budgetService.setBudget(categoryId, categoryBudget);
            view.showBudgetModificationResult(success);
        }
    }

    private void viewBudgets() {
        if (budgetService.showCategoriesWithBudgets().isEmpty()) {
            view.showNoCategoriesAvailable();
        } else {
            view.showBudgets(budgetService.showCategoriesWithBudgets());
        }
    }

    private void deleteBudget() {
        if (budgetService.showCategoriesWithBudgets().isEmpty()) {
            view.showNoCategoriesAvailable();
        } else {
            int categoryId = getCategoryWithBudgets();
            boolean success = budgetService.resetBudget(categoryId);
            view.showBudgetDeletionResult(success);
        }
    }

    private int getCategoryWithoutBudgets() {
        List<String> categories = budgetService.showCategoriesWithoutBudgets();
        boolean isCategoryWithoutBudget;
        int categoryId;
        do {
            categoryId = view.promptForCategorySelection(categories);
            isCategoryWithoutBudget = budgetService.isCategoryWithoutBudget(categoryId);
            view.showCategoriesSelectionResult(isCategoryWithoutBudget);
        } while (!isCategoryWithoutBudget);
        return categoryId;
    }

    private int getCategoryWithBudgets() {
        List<String> categories = budgetService.showCategoriesWithBudgets();
        boolean isCategoryWithBudget;
        int categoryId;
        do {
            categoryId = view.promptForCategorySelection(categories);
            isCategoryWithBudget = !budgetService.isCategoryWithoutBudget(categoryId);
            view.showCategoriesSelectionResult(isCategoryWithBudget);
        } while (!isCategoryWithBudget);
        return categoryId;
    }


    private enum enumMenuOptions implements MenuOption {
        CREATE("Create Budget"),
        MODIFY("Modify Budget"),
        VIEW("View Budget"),
        DELETE("Delete Budget"),
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
