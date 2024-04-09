package interfaces;

import entity.Product;

import java.util.List;

public interface IOrderBuilder {
    void setOrderStatus(String status);
    void setOrderOwnerId(String orderOwner);
    void setPaymentMethod(String paymentMethod);
    void setShippingAddress(String shippingAddress);
    void setOrderProducts(List<Product> orderProducts);
    IOrder getOrder();
}
