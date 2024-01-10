package org.sematec;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.log4j.Logger;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.jdom2.Document;
import org.jdom2.Element;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class Export {
    final static Logger logger = Logger.getLogger(Import.class);

    public static void ExportToJSON() throws IOException {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CustomersData");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<Customer> customerQuery = em.createQuery("SELECT customer from Customer customer", Customer.class);
        List<Customer> customers = customerQuery.getResultList();

        TypedQuery<Account> accountQuery = em.createQuery("select account from Account account", Account.class);
        List<Account> accounts = accountQuery.getResultList();

        ObjectMapper mapper = new ObjectMapper();

        mapper.registerModule(new JavaTimeModule());
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.writeValue(new File("customers.json"), customers);
        mapper.writeValue(new File("accounts.json"), accounts);

        logger.info(" \"" + customers.size() + "\" customers exported to customers.json file");
        logger.info(" \"" + accounts.size() + "\" accounts exported to accounts.json file");

    }

    public static void ExportToXML() throws IOException {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CustomersData");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<Customer> customerQuery = em.createQuery("SELECT customer from Customer customer", Customer.class);
        List<Customer> customers = customerQuery.getResultList();

        TypedQuery<Account> accountQuery = em.createQuery("select account from Account account", Account.class);
        List<Account> accounts = accountQuery.getResultList();


        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.setFormat(Format.getPrettyFormat());
        Document customersDocument = new Document();
        Document accountsDocument = new Document();

        Element customersElement = new Element("customers");
        Element accountsElement = new Element("accounts");

        Iterator<Customer> customerResultSet = customers.iterator();

        while (customerResultSet.hasNext()) {
            Customer customer = customerResultSet.next();
            Element customerNode = new Element("customer");
            customerNode.setAttribute("id", String.valueOf(customer.getCustomerId()));

            Element nameElement = new Element("name");
            nameElement.setText(customer.getCustomerName());
            customerNode.addContent(nameElement);

            Element surnameElement = new Element("surname");
            surnameElement.setText(customer.getCustomerSurname());
            customerNode.addContent(surnameElement);

            Element addressElement = new Element("address");
            addressElement.setText(customer.getCustomerAddress());
            customerNode.addContent(addressElement);

            Element zipCodeElement = new Element("zipCode");
            zipCodeElement.setText(customer.getCustomerZipCode());
            customerNode.addContent(zipCodeElement);

            Element nationalIdElement = new Element("nationalId");
            nationalIdElement.setText(customer.getCustomerNationalId());
            customerNode.addContent(nationalIdElement);

            Element birthDateElement = new Element("birthDate");
            birthDateElement.setText(customer.getCustomerBirthDate().toString());
            customerNode.addContent(birthDateElement);

            customersElement.addContent(customerNode);
        }

        Iterator<Account> accountResultSet = accounts.iterator();

        while (accountResultSet.hasNext()) {
            Account account = accountResultSet.next();

            Element accountNode = new Element("accountNumber");
            accountNode.setAttribute("id", String.valueOf(account.getAccountNumber()));

            Element nameElement = new Element("accountOpenDate");
            nameElement.setText(account.getAccountOpenDate());
            accountNode.addContent(nameElement);

            Element surnameElement = new Element("accountLimit");
            surnameElement.setText(Double.toString(account.getAccountLimit()));
            accountNode.addContent(surnameElement);

            Element addressElement = new Element("accountCustomerId");
            addressElement.setText(Long.toString(account.getAccountCustomerId()));
            accountNode.addContent(addressElement);

            Element zipCodeElement = new Element("accountType");
            zipCodeElement.setText(account.getAccountType().toString());
            accountNode.addContent(zipCodeElement);

            Element nationalIdElement = new Element("accountBalance");
            nationalIdElement.setText(Double.toString(account.getAccountBalance()));

            accountsElement.addContent(accountNode);
        }

        customersDocument.setRootElement(customersElement);
        accountsDocument.setRootElement(accountsElement);

        xmlOutputter.output(customersDocument, new FileOutputStream("customers.xml"));

        xmlOutputter.output(accountsDocument, new FileOutputStream("accounts.xml"));

        logger.info(" \"" + customers.size() + "\" customers exported to customers.xml file");
        logger.info(" \"" + accounts.size() + "\" accounts exported to accounts.xml file");

    }
}
