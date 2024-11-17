package service;

import dao.CategoryDao;
import model.CategoryModel;

import java.util.List;

public class BudgetService {

    private static CategoryDao categoryDao = null;

    public BudgetService() {
        categoryDao = CategoryService.getCategoryDaoInstance();

    }

    public List<String> getCategoriesWithoutBudgets() {
        return categoryDao.getCategories().stream()
                .filter(category -> category.getCategoryBudget() < 0)
                .map(CategoryModel::getName)
                .toList();
    }

    public List<String> getCategoriesWithBudgets() {
        return categoryDao.getCategories().stream()
                .filter(category -> category.getCategoryBudget() >= 0)
                .map(CategoryModel::getName)
                .toList();
    }

    public String getCategoryName(int categoryId) {
        String categoryName = "";

        for (CategoryModel category : categoryDao.getCategories()) {
            if (categoryId == category.getID()) {
                categoryName = category.getName();
                break;
            }
        }
        return categoryName;
    }


    public boolean isCategoryBudgetValid(String categoryName, int budget) {
        return (categoryName != null)
                && (!categoryName.isEmpty())
                && (budget >= 0)
                && (categoryDao.isCategoryNameExists(categoryName));
    }

    public boolean setBudget(String categoryName, int budget) {
        return categoryDao.updateCategoryBudget(categoryName, budget);
    }

    public boolean resetBudget(String categoryName) {
        return categoryDao.updateCategoryBudget(categoryName, -1);
    }
}

