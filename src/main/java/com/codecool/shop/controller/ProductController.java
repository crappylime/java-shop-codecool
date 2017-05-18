package com.codecool.shop.controller;

import com.codecool.shop.dao.*;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductController extends BaseController {
    private ProductDao productDao = new ProductDaoSqlite();
    private ProductCategoryDao productCategoryDao = new ProductCategoryDaoSqlite();
    private SupplierDao supplierDao = new SupplierDaoSqlite();

    public String showList(Request req, Response res) {
        Map params = new HashMap<>();
        List<Product> products = productDao.getAll();
        List<ProductCategory> categories = productCategoryDao.getAll();
        List<Supplier> suppliers = supplierDao.getAll();

        if (!(req.queryParams("category_id") == null)) {
            ProductCategory category = productCategoryDao.find(Integer.parseInt(req.queryParams("category_id")));
            products = productDao.getBy(category);
            params.put("category", category);
        }
        if (!(req.queryParams("supplier_id") == null)) {
            Supplier supplier = supplierDao.find(Integer.parseInt(req.queryParams("supplier_id")));
            products = productDao.getBy(supplier);
            params.put("supplier", supplier);
        }
        if (!(req.queryParams("query") == null)) {
            products = productDao.getBy(req.queryParams("query"));
        }
        params.put("categories", categories);
        params.put("suppliers", suppliers);
        params.put("products", products);

        params.put("basket", req.session().attribute("basket"));

        ModelAndView modelAndView = new ModelAndView(params, "product/index");

        return render(modelAndView);
    }
}
