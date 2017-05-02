package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ProductDaoSqlite;
import com.codecool.shop.model.Product;

import java.util.List;

public class ProductController {

    public void listProducts() {
        ProductDao productDao = new ProductDaoSqlite();
        List<Product> products = productDao.getAll();

        for(Product p: products) {
            System.out.println(p.getName());
        }
    }

}
