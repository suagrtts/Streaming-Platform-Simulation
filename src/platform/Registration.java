package auth;

import java.util.*;
import user.*;

public class Registration {
    public void displayRegistrationInfo(){
        System.out.println("Registration information displayed.");
    }

    public void registerNewUser(){
        Scanner scan = new Scanner(System.in);
        InputAuthenticator inputAuth = new InputAuthenticator();
        User user = new User();

        System.out.println("Welcome to a Streaming Platform Simulation");

        while(true){
            try{
                System.out.println("Enter Email: ");
                String email = scan.nextLine();
                if(!inputAuth.isValidEmail(email)){
                    throw new InputMismatchException("Invalid email format! Try again.");
                }
                break;
            }catch(InputMismatchException e){
                System.out.println(e.getMessage());
                scan.nextLine();
            }
        }

        while(true){
            try{
                System.out.println("Enter Password: ");
                String password = scan.nextLine();
                if(!inputAuth.isValidPassword(password)){
                    throw new InputMismatchException("Invalid password format! Try again.");
                }
                break;
            }catch(InputMismatchException e){
                System.out.println(e.getMessage());
                scan.nextLine();
            }
        }
    }
}