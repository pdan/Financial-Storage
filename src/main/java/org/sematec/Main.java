package org.sematec;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import javax.sql.DataSource;
import java.nio.file.Files;
//import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {

        // customer-data -f account.csv -f customer.csv -o output/
        // customer-data export ./output_directory
        // args[0] = accounts.csv
        // args[1] = customers.csv
//        System.out.println(Paths.get("./mock_data"));

        if (args.length < 2 ) {
            System.out.println("Usage: customer-data import -c <Customer CSV file> -a <Account CSV file> ...");
            System.out.println("       customer-data export <Out directory>");
            System.exit(1);
        }

        Import Importer = new Import();
        switch (args[0]) {
            case "import":
                for (int i=1; i < 4; i=i+2) {
                    if (args[i].equals("-c")) {
                        Importer.fromCustomers(args[i+1]);
                    } else if(args[i].equals("-a")) {
                        Importer.fromAccounts(args[i+1]);
                    }
                }

            case "export":
                // Read customer from database
                Export.ExportToJSON();
                Export.ExportToXML();
//                Customer.export();
//                Account.export();
                break;
            default:
                System.out.println("Invalid command: " + args[0]);
                System.exit(1);
        }

//                System.out.println(args[1] + ": " + args[0]);
                // Start to load
//                Map<String, Account> accounts = Import.readAccounts();
//                Map<String, Customer> customers = Import.readCustomers();




        }

//        try {
//            DataSource data = Database.getDatasource();
//            Connection conn = data.getConnection();
//            conn.prepareStatement()
//        } catch (SQLException e) {
//
//        }
//        String accountCSVFile = "./mock_data/account.csv";
//        String customerCSVFile = "./mock_data/customer.csv";
//
//        Map<String, Account> accounts = new HashMap<>();
//        try (Reader reader = new FileReader(accountCSVFile);
//             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())) {
//            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/M/yyyy hh:mm:ss");
//
//            for (CSVRecord csvRecord : csvParser.getRecords()) {
//                Account account = new Account(
//                        csvRecord.get("ACCOUNT_NUMBER"),
//                        Integer.parseInt(csvRecord.get("ACCOUNT_CUSTOMER_ID")),
//                        Double.parseDouble(csvRecord.get("ACCOUNT_LIMIT")),
//                        dateFormat.parse(csvRecord.get("ACCOUNT_OPEN_DATE")),
//                        Double.parseDouble(csvRecord.get("ACCOUNT_BALANCE")),
//                        Account.accountType.valueOf(csvRecord.get("ACCOUNT_TYPE"))
//                );
//                accounts.put(csvRecord.get("ACCOUNT_NUMBER"), account);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }
//
//        Map<String, Customer> customers = new HashMap<>();
//        try (Reader reader = new FileReader(customerCSVFile);
//             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())) {
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-dd");
//
//            for (CSVRecord csvRecord : csvParser.getRecords()) {
//                Customer customer = new Customer(
//                        Integer.parseInt(csvRecord.get("CUSTOMER_ID")),
//                        csvRecord.get("CUSTOMER_NAME"),
//                        csvRecord.get("CUSTOMER_SURNAME"),
//                        csvRecord.get("CUSTOMER_ADDRESS"),
//                        csvRecord.get("CUSTOMER_ZIP_CODE"),
//                        csvRecord.get("CUSTOMER_NATIONAL_ID"),
//                        dateFormat.parse(csvRecord.get("CUSTOMER_BIRTH_DATE"))
//                );
//                customers.put(csvRecord.get("CUSTOMER_ID"), customer);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }
//
//
//        System.out.println(accounts.size() +  " " +customers.size());

//    }
}
