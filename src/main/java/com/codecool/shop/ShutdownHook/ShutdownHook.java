package com.codecool.shop.ShutdownHook;

import com.codecool.shop.Application;

import java.sql.SQLException;

/**
 * Created by ihni on 16.05.17.
 */

public class ShutdownHook extends Thread {

    @Override
    public void run(){
        try {
            Application.getConnection().close();
            System.out.println("DB connection closed safely");
            System.out.println("Bye bye!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


