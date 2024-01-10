package org.sematec;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUSTOMER_ID")
    private long customerId;
    @Column(name = "CUSTOMER_NAME")
    private String customerName;
    @Column(name = "CUSTOMER_SURNAME")
    private String customerSurname;
    @Column(name = "CUSTOMER_ADDRESS")
    private String customerAddress;
    @Column(name = "CUSTOMER_ZIP_CODE")
    private String customerZipCode;
    @Column(name = "CUSTOMER_NATIONAL_ID")
    private String customerNationalId;
    @Column(name = "CUSTOMER_BIRTH_DATE")
    private LocalDate customerBirthDate;

    public long getCustomerId() {
        return customerId;
    }

    public Customer setCustomerId(long customerId) {
        this.customerId = customerId;
        return this;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Customer setCustomerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    public String getCustomerSurname() {
        return customerSurname;
    }

    public Customer setCustomerSurname(String customerSurname) {
        this.customerSurname = customerSurname;
        return this;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public Customer setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
        return this;
    }

    public String getCustomerZipCode() {
        return customerZipCode;
    }

    public Customer setCustomerZipCode(String customerZipCode) {
        this.customerZipCode = customerZipCode;
        return this;
    }

    public String getCustomerNationalId() {
        return customerNationalId;
    }

    public Customer setCustomerNationalId(String customerNationalId) {
        this.customerNationalId = customerNationalId;
        return this;
    }

    public LocalDate getCustomerBirthDate() {
        return customerBirthDate;
    }

    public Customer setCustomerBirthDate(LocalDate customerBirthDate) {
        this.customerBirthDate = customerBirthDate;
        return this;
    }
}
