package org.sematec;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Import {

    public void fromCustomers(String source) {
        File CSVFile = new File(source);
        try (Reader reader = new FileReader(CSVFile);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())) {
//            String insertQuery = "INSERT INTO customers (RECORD_NUMBER, ACCOUNT_OPEN_DATE, ACCOUNT_LIMIT, ACCOUNT_CUSTOMER_ID, ACCOUNT_TYPE, ACCOUNT_NUMBER) VALUES (?,?,?,?,?,?)";
            String insertQuery = "INSERT INTO customers (CUSTOMER_ID, CUSTOMER_NAME, CUSTOMER_SURNAME, CUSTOMER_ADDRESS, CUSTOMER_ZIP_CODE, CUSTOMER_NATIONAL_ID, CUSTOMER_BIRTH_DATE) VALUES (?,?,?,?,?,?,?)";
            try (Connection connection = Database.getConnection()) {
                connection.setAutoCommit(false);
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

                    for (CSVRecord csvRecord : csvParser.getRecords()) {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-dd");
                        LocalDate dateAndTime =  LocalDate.parse(csvRecord.get("CUSTOMER_BIRTH_DATE"), formatter);

                        Customer newCustomer = new Customer(
                                Long.parseLong(csvRecord.get("CUSTOMER_ID")),
                                csvRecord.get("CUSTOMER_NAME"),
                                csvRecord.get("CUSTOMER_SURNAME"),
                                csvRecord.get("CUSTOMER_ADDRESS"),
                                csvRecord.get("CUSTOMER_ZIP_CODE"),
                                csvRecord.get("CUSTOMER_NATIONAL_ID"),
                                dateAndTime
                        );

                        preparedStatement.setLong(1, newCustomer.customerId);
                        preparedStatement.setString(2, newCustomer.customerName);
                        preparedStatement.setString(3, newCustomer.customerSurname);
                        preparedStatement.setString(4, newCustomer.customerAddress);
                        preparedStatement.setString(5, newCustomer.customerZipCode);
                        preparedStatement.setString(6, newCustomer.customerNationalId);
                        preparedStatement.setDate(7, Date.valueOf(newCustomer.customerBirthDate));


                        preparedStatement.addBatch();


                    }

                    preparedStatement.executeBatch();
                    connection.commit();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fromAccounts(String source) {
        File CSVFile = new File(source);
        try (Reader reader = new FileReader(CSVFile);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())) {
            String insertQuery = "INSERT INTO accounts (ACCOUNT_NUMBER, ACCOUNT_OPEN_DATE, ACCOUNT_LIMIT, ACCOUNT_CUSTOMER_ID, ACCOUNT_TYPE, ACCOUNT_BALANCE) VALUES (?,?,?,?,?,?)";
            try (Connection connection = Database.getConnection()) {
                connection.setAutoCommit(false);
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

                    for (CSVRecord csvRecord : csvParser.getRecords()) {

                        Account newAccount = new Account(
                                Long.parseLong(csvRecord.get("ACCOUNT_NUMBER")),
                                csvRecord.get("ACCOUNT_OPEN_DATE"),
                                Double.parseDouble(csvRecord.get("ACCOUNT_LIMIT")),
                                Long.parseLong(csvRecord.get("ACCOUNT_CUSTOMER_ID")),
                                Account.AccountType.valueOf(csvRecord.get("ACCOUNT_TYPE")),
                                Double.parseDouble(csvRecord.get("ACCOUNT_BALANCE"))
                        );

                        preparedStatement.setLong(1, newAccount.accountNumber);
                        preparedStatement.setString(2, newAccount.accountOpenDate);
                        preparedStatement.setDouble(3, newAccount.accountLimit);
                        preparedStatement.setLong(4, newAccount.accountCustomerId);
                        preparedStatement.setString(5, newAccount.accountType.toString());
                        preparedStatement.setDouble(6, newAccount.accountBalance);


                        preparedStatement.executeUpdate();
                        connection.commit();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
