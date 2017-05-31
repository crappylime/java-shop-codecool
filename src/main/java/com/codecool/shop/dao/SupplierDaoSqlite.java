package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoSqlite extends BaseDao implements SupplierDao {
    public SupplierDaoSqlite() {
    }

    public SupplierDaoSqlite(Connection connection) {
        super(connection);
    }

    @Override
    public Supplier find(int id) {
        Supplier supplier = null;

        try {
            PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM suppliers WHERE id=(?)");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                supplier = new Supplier(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description")
                );
            }
        } catch (SQLException e) {
            System.out.println("Connect to DB failed");
            System.out.println(e.getMessage());
        }
        return supplier;
    }

    @Override
    public List<Supplier> getAll() {
        List<Supplier> suppliers = new ArrayList<>();

        try {
            Statement statement = getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM suppliers");
            while (rs.next()) {
                Supplier supplier = new Supplier(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description")
                );
                suppliers.add(supplier);
            }
        } catch (SQLException e) {
            System.out.println("Connect to DB failed");
            System.out.println("Conne unnecessary");
            System.out.println(e.getMessage());
        }
        return suppliers;
    }
}
