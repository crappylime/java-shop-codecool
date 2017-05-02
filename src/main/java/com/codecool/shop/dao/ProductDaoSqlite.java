package com.codecool.shop.dao;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by przemek on 02.05.2017.
 */
public class ProductDaoSqlite implements ProductDao {
    @Override
    public void add(Product product) {

    }

    @Override
    public Product find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/database.db");
        } catch(SQLException e) {
            System.out.println("Connect to DB failed");
            System.out.println(e.getMessage());
        }

        List<Product> products = new ArrayList<Product>();
        ProductCategory category = new ProductCategory("Category", "Department", "Description");
        Supplier supplier = new Supplier("Supplier", "Description");
        Product product1 = new Product("Product 1", 12.50f, "PLN", "Opis", category, supplier);
        Product product2 = new Product("Product 2", 12.50f, "PLN", "Opis", category, supplier);
        Product product3 = new Product("Product 3", 12.50f, "PLN", "Opis", category, supplier);
        products.add(product1);
        products.add(product2);
        products.add(product3);
        return products;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return null;
    }
}
