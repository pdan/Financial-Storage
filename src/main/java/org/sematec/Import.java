package org.sematec;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class Import {

    public static List<String> filesLocations = new ArrayList<String>();

    private static List<Map<String, String>> loadCsvFiles() {
        for (String location : filesLocations) {
            try (Reader reader = new FileReader(location);
                 CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())) {
                for (CSVRecord csvRecord : csvParser.getRecords()) {
                    System.out.println(csvRecord.get(1));
//            // Print the data
//            assert dataList != null;
//            for (Map<String, String> data : dataList) {
//                System.out.println(data);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
//            } catch (ParseException e) {
//                throw new RuntimeException(e);
//            }
        }
        return null;
    }

    public static void addFileLocation(String location) {
        filesLocations.add(location);
    }

//    public static Predicate<String> SetImportFiles() {
//        return x ->
//    }

    public static void Run() {
        loadCsvFiles();
    }

//    public static Map<String, Account> readAccounts() {
//    }
//
//    public static Map<String, Customer> readCustomers() {
//    }
}
