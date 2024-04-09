package views;


import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;
import entity.Client;

public class RegistrationOrSignInView {
    private final Scanner scanner;

    public RegistrationOrSignInView() {
        this.scanner = new Scanner(System.in);
    }

    public Optional<Client> displayMenu() throws IOException {
        while (true) {
            String menu = """ 
                    ---------------------
                    Welcome to Sport Shop:
                    ---------------------
                    Choice 1 => Sign In
                    Choice 2 => Sign Up
                    Choice 3 => Exit
                    """;
            System.out.println(menu);
            System.out.print("Enter your choice: ");

            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    SignInView signInView = new SignInView();
                    Optional<Client> client = signInView.displaySignIn();
                    if (client.isPresent()) {
                        System.out.println("Successfully signed in as: " + client.get().email());
                        return client;
                    }
                    break;
                case "2":
                    RegistrationView registrationView = new RegistrationView();
                    return registrationView.registerClient();
                case "3":
                    scanner.close();
                    System.out.println("Exit...");
                    return Optional.empty();
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

}
