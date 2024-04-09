package interfaces;

import entity.Product;

import java.util.List;

public interface IBucket {
    boolean addProduct(Product product);
    boolean removeProduct(Product product);
    void setBucketOwnerId(String bucketOwner);
    String getBucketOwnerId();
    void setBucketProducts(List<Product> bucketProducts);
    double getTotal();
    List<Product> getProducts();
}
