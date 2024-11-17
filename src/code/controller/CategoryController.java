package controller;

import service.CategoryService;
import view.CategoryView;

public class CategoryController extends BaseClass<CategoryView> {

    private final CategoryService categoryService = new CategoryService();

    public CategoryController() {
        super(new CategoryView());
    }

    // Method to start the application flow
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
                    System.out.println("Exit");
                    break;
                default:
                    throw new IllegalStateException("Invalid value for selection.");
            }
        }
        while (selectedOption != enumMenuOptions.EXIT);
    }

    private void createCategory() {
        String categoryName = getCategoryName();
        boolean success = categoryService.createCategory(categoryName);
        view.showCategoryCreationResult(success);
    }

    private void modifyCategory() {
        int categoryId = view.promptForCategorySelection(categoryService.getCategoriesForSelection());
        String categoryName = getCategoryName();
        boolean success = categoryService.updateCategory(categoryId, categoryName);
        view.showCategoryModificationResult(success);
    }

    private void viewCategories() {
        view.showCategories(categoryService.getAllCategoriesForDisplay());
    }

    private void deleteCategory() {
        int categoryId = view.promptForCategorySelection(categoryService.getCategoriesForSelection());
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

    private enum enumMenuOptions implements MenuOption {
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
