package com.emailapp.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_SERVER = "localhost:3306";
    private static final String DB_NAME = "emailappdb";
    private static final String DB_URL = "jdbc:mysql://" + DB_SERVER + "/" + DB_NAME + "?zeroDateTimeBehavior=convertToNull" +
            "&serverTimezone=Europe/Athens&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "1111";

    private static Database databaseInstance;
    public static final String COLUMN_ID = "`id`";

    private Connection connection;

    private Database() {
    }

    public static Database getInstance() {
        if (databaseInstance == null) {
            databaseInstance = new Database();
        }
        return databaseInstance;
    }

    public Connection getConnection() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Couldn't connect to the database: " + e.getMessage());
        }
        return connection;
    }

    public boolean connectionTest() {

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = connection.prepareStatement("SELECT 1;");
             ResultSet resultSet = pstmt.executeQuery()) {

            if (resultSet.next()) {
                System.out.println("Connection successful.");
                return true;
            } else {
                System.out.println("Connection failure.");
                return false;
            }


        } catch (SQLException e) {
            System.out.println("Problem with the database!");
        }

        return false;
    }
}
