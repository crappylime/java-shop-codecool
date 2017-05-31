package com.codecool.shop.dao;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProductDaoSqlite extends BaseDao implements ProductDao {

    @Override
    public void add(Product product) {
        try {
            PreparedStatement statement = getConnection().prepareStatement(
                    "INSERT INTO products (name, description, default_price, currency, category_id, " +
                            "supplier_id) VALUES (?, ?, ?, ?, ?, ?)");
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setFloat(3, product.getDefaultPrice());
            statement.setString(4, product.getDefaultCurrency().getDisplayName());
            statement.setInt(5, product.getProductCategory().getId());
            statement.setInt(6, product.getSupplier().getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Connect to DB failed");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Product find(int id) {
        Product product = null;
        ProductCategoryDao productCategoryDao = new ProductCategoryDaoSqlite();
        SupplierDao supplierDao = new SupplierDaoSqlite();

        try {
            PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM products WHERE id=(?)");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getFloat("default_price"),
                        rs.getString("currency"),
                        rs.getString("description"),
                        productCategoryDao.find(rs.getInt("category_id")),
                        supplierDao.find(rs.getInt("supplier_id"))
                );
            }
        } catch (SQLException e) {
            System.out.println("Connect to DB failed");
            System.out.println(e.getMessage());
        }
        return product;
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();

        try {
            PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM products");
            products = getProducts(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        List<Product> products = new ArrayList<>();

        try {
            PreparedStatement statement = getConnection().
                    prepareStatement("SELECT * FROM products WHERE supplier_id=(?)");
            statement.setInt(1, supplier.getId());
            products = getProducts(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public List<Product> getBy(String name) {
        List<Product> products = new ArrayList<>();

        try {
            PreparedStatement statement = getConnection().
                    prepareStatement("SELECT * FROM products WHERE name LIKE (?)");
            statement.setString(1, "%" + name + "%");
            products = getProducts(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        List<Product> products = new ArrayList<>();

        try {
            PreparedStatement statement = getConnection().
                    prepareStatement("SELECT * FROM products WHERE category_id=(?)");
            statement.setInt(1, productCategory.getId());
            products = getProducts(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    private List<Product> getProducts(PreparedStatement statement) {
        List<Product> products = new ArrayList<>();
        ProductCategoryDao productCategoryDao = new ProductCategoryDaoSqlite();
        SupplierDao supplierDao = new SupplierDaoSqlite();

        try {
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getFloat("default_price"),
                        "PLN",
                        rs.getString("description"),
                        productCategoryDao.find(rs.getInt("category_id")),
                        supplierDao.find(rs.getInt("supplier_id"))
                );
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}
