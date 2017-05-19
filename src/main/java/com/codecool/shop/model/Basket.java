package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    private List<BasketItem> items = new ArrayList<>();

    public void add(Product product, Integer quantity) {
        boolean productExists = false;
        for (BasketItem item : this.getItems()) {
            if (item.getProduct().getId() == product.getId()) {
                item.setQuantity(item.getQuantity() + quantity);
                productExists = true;
            }
        }

        if (!productExists) {
            BasketItem item = new BasketItem(product, quantity);
            this.getItems().add(item);
        }
    }

    public List<BasketItem> getItems() {
        return items;
    }

    public void setItems(List<BasketItem> items) {
        this.items = items;
    }

    public Integer getTotalCount() {
        Integer count = 0;
        for (BasketItem item : this.getItems()) {
            count += item.getQuantity();
        }
        return count;
    }

    public float getTotalValue() {
        float value = 0;
        for (BasketItem item : this.getItems()) {
            value += item.getValue();
        }
        return Math.round(value * 100.0) / 100.0F;
    }

    public String getCurrency() {
        return "PLN";
    }
}
