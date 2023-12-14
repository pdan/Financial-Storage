package org.sematec;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.jdom2.Document;
import org.jdom2.Element;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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

    public static  void ExportToXML() throws IOException {
        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.setFormat(Format.getPrettyFormat());
        Document customersDocument = new Document();
        Document accountsDocument = new Document();

        Element customersElement = new Element("customers");
        Element accountsElement = new Element("accounts");
        customersDocument.setRootElement(customersElement);
        accountsDocument.setRootElement(accountsElement);


        try (Connection conn = Database.getConnection()) {
            PreparedStatement customersStatement = conn.prepareStatement("SELECT * from customers");
            ResultSet customerResultSet = customersStatement.executeQuery();
            while (customerResultSet.next()) {

                Element customerNode = new Element("customer");
                customerNode.setAttribute("id", String.valueOf(customerResultSet.getInt("CUSTOMER_ID")));

                Element nameElement = new Element("name");
                nameElement.setText(customerResultSet.getString("CUSTOMER_NAME"));
                customerNode.addContent(nameElement);

                Element surnameElement = new Element("surname");
                surnameElement.setText(customerResultSet.getString("CUSTOMER_SURNAME"));
                customerNode.addContent(surnameElement);

                Element addressElement = new Element("address");
                addressElement.setText(customerResultSet.getString("CUSTOMER_ADDRESS"));
                customerNode.addContent(addressElement);

                Element zipCodeElement = new Element("zipCode");
                zipCodeElement.setText(customerResultSet.getString("CUSTOMER_ZIP_CODE"));
                customerNode.addContent(zipCodeElement);

                Element nationalIdElement = new Element("nationalId");
                nationalIdElement.setText(customerResultSet.getString("CUSTOMER_NATIONAL_ID"));
                customerNode.addContent(nationalIdElement);

                Element birthDateElement = new Element("birthDate");
                birthDateElement.setText(customerResultSet.getDate("CUSTOMER_BIRTH_DATE").toString());
                customerNode.addContent(birthDateElement);

                customersElement.addContent(customerNode);
            }

            xmlOutputter.output(customersDocument, new FileOutputStream("customers.xml"));

            PreparedStatement accountsStatement = conn.prepareStatement("SELECT * from accounts");
            ResultSet accountResultSet = accountsStatement.executeQuery();

            while (accountResultSet.next()) {

                Element accountNode = new Element("accountNumber");
                accountNode.setAttribute("id", String.valueOf(accountResultSet.getLong("ACCOUNT_NUMBER")));

                Element nameElement = new Element("accountOpenDate");
                nameElement.setText(accountResultSet.getString("ACCOUNT_OPEN_DATE"));
                accountNode.addContent(nameElement);

                Element surnameElement = new Element("accountLimit");
                surnameElement.setText(accountResultSet.getString("ACCOUNT_LIMIT"));
                accountNode.addContent(surnameElement);

                Element addressElement = new Element("accountCustomerId");
                addressElement.setText(accountResultSet.getString("ACCOUNT_CUSTOMER_ID"));
                accountNode.addContent(addressElement);

                Element zipCodeElement = new Element("accountType");
                zipCodeElement.setText(accountResultSet.getString("ACCOUNT_TYPE"));
                accountNode.addContent(zipCodeElement);

                Element nationalIdElement = new Element("accountBalance");
                nationalIdElement.setText(accountResultSet.getString("ACCOUNT_BALANCE"));

                accountsElement.addContent(accountNode);
            }

            xmlOutputter.output(customersDocument, new FileOutputStream("accounts.xml"));




        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
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

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
