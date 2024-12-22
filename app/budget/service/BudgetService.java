package budget.service;

import category.service.CategoryService;

import java.util.List;

public class BudgetService {

    private final CategoryService categoryService;

    public BudgetService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public List<String> showCategoriesWithoutBudgets() {
        return categoryService.showCategoriesWithoutBudgets();
    }

    public List<String> showCategoriesWithBudgets() {
        return categoryService.showCategoriesWithBudgets();
    }

    public boolean isCategoryWithoutBudget(int id) {
        return categoryService.isCategoryWithoutBudget(id);
    }

    public boolean setBudget(int id, int budget) {
        return categoryService.setBudget(id, budget);
    }

    public boolean resetBudget(int id) {
        return categoryService.resetBudget(id);
    }
}