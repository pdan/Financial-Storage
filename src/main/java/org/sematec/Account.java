package org.sematec;

import java.util.function.Predicate;

public class Account {
    private  String accountId;
    private  static String ownerName;
//    private int loyaltyRate;
    public  double accountBalance;
    Account(String accountId, String ownerName, double accountBalance) {
        this.accountId = accountId;
        this.ownerName = ownerName;
        this.accountBalance = accountBalance;
    }

    public static Predicate<Product> loyaltyRate() {
        return product -> product.getName().equals(ownerName);
    }


}
