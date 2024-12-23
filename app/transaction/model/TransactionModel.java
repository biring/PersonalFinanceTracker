package transaction.model;

import base.BaseModel;
import common.DateUtils;

import java.util.Date;

/**
 * This is a model class that represents a transaction including transaction date, amount and associated account id.
 * <p>
 * This class extends {@link base.BaseModel} and adds functionality
 * for holding details of a transaction, providing access to them, and
 * generating a string representation of the transaction.
 * </p>
 */
public class TransactionModel extends BaseModel {
    /**
     * This variable stores the format template for generating a descriptive string representation
     * of a TransactionModel instance. The format includes placeholders for the ID, Date, Name, Amount, and AccountId.
     * The placeholders are:
     * - %d for the ID (integer)
     * - %s for the Date (string)
     * - %s for the name (string)
     * - %f for the amount (double)
     * - %d for the AccountId (integer)
     */
    protected final String toStringFormat = "Transaction: Id=%d, Date=%s, Name=%s, Amount=%f, AccountId=%d";

    /**
     * Represents the date associated with a transaction model.
     * This field is immutable and accessible only within the same package.
     */
    private final Date date;

    /**
     * Represents the amount associated with a transaction model.
     * This field is immutable and accessible only within the same package.
     */
    private final double amount;

    /**
     * Represents the account ID associated with a transaction model.
     * This field is immutable and accessible only within the same package.
     */
    private final int accountId;

    /**
     * Constructs a new TransactionModel with the specified ID, date, name, amount, and account ID.
     *
     * @param id        the unique identifier for the Transaction
     * @param date      the date associated with the Transaction
     * @param name      the name associated with the Transaction
     * @param amount    the amount exchanged in the Transaction
     * @param accountId the account ID associated with the Transaction
     */
    public TransactionModel(int id, Date date, String name, double amount, int accountId) {
        super(id, name);
        this.date = date;
        this.amount = amount;
        this.accountId = accountId;
    }

    /**
     * Retrieves the date associated with the TransactionModel instance.
     *
     * @return the date of the TransactionModel
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * Retrieves the amount associated with the TransactionModel instance.
     *
     * @return the amount of the TransactionModel
     */
    public double getAmount() {
        return this.amount;
    }

    /**
     * Retrieves the account ID associated with the TransactionModel instance.
     *
     * @return the account ID of the TransactionModel
     */
    public int getAccountId() {
        return this.accountId;
    }

    /**
     * Returns a string representation of the TransactionModel instance.
     * The format includes placeholders for the ID, Date, Name, Amount, and AccountId.
     *
     * @return a formatted string representing the TransactionModel instance
     */
    @Override
    public String toString() {
        String dateAsString = DateUtils.formatDateObjectToString(getDate());
        return String.format(toStringFormat, getID(), dateAsString, getName(), getAmount(), getAccountId());
    }
}