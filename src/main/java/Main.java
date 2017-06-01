import com.codecool.shop.Application;

import javax.management.InstanceAlreadyExistsException;
import java.util.NoSuchElementException;

public class Main {

    public static void main(String[] args) {
        try {
            Application.run(args);
        } catch (InstanceAlreadyExistsException e) {
            System.out.println(e.getMessage());
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            System.out.println("Try running app with one of these arguments:"
            +"\n1. --init-db"
            +"\n2. --migrate-db");
        }
    }
}
