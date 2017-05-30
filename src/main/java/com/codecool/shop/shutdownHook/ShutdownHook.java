package com.codecool.shop.shutdownHook;

import com.codecool.shop.Application;

import java.sql.SQLException;

public class ShutdownHook extends Thread {

    @Override
    public void run() {
        try {
            Application.getApp().getConnector().getConnection().close();
            System.out.println("DB connection closed safely");
            System.out.println("Bye bye!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
