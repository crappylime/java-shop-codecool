import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.SqliteJDBCConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    static ProductController productController = new ProductController();

    public static void main(String[] args) {
        if(args.length > 0 && args[0].equals("--create-tables")) {
            try {
                SqliteJDBCConnector.createTables();
            } catch (SQLException e) {
                System.out.println("Cannot create tables in DB");
                System.out.println(e);
            }
        }

        System.out.println("1. List all products");
        System.out.println("2. List products by Category");
        System.out.print("Select option: ");

        Scanner scanner = new Scanner(System.in);
        while(!scanner.hasNextInt()) {
            System.out.print("Invalid input. Try again");
            scanner.next();
        }
        Integer option = scanner.nextInt();

        switch (option) {
            case 1:
                productController.listProducts();
                break;
            case 2:
                productController.listProductsByCategory();
                break;
            default:
                System.out.println("Option not found");
        }
    }


}
