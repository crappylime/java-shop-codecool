package com.codecool.shop.controller;

import java.util.Scanner;

public class MainMenuController {
    ProductController productController = new ProductController();

    public void mainMenuAction() {
        System.out.println("1. List all products");
        System.out.println("2. List products by Category");
        System.out.print("Select option: ");

        Scanner scanner = new Scanner(System.in);
        while(!scanner.hasNextInt()) {
            System.out.print("Invalid input. Try again");
            scanner.next();
        }
        Integer option = scanner.nextInt();

        switch (option) {
            case 1:
                this.productController.listProducts();
                break;
            case 2:
                this.productController.listProductsByCategory();
                break;
            default:
                System.out.println("Option not found");
        }
    }
}
