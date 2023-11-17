package org.sematec;

import java.util.Date;

public class Customer {
    private int id;
    private String name;
    private String surname;
    private String address;
    private String zipCode;
    private String nationalId;
    private Date birthDate;

    public Customer(int id,String name,String surname,String address,String zipCode,String nationalId,Date birthDate) {
//        this.id = (int) Math.round(Math.random() * 100000);
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.zipCode = zipCode;
        this.nationalId = nationalId;
        this.birthDate = birthDate;
    }
}
