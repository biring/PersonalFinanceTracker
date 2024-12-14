package account.model;

import base.BaseModel;

public class AccountModel extends BaseModel {

    private final AccountType accountType;

    public AccountModel(int id, String name, AccountType type) {
        super(id, name);
        this.accountType = type;
    }

    public AccountType getAccountType() {
        return this.accountType;
    }

    @Override
    public String toString() {
        return "Account: Id=" + super.id + ", Name=" + this.name + ", Type=" + this.accountType;
    }
}
