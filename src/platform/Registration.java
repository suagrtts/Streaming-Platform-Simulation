package platform;

<<<<<<< HEAD
import user.User;
import user.FreeUser;
import user.PremiumUser;
import billing.FreeSubscription;
import billing.PremiumSubscription;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Registration {

    private Platform platform;

    public Registration(Platform platform) {
        this.platform = platform;
    }

    public boolean register(String username, String password, String email, String subscriptionType) {
        if (subscriptionType.equalsIgnoreCase("free")) {
            FreeUser newUser = new FreeUser(username, password, email);
            FreeSubscription freeSubscription = new FreeSubscription(username);
            newUser.setSubscription(freeSubscription);
            platform.addUser(newUser);
            platform.addSubscription(freeSubscription);
            return true;
        } else if (subscriptionType.equalsIgnoreCase("premium")) {
            PremiumUser newUser = new PremiumUser(username, password, email);
            PremiumSubscription premiumSubscription = new PremiumSubscription(username, "monthly");
            newUser.setSubscription(premiumSubscription);
            platform.addUser(newUser);
            platform.addSubscription(premiumSubscription);
            return true;
        }
        return false;

    public boolean isUsernameTaken(String username) {
        return platform.getUsers().stream().anyMatch(user -> user.getUsername().equals(username));
    }

    public boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".");
    }

    public boolean isValidPassword(String password) {
        return password.length() >= 6;
    }   

    public void displayRegistrationInfo(){
        System.out.println("Registration information displayed.");
    }

    public boolean registerNewUser(String username, String password, String email, String subscriptionType) {
        if (!isValidEmail(email) || !isValidPassword(password) || isUsernameTaken(username)) {
            return false;
        }
        return register(username, password, email, subscriptionType);
    }

=======
import user.*;
import java.util.*;

public class Registration {
    private Scanner scan = new Scanner(System.in);
>>>>>>> 6fc0daec17bba5660cc5cceb95d62e0834914aaf
    public void registerNewUser(){
        InputAuthenticator inputAuth = new InputAuthenticator();
        User user = new User();

        while(true){
            try{
                System.out.print("Enter Email: ");
                String email = scan.nextLine();
                if(!inputAuth.isValidEmail(email)){
                    throw new InputMismatchException("Invalid email format! Try again.");
                }
                user.setEmail(email);
                break;
            }catch(InputMismatchException e){
                System.out.println(e.getMessage());
            }
        }

        while(true){
            try{
                System.out.print("Enter Password: ");
                String password = scan.nextLine();
                if(!inputAuth.isValidPassword(password)){
                    throw new InputMismatchException("Invalid password format! Try again.");
                }
                user.setPassword(password);
                break;
            }catch(InputMismatchException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void choosePlanType(){
        User user = new User();

        while(true){
            try{
                System.out.println("Plan Types:");
                System.out.println("Free");
                System.out.println("Premium");
                System.out.print("Enter a plan type (free/premium): ");
                String type = scan.nextLine();
    
                if(!type.equals("free") && !type.equals("premium")){
                    throw new InputMismatchException("Invalid plan type. Choose again!");
                }
                user.setSubscriptionType(type);
                break;
            }catch(InputMismatchException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
