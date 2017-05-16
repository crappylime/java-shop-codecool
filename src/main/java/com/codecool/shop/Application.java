package com.codecool.shop;

import spark.Request;
import spark.Response;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static spark.Spark.*;

public class Application {
    private static Connection connection;
    private static Application app;

    public Application() {
        System.out.println("Initializing application...");

        try {
            this.connectToDb();
            app=this;
            exception(Exception.class, (e, req, res) -> e.printStackTrace());
            staticFileLocation("/public");
            port(8888);
            routes();
        } catch (SQLException e) {
            System.out.println("Application initialization failed...");
            e.printStackTrace();
        }
    }

    private void connectToDb() throws SQLException {
        System.out.println("Connection to DB...");
        this.connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/database.db");
    }

    private void routes() {
        get("/", (Request req, Response res) -> {
            return "hello world";
        });
    }
}
