package interfaces;

import entity.Product;

import java.util.List;

public interface IBucketBuilder {
    void setBucketOwnerId(String bucketOwnerId);
    void setBucketProducts(List<Product> bucketProducts);
    IBucket getBucket();
}
