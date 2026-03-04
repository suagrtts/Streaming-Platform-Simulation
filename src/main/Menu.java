package main;

import admin.*;
import billing.*;
import content.*;
import java.util.*;
import platform.*;
import user.*;

public class Menu {
    private final Scanner scan = new Scanner(System.in);
    private User user = new User();
    private final ContentLibrary library = new ContentLibrary();
    private final Platform platform = new Platform();
    private final WatchHistory history = new WatchHistory();

    public void showMenu(){
        Registration registration = new Registration();

        displayWelcome();

        System.out.println("Please register a new user");
        registration.registerNewUser();
        registration.setUsername();
        this.user = registration.getUser();
        displayPlanTypes(user.getUsername());

        registration.choosePlanType();
        
        platform.setCurrentUser(user.getUsername());
        platform.setCurrentSubsription(user.getSubscriptionType());

        menuOptions();
    }

    public void menuOptions(){
        System.out.println("Menu");
        System.out.println("1. Search");
        System.out.println("2. Movies");
        System.out.println("3. Series ");
        System.out.println("4. Watch History");
        System.out.println("5. Log out");

        int option = 0;
        while(true){
            try{
                System.out.print("Choose an option: ");
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
                scan.nextLine();
                System.out.print("Enter a Movie/Series title to search: ");
                String title = scan.nextLine();
                search(title);
            }
            case 2 -> {
                displayMovieTitles();
            }
            case 3 -> {
                displaySeriesTitles();
            }
            case 4 -> {
                displayWatchHistory();
            }
            case 5 ->{
                break;
            }
        }
    }

    public void displayPlanTypes(String username){
        Subscription freeSubs = new FreeSubscription(user.getUsername());
        Subscription premSubs = new PremiumSubscription(user.getUsername(), "Premium");
        System.out.println("Choose the plan that's right for you!");

        freeSubs.displayPlanDetails();
        premSubs.displayPlanDetails();

    }

    public void search(String title){
        Media media = library.findByTitle(title);

        if(media == null){
            System.out.println("No media found with the title: " + title);
        }else{
            System.out.println("=== Media Found ===");
            System.out.println(media.getDetails());
            System.out.println();
            System.out.print("Do you want to play it? (y/n): ");
            String choice = scan.nextLine();
            if (choice.equalsIgnoreCase("y")) {
                history.addRecord(title);
                media.play();

                while(true){
                    try{
                        System.out.println("Enter 0 to exit: ");
                        int exit = scan.nextInt();

                        if(exit != 0){
                            throw new InputMismatchException("Please enter 0 to exit."); 
                        }
                        menuOptions();
                    }catch(InputMismatchException e){
                        System.out.println(e.getMessage());
                    }
                }
            }else{
                menuOptions();
            }
        }
    }

    public void displayMovieTitles(){
        List<String> allMovieTitles = library.getAllMovieTitles();
        List<String> freeMovieTitles = library.getAllFreeMovieTitles();

        System.out.println("All Movies");

        if(platform.getCurrentSubscription().equals("free")){
            for(int i = 0; i < freeMovieTitles.size(); i++){
                System.out.println((i + 1) + ". " + freeMovieTitles.get(i));
            }
        }else{
            for(int i = 0; i < allMovieTitles.size(); i++){
                System.out.println((i + 1) + ". " + allMovieTitles.get(i));
            }
        }

        System.out.println("0. Exit");
        
        while(true){
            try{
                System.out.print("Choose a movie title: ");
                int choice = scan.nextInt();
                scan.nextLine(); // Consume the newline character

                if(choice == 0){
                    menuOptions();
                    return;
                }
                if (choice < 1 || choice > freeMovieTitles.size()) {
                    System.out.println("Invalid option. Choose again!");
                    continue;
                }

                String title;
                if(platform.getCurrentSubscription().equals("premium")){
                    title = allMovieTitles.get(choice - 1);
                }else{
                    title = freeMovieTitles.get(choice - 1);
                }

                search(title);
                break;
            }catch(InputMismatchException e){
                System.out.println("Invalid option. Choose again!");
                scan.nextLine();
            }
        }
            
    }

    public void displaySeriesTitles(){
        List<String> allSeriesTitles = library.getAllSeriesTitles();
        List<String> freeSeriesTitles = library.getAllFreeSeriesTitles();

        System.out.println("All Movies");

        if(platform.getCurrentSubscription().equals("free")){
            for(int i = 0; i < freeSeriesTitles.size(); i++){
                System.out.println((i + 1) + ". " + freeSeriesTitles.get(i));
            }
        }else{
            for(int i = 0; i < allSeriesTitles.size(); i++){
                System.out.println((i + 1) + ". " + allSeriesTitles.get(i));
            }
        }

        System.out.println("0. Exit");

        while(true){
            try{
                System.out.print("Choose a series title: ");
                int choice = scan.nextInt();
                scan.nextLine(); // Consume the newline character

                if(choice == 0){
                    menuOptions();
                    return;
                }

                if (choice < 1 || choice > freeSeriesTitles.size()) {
                    System.out.println("Invalid option. Choose again!");
                    continue;
                }

                String title;
                if(platform.getCurrentSubscription().equals("premium")){
                    title = allSeriesTitles.get(choice - 1);
                }else{
                    title = freeSeriesTitles.get(choice - 1);
                }

                search(title);
                break;
            }catch(InputMismatchException e){
                System.out.println("Invalid option. Try Again!");
                scan.nextLine();
            }
        }
    }

    public void displayWatchHistory(){
        List<String> historyTitles = history.getHistoryList();

        System.out.println("Your Watch History");
        for(int i = 0; i < historyTitles.size(); i++){
            System.out.println((i + 1) + ". " + historyTitles.get(i));
        }

        System.out.println((historyTitles.size() + 1) + ". Clear History List");
        System.out.println("0. Exit");

        while(true){
            try{
                System.out.print("Enter a number: ");
                int choice = scan.nextInt();

                if(choice == 0) {
                    menuOptions();
                    return;
                }
                if(choice == historyTitles.size() + 1) {
                    history.clearHistory();
                    return;
                }

                if(choice < 1 || choice > historyTitles.size()) {
                    System.out.println("Invalid option. Choose again!");
                    continue;
                }

                String title = historyTitles.get(choice - 1);
                search(title);
                break;
            }catch(InputMismatchException e){
                System.out.println("Invalid Choice. Try again!");
                scan.nextLine();
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
        System.out.println("║            Your Entertainment, Anytime, Anywhere          ║");
        System.out.println("║                                                              ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("  [ Movies ]   [ Series ]   [ Live TV ]   [ My List ]");
        System.out.println();
        System.out.println("──────────────────────────────────────────────────────────────");
        System.out.println("               Please register to continue.");
        System.out.println("──────────────────────────────────────────────────────────────");
        System.out.println();
    }
}
