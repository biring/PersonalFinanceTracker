package view;

import controller.MenuOption;

import java.util.List;

import static view.messages.BudgetMessages.*;

public class BudgetView extends BaseClass {

    public BudgetView() {
        super();
    }

    public <T extends Enum<T> & MenuOption> T promptForEnumMenuSelection(Class<T> enumType) {
        return super.getEnumMenuSelection(enumType, TITLE_MENU, PROMPT_FOR_MENU_SELECTION);
    }

    public int promptForCategorySelection(List<String> categories) {
        return super.getTableSelection(categories, TITLE_NO_BUDGET_TABLE, PROMPT_FOR_SELECTION_BY_NO);
    }

    public int promptForCategoryBudget() {
        return super.promptForIntInput(PROMPT_FOR_CATEGORY_BUDGET);
    }

    public void showBudgets(List<String> budgets) {
        super.displayTableData(budgets, TITLE_BUDGET_TABLE);
    }

    public void showBudgetCreationResult(boolean success) {
        super.displaySuccessFailureMessage(success, INFO_CREATION_SUCCESS, WARNING_CREATION_FAILED );
    }

    public void showBudgetModificationResult(boolean success) {
        super.displaySuccessFailureMessage(success, INFO_MODIFICATION_SUCCESS, WARNING_MODIFICATION_FAILED);
    }

    public void showBudgetDeletionResult(boolean success) {
        super.displaySuccessFailureMessage(success, INFO_DELETION_SUCCESS, WARNING_DELETION_FAILED);
    }

}
