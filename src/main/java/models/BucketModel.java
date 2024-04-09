package models;

import builders.BucketBuilder;
import builders.OrderBuilder;
import builders.ProductBuilder;
import config.Config;
import entity.Bucket;
import entity.Client;
import entity.Order;
import entity.Product;
import utils.BucketJsonPojo;
import utils.OrderJsonPojo;

import java.io.IOException;
import java.util.*;

public class BucketModel {

    public BucketModel() {
    }
    public int addProductToBucket(String clientId, String productId, int quantity) throws IOException {
        BucketJsonPojo bucketJsonPojo = new BucketJsonPojo();
        Map<String, List<Product>> buckets = bucketJsonPojo.readBuckets();
        List<Product> clientProducts;
        try{
            clientProducts = buckets.get(clientId);
        } catch (NullPointerException e) {
            clientProducts = Collections.emptyList();
        }
        BucketBuilder bucketBuilder = new BucketBuilder();
        bucketBuilder.setBucketOwnerId(clientId);
        bucketBuilder.setBucketProducts(clientProducts);
        Bucket bucket = bucketBuilder.getBucket();
        ProductModel productModel = new ProductModel();
        Optional<Product> currentProduct = productModel.getProductById(productId);
        if (currentProduct.isPresent()) {
            Product product = currentProduct.get();
            if (product.getQuantity() < quantity) {
                return 1;
            }
            productModel.updateProductQuantity(productId, quantity, false);
            if (clientProducts.stream().anyMatch(p -> p.getId().equals(productId))) {
                clientProducts.stream()
                        .filter(p -> p.getId().equals(productId))
                        .findFirst().ifPresent(productToUpdate -> productToUpdate.setQuantity
                                (productToUpdate.getQuantity() + quantity));
                buckets.put(clientId, clientProducts);
                bucketJsonPojo.writeBuckets(buckets);
                return 0;
            } else {
                ProductBuilder productBuilder = new ProductBuilder();
                productBuilder.setProductId(product.getId());
                productBuilder.setProductName(product.getName());
                productBuilder.setProductPrice(product.getPrice());
                productBuilder.setColor(product.getColor());
                productBuilder.setQuantity(quantity);
                productBuilder.setProductDescription(product.getDescription());
                productBuilder.setDiscount(product.getDiscount());
                productBuilder.setBrand(product.getBrand());
                productBuilder.setProductCategory(product.getCategory());
                productBuilder.setSubCategory(product.getSubCategory());
                Product newProduct = productBuilder.getProduct();
                boolean isAdd = bucket.addProduct(newProduct);
                if (!isAdd) {
                    productModel.updateProductQuantity(productId, quantity, true);
                    return 3;
                }
                buckets.put(clientId, bucket.getProducts());

                bucketJsonPojo.writeBuckets(buckets);
                return 0;
            }
        } else {
            // Product not found
            return 2;
        }
    }
    public int removeProductFromBucket(String clientId, String id) throws IOException {
        BucketJsonPojo bucketJsonPojo = new BucketJsonPojo();
        Map<String, List<Product>> buckets = bucketJsonPojo.readBuckets();
        List<Product> clientProducts = buckets.getOrDefault(clientId, Collections.emptyList());
        Product productToRemove = clientProducts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (productToRemove == null) {
            return 1;
        }
        ProductModel productModel = new ProductModel();
        productModel.updateProductQuantity(id, productToRemove.getQuantity(), true);
        clientProducts.remove(productToRemove);
        buckets.put(clientId, clientProducts);
        bucketJsonPojo.writeBuckets(buckets);
        return 0;
    }

    public List<Product> getBucket(String bucketId) throws IOException {
        BucketJsonPojo bucketJsonPojo = new BucketJsonPojo();
        Map<String, List<Product>> buckets = bucketJsonPojo.readBuckets();
        return buckets.getOrDefault(bucketId, Collections.emptyList());
    }

    public String showBucket(List<Product> bucketProducts) {
        StringBuilder bucketContent = new StringBuilder();
        ProductModel productModel = new ProductModel();
        double total = 0;
        for (Product product : bucketProducts) {
            bucketContent.append(productModel.displayProduct(product));
            bucketContent.append("\n");
            total += (product.getPrice()-product.getPrice()*product.getDiscount()) * product.getQuantity();
        }
        bucketContent.append("Total: ").append(total).append(" ").append(Config.CURRENCY);
        return bucketContent.toString();
    }

    public boolean bucketIsEmpty(String clientId) throws IOException {
        BucketJsonPojo bucketJsonPojo = new BucketJsonPojo();
        Map<String, List<Product>> buckets = bucketJsonPojo.readBuckets();
        List<Product> clientProducts = buckets.getOrDefault(clientId,  new ArrayList<>());
        return clientProducts.isEmpty();
    }

    public boolean clearBucketAndCreateOrder(Client client, String shippingAddress, String paymentMethod) throws IOException {
        BucketJsonPojo bucketJsonPojo = new BucketJsonPojo();
        Map<String, List<Product>> buckets = bucketJsonPojo.readBuckets();
        List<Product> clientProducts = buckets.getOrDefault(client.id(), new ArrayList<>());
        if (clientProducts.isEmpty()) {
            return false;
        }
        OrderJsonPojo orderJsonPojo = new OrderJsonPojo();
        Map<String, List<Order>> orders = orderJsonPojo.readOrders();
        List<Order> clientOrders = orders.getOrDefault(client.id(), new ArrayList<>());
        OrderBuilder orderBuilder = new OrderBuilder();
        orderBuilder.setOrderOwnerId(client.id());
        orderBuilder.setOrderStatus("PENDING");
        orderBuilder.setPaymentMethod(paymentMethod);
        orderBuilder.setShippingAddress(shippingAddress);
        orderBuilder.setOrderProducts(clientProducts);
        Order order = orderBuilder.getOrder();
        clientOrders.add(order);
        orders.put(client.id(), clientOrders);
        orderJsonPojo.writeOrders(orders);
        buckets.put(client.id(), Collections.emptyList());
        bucketJsonPojo.writeBuckets(buckets);
        return true;
    }

}
