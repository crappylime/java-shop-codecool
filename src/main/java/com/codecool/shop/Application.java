package com.codecool.shop;

import com.codecool.shop.controller.BasketController;
import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.SQLiteJDBCConnector;
import com.codecool.shop.model.Basket;
import com.codecool.shop.shutdownHook.ShutdownHook;
import spark.Request;
import spark.Response;

import java.util.NoSuchElementException;
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

    public Application() {
        connector = new SQLiteJDBCConnector();
        connector.connectToDb();
        app = this;
        this.productController = new ProductController();
        this.basketController = new BasketController();
    }

    public Application(SQLiteJDBCConnector connector) {
        this.connector = connector;
        connector.connectToDb();
        app = this;
        this.productController = new ProductController();
        this.basketController = new BasketController();
    }

    public void run(String[] args) throws NoSuchElementException {
        System.out.println("Initializing application...");
        Runtime.getRuntime().addShutdownHook(new ShutdownHook());

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
        if (app.getConnector().tablesCounter() == requiredTablesCount) {
            exception(Exception.class, (e, req, res) -> e.printStackTrace());
            staticFileLocation("/public");
            port(8888);
            app.dispatchRoutes();
        } else {
            throw new NoSuchElementException("Database missing tables...");
        }
    }
    
    public static Application getApp() {
        return app;
    }

    private void dispatchRoutes() {
        path("/", () -> {
            before("/*", (request, response) -> {
                if (request.session().isNew()) {
                    request.session().attribute("basket", new Basket());
                }
            });
            path("/", () -> {
                get("", (Request req, Response res) -> {
                    res.redirect("/products");
                    return null;
                });
            });
            path("/products", () -> {
                get("", productController::index);
                get("/new", productController::add);
                post("/new", productController::add);
                post("/:id/add_to_cart", basketController::addToCartAction);
            });
            path("/basket", () -> {
                get("", basketController::show);
            });
        });
    }
}
