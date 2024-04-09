package views;
import entity.Product;

import java.io.IOException;
import java.util.List;

import models.ProductModel;
import utils.ProductsJsonPojo;
import config.Config;

public class ProductsView {

    public void showProducts() {
        try {
            ProductModel productModel = new ProductModel();
            ProductsJsonPojo productsJsonPojo = new ProductsJsonPojo();
            List<Product> products = productsJsonPojo.productsJsonToPojo(Config.PATH_PRODUCTS_JSON);

            if (products == null || products.isEmpty()) {
                System.out.println("No products available.");
                return;
            }

            System.out.println("Available products:");
            for (Product product : products) {
                String prodactString = productModel.displayProduct(product);
                System.out.print(prodactString);
            }
            System.out.println();
        } catch (IOException e) {
            System.err.println("Failed to load products: " + e.getMessage());
        }
    }
}


