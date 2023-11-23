package org.sematec;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class Main {
    static final String JDBC_DRIVER = "org.sqlite.JDBC";

    public static void main(String[] args) {

        ObjectMapper objectMapper = new ObjectMapper();
//        Staff staff = new Staff();
//        staff.setName("modernISC");
//        staff.setAge(38);
//        staff.setPosition(new String[]{"Founder", "CTO", "Writer"});
//        Map<String, BigDecimal> salary = new HashMap() {{
//            put("2010"
//                    , new BigDecimal(10000));
//            put("2012"
//                    , new BigDecimal(12000));
//            put("2018"
//                    , new BigDecimal(14000));
//        }};
//        staff.setSalary(salary);
//        staff.setSkills(Arrays.asList("java", "python", "node", "kotlin"));

        try {
//            objectMapper.writeValue(new File("temp/staff.json"),staff);
            Staff staff = objectMapper.readValue(new File("temp/staff.json"), Staff.class);
            System.out.println(staff.getSalary());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void insertData(Connection connection, int id, String name) throws SQLException {
        String insertSQL = "INSERT INTO example (id, name) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.executeUpdate();
        }
    }

    private static void deleteData(Connection connection, String name) throws SQLException {
        String deleteSQL = "DELETE FROM example WHERE name=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        }
    }

    public static Predicate<Customer> filterLoyaltyRate() {
        return c -> c.getLoyaltyRate() > 5;
    }
}