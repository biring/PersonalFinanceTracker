package model;

public class CategoryModel extends BaseModel {

    private int categoryBudget;

    public static final int NO_BUDGET = 0;

    public CategoryModel(int id, String name) {
        super(id, name);
        this.categoryBudget = NO_BUDGET;
    }

    public int getCategoryBudget() {
        return this.categoryBudget;
    }

    public void setCategoryBudget(int categoryBudget) {
        this.categoryBudget = categoryBudget;
    }
}