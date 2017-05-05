package com.codecool.shop.view;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;

import java.util.List;

public class ProductView {

    public void displayProductsList(List<Product> products) {
        for(Product product: products) {
            System.out.println(product);
        }
    }

    public void displayCategoriesList(List<ProductCategory> categories) {
        System.out.println("Categories list");
        for(ProductCategory category: categories) {
            System.out.println(category.getId() + ") " + category.getName());
        }
    }

}
