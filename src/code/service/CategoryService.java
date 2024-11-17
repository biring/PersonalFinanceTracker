package service;

import dao.CategoryDao;
import model.BaseModel;

import java.util.List;

public class CategoryService {

    private final CategoryDao categoryDao = new CategoryDao();

    public CategoryService() {
    }

    public boolean createCategory(String name) {
        int id = categoryDao.getNextID();
        return categoryDao.addCategory(id, name);
    }

    public boolean updateCategory(int id, String name) {
        return categoryDao.updateCategory(id, name);
    }

    public List<String> getCategoriesForSelection() {
        return categoryDao.getCategories().stream()
                .map(BaseModel::getName)
                .toList();
    }

    public List<String> getAllCategoriesForDisplay() {
        return categoryDao.getCategories().stream()
                .map(category -> "[" + category.getID() + "] " + category.getName())
                .toList();
    }

    public boolean deleteCategory(int id) {
        return categoryDao.deleteCategory(id);
    }

    public boolean isCategoryNameValid(String categoryName) {
        return (categoryName != null)
                && (!categoryName.isEmpty())
                && (!categoryDao.isCategoryNameExists(categoryName));
    }
}
