package budget.controller;

import base.BaseController;
import budget.service.BudgetService;
import budget.view.BudgetView;
import interfaces.MenuInterface;
import category.service.CategoryService;

import java.util.List;

public class BudgetController extends BaseController<BudgetView> {

    private final BudgetService budgetService;

    public BudgetController(CategoryService categoryService) {
        super(new BudgetView());
        this.budgetService = new BudgetService(categoryService);
    }

    public void start() {
    }


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
                    break;
                default:
                    throw new IllegalStateException("Invalid value for selection.");
            }
        }
        while (selectedOption != enumMenuOptions.EXIT);
    }

    public void stop() {
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


    private enum enumMenuOptions implements MenuInterface {
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
