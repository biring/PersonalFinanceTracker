package category.model;

import base.BaseModel;

/**
 * This is a model class that represents a category for budget tracking.
 * <p>
 * This class extends {@link base.BaseModel} and adds functionality
 * for holding category budget amount, providing access to it, and
 * generating a string representation of the account.
 * </p>
 */
public class CategoryModel extends BaseModel {

    /**
     * Represents a constant value to indicate no budget allocated.
     * The value of 0 signifies that there is no budget available.
     */
    public static final int NO_BUDGET = 0;
    /**
     * A string format template used for generating the string representation of a category.
     * <p>
     * The format consists of placeholders that will be replaced with actual values when generating the string.
     * The placeholders are as follows:
     * - %d: Representing the category ID as an integer value.
     * - %s: Representing the category name as a string value.
     * - %d: Representing the category budget as an integer value.
     * </p>
     */
    protected final String toStringFormat = "Category: Id=%d, Name=%s, Budget=%d";
    /**
     * Represents the budget allocated for a specific category.
     * This value indicates the amount of funds available for the category.
     */
    private int categoryBudget;

    /**
     * Constructs a new CategoryModel with the specified ID and name.
     *
     * @param id   the unique identifier for the category
     * @param name the name associated with the category
     */
    public CategoryModel(int id, String name) {
        super(id, name);
        this.categoryBudget = NO_BUDGET;
    }

    /**
     * Retrieves the budget allocated for the specific category.
     *
     * @return the budget amount allocated for the category
     */
    public int getCategoryBudget() {
        return this.categoryBudget;
    }

    /**
     * Sets the budget for the category.
     *
     * @param categoryBudget the budget amount to be set for the category
     */
    public void setCategoryBudget(int categoryBudget) {
        this.categoryBudget = categoryBudget;
    }

    /**
     * Returns a string representation of the CategoryModel instance.
     * The format of the string includes the category ID, name, and budget.
     *
     * @return a formatted string representing the category with placeholders replaced by actual values
     */
    @Override
    public String toString() {
        return String.format(toStringFormat, getID(), getName(), getCategoryBudget());
    }
}