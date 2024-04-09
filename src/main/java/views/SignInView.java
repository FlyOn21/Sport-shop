package views;


import java.io.Console;
import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;
import entity.Client;
import models.SignInModel;
import utils.Validation;


public class SignInView {
    final Scanner scanner = new Scanner(System.in);

    public Optional<Client> displaySignIn() throws IOException {

        while (true) {
            System.out.println("Please sign in to your account.");

            String email;
            boolean checkEmail;
            do {
                System.out.print("Email: ");
                email = scanner.nextLine();
                checkEmail = Validation.isValidEmail(email);
                if (!checkEmail) {
                    System.out.println("Incorrect email format");
                }

            } while (!checkEmail);

            Console console = System.console();

            String enteredPassword = new String(console.readPassword("Please enter your password: "));

            Optional<Client> isSignedIn = signInUser(email, enteredPassword);

            if (isSignedIn.isPresent()) {
                System.out.println("Sign-in successful. Welcome back!");
                return isSignedIn;
            } else {
                System.out.println("Sign-in failed. Please check your credentials and try again.");
                System.out.println("Do you want to try again? (yes/no)");
                String response = scanner.nextLine();
                if (!response.equalsIgnoreCase("yes")) {
                    return Optional.empty();
                }
            }
        }
    }


    private Optional<Client> signInUser(String email, String password) throws IOException {
        SignInModel signInModel = new SignInModel();
        return signInModel.signInClient(email, password);
    }
}
