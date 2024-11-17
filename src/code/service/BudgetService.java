package service;

import dao.CategoryDAO;

import java.util.List;

public class BudgetService {

    private CategoryDAO categoryDAO = null;

    public BudgetService(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    public List<String> showCategoriesWithoutBudgets() {
        return categoryDAO.readAll().stream()
                .filter(category -> category.getCategoryBudget() <= 0)
                .map(category -> "[" + category.getID() + "] " + category.getName() + " ($" + category.getCategoryBudget() + ")")
                .toList();
    }

    public List<String> showCategoriesWithBudgets() {
        return categoryDAO.readAll().stream()
                .filter(category -> category.getCategoryBudget() > 0)
                .map(category -> "[" + category.getID() + "] " + category.getName() + " ($" + category.getCategoryBudget() + ")")
                .toList();
    }

    public boolean isCategoryBudgetValid(String categoryName, int budget) {
        return (categoryName != null)
                && (!categoryName.isEmpty())
                && (budget >= 0)
                && (categoryDAO.isCategoryNameExists(categoryName));
    }

    public boolean isCategoryWithoutBudget(int id) {
        try {
            return categoryDAO.read(id).getCategoryBudget() <= 0;
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
            return categoryDAO.updateBudget(id, 0);
        } catch (NullPointerException e) {
            return false;
        }
    }
}