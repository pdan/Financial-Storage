package org.sematec;

import java.sql.Array;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {


        Account firstAccount = new Account("A123004", "Hasan George", 100.000);
        Account secondAccount = new Account("A123005", "Parham Baghi", 2300.040);
        Account thirdAccount = new Account("A123006", "Afsaneh Samadi", 300.008);
        Account forthAccount = new Account("A123007", "Samaneh Ostad", 300.008);


        List<Account> account = Arrays.asList(firstAccount, secondAccount, thirdAccount, forthAccount);
        Map<Double, List<Account>> oo = account.stream().collect(Collectors.groupingBy(p -> p.accountBalance));

        oo.forEach((balance, p) -> System.out.format("%s , %s \n", balance));
    }
}