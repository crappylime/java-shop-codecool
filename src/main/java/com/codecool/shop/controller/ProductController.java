package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ProductDaoSqlite;
import com.codecool.shop.model.Product;
import com.codecool.shop.view.ProductView;

import java.util.List;

public class ProductController {

    public void listProducts() {
        ProductDao productDao = new ProductDaoSqlite();
        List<Product> products = productDao.getAll();
        ProductView view = new ProductView();
        view.displayProductsList(products);
    }

}
