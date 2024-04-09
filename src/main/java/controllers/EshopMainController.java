package controllers;

import java.io.IOException;
import java.util.Optional;
import entity.Client;
import views.RegistrationOrSignInView;
import views.EshopView;

public class EshopMainController {

    public void eshopProcessing() {
        try {
            RegistrationOrSignInView registrationOrSignInView = new RegistrationOrSignInView();
            Optional<Client> client = registrationOrSignInView.displayMenu();

            if (client.isPresent()) {
                EshopView eshopView = new EshopView(client.get());
                eshopView.showMenu();
            } else {
                System.out.println("Goodbye!");
            }
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
