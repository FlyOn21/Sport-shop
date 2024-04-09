package views;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import entity.Product;
import models.BucketModel;
import entity.Client;

public class BucketView {
    private final BucketModel bucketModel;
    private final Scanner scanner;
    private final Client client;

    public BucketView(Client client) {
        this.bucketModel = new BucketModel();
        this.scanner = new Scanner(System.in);
        this.client = client;
    }

    public void showMenu() {
        while (true) {
            String menu = """ 
                    ------------
                    Bucket menu:
                    ------------
                    Choice 1 => Show My Bucket
                    Choice 2 => Remove Product from Bucket
                    Choice 3 => Buy All in Bucket
                    Choice 4 => Exit
                    """;
            System.out.println(menu);
            System.out.print("Enter your choice: ");
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    showBucket();
                    break;
                case "2":
                    removeProductFromBucket();
                    break;
                case "3":
                    buyAllInBucket();
                    break;
                case "4":
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    public void showBucket() {
        try {
            List<Product> bucketProducts = bucketModel.getBucket(client.id());
            if (bucketProducts.isEmpty()) {
                System.out.println("Bucket is empty.");
            } else {
                System.out.println("Bucket Contents:");
                System.out.println(bucketModel.showBucket(bucketProducts));
            }
        } catch (IOException e) {
            System.err.println("Error displaying bucket: " + e.getMessage());
        }
    }

    private void removeProductFromBucket() {
        System.out.print("Enter Product id to Remove: ");
        String id = scanner.nextLine();

        try {
            if (bucketModel.removeProductFromBucket(client.id(), id) == 0) {
                System.out.println("Product removed from bucket.");
            } else {
                System.out.println("Product not found in bucket.");
            }
        } catch (IOException e) {
            System.err.println("Error removing product from bucket: " + e.getMessage());
        }
    }

    private void buyAllInBucket() {
        try {
            boolean isEmpty = bucketModel.bucketIsEmpty(client.id());
            if (isEmpty) {
                System.out.println("Bucket is empty.");
                return;
            }
            System.out.print("Enter Shipping Address: ");
            String shippingAddress = scanner.nextLine();
            System.out.print("Enter Payment Method: ");
            String paymentMethod = scanner.nextLine();
            if (bucketModel.clearBucketAndCreateOrder(client, shippingAddress, paymentMethod)) {
                System.out.println("Purchase successful. Thank you!");
            } else {
                System.out.println("Bucket is already empty.");
            }
        } catch (IOException e) {
            System.err.println("Error during purchase: " + e.getMessage());
        }
    }

}
