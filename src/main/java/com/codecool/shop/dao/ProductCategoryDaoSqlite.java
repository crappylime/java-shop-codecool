package com.codecool.shop.dao;

import com.codecool.shop.model.ProductCategory;

import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoSqlite implements ProductCategoryDao {
    @Override
    public void add(ProductCategory category) {

    }

    @Override
    public ProductCategory find(int id) {
        return new ProductCategory("Category", "Department", "Description");
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<ProductCategory> getAll() {
        List<ProductCategory> categories = new ArrayList<ProductCategory>();
        for(int i=1; i<=3; i++) {
            String name = "Category " + Integer.toString(i);
            ProductCategory category = new ProductCategory(name, "Department", "Description");
            category.setId(i);
            categories.add(category);
        }

        return categories;
    }
}
