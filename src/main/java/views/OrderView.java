package views;
import java.io.IOException;
import java.util.List;

import models.OrderModel;
import entity.Order;


public class OrderView {

    private final OrderModel orderModel;

    public OrderView() {
        this.orderModel = new OrderModel();
    }

    public void displayClientOrders(String clientId) {
        try {
            List<Order> orders = orderModel.showClientOrders(clientId);
            if (orders.isEmpty()) {
                System.out.println("No orders found for client ID: " + clientId);
            } else {
                System.out.println("Orders for client ID: " + clientId + ":");
                for (Order order : orders) {
                    String orderString = orderModel.displayOrderDetails(order);
                    System.out.print(orderString);
                    System.out.println();
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while fetching orders: " + e.getMessage());
        }
    }
}