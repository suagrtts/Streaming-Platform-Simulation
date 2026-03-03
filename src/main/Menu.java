package main;

import user.*;
import platform.*;
import java.util.*;

public class Menu {
    private Scanner scan = new Scanner(System.in);
    public void showMenu(){
        Registration registration = new Registration();

        displayWelcome();

        System.out.println("Please register a new user");
        registration.registerNewUser();

        registration.choosePlanType();

        
        
    }

    public void menuOptions(){
        System.out.println("1. Menu");
        System.out.println("2. Movies");
        System.out.println("3. Series ");
        System.out.println("4. Watch History");
        System.out.println("5. Log out");

        int option = 0;
        while(true){
            try{
                System.out.println("Choose an option: ");
                option = scan.nextInt();

                if(option <= 0){
                    throw new InputMismatchException("Invalid option. Try again!");
                }
                break;
            }catch(InputMismatchException e){
                System.out.println(e.getMessage());
            }
        }

        switch(option){
            case 1 -> {

            }
        }
    }

    public static void displayWelcome() {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                              ║");
        System.out.println("║   ░██████╗████████╗██████╗░███████╗░█████╗░███╗░░░███╗     ║");
        System.out.println("║   ██╔════╝╚══██╔══╝██╔══██╗██╔════╝██╔══██╗████╗░████║     ║");
        System.out.println("║   ╚█████╗░░░░██║░░░██████╔╝█████╗░░███████║██╔████╔██║     ║");
        System.out.println("║   ░╚═══██╗░░░██║░░░██╔══██╗██╔══╝░░██╔══██║██║╚██╔╝██║     ║");
        System.out.println("║   ██████╔╝░░░██║░░░██║░░██║███████╗██║░░██║██║░╚═╝░██║     ║");
        System.out.println("║   ╚═════╝░░░░╚═╝░░░╚═╝░░╚═╝╚══════╝╚═╝░░╚═╝╚═╝░░░░╚═╝     ║");
        System.out.println("║                                                              ║");
        System.out.println("║          ██████╗░██╗░░░░░░█████╗░██╗░░░██╗                 ║");
        System.out.println("║          ██╔══██╗██║░░░░░██╔══██╗╚██╗░██╔╝                 ║");
        System.out.println("║          ██████╔╝██║░░░░░███████║░╚████╔╝░                 ║");
        System.out.println("║          ██╔═══╝░██║░░░░░██╔══██║░░╚██╔╝░░                 ║");
        System.out.println("║          ██║░░░░░███████╗██║░░██║░░░██║░░░                 ║");
        System.out.println("║          ╚═╝░░░░░╚══════╝╚═╝░░╚═╝░░░╚═╝░░░                 ║");
        System.out.println("║                                                              ║");
        System.out.println("║          🎬  Your Entertainment, Anytime, Anywhere  🎬      ║");
        System.out.println("║                                                              ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("  [ Movies ]   [ Series ]   [ Live TV ]   [ My List ]");
        System.out.println();
        System.out.println("──────────────────────────────────────────────────────────────");
        System.out.println("  Please log in or register to continue.");
        System.out.println("──────────────────────────────────────────────────────────────");
        System.out.println();
    }
}
