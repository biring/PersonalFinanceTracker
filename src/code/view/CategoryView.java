package view;

import controller.MenuOption;
import utility.Console;

import java.util.List;

import static view.messages.CategoryMessages.*;

public class CategoryView extends BaseClass {

    public CategoryView() {
        super();
    }

    public <T extends Enum<T> & MenuOption> T promptForEnumMenuSelection(Class<T> enumType) {
        return super.getEnumMenuSelection(enumType, TITLE_MENU, PROMPT_FOR_MENU_SELECTION);
    }

    public String promptForCategoryName() {
        return super.promptForStringInput(PROMPT_FOR_NAME);
    }

    public int promptForCategorySelection(List<String> categories) {
        return super.getTableSelection(categories, TITLE_TABLE, PROMPT_FOR_SELECTION_BY_NO);
    }

    public void showCategories(List<String> categories) {
        super.displayTableData(categories, TITLE_TABLE);
    }

    public void showCategoryNameValidResult(boolean success) {
        super.displaySuccessFailureMessage(success, INFO_NAME_VALID, WARNING_NAME_INVALID);
    }

    public void showCategoryCreationResult(boolean success) {
        super.displaySuccessFailureMessage(success, INFO_CREATION_SUCCESS, WARNING_CREATION_FAILED);
    }

    public void showCategoryModificationResult(boolean success) {
        super.displaySuccessFailureMessage(success, INFO_MODIFICATION_SUCCESS, WARNING_MODIFICATION_FAILED);
    }

    public void showCategoryDeletionResult(boolean success) {
        super.displaySuccessFailureMessage(success, INFO_DELETION_SUCCESS, WARNING_DELETION_FAILED);
    }

    public void showDbReadError() {
        Console.printMessage(WARNING_DB_READ_FAILED);
    }

    public void showDbWriteError() {
        Console.printMessage(WARNING_DB_WRITE_FAILED);
    }
}