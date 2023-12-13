package org.sematec;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Export {
    public static void Run(String fileLocation) {
        System.out.println(fileLocation);
    }

    public static void ExportToJSON() throws IOException {
        // Export Customers
        List<Customer> customers = new ArrayList<Customer>();
        try (Connection conn = Database.getConnection()) {
            PreparedStatement customersStatement = conn.prepareStatement("SELECT * from customers");
            ResultSet customerResultSet = customersStatement.executeQuery();
            while (customerResultSet.next()) {
                Customer newCustomer = new Customer(
                        customerResultSet.getInt("CUSTOMER_ID"),
                        customerResultSet.getString("CUSTOMER_NAME"),
                        customerResultSet.getString("CUSTOMER_SURNAME"),
                        customerResultSet.getString("CUSTOMER_ADDRESS"),
                        customerResultSet.getString("CUSTOMER_ZIP_CODE"),
                        customerResultSet.getString("CUSTOMER_NATIONAL_ID"),
                        customerResultSet.getDate("CUSTOMER_BIRTH_DATE").toLocalDate()
                );

                customers.add(newCustomer);
            }
            ObjectMapper mapper = new ObjectMapper();

            mapper.registerModule(new JavaTimeModule());
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            mapper.writeValue(new File("cusomers.json"), customers);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Export Account
        List<Account> accounts = new ArrayList<Account>();
        try (Connection conn = Database.getConnection()) {
            PreparedStatement customersStatement = conn.prepareStatement("SELECT * from accounts");
            ResultSet customerResultSet = customersStatement.executeQuery();
            while (customerResultSet.next()) {
//                String openDateString = customerResultSet.getDate("ACCOUNT_OPEN_DATE").toString();
//                String dateOnlyString = openDateString.substring(0, 10);
//                java.sql.Date accountOpenDate = new java.sql.Date(Date.parse(dateOnlyString));
                Account newAccount = new Account(
                        customerResultSet.getLong("ACCOUNT_NUMBER"),
                        "",
                        customerResultSet.getDouble("ACCOUNT_LIMIT"),
                        customerResultSet.getLong("ACCOUNT_CUSTOMER_ID"),
                        Account.AccountType.valueOf(customerResultSet.getString("ACCOUNT_TYPE")),
                        customerResultSet.getDouble("ACCOUNT_BALANCE")
                );

                accounts.add(newAccount);
            }
            ObjectMapper mapper = new ObjectMapper();

            mapper.registerModule(new JavaTimeModule());
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            mapper.writeValue(new File("accounts.json"), accounts);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void ExportToXML() {

    }
}
