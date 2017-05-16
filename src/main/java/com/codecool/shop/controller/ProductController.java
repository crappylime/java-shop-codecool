package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductCategoryDaoSqlite;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ProductDaoSqlite;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.view.ProductView;
import com.codecool.shop.view.UserInput;
import spark.ModelAndView;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.ArrayList;
import java.util.List;

public class ProductController {
    private ProductDao productDao = new ProductDaoSqlite();
    private ProductCategoryDao productCategoryDao = new ProductCategoryDaoSqlite();
    private ProductView view = new ProductView();

    public static String render(ModelAndView modelAndView){
        String rendered = new ThymeleafTemplateEngine().render(modelAndView);

        return rendered;
    }

    public void listProducts() {
        List<Product> products = productDao.getAll();
        view.displayProductsList(products);
    }

    public void listProductsByCategory() {
        List<ProductCategory> categories = productCategoryDao.getAll();
        view.displayCategoriesList(categories);

        Integer categoryId = UserInput.getUserInput();
        ProductCategory category = productCategoryDao.find(categoryId);
        List<Product> products = productDao.getBy(category);
        view.displayProductsList(products);
    }

}
