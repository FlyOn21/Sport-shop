package models;


import java.io.IOException;
import java.util.Optional;
import entity.Client;
import utils.ClientsJsonPojo;

public class RegistrationModel {
    public boolean ifClientExist (String email) throws IOException {
        ClientsJsonPojo clientsJsonPojo = new ClientsJsonPojo();
        Optional<Client> ifClient = clientsJsonPojo.clientJsonToPojo(email);
        return ifClient.isPresent();
    }
}
