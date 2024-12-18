package account.model;

import base.BaseModel;

/**
 * Represents an account with an associated account type.
 * <p>
 * This class extends {@link base.BaseModel} and adds functionality
 * for holding an account type, providing access to it, and
 * generating a string representation of the account.
 * </p>
 */
public class AccountModel extends BaseModel {

    /**
     * The account type for the account based on {@link account.model.AccountType}
     * This field is final and cannot be modified.
     */
    private final AccountType accountType;
    protected final String toStringFormat = "Account: Id=%d, Name=%s, Type=%s";

    /**
     * Constructs an AccountModel instance with the specified ID, name, and account type.
     *
     * @param id   the unique identifier of the account
     * @param name the name associated with the account
     * @param type the type of the account, represented by an {@link AccountType}
     */
    public AccountModel(int id, String name, AccountType type) {
        super(id, name);
        this.accountType = type;
    }

    /**
     * Gets the type of the account.
     *
     * @return the account type, represented by an {@link AccountType}
     */
    public AccountType getAccountType() {
        return this.accountType;
    }

    /**
     * Returns a string representation of the account, including its ID, name, and type.
     *
     * @return a string in the format "Account: Id=<id>, Name=<name>, Type=<accountType>"
     */
    @Override
    public String toString() {
        return String.format(toStringFormat, super.id, super.name, this.accountType);
    }
}
