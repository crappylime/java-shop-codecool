package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ProductDaoSqlite;
import com.codecool.shop.model.Basket;
import com.codecool.shop.model.BasketItem;
import com.codecool.shop.model.Product;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasketController extends BaseController {
    ProductDao productDao = new ProductDaoSqlite();

    public String show(Request req, Response res) {
        Basket basket = req.session().attribute("basket");
        List<BasketItem> basketItems = basket.getItems();
        Map params = new HashMap<>();
        params.put("items", basketItems);
        params.put("basket", basket);
        ModelAndView modelAndView = new ModelAndView(params, "basket/show");

        return render(modelAndView);
    }

    public String addToCartAction(Request req, Response res) {
        Integer productId = Integer.valueOf(req.params(":id"));
        Product productToAdd = productDao.find(productId);
        Integer quantity = !req.queryMap("amount").value().isEmpty() ? req.queryMap("amount").integerValue() : 1;
        Basket basket = req.session().attribute("basket");
        basket.add(productToAdd, quantity);
        res.redirect("/products");
        return null;
    }
}
