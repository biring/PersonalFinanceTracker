package controller;

import service.BudgetService;
import view.BudgetView;

import java.util.List;

public class BudgetController extends BaseClass<BudgetView> {

    private final BudgetService budgetService = new BudgetService();

    public BudgetController() {
        super(new BudgetView());
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
                    System.out.println("Exit");
                    break;
                default:
                    throw new IllegalStateException("Invalid value for selection.");
            }
        }
        while (selectedOption != enumMenuOptions.EXIT);
    }

    private void createBudget() {
        List<String> categories = budgetService.getCategoriesWithoutBudgets();
        int categoryId = view.promptForCategorySelection(categories);
        String categoryName = budgetService.getCategoryName(categoryId);
        int categoryBudget = view.promptForCategoryBudget();
        boolean success = budgetService.setBudget(categoryName, categoryBudget);
        view.showBudgetCreationResult(success);
    }

    private void modifyBudget() {
        List<String> categories = budgetService.getCategoriesWithBudgets();
        int categoryId = view.promptForCategorySelection(categories);
        String categoryName = budgetService.getCategoryName(categoryId);
        int categoryBudget = view.promptForCategoryBudget();
        boolean success = budgetService.setBudget(categoryName, categoryBudget);
        view.showBudgetModificationResult(success);
    }

    private void viewBudgets() {
        view.showBudgets(budgetService.getCategoriesWithBudgets());
    }

    private void deleteBudget() {
        List<String> categories = budgetService.getCategoriesWithBudgets();
        int categoryId = view.promptForCategorySelection(categories);
        String categoryName = budgetService.getCategoryName(categoryId);
        boolean success = budgetService.resetBudget(categoryName);
        view.showBudgetDeletionResult(success);
    }


    private enum enumMenuOptions implements MenuOption {
        CREATE("Create Category"),
        MODIFY("Modify Category"),
        VIEW("View Category"),
        DELETE("Delete Category"),
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
