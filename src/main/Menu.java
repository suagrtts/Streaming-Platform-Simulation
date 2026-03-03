package main;

import content.*;
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
                search();
            }
            case 2 -> {
                displayMovieTitles();
            }
            case 3 -> {
                displaySeriesTitles();
            }
        }
    }

    public void search(String title){
        ContentLibrary content = new ContentLibrary();
        Media media = content.findByTitle(title);

        if(media == null){
            System.out.println("No media found with the title: " + title);
        }else{
            System.out.println("=== Media Found ===");
            System.out.println(media.getDetails());
            System.out.println();
            scan.nextLine();
            System.out.print("Do you want to play it? (y/n): ");
            String choice = scan.nextLine();
            if (choice.equalsIgnoreCase("y")) {
                media.play();
            }
        }
    }

    public void search(){
        ContentLibrary content = new ContentLibrary();
        scan.nextLine();

        System.out.print("Enter a Movie/Series title to search: ");
        String title = scan.nextLine();

        Media media = content.findByTitle(title);

        if(media == null){
            System.out.println("No media found with the title: " + title);
        }else{
            System.out.println("=== Media Found ===");
            System.out.println(media.getDetails());
            System.out.println();
            System.out.print("Do you want to play it? (y/n): ");
            String choice = scan.nextLine();
            if (choice.equalsIgnoreCase("y")) {
                media.play();
            }
        }
    }

    public void displayMovieTitles(){
        ContentLibrary library = new ContentLibrary();

        List<String> movieTitles = library.getAllMovieTitles();

        System.out.println("All Movies");

        for(int i = 0; i < movieTitles.size(); i++){
            System.out.println((i + 1) + ". " + movieTitles.get(i));
        }

        while(true){
            try{
                System.out.print("Choose a movie title: ");
                int choice = scan.nextInt();

                String title = movieTitles.get(choice - 1);

                search(title);
                break;
            }catch(InputMismatchException e){
                System.out.println("Invalid option. Choose again!");
                scan.nextLine();
            }
        }
            
    }

    public void displaySeriesTitles(){
        ContentLibrary library = new ContentLibrary();

        List<String> seriesTitles = library.getAllSeriesTitles();

        System.out.println("All Series");

        for(int i = 0; i < seriesTitles.size(); i++){
            System.out.println((i + 1) + ". " + seriesTitles.get(i));
        }

        while(true){
            try{
                System.out.print("Choose a series title: ");
                int choice = scan.nextInt();

                String title = seriesTitles.get(choice - 1);

                search(title);
                break;
            }catch(InputMismatchException e){
                System.out.println("Invalid option. Try Again!");
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
        System.out.println("  Please log in or register to continue.");
        System.out.println("──────────────────────────────────────────────────────────────");
        System.out.println();
    }
}
