package model;

public class BaseModel {
    protected int id;
    protected String name;

    public BaseModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public final int getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}