package com.codecool.shop.dao;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by przemek on 02.05.2017.
 */
public class ProductDaoSqlite extends BaseDao implements ProductDao {
    @Override
    public void add(Product product) {

    }

    @Override
    public Product find(int id) {
        Supplier supplier = new Supplier("Supplier", "Description");
        ProductCategory category = new ProductCategory("Category", "Department", "Description");

        Product p = new Product(
                "Product name",
                12.00f,
                "PLN",
                "Description",
                category,
                supplier
        );
        p.setId(id);
        return p;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<Product>();

        try {
            PreparedStatement statement = this.getConnection().prepareStatement("SELECT * FROM products");
            products = this.getProducts(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        List<Product> products = new ArrayList<Product>();

        try {
            PreparedStatement statement = this.getConnection().
                    prepareStatement("SELECT * FROM products WHERE category_id = ?");
            statement.setInt(1, productCategory.getId());
            products = this.getProducts(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    private List<Product> getProducts(PreparedStatement statement) throws SQLException {
        List<Product> products = new ArrayList<Product>();
        Supplier supplier = new Supplier("Supplier", "Description");
        ProductCategory category = new ProductCategory("Category", "Department", "Description");

        ResultSet rs = statement.executeQuery();
        while(rs.next()) {
            Product product = new Product(
                    rs.getString("name"),
                    rs.getFloat("price"),
                    "PLN",
                    rs.getString("description"),
                    category,
                    supplier
            );
            product.setId(rs.getInt("id"));
            products.add(product);
        }

        return products;
    }
}
