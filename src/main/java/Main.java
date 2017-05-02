import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Product> products = new ArrayList<Product>();
        ProductCategory category = new ProductCategory("Category", "Department", "Description");
        Supplier supplier = new Supplier("Supplier", "Description");
        Product product1 = new Product("Product 1", 12.50f, "PLN", "Opis", category, supplier);
        Product product2 = new Product("Product 2", 12.50f, "PLN", "Opis", category, supplier);
        Product product3 = new Product("Product 3", 12.50f, "PLN", "Opis", category, supplier);
        products.add(product1);
        products.add(product2);
        products.add(product3);

        for(Product p: products) {
            System.out.println(p.getName());
        }
    }


}
