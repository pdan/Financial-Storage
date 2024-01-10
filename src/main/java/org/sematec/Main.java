package org.sematec;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        if (args.length < 2) {
            System.out.println("Usage: customer-data import -c <Customer CSV file> -a <Account CSV file> ...");
            System.out.println("       customer-data export <Out directory>");
            System.exit(1);
        }

        Import Importer = new Import();
        switch (args[0]) {
            case "import":
                for (int i = 1; i < 4; i = i + 2) {
                    if (args[i].equals("-c")) {
                        Importer.fromCustomers(args[i + 1]);
                    } else if (args[i].equals("-a")) {
                        Importer.fromAccounts(args[i + 1]);
                    }
                }
                break;
            case "export":
                Export.ExportToJSON();
                Export.ExportToXML();
                break;
            default:
                System.out.println("Invalid command: " + args[0]);
                System.exit(1);
        }
    }
}
