package dao;

import model.CategoryModel;

import java.util.List;

public class CategoryDAO extends BaseDAO<CategoryModel> {

    private final List<CategoryModel> catagories = super.items;

    public CategoryDAO(){
        super();
    }

    @Override
    protected int extractID(CategoryModel category) {
        return category.getID();
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
}
