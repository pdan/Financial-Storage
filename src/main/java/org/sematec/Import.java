package org.sematec;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.log4j.Logger;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class Import {
    final static Logger logger = Logger.getLogger(Import.class);

    public void fromCustomers(String source) throws IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CustomersData");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        File CSVFile = new File(source);
        try (Reader reader = new FileReader(CSVFile);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())) {
            for (CSVRecord csvRecord : csvParser) {

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-dd");
                LocalDate dateAndTime = LocalDate.parse(csvRecord.get("CUSTOMER_BIRTH_DATE"), formatter);

                Customer customer = new Customer();
                customer.setCustomerId(Long.parseLong(csvRecord.get("CUSTOMER_ID")));
                customer.setCustomerName(csvRecord.get("CUSTOMER_NAME"));
                customer.setCustomerSurname(csvRecord.get("CUSTOMER_SURNAME"));
                customer.setCustomerAddress(csvRecord.get("CUSTOMER_ADDRESS"));
                customer.setCustomerZipCode(csvRecord.get("CUSTOMER_ZIP_CODE"));
                customer.setCustomerNationalId(csvRecord.get("CUSTOMER_NATIONAL_ID"));
                customer.setCustomerBirthDate(dateAndTime);
                em.merge(customer);

                logger.info("Customer \"" + customer.getCustomerName() + " " + customer.getCustomerSurname() + "\" has been added");
            }
        } catch (IOException e) {

        }
        em.getTransaction().commit();
        emf.close();
        em.close();
    }

    public void fromAccounts(String source) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CustomersData");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        File CSVFile = new File(source);
        try (Reader reader = new FileReader(CSVFile);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())) {
            for (CSVRecord csvRecord : csvParser) {
                Account account = new Account();
                account.setAccountBalance(Double.parseDouble(csvRecord.get("ACCOUNT_BALANCE")));
                account.setAccountLimit(Double.parseDouble(csvRecord.get("ACCOUNT_LIMIT")));
                account.setAccountNumber(Long.parseLong(csvRecord.get("ACCOUNT_NUMBER")));
                account.setAccountType(Account.AccountType.valueOf(csvRecord.get("ACCOUNT_TYPE")));
                account.setAccountCustomerId(Long.parseLong(csvRecord.get("ACCOUNT_CUSTOMER_ID")));
                account.setAccountOpenDate(csvRecord.get("ACCOUNT_OPEN_DATE"));
                em.merge(account);

                logger.info("Account number \"" + account.getAccountNumber() + " with balance of " + account.getAccountBalance()  + "\" has been added");
            }
        } catch (IOException e) {

        }
        try {
            em.getTransaction().commit();
            emf.close();
            em.close();
        } catch (Exception e) {
            e.getCause();
        }
    }
}

