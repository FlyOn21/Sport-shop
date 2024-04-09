package models;

import java.io.IOException;
import java.util.*;
import entity.Order;
import entity.Product;
import utils.OrderJsonPojo;
import config.Config;

public class OrderModel {

    public OrderModel() {

    }

    public List<Order> showClientOrders(String clientId) throws IOException {
        OrderJsonPojo orderJsonPojo = new OrderJsonPojo();
        Map<String, List<Order>> allOrders = orderJsonPojo.readOrders();
        return allOrders.getOrDefault(clientId, Collections.emptyList());
    }

    public String displayOrderDetails(Order order) {
        ProductModel productModel = new ProductModel();
        StringBuilder productDetails = new StringBuilder();
        ClientModel clientModel = new ClientModel();
        List<Product> products = order.getProducts();
        if (products != null && !products.isEmpty()) {
            products.forEach(product -> productDetails.append(productModel.displayProduct(product)).append("\n"));
        } else {
            productDetails.append(" - No products in order.\n");
        }

        return String.format(
                "Order ID: %s\n" +
                "Buyer: %s\n" +
                "Currency: %s\n" +
                "Status: %s\n" +
                "Payment Method: %s\n" +
                "Shipping Address: %s\n" +
                "Products:\n%s" +
                "Total Cost: %.2f %s\n",
                order.getOrderId(),
                clientModel.getClientEmail(order.getOrderOwnerId()),
                Config.CURRENCY,
                order.getOrderStatus(),
                order.getPaymentMethod(),
                order.getShippingAddress(),
                productDetails,
                order.getTotal(),
                Config.CURRENCY
        );
    }


}


