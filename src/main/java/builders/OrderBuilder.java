package builders;

import entity.Order;
import entity.Product;

public class OrderBuilder implements interfaces.IOrderBuilder{
    private final Order order;

    public OrderBuilder() {
        this.order = new Order();
    }

    public void setOrderStatus(String status) {
        this.order.setOrderStatus(status);
    }

    public void setOrderOwnerId(String orderOwnerId) {
        this.order.setOrderOwnerId(orderOwnerId);
    }

    public void setPaymentMethod(String paymentMethod) {
        this.order.setPaymentMethod(paymentMethod);
    }

    public void setShippingAddress(String shippingAddress) {
        this.order.setShippingAddress(shippingAddress);
    }

    public void setOrderProducts(java.util.List<Product> orderProducts) {
        this.order.setOrderProducts(orderProducts);
    }

    public Order getOrder() {
        return this.order;
    }
}
