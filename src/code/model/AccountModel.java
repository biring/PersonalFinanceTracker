package model;

public class AccountModel extends BaseModel {
    private String name;

    public AccountModel(int id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Account: Id=" + super.id + ", Name=" + this.name;
    }
}
