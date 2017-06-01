package com.codecool.shop.dao;

public enum SQLFilesPaths {
    PRODUCTS_DATA("src/main/resources/sql/productsData.sql"),
    PRODUCTS("src/main/resources/sql/products.sql"),
    CATEGORIES_DATA("src/main/resources/sql/categoriesData.sql"),
    CATEGORIES("src/main/resources/sql/categories.sql"),
    SUPPLIERS_DATA("src/main/resources/sql/suppliersData.sql"),
    SUPPLIERS("src/main/resources/sql/suppliers.sql");

    private final String path;

    SQLFilesPaths(String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }

    @Override
    public String toString() {
        return path;
    }
}
