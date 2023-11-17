package org.sematec;

import java.util.Date;

public class Account {
    private final Account.accountType accountType;
    private String accountNumber;

    public enum accountType {
        SAVING,
        CHECKING,
        CREDIT
    };
    private int customerId;
    private double limit;
    private Date openDate;
    private double balance;

    public Account(String accountNumber, int customerId, double limit, Date openDate, double balance, accountType accountType) {
        this.accountNumber = accountNumber;
        this.customerId = customerId;
        this.limit = limit;
        this.openDate = openDate;
        this.balance = balance;
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

}
