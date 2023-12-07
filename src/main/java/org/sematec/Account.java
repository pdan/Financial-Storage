package org.sematec;

import java.time.LocalDateTime;
import java.util.Date;

public class Account {
    public enum AccountType {
        CREDIT,
        CHECKING,
        SAVING
    }
    public long accountNumber;
    public String accountOpenDate;
    public double accountLimit;
    public long accountCustomerId;
    public AccountType accountType;
    public double accountBalance;

    public Account(long accountNumber, String accountOpenDate, double accountLimit, long accountCustomerId, AccountType accountType,  double accountBalance) {
        this.accountNumber = accountNumber;
        this.accountOpenDate = accountOpenDate;
        this.accountLimit = accountLimit;
        this.accountCustomerId = accountCustomerId;
        this.accountType = accountType;
        this.accountBalance = accountBalance;
    }
}


