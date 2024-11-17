package service;

import dao.CategoryDAO;

import java.util.List;

public class CategoryService {

    private final CategoryDAO categoryDAO;

    public CategoryService(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
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
        return categoryDAO.delete(id);
    }

    public boolean isCategoryNameValid(String categoryName) {
        return (categoryName != null)
                && (!categoryName.isEmpty())
                && (!categoryDAO.isCategoryNameExists(categoryName));
    }
}
