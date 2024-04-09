package entity;

import utils.Rounder;

import java.util.ArrayList;
import java.util.List;

public class Bucket implements interfaces.IBucket {
    private String bucketOwnerId;
    private double total;
    private final List<Product> products;


    public Bucket() {
        this.products = new ArrayList<>();
        this.total = 0;
    }

    public Bucket(String bucketOwnerId, List<Product> products) {
        this.bucketOwnerId = bucketOwnerId;
        this.products = new ArrayList<>();
        this.total = 0;
        products.forEach(this::addProduct);
    }

    private void setTotal(Product product, boolean isAdd ) {
        if (isAdd) {
            this.total += product.getPrice() - product.getPrice() * product.getDiscount();
        } else {
            this.total -= product.getPrice() - product.getPrice() * product.getDiscount();
        }
        this.total = Rounder.round(this.total, 2);
    }

    public boolean addProduct(Product product) {
        try {
            this.products.add(product);
            setTotal(product, true);
            return true;
        } catch (NullPointerException | ClassCastException | UnsupportedOperationException | IllegalArgumentException e ) {
            return false;
        }
    }

    public boolean removeProduct(Product product) {
        try {
            this.products.remove(product);
            setTotal(product, false);
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }

    public void setBucketOwnerId(String bucketOwnerId) {
        this.bucketOwnerId = bucketOwnerId;
    }

    public void setBucketProducts(List<Product> bucketProducts) {
        if (bucketProducts == null) {
            return;
        }
        bucketProducts.forEach(this::addProduct);
    }

    public String getBucketOwnerId() {
        return this.bucketOwnerId;
    }

    public double getTotal() {
        return this.total;
    }

    public List<Product> getProducts() {
        return this.products;
    }


}
