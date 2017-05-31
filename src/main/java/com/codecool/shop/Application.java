package com.codecool.shop;

import com.codecool.shop.controller.BasketController;
import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.SQLiteJDBCConnector;
import com.codecool.shop.model.Basket;
import com.codecool.shop.shutdownHook.ShutdownHook;
import spark.Request;
import spark.Response;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static spark.Spark.*;

public class Application {
    private static Application app;
    private ProductController productController;
    private BasketController basketController;
    private SQLiteJDBCConnector connector;

    public SQLiteJDBCConnector getConnector() {
        return this.connector;
    }

    private Application() {
        connector = new SQLiteJDBCConnector();
        try {
            connector.connectToDb();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Application initialization failed...");
        }
        app = this;
        this.productController = new ProductController();
        this.basketController = new BasketController();
    }

    public static void run(String[] args) {
        System.out.println("Initializing application...");
        Runtime.getRuntime().addShutdownHook(new ShutdownHook());

        if (app == null) {
            try {
                new Application();
                if (args.length > 0) {
                    if (Objects.equals(args[0], "--init-db")) {
                        app.connector.dropTables();
                        app.connector.createTables();
                        app.connector.fillTables();
                    } else if (Objects.equals(args[0], "--migrate-db")) {
                        app.connector.createTables();
                    }
                }
                Integer requiredTablesCount = 4;
                if (app.connector.tablesCounter() == requiredTablesCount) {
                    exception(Exception.class, (e, req, res) -> e.printStackTrace());
                    staticFileLocation("/public");
                    port(8888);
                    app.routes();
                } else {
                    System.out.println("Database missing tables...");
                }
            } catch (SQLException e) {
                System.out.println("Application initialization failed...");
                e.printStackTrace();
            }
        } else {
            System.out.println("Application already running!");
        }
    }



    public static Application getApp() {
        return app;
    }

    private void routes() {

        before((request, response) -> {
            if (request.session().isNew()) {
                request.session().attribute("basket", new Basket());
            }
        });
        get("/", (Request req, Response res) -> {
            res.redirect("/products");
            return null;
        });
        get("/products", productController::index);
        get("/products/new", productController::add);
        post("/products/new", productController::add);
        post("/products/:id/add_to_cart", basketController::addToCartAction);
        get("/basket", basketController::show);
    }
}
