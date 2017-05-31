package com.codecool.shop.dao;

import com.codecool.shop.model.ProductCategory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoSqlite extends BaseDao implements ProductCategoryDao {

    public ProductCategoryDaoSqlite() {
    }

    public ProductCategoryDaoSqlite(Connection connection) {
        super(connection);
    }

    @Override
    public ProductCategory find(int id) {
        ProductCategory category = null;

        try {
            PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM categories WHERE id=(?)");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                category = new ProductCategory(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("department")
                );
            }
        } catch (SQLException e) {
            System.out.println("Connect to DB failed");
            System.out.println(e.getMessage());
        }
        return category;
    }

    @Override
    public List<ProductCategory> getAll() {
        List<ProductCategory> categories = new ArrayList<>();

        try {
            Statement statement = getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM categories");
            while (rs.next()) {
                ProductCategory category = new ProductCategory(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("department")
                );
                categories.add(category);
            }
        } catch (SQLException e) {
            System.out.println("Connect to DB failed");
            System.out.println(e.getMessage());
        }
        return categories;
    }
}
