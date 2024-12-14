package transaction.model;

import base.BaseModel;
import java.util.Date;

public class TransactionModel extends BaseModel {
    private final Date date;
    private final double amount;
    private final int accountId;

    public TransactionModel(int id, Date date, String name, double amount, int accountId) {
        super(id, name);
        this.date = date;
        this.amount = amount;
        this.accountId = accountId;
    }

    public Date getDate() {
        return this.date;
    }

    public double getAmount() {
        return this.amount;
    }

    public int getAccountId() {
        return this.accountId;
    }

    @Override
    public String toString() {
        return "Transaction: " +
                "Id=" + super.id + ", " +
                "Date=" + this.date + ", " +
                "Name=" + this.name + ", " +
                "Amount=" + this.amount + ", " +
                "AccountId=" + this.accountId;
        }
}