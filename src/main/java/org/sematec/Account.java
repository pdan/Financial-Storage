package org.sematec;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "accounts")
public class Account {
    public enum AccountType {
        CREDIT,
        CHECKING,
        SAVING
    }
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNT_NUMBER")
    private long accountNumber;
    @Column(name = "ACCOUNT_OPEN_DATE")
    private String accountOpenDate;
    @Column(name = "ACCOUNT_LIMIT")
    private double accountLimit;
    @Column(name="ACCOUNT_CUSTOMER_ID")
    private long accountCustomerId;
    @Column(name="ACCOUNT_TYPE")
    private AccountType accountType;
    @Column(name="ACCOUNT_BALANCE")
    private double accountBalance;

    public long getAccountNumber() {
        return accountNumber;
    }

    public Account setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public String getAccountOpenDate() {
        return accountOpenDate;
    }

    public Account setAccountOpenDate(String accountOpenDate) {
        this.accountOpenDate = accountOpenDate;
        return this;
    }

    public double getAccountLimit() {
        return accountLimit;
    }

    public Account setAccountLimit(double accountLimit) {
        this.accountLimit = accountLimit;
        return this;
    }

    public long getAccountCustomerId() {
        return accountCustomerId;
    }

    public Account setAccountCustomerId(long accountCustomerId) {
        this.accountCustomerId = accountCustomerId;
        return this;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public Account setAccountType(AccountType accountType) {
        this.accountType = accountType;
        return this;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public Account setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
        return this;
    }
}


