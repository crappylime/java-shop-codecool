package com.codecool.shop;

import com.codecool.shop.controller.MainMenuController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application {
    private static Connection connection;
    private static Application app;

    public Application() {
        System.out.println("Initializing application...");

        try {
            this.connectToDb();
            app=this;
            this.dispatchMainMenuController();
        } catch (SQLException e) {
            System.out.println("Application initialization failed...");
            e.printStackTrace();
        }
    }

    private void connectToDb() throws SQLException {
        System.out.println("Connection to DB...");
        this.connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/database.db");
    }

    private void dispatchMainMenuController() {
        MainMenuController mainMenuController = new MainMenuController();
        mainMenuController.mainMenuAction();
    }
}
