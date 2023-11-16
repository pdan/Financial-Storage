package org.sematec;

import java.util.Date;

public class Account {
    private String recordNumber;
    private String accountNumber;
    public enum accountType {
        SAVING,
        CHECKING,
        CREDIT
    };
    private int accountCustomerId;
    private boolean accountLimit;
    private Date accountOpenDate;
    private long accountBalance;
}
