package org.sematec;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Customer {
     long customerId;
     String customerName;
     String customerSurname;
     String customerAddress;
     String customerZipCode;
     String customerNationalId;
     LocalDate customerBirthDate;

    public Customer(long customerId, String customerName, String customerSurname, String customerAddress, String customerZipCode, String customerNationalId, LocalDate customerBirthDate) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerSurname = customerSurname;
        this.customerAddress = customerAddress;
        this.customerZipCode = customerZipCode;
        this.customerNationalId = customerNationalId;
        this.customerBirthDate = customerBirthDate;
    }
}
