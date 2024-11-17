package model;

public class CategoryModel extends BaseModel {

    private int categoryBudget;

    public CategoryModel(int id, String name) {
        super(id, name);
        this.categoryBudget = -1; // Default value mean no budget set
    }

    public int getCategoryBudget() {
        return this.categoryBudget;
    }

    public void setCategoryBudget(int categoryBudget) {
        this.categoryBudget = categoryBudget;
    }
}