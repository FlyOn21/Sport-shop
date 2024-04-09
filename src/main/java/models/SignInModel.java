package models;

import java.io.IOException;
import java.util.Optional;
import entity.Client;
import utils.ClientsJsonPojo;
import utils.PasswordProcessing;

public class SignInModel {
    public Optional<Client> signInClient (String email, String password) throws IOException {
        ClientsJsonPojo clientsJsonPojo = new ClientsJsonPojo();
        Optional<Client> ifClient =  clientsJsonPojo.clientJsonToPojo(email);
        if (ifClient.isEmpty()) {
             return Optional.empty();
        }
        else {
            Client client = ifClient.get();
            String hash = client.passwordHash();
            boolean validatePassword = PasswordProcessing.checkPassword(password, hash);
            if (!validatePassword) {
                return Optional.empty();
            } else {
                return ifClient;
            }
        }
    }
}
