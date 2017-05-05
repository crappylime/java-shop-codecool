package com.codecool.shop.model;

public class BasketItem {
    private Product product;
    private Integer quantity;

    BasketItem(Product product, Integer quantity) {
        this.setProduct(product);
        this.setQuantity(quantity);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
