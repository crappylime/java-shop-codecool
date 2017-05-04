package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ProductDaoSqlite;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.view.ProductView;

import java.util.ArrayList;
import java.util.List;

public class ProductController {
    private ProductDao productDao = new ProductDaoSqlite();
    private ProductView view = new ProductView();

    public void listProducts() {
        List<Product> products = productDao.getAll();
        view.displayProductsList(products);
    }

    public void listProductsByCategory() {
        List<ProductCategory> categories = new ArrayList<ProductCategory>();
        for(int i=1; i<=3; i++) {
            String name = "Category " + Integer.toString(i);
            ProductCategory category = new ProductCategory(name, "Department", "Description");
            categories.add(category);
        }

        view.displayCategoriesList(categories);
    }

}
