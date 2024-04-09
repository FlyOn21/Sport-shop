package entity;

import utils.RandomGenerator;
import utils.Rounder;

import java.util.ArrayList;
import java.util.List;

public class Order implements interfaces.IOrder{
    private final List<Product> products;
    private String orderStatus;
    private String paymentMethod;
    private String shippingAddress;
    private String orderOwnerId;
    private double total;
    private final String orderId;

    public Order() {
        this.orderId = new RandomGenerator().generate6DigitString();
        this.products = new ArrayList<>();
    }

    public Order(String orderId, String orderOwnerId, List<Product> products, String orderStatus, String paymentMethod, String shippingAddress) {
        this.orderId = orderId;
        this.orderOwnerId = orderOwnerId;
        this.products = new ArrayList<>();
        this.orderStatus = orderStatus;
        this.paymentMethod = paymentMethod;
        this.shippingAddress = shippingAddress;
        this.total = 0;
        products.forEach(this::addProduct);
    }

    public String getOrderId() {
        return this.orderId;
    }

    private void setTotal(Product product, boolean isAdd ) {
        if (isAdd) {
            this.total += product.getPrice() - product.getPrice() * product.getDiscount();
        } else {
            this.total -= product.getPrice() - product.getPrice() * product.getDiscount();
        }
        this.total = Rounder.round(this.total, 2);
    }

    public void addProduct(Product product) {

        this.products.add(product);
        setTotal(product, true);
    }

    public void removeProduct(Product product) {
        this.products.remove(product);
        setTotal(product, false);
    }

    public void setOrderStatus(String status) {
        this.orderStatus = status;
    }

    public String getOrderStatus() {
        return this.orderStatus;
    }

    public double getTotal() {
        return this.total;
    }

    public String getPaymentMethod() {
        return this.paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getShippingAddress() {
        return this.shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getOrderOwnerId() {
        return this.orderOwnerId;
    }

    public void setOrderOwnerId(String orderOwnerId){

        this.orderOwnerId = orderOwnerId;
    }

    public void setOrderProducts(List<Product> orderProducts) {
        orderProducts.forEach(this::addProduct);
    }

    public List<Product> getProducts() {
        return this.products;
    }

}
