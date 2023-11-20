package org.sematec;

import java.util.function.Predicate;

public class Product {

    private String name;

    public String getName() {
        return this.name;
    }


    public static Predicate<Product> loyaltyRate() {
        return product -> product.getName().equals("name");
    }
}
