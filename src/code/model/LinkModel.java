package model;

public class LinkModel extends BaseModel {

    private final int categoryId;

    public LinkModel(int id, String name, int categoryId) {
        super(id, name);
        this.categoryId = categoryId;
    }

    public int getCategoryId() {
        return this.categoryId;
    }

    @Override
    public String toString() {
        return "Link: " +
                "Id = " + this.id +
                ", Name = " + this.name +
                ", CategoryId = " + this.categoryId;
    }
}