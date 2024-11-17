package dao;

import model.CategoryModel;

import java.util.ArrayList;
import java.util.List;

public class CategoryDao {
    private final List<CategoryModel> catagories = new ArrayList<>();
    private int nextID = 0;

    // get the next available ID
    public int getNextID() {
        if (this.nextID == 0) {
            for (CategoryModel category : catagories) {
                if (this.nextID <= category.getID()) {
                    this.nextID = category.getID();
                }
            }
            this.nextID ++;
        }
        return this.nextID;
    }

    // Create a new category
    public boolean addCategory(int id, String name) {
        CategoryModel category = new CategoryModel(id, name);
        catagories.add(category);
        this.nextID ++;
        for (CategoryModel cat : catagories) {
            if (cat.getID() == id) {
                return true;
            }
        }
        return false;
    }

    // Update a category
    public boolean updateCategory(int id, String name) {
        for (CategoryModel category : catagories) {
            if (category.getID() == id) {
                category.setName(name);
                return true;
            }
        }
        return false;
    }

    // view all categories
    public List<CategoryModel> getCategories() {
        return catagories;
    }

    // delete a category
    public boolean deleteCategory(int id) {
        for (CategoryModel category : catagories) {
            if (category.getID() == id) {
                catagories.remove(category);
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
