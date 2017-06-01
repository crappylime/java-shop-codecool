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
    private SQLFilesPaths sqlFilesPaths;

    public Connection getConnection() {
        return this.connection;
    }

    //method made for dependency injection
    public void setDatabaseFilePath(String databaseFilePath) {
        this.databaseFilePath = databaseFilePath;
    }

    public void setSqlFiles(SQLFilesPaths sqlFiles) {
        this.sqlFilesPaths = sqlFiles;
    }

    public void connectToDb() {
        try {
            System.out.println("Connection to DB...");
            this.connection = DriverManager.getConnection(databaseFilePath);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropTables() {
        try {
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createTables() {
        try {
            Statement statement = connection.createStatement();
            statement.execute(prepareQuery(SQLFilesPaths.PRODUCTS.getPath()));
            statement.execute(prepareQuery(SQLFilesPaths.CATEGORIES.getPath()));
            statement.execute(prepareQuery(SQLFilesPaths.SUPPLIERS.getPath()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void fillTables() {
        try {
            Statement statement = connection.createStatement();
            String[] files = {
                    prepareQuery(SQLFilesPaths.PRODUCTS_DATA.getPath()),
                    prepareQuery(SQLFilesPaths.CATEGORIES_DATA.getPath()),
                    prepareQuery(SQLFilesPaths.SUPPLIERS_DATA.getPath())};
            for (String file : files) {
                for (String line : file.split(";")) {
                    statement.execute(line);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer tablesCounter() {
        Integer tablesCounter = 0;
        List<String> requiredTables = new ArrayList<>();
        requiredTables.add("sqlite_sequence");
        requiredTables.add("products");
        requiredTables.add("categories");
        requiredTables.add("suppliers");
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT name FROM sqlite_master WHERE type='table'");
            while (rs.next()) {
                if (requiredTables.contains(rs.getString("name"))) {
                    tablesCounter++;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tablesCounter;
    }

    String prepareQuery(String fileName) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(
                    new FileReader(fileName)
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
