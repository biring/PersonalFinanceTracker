package model;

public class AccountModel extends BaseModel {
    private String name;

    public AccountModel(int id, String name) {
        super(id);
        this.name = name;
        System.out.println(this);
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "Account: Id=" + super.id + ", Name=" + this.name;
    }
}
