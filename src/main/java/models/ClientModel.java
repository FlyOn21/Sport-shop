package models;

import entity.Client;
import utils.ClientsJsonPojo;

import java.io.IOException;
import java.util.Optional;

public class ClientModel {

    public String getClientEmail(String clientId) {
        ClientsJsonPojo clientsJsonPojo = new ClientsJsonPojo();
        try{
            Optional<Client> client = clientsJsonPojo.clientJsonToPojoFindById(clientId);
            if (client.isEmpty()) {
                return clientId;
            }
            return client.map(Client::email).orElse(null);
        } catch (IOException e) {
            return clientId;
        }
    }
}
