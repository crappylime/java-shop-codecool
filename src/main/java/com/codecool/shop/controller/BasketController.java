package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ProductDaoSqlite;
import com.codecool.shop.model.Basket;
import com.codecool.shop.model.Product;
import com.codecool.shop.view.BasketView;
import com.codecool.shop.view.ProductView;
import com.codecool.shop.view.UserInput;
import spark.Request;
import spark.Response;

import java.util.List;

public class BasketController extends BaseController{
    ProductDao productDao = new ProductDaoSqlite();


    public String addToCart(Request req, Response res) {
        Integer productId = Integer.valueOf(req.params(":id"));
        Product productToAdd = productDao.find(productId);
        Integer quantity = req.queryMap("amount").integerValue();
        Basket basket = req.session().attribute("basket");
        basket.add(productToAdd, quantity);
        res.redirect("/products");   //TODO universal link
        return null;
    }



}
