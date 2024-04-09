package utils;

import config.Config;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import entity.Order;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderJsonPojo {
    private final ObjectMapper objectMapper;
    private final File ordersFile;

    public OrderJsonPojo() {
        objectMapper = new ObjectMapper()
                .enable(SerializationFeature.INDENT_OUTPUT)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        ordersFile = new File(Config.PATH_ORDERS_JSON);
    }

    public  Map<String, List<Order>> readOrders() throws IOException {
        if (ordersFile.exists()) {
            return objectMapper.readValue(ordersFile, new TypeReference<>() {});
        }
        return new HashMap<>();
    }

    public void writeOrders(Map<String, List<Order>> orders) throws IOException {
        objectMapper.writeValue(ordersFile, orders);
    }
}
