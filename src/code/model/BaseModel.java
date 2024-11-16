package model;

public class BaseModel {
    protected int id;

    public BaseModel(int id) {
        this.id = id;
    }

    public final int getID() {
        return this.id;
    }
}