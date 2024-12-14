package category.dao;

import base.BaseDAO;
import category.model.CategoryModel;

import java.util.ArrayList;
import java.util.List;

public class CategoryDAO extends BaseDAO<CategoryModel> {

    private final List<CategoryModel> catagories = super.items;

    public static final int NO_BUDGET = CategoryModel.NO_BUDGET;

    public CategoryDAO(){
        super(CategoryModel.class);
    }

    @Override
    protected int extractID(CategoryModel category) {
        return category.getID();
    }

    public List<Integer> getIDs() {
        List<Integer> ids = new ArrayList<>();
        for (CategoryModel category : catagories) {
            ids.add(category.getID());
        }
        return ids;
    }

    // Create a new category
    public boolean create(int id, String name) {
        CategoryModel category = new CategoryModel(id, name);
        return createItem(category);
    }

    // Update a category
    public boolean updateName(int id, String name) {
        for (CategoryModel category : catagories) {
            if (category.getID() == id) {
                category.setName(name);
                return true;
            }
        }
        return false;
    }

    public boolean updateBudget(int id, int budgetAmount) {
        for (CategoryModel category : catagories) {
            if (category.getID() == id) {
                category.setCategoryBudget(budgetAmount);
                return true;
            }
        }
        return false;
    }

    // Check if a category exists
    public boolean isCategoryNameExists(String name) {
        for (CategoryModel category : catagories) {
            if (category.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    // Get category budget by ID
    public int getBudgetById(int id) {
        try {
            return catagories.stream()
                    .filter(category -> category.getID() == id)
                    .findFirst()
                    .map(CategoryModel::getCategoryBudget)
                    .orElseThrow(() -> new IndexOutOfBoundsException("Category ID not found"));
        } catch (Exception e) {
            throw new IndexOutOfBoundsException("Category ID not found");
        }
    }
}
