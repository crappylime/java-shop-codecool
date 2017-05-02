import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.SqliteJDBCConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {
        if(args.length > 0 && args[0].equals("--create-tables")) {
            try {
                Connection connection = SqliteJDBCConnector.connection();
                Statement statement = connection.createStatement();
                statement.executeQuery("CREATE TABLE IF NOT EXISTS products\n" +
                        "(\n" +
                        "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                        "    name VARCHAR NOT NULL,\n" +
                        "    description TEXT,\n" +
                        "    price DOUBLE DEFAULT 0.00 NOT NULL\n" +
                        ")");
            } catch (SQLException e) {
            }
        }

        ProductController productController = new ProductController();
        productController.listProducts();
    }


}
