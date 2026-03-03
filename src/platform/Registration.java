package platform;

import java.util.*;
import user.*;

public class Registration {
    private Scanner scan = new Scanner(System.in);
    public void registerNewUser(){
        InputAuthenticator inputAuth = new InputAuthenticator();
        User user = new User();

        while(true){
            try{
                System.out.print("Enter Email: ");
                String email = scan.nextLine();
                if(!inputAuth.isValidEmail(email)){
                    throw new InputMismatchException("Invalid email format! Try again. Must contain '@' and a domain.");
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
                    throw new InputMismatchException("Invalid password format! Try again. Must have at least 8 characters, including uppercase, lowercase, and a number.");
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
