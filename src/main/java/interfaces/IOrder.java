package interfaces;

import entity.Product;

import java.util.List;

public interface IOrder {
    String getOrderId();
    void addProduct(Product product);
    void removeProduct(Product product);
    void setOrderStatus(String status);
    String getOrderOwnerId();
    void setOrderOwnerId(String orderOwner);
    String getOrderStatus();
    double getTotal();
    String getPaymentMethod();
    void setPaymentMethod(String paymentMethod);
    String getShippingAddress();
    void setShippingAddress(String shippingAddress);
    void setOrderProducts(List<Product> orderProducts);
    List<Product> getProducts();
}
