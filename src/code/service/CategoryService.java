package service;

import dao.CategoryDAO;
import dao.LinkDAO;

import java.io.IOException;
import java.util.List;

public class CategoryService {

    public static final int NO_BUDGET = CategoryDAO.NO_BUDGET;

    private final CategoryDAO categoryDAO;
    private final LinkDAO linkDAO;

    public CategoryService(CategoryDAO categoryDAO, LinkDAO linkDAO) {
        this.categoryDAO = categoryDAO;
        this.linkDAO = linkDAO;
    }

    public boolean createCategory(String name) {
        int id = categoryDAO.getNextID();
        return categoryDAO.create(id, name);
    }

    public boolean updateCategory(int id, String name) {
        return categoryDAO.updateName(id, name);
    }

    public List<String> showCategories() {
        return categoryDAO.readAll().stream()
                .map(category -> "[" + category.getID() + "] " + category.getName())
                .toList();
    }

    public boolean deleteCategory(int id) {
        boolean result = categoryDAO.delete(id);
        result &= linkDAO.deleteLinksByCategoryId(id);
        return result;
    }

    // Get category name using ID
    protected String getCategoryNameById(int id) {
        return categoryDAO.getNameById(id);
    }

    public boolean isCategoryExists(int id) {
        return categoryDAO.exists(id);
    }


    public boolean isCategoryNameValid(String categoryName) {
        return (categoryName != null)
                && (!categoryName.isEmpty())
                && (!categoryDAO.isCategoryNameExists(categoryName));
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

    public void loadFromDb() throws IOException {
        categoryDAO.deserialize();
    }

    public void storeToDb() throws IOException {
        categoryDAO.serialize();
    }
}
