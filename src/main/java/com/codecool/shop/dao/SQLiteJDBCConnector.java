package com.codecool.shop.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLiteJDBCConnector {
    private Connection connection;
    private String databaseFilePath = "jdbc:sqlite:src/main/resources/database.db";

    public Connection getConnection() {
        return this.connection;
    }

    //method made for dependency injection
    public void setDatabaseFilePath(String databaseFilePath) {
        this.databaseFilePath = databaseFilePath;
    }

    public void connectToDb() throws SQLException {
        System.out.println("Connection to DB...");
        this.connection = DriverManager.getConnection(databaseFilePath);
    }

    public void dropTables() throws SQLException {
        Statement statement = connection.createStatement();
        List<String> tables = new ArrayList<>();

        ResultSet rs = statement.executeQuery("" +
                "SELECT name FROM sqlite_master WHERE type='table' AND name!='sqlite_sequence'");
        while (rs.next()) {
            tables.add(rs.getString("name"));
        }
        for (String table : tables) {
            statement.execute("DROP TABLE '" + table + "'");
        }
    }

    public void createTables() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(prepareQuery("products.sql"));
        statement.execute(prepareQuery("categories.sql"));
        statement.execute(prepareQuery("suppliers.sql"));
    }

    public void fillTables() throws SQLException {
        Statement statement = connection.createStatement();
        String[] files = {
                prepareQuery("productsData.sql"),
                prepareQuery("categoriesData.sql"),
                prepareQuery("suppliersData.sql")};
        for (String file : files) {
            for (String line : file.split(";")) {
                statement.execute(line);
            }
        }
    }

    public Integer tablesCounter() throws SQLException {
        Integer tablesCounter = 0;
        List<String> requiredTables = new ArrayList<>();
        requiredTables.add("sqlite_sequence");
        requiredTables.add("products");
        requiredTables.add("categories");
        requiredTables.add("suppliers");
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT name FROM sqlite_master WHERE type='table'");
        while (rs.next()) {
            if (requiredTables.contains(rs.getString("name"))) {
                tablesCounter++;
            }
        }
        return tablesCounter;
    }

    String prepareQuery(String fileName) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(
                    new FileReader("src/main/resources/sql/" + fileName)
            );
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
