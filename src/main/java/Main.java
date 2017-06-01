import com.codecool.shop.Application;

import java.util.NoSuchElementException;

public class Main {

    public static void main(String[] args) {
        try {
            Application application = new Application();
            application.run(args);
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            System.out.println("Try running app with one of these arguments:"
            +"\n1. --init-db"
            +"\n2. --migrate-db");
        }
    }
}
