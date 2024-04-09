package views;

import java.io.IOException;
import java.util.Scanner;
import entity.Client;
import models.BucketModel;

public class EshopView {
    private final Scanner scanner;
    private final Client client;

    public EshopView(Client client) {
        this.scanner = new Scanner(System.in);
        this.client = client;
    }

    public void showMenu() {
        boolean running = true;
        while (running) {
            String menu = """ 
                    ---------------------
                    Sport Shop main menu:
                    ---------------------
                    Choice 1 => Show list of products and add to bucket
                    Choice 2 => View my bucket or purchase products
                    Choice 3 => View my orders
                    Choice 4 => Exit
                    """;
            System.out.println(menu);
            System.out.print("Enter your choice: ");

            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    showProductsAndAddToBucket();
                    break;
                case "2":
                    new BucketView(client).showMenu();
                    break;
                case "3":
                    new OrderView().displayClientOrders(client.id());
                    break;
                case "4":
                    running = false;
                    System.out.println("Exit...");
                    scanner.close();
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    private void showProductsAndAddToBucket() {
        ProductsView productsView = new ProductsView();
        productsView.showProducts();
        System.out.println("Do you want add Product to bucket (y/n): ");
        String isAgree = scanner.nextLine();
        if ("y".equalsIgnoreCase(isAgree)) {
            try {
                System.out.print("Enter Product ID: ");
                String productId = scanner.nextLine();
                System.out.print("Enter Quantity: ");
                int quantity = Integer.parseInt(scanner.nextLine());
                BucketModel bucketModel = new BucketModel();
                int status = bucketModel.addProductToBucket(client.id(), productId, quantity);
                if (status == 0) {
                    System.out.println("Product added to your bucket.");
                } else if (status == 1) {
                    System.out.println("There is no specified quantity in the store.");
                } else if (status == 2) {
                    System.out.println("Product not found.");
                } else if (status == 3) {
                    System.out.println("Product already exists in the bucket. Quantity increased.");
                }
            } catch (IOException e) {
                System.err.println("Error adding product to bucket: " + e.getMessage());
            }
        }
    }
}