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
        // attach shared watch history to user so admin.Recommendation can use it
        this.user.setWatchHistory(history);
        displayPlanTypes(user.getUsername());

        registration.choosePlanType();
        
        // Store the user in the platform for persistence
        platform.addUser(this.user);
        
        platform.setCurrentUser(user.getUsername());
        platform.setCurrentSubscription(user.getSubscriptionType());

        menuOptions();
    }

    public void menuOptions(){
        System.out.println("Menu");
        System.out.println("1. Search");
        System.out.println("2. Movies");
        System.out.println("3. Series");
        System.out.println("4. Recommendation");
        System.out.println("5. Watch History");
        System.out.println("6. Log out");

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
                displayRecommendation();
            }
            case 5 -> {
                displayWatchHistory();
            }
            case 6 ->{
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
            menuOptions();
            return;
        }

        // Check if user has access to this content
        boolean isFreeUser = platform.getCurrentSubscription().equals("free");
        if (isFreeUser && media.getIsPremium()) {
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║  ⛔ PREMIUM CONTENT ACCESS DENIED    ║");
            System.out.println("║  This content is only for Premium     ║");
            System.out.println("║  members. Upgrade your plan to watch. ║");
            System.out.println("╚════════════════════════════════════════╝");
            System.out.println();
            menuOptions();
            return;
        }

        System.out.println("=== Media Found ===");
        System.out.println(media.getDetails());
        
        // Display quality restriction info
        String quality = isFreeUser ? "480p" : "4K";
        System.out.println("Available Quality: " + quality);
        System.out.println();
        
        System.out.print("Do you want to play it? (y/n): ");
        String choice = scan.nextLine();
        
        if (choice.equalsIgnoreCase("y")) {
            history.addRecord(title);
            history.addWatchedMedia(media);
            
            // Pass subscription type to determine ads
            boolean showAds = isFreeUser;
            playMedia(media, showAds);
            
            while(true){
                try{
                    System.out.println("Enter 0 to exit: ");
                    int exit = scan.nextInt();

                    if(exit != 0){
                        throw new InputMismatchException("Please enter 0 to exit."); 
                    }
                    menuOptions();
                    break;
                }catch(InputMismatchException e){
                    System.out.println(e.getMessage());
                    scan.nextLine();
                }
            }
        }else{
            menuOptions();
        }
    }

    private void playMedia(Media media, boolean showAds) {
        if (showAds) {
            displayAd();
        }
        media.play();
    }
    
    private void displayAd() {
        System.out.println();
        System.out.println("╔═══════════════════════════════════════╗");
        System.out.println("║          📺 ADVERTISEMENT 📺          ║");
        System.out.println("╠═══════════════════════════════════════╣");
        System.out.println("║  Upgrade to PREMIUM and enjoy          ║");
        System.out.println("║  ad-free streaming in 4K quality!      ║");
        System.out.println("║                                       ║");
        System.out.println("║  Only $12.99/month                    ║");
        System.out.println("╚═══════════════════════════════════════╝");
        System.out.println("  (Skipping ad in 3 seconds...)");
        System.out.println();
    }

    public void displayMovieTitles(){
        // Get titles based on subscription type
        String subscription = platform.getCurrentSubscription();
        List<String> availableTitles = library.getAccessibleMovieTitles(subscription);

        System.out.println("=== All Movies Available to You ===");
        
        if(availableTitles.isEmpty()){
            System.out.println("No movies available for your subscription tier.");
            menuOptions();
            return;
        }

        for(int i = 0; i < availableTitles.size(); i++){
            System.out.println((i + 1) + ". " + availableTitles.get(i));
        }

        if(subscription.equals("free")){
            System.out.println("\n💡 Tip: Upgrade to Premium to access exclusive content!");
        }
        
        System.out.println("0. Exit");
        
        while(true){
            try{
                System.out.print("Choose a movie title: ");
                int choice = scan.nextInt();
                scan.nextLine();

                if(choice == 0){
                    menuOptions();
                    return;
                }
                
                if (choice < 1 || choice > availableTitles.size()) {
                    System.out.println("Invalid option. Choose again!");
                    continue;
                }

                String title = availableTitles.get(choice - 1);
                search(title);
                break;
            }catch(InputMismatchException e){
                System.out.println("Invalid option. Choose again!");
                scan.nextLine();
            }
        }
            
    }

    public void displaySeriesTitles(){
        // Get titles based on subscription type
        String subscription = platform.getCurrentSubscription();
        List<String> availableTitles = library.getAccessibleSeriesTitles(subscription);

        System.out.println("=== All Series Available to You ===");
        
        if(availableTitles.isEmpty()){
            System.out.println("No series available for your subscription tier.");
            menuOptions();
            return;
        }

        for(int i = 0; i < availableTitles.size(); i++){
            System.out.println((i + 1) + ". " + availableTitles.get(i));
        }

        if(subscription.equals("free")){
            System.out.println("\n💡 Tip: Upgrade to Premium to access exclusive content!");
        }

        System.out.println("0. Exit");

        while(true){
            try{
                System.out.print("Choose a series title: ");
                int choice = scan.nextInt();
                scan.nextLine();

                if(choice == 0){
                    menuOptions();
                    return;
                }

                if (choice < 1 || choice > availableTitles.size()) {
                    System.out.println("Invalid option. Choose again!");
                    continue;
                }

                String title = availableTitles.get(choice - 1);
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

    public void displayRecommendation(){
        Recommendation recommendation = new Recommendation();
        List<Media> allMedia = platform.getContentLibrary().getAllMedia();
        List<Media> recommended = recommendation.recommendMedia(user, allMedia);

        if (recommended == null || recommended.isEmpty()) {
            System.out.println("No recommendations yet. Watch something first!");
            menuOptions();
            return;
        }

        System.out.println("Recommended for you:");
        for (int i = 0; i < recommended.size(); i++) {
            Media m = recommended.get(i);
            System.out.println((i + 1) + ". " + m.getTitle() + " (" + m.getGenre() + ")");
        }
        System.out.println("0. Exit");

        while (true) {
            try {
                System.out.print("Choose a title to play: ");
                int choice = scan.nextInt();

                if (choice == 0) {
                    menuOptions();
                    return;
                }

                if (choice < 1 || choice > recommended.size()) {
                    System.out.println("Invalid option. Try again!");
                    continue;
                }

                Media selected = recommended.get(choice - 1);
                search(selected.getTitle());
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid option. Try again!");
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
