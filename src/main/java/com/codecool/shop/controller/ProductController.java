package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductCategoryDaoSqlite;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ProductDaoSqlite;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.view.ProductView;
import com.codecool.shop.view.UserInput;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.*;

public class ProductController extends BaseController{
    private ProductDao productDao = new ProductDaoSqlite();
    private ProductCategoryDao productCategoryDao = new ProductCategoryDaoSqlite();
    private ProductView view = new ProductView();



    public String show(Request req, Response res) {
        Map params = new HashMap<>();

        List<Product> products = productDao.getAll();
        params.put("products", products);

        ModelAndView modelAndView = new ModelAndView(params, "product/index");

        return render(modelAndView);
    }

    public String searchAction(Request req, Response res) {
        Map params = new HashMap<>();

        List<Product> products = productDao.getBy(req.queryParams("query"));
        params.put("products", products);

        ModelAndView modelAndView = new ModelAndView(params, "product/index");

        return render(modelAndView);
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
