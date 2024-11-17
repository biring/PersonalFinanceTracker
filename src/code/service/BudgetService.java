package service;

import dao.CategoryDAO;

import java.util.List;

public class BudgetService {

    private CategoryDAO categoryDAO = null;

    public static final int NO_BUDGET = CategoryDAO.NO_BUDGET;

    public BudgetService(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    public List<String> showCategoriesWithoutBudgets() {
        return categoryDAO.readAll().stream()
                .filter(category -> category.getCategoryBudget() <= NO_BUDGET)
                .map(category -> "[" + category.getID() + "] " + category.getName() + " ($" + category.getCategoryBudget() + ")")
                .toList();
    }

    public List<String> showCategoriesWithBudgets() {
        return categoryDAO.readAll().stream()
                .filter(category -> category.getCategoryBudget() > NO_BUDGET)
                .map(category -> "[" + category.getID() + "] " + category.getName() + " ($" + category.getCategoryBudget() + ")")
                .toList();
    }

    public boolean isCategoryWithoutBudget(int id) {
        try {
            return (categoryDAO.read(id).getCategoryBudget() <= NO_BUDGET);
        } catch (NullPointerException e) {
            return false;
        }
    }

    public boolean setBudget(int id, int budget) {
        try {
            return categoryDAO.updateBudget(id, budget);
        } catch (NullPointerException e) {
            return false;
        }
    }

    public boolean resetBudget(int id) {
        try {
            return categoryDAO.updateBudget(id, NO_BUDGET);
        } catch (NullPointerException e) {
            return false;
        }
    }
}