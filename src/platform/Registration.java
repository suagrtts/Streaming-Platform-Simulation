package platform;

import java.util.InputMismatchException;
import java.util.Scanner;
import user.User;
import user.FreeUser;
import user.PremiumUser;

public class Registration {
    private final Scanner scan = new Scanner(System.in);
    private User user;
    private String email;
    private String password;
    private String username;

    public void registerNewUser() {
        InputAuthenticator inputAuth = new InputAuthenticator();

        while (true) {
            try {
                System.out.print("Enter Email: ");
                String e = scan.nextLine();
                if (!inputAuth.isValidEmail(e))
                    throw new InputMismatchException("Invalid email format! Must contain '@' and a domain.");
                this.email = e;
                break;
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.print("Enter Password: ");
                String p = scan.nextLine();
                if (!inputAuth.isValidPassword(p))
                    throw new InputMismatchException("Invalid password! Must be 8+ chars with upper, lower, digit, and special character.");
                this.password = p;
                break;
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void setUsername() {
        System.out.print("Please enter a username: ");
        this.username = scan.nextLine();
    }

    public void choosePlanType() {
        while (true) {
            try {
                System.out.print("Enter a plan type (free/premium): ");
                String type = scan.nextLine().trim().toLowerCase();
                if (!type.equals("free") && !type.equals("premium"))
                    throw new InputMismatchException("Invalid plan type. Choose again!");

                if (type.equals("free")) {
                    this.user = new FreeUser(username, email, password);
                } else {
                    this.user = new PremiumUser(username, email, password);
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public User getUser() { return user; }
}