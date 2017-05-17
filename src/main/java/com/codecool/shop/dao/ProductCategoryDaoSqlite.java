package com.codecool.shop.dao;

import com.codecool.shop.model.ProductCategory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoSqlite extends BaseDao implements ProductCategoryDao  {
    @Override
    public void add(ProductCategory category) {

    }

    @Override
    public ProductCategory find(int id) {
        ProductCategory category = null;

        try {
            PreparedStatement statement = getConnection().prepareStatement("select * from categories where id=(?)");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if(rs.next()) {
                category = new ProductCategory(
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("department")
                );
                category.setId(rs.getInt("id"));
            }
        } catch(SQLException e) {
            System.out.println("Connect to DB failed");
            System.out.println(e.getMessage());
        }
        return category;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<ProductCategory> getAll() {
        List<ProductCategory> categories = new ArrayList<>();

        try {
            Statement statement = getConnection().createStatement();
            ResultSet rs = statement.executeQuery("select * from categories");
            while(rs.next()) {
                ProductCategory category = new ProductCategory(
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("department")
                );
                category.setId(rs.getInt("id"));
                categories.add(category);
            }
        } catch(SQLException e) {
            System.out.println("Connect to DB failed");
            System.out.println(e.getMessage());
        }
        return categories;
    }
}
