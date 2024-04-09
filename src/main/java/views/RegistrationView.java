package views;


import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;
import entity.Client;
import models.RegistrationModel;
import utils.ClientsJsonPojo;
import utils.PasswordProcessing;
import utils.Validation;

public class RegistrationView {

    final Scanner scanner = new Scanner(System.in);
    public Optional<Client> registerClient() throws IOException {


        System.out.println("Enter first name:");
        String firstName = scanner.nextLine();

        System.out.println("Enter second name:");
        String secondName = scanner.nextLine();

        String email;
        boolean ifExistClient;
        do {
            System.out.println("Enter email:");
            email = scanner.nextLine();
            RegistrationModel registrationModel = new RegistrationModel();
            ifExistClient = registrationModel.ifClientExist(email);
            if (ifExistClient) {
                System.out.println("Client with that email exists. Please try another email.");
            } else if (!Validation.isValidEmail(email)) {
                System.out.println("Invalid email format. Please enter a valid email.");
            }
        } while (!Validation.isValidEmail(email) || ifExistClient);

        String phone;
        do {
            System.out.println("Enter phone number:");
            phone = scanner.nextLine();
        } while (!Validation.isValidPhoneNumber(phone));
        String password;
        String password2;
        do {
            System.out.println("Enter password:");
            password = scanner.nextLine();
            System.out.println("Repeat password:");
            password2 = scanner.nextLine();
        } while (!Objects.equals(password, password2));

        try {
            String passwordHash = PasswordProcessing.passwordHash(password);
            String id = UUID.randomUUID().toString();

            Client newClient = new Client(firstName, secondName, email, phone, id, passwordHash);

            ClientsJsonPojo clientsJsonPojo = new ClientsJsonPojo();
            clientsJsonPojo.clientPogoToJson(newClient);
            System.out.println("Client registered successfully.");
            return Optional.of(newClient);
        } catch (IOException e) {
            System.err.println("Failed to register the client due to an IO error.");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("An error occurred during registration.");
            e.printStackTrace();
        }
        return Optional.empty();
    }

}

