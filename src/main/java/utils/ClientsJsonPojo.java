package utils;

import entity.Client;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import config.Config;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

public class ClientsJsonPojo {
    public Optional<Client> clientJsonToPojo(String email) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File productsJsonFile = new File(Config.PATH_CLIENTS_JSON);
        // read json structure
        JsonNode clients = objectMapper.readTree(productsJsonFile);
        boolean ifExists = clients.has(email);
        if (ifExists) {
            JsonNode currentClient = clients.get(email);
            Client client = objectMapper.treeToValue(currentClient, Client.class);

            return  Optional.of(client);
        }
        else {
            return Optional.empty();
        }
    }

    public Optional<Client> clientJsonToPojoFindById(String clientId) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File clientsJsonFile = new File(Config.PATH_CLIENTS_JSON);
        JsonNode clientsNode = objectMapper.readTree(clientsJsonFile);
        if (clientsNode.isObject()) {
            Iterator<Map.Entry<String, JsonNode>> fields = clientsNode.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                JsonNode idNode = entry.getValue().get("id");
                if (idNode != null && clientId.equals(idNode.asText())) {
                    // Directly return Optional of the matched client
                    return Optional.of(objectMapper.treeToValue(entry.getValue(), Client.class));
                }
            }
        }
        return Optional.empty();
    }

    public void clientPogoToJson(Client newClient) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        File clientsJsonFile = new File(Config.PATH_CLIENTS_JSON);

        Map<String, Client> clientsMap;
        if(clientsJsonFile.exists() && clientsJsonFile.length() != 0) {

            clientsMap = objectMapper.readValue(clientsJsonFile, new TypeReference<Map<String, Client>>() {});
        } else {
            clientsMap = new java.util.HashMap<>();
        }
        clientsMap.put(newClient.email(), newClient);
        objectMapper.writeValue(clientsJsonFile, clientsMap);
    }
}
