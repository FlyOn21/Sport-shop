package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import entity.Product;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ProductsJsonPojo {

    public List<Product> productsJsonToPojo(String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        File productsJsonFile = new File(path);
        JsonNode root = objectMapper.readTree(productsJsonFile);
        JsonNode productsNode = root.get("products");

        if (productsNode != null) {
            return objectMapper.readerFor(new TypeReference<List<Product>>() {}).readValue(productsNode);
        } else {
            return null;
        }
    }

    public void productsPogoToJson(String path, List<Product> products) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        File productsJsonFile = new File(path);
        Map<String, List<Product>> productsMap = Map.of("products", products);
        objectMapper.writeValue(productsJsonFile, productsMap);
    }

}

