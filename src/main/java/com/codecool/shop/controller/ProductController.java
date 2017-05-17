package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductCategoryDaoSqlite;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ProductDaoSqlite;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.view.ProductView;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductController extends BaseController {
    private ProductDao productDao = new ProductDaoSqlite();
    private ProductCategoryDao productCategoryDao = new ProductCategoryDaoSqlite();
    private ProductView view = new ProductView();

    public String showProducts(Request req, Response res) {
        Map params = new HashMap<>();
        List<Product> products = productDao.getAll();
        List<ProductCategory> categories = productCategoryDao.getAll();

        if (!(req.queryParams("category_id") == null)) {
            ProductCategory category = productCategoryDao.find(Integer.parseInt(req.queryParams("category_id")));
            products = productDao.getBy(category);
        }
        params.put("categories", categories);
        params.put("products", products);

        ModelAndView modelAndView = new ModelAndView(params, "product/index");

        return render(modelAndView);
    }

}
