package builders;

import entity.Bucket;
import entity.Product;

import java.util.List;

public class BucketBuilder implements interfaces.IBucketBuilder {
    private final Bucket bucket;

    public BucketBuilder() {
        this.bucket = new Bucket();
    }

    public void setBucketOwnerId(String bucketOwnerId) {
        this.bucket.setBucketOwnerId(bucketOwnerId);
    }

    public void setBucketProducts(List<Product> bucketProducts) {
        this.bucket.setBucketProducts(bucketProducts);
    }

    public Bucket getBucket() {
        return this.bucket;
    }
}
