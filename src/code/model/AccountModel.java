package model;

public class AccountModel extends BaseModel {
    private String name;
    private final AccountType accountType;

    public AccountModel(int id, String name, AccountType type) {
        super(id);
        this.name = name;
        this.accountType = type;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AccountType getAccountType() {
        return this.accountType;
    }

    @Override
    public String toString() {
        return "Account: Id=" + super.id + ", Name=" + this.name + ", Type=" + this.accountType;
    }
}
