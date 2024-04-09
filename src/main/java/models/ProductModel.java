package models;

import entity.Product;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import utils.ProductsJsonPojo;
import config.Config;

public class ProductModel {
    private final ProductsJsonPojo productsJsonPojo = new ProductsJsonPojo();

    public List<Product> loadProducts() throws IOException {
        return productsJsonPojo.productsJsonToPojo(Config.PATH_PRODUCTS_JSON);
    }

    public void saveProducts(List<Product> products) throws IOException {
        productsJsonPojo.productsPogoToJson(Config.PATH_PRODUCTS_JSON, products);
    }

    public Optional<Product> getProductById(String productId) throws IOException {
        return loadProducts().stream()
                .filter(p -> p.getId().equals(productId))
                .findFirst();
    }

    public void updateProductQuantity(String id, int quantityChange, boolean isIncrease
    ) throws IOException {
        List<Product> products = loadProducts();
        Product product = products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + id));
        if (isIncrease) {
            quantityChange = Math.abs(quantityChange);
        } else {
            quantityChange = -Math.abs(quantityChange);
        }
        int newQuantity = product.getQuantity() + quantityChange;
        if (newQuantity < 0) {
            throw new IllegalArgumentException("Not enough quantity for product ID: " + id);
        }

        product.setQuantity(newQuantity);
        saveProducts(products);
    }

    public String displayProduct(Product product) {
        return product.toString();
    }
}
