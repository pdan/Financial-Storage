package org.sematec;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter CSV file location: ");
        String fileLocation = scanner.nextLine();

        // Wrap the file reading in a try-with-resources to automatically close the Reader
        try (Reader reader = Files.newBufferedReader(Paths.get(fileLocation))) {
            List<Map<String, String>> dataList = readCsvFile(reader);

            // Print the data
            assert dataList != null;
            for (Map<String, String> data : dataList) {
                System.out.println(data);
            }
        } catch (IOException e) {
            // Handle the IOException appropriately
            e.printStackTrace();
        }
    }

    // Adjust the method to accept a Reader instead of a file location
    private static List<Map<String, String>> readCsvFile(Reader reader) {
        // Implement your CSV parsing logic here
        // Example: Use a library like OpenCSV or write custom logic to parse CSV
        // Return the list of maps containing the CSV data
        return null;
    }
}
