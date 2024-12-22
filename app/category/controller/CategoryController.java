package category.controller;

import base.BaseController;
import category.service.CategoryService;
import category.view.CategoryView;
import interfaces.MenuInterface;

import java.io.IOException;
import java.util.List;

public class CategoryController extends BaseController<CategoryView> {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        super(new CategoryView());
        this.categoryService = categoryService;
    }

    public void start() {
        readFromDatabase();
    }

    public void run() {
        enumMenuOptions selectedOption;
        do {
            selectedOption = view.promptForEnumMenuSelection(enumMenuOptions.class);
            switch (selectedOption) {
                case CREATE:
                    createCategory();
                    break;
                case MODIFY:
                    modifyCategory();
                    break;
                case VIEW:
                    viewCategories();
                    break;
                case DELETE:
                    deleteCategory();
                    break;
                case EXIT:
                    writeToDatabase();
                    break;
                default:
                    throw new IllegalStateException("Invalid value for selection.");
            }
        }
        while (selectedOption != enumMenuOptions.EXIT);
    }

    public void stop() {
        writeToDatabase();
    }

    private void readFromDatabase() {
        try {
            categoryService.loadFromDb();
        } catch (IOException e) {
            view.showDbReadError();
        }
    }

    private void writeToDatabase() {
        try {
            categoryService.storeToDb();
        } catch (IOException e) {
            view.showDbWriteError();
        }
    }

    private void createCategory() {
        String categoryName = getCategoryName();
        boolean success = categoryService.createCategory(categoryName);
        view.showCategoryCreationResult(success);
    }

    private void modifyCategory() {
        List<String> categories = categoryService.showCategories();
        int categoryId = view.promptForCategorySelection(categories);
        String categoryName = getCategoryName();
        boolean success = categoryService.updateCategory(categoryId, categoryName);
        view.showCategoryModificationResult(success);
    }

    private void viewCategories() {
        view.showCategories(categoryService.showCategories());
    }

    private void deleteCategory() {
        List<String> categories = categoryService.showCategories();
        int categoryId = view.promptForCategorySelection(categories);
        boolean success = categoryService.deleteCategory(categoryId);
        view.showCategoryDeletionResult(success);
    }

    private String getCategoryName() {
        boolean isCategoryNameValid = false;
        String categoryName = "";
        do {
            categoryName = view.promptForCategoryName();
            isCategoryNameValid = categoryService.isCategoryNameValid(categoryName);
            view.showCategoryNameValidResult(isCategoryNameValid);
        } while (!isCategoryNameValid);
        return categoryName;
    }

    private enum enumMenuOptions implements MenuInterface {
        CREATE("Create Category"),
        MODIFY("Modify Category"),
        VIEW("View Category"),
        DELETE("Delete Category"),
        EXIT("Exit");

        private final String text;

        enumMenuOptions(String text) {
            this.text = text;
        }

        @Override
        public String getText() {
            return text;
        }
    }
}
