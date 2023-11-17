package org.sematec;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String accountCSVFile = "./mock_data/account.csv";
        String customerCSVFile = "./mock_data/customer.csv";

        Map<String, Account> accounts = new HashMap<>();
        try (Reader reader = new FileReader(accountCSVFile);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/M/yyyy hh:mm:ss");

            for (CSVRecord csvRecord : csvParser.getRecords()) {
                Account account = new Account(
                        csvRecord.get("ACCOUNT_NUMBER"),
                        Integer.parseInt(csvRecord.get("ACCOUNT_CUSTOMER_ID")),
                        Double.parseDouble(csvRecord.get("ACCOUNT_LIMIT")),
                        dateFormat.parse(csvRecord.get("ACCOUNT_OPEN_DATE")),
                        Double.parseDouble(csvRecord.get("ACCOUNT_BALANCE")),
                        Account.accountType.valueOf(csvRecord.get("ACCOUNT_TYPE"))
                );
                accounts.put(csvRecord.get("ACCOUNT_NUMBER"), account);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Map<String, Customer> customers = new HashMap<>();
        try (Reader reader = new FileReader(customerCSVFile);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-dd");

            for (CSVRecord csvRecord : csvParser.getRecords()) {
                Customer customer = new Customer(
                        Integer.parseInt(csvRecord.get("CUSTOMER_ID")),
                        csvRecord.get("CUSTOMER_NAME"),
                        csvRecord.get("CUSTOMER_SURNAME"),
                        csvRecord.get("CUSTOMER_ADDRESS"),
                        csvRecord.get("CUSTOMER_ZIP_CODE"),
                        csvRecord.get("CUSTOMER_NATIONAL_ID"),
                        dateFormat.parse(csvRecord.get("CUSTOMER_BIRTH_DATE"))
                );
                customers.put(csvRecord.get("CUSTOMER_ID"), customer);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        System.out.println(accounts.size() +  " " +customers.size());

    }
}
