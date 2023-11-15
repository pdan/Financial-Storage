package org.sematec;

public class Account {
    private  String accountId;
    private  String ownerName;
    public  double accountBalance;
    Account(String accountId, String ownerName, double accountBalance) {
        this.accountId = accountId;
        this.ownerName = ownerName;
        this.accountBalance = accountBalance;
    }


}
