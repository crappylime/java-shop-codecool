package com.codecool.shop.dao;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        Product product = null;
        ProductCategoryDao productCategoryDao = new ProductCategoryDaoSqlite();
        SupplierDao supplierDao = new SupplierDaoSqlite();

        try {
            PreparedStatement statement = getConnection().prepareStatement("select * from products where id=(?)");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if(rs.next()) {
                product = new Product(
                        rs.getString("name"),
                        rs.getFloat("default_price"),
                        rs.getString("description"),
                        rs.getString("currency"),
                        productCategoryDao.find(rs.getInt("category_id")),
                        supplierDao.find(rs.getInt("supplier_id"))
                );
                product.setId(rs.getInt("id"));
            }
        } catch(SQLException e) {
            System.out.println("Connect to DB failed");
            System.out.println(e.getMessage());
        }
        return product;
    }

    @Override
    public void remove(int id) {

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
            PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM products WHERE supplier_id=(?)");
            statement.setInt(1, supplier.getId());
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
            PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM products WHERE category_id=(?)");
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

            while(rs.next()) {
                Product product = new Product(
                        rs.getString("name"),
                        rs.getFloat("price"),
                        "PLN",
                        rs.getString("description"),
                        productCategoryDao.find(rs.getInt("category_id")),
                        supplierDao.find(rs.getInt("supplier_id"))
                );
                product.setId(rs.getInt("id"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}
