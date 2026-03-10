package main;

import admin.Recommendation;
import admin.WatchHistory;
import billing.FreeSubscription;
import billing.PremiumSubscription;
import billing.Subscription;
import content.ContentLibrary;
import content.Media;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import platform.Authentication;
import platform.Platform;
import platform.Registration;
import user.User;

public class Menu {
    private final Scanner scan = new Scanner(System.in);
    private User user;
    private final ContentLibrary library = new ContentLibrary();
    private final Platform platform = new Platform();
    private final WatchHistory history = new WatchHistory();

    public void showMenu() {
        displayWelcome();

        Authentication auth = new Authentication(platform);

        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.print("Choose: ");
        String choice = scan.nextLine().trim();

        if (choice.equals("1")) {
            Registration registration = new Registration();

            System.out.println("Please register a new user");
            registration.registerNewUser();
            registration.setUsername();
            displayPlanTypes();
            registration.choosePlanType();

            this.user = registration.getUser();
            this.user.setWatchHistory(history);
            platform.addUser(this.user);

        } else if (choice.equals("2")) {
            System.out.print("Username: ");
            String username = scan.nextLine();
            System.out.print("Password: ");
            String password = scan.nextLine();

            User logged = auth.authenticate(username, password);
            if (logged == null) {
                System.out.println("Login failed. Exiting.");
                return;
            }
            this.user = logged;
            this.user.setWatchHistory(history);

        } else {
            System.out.println("Invalid choice. Exiting.");
            return;
        }

        platform.setCurrentUser(user.getUsername());
        platform.setCurrentSubsription(user.getSubscriptionType());

        menuOptions();
    }

    public void menuOptions() {
        while (true) {
            System.out.println("\nMenu");
            System.out.println("1. Search");
            System.out.println("2. Movies");
            System.out.println("3. Series");
            System.out.println("4. Recommendation");
            System.out.println("5. Watch History");
            System.out.println("6. Log out");

            int option;
            try {
                System.out.print("Choose an option: ");
                option = scan.nextInt();
                scan.nextLine(); // clear buffer
                if (option <= 0 || option > 6) {
                    System.out.println("Invalid option. Try again!");
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid option. Try again!");
                scan.nextLine();
                continue;
            }

            switch (option) {
                case 1 -> {
                    System.out.print("Enter a Movie/Series title to search: ");
                    String title = scan.nextLine();
                    search(title);
                }
                case 2 -> displayMovieTitles();
                case 3 -> displaySeriesTitles();
                case 4 -> displayRecommendation();
                case 5 -> displayWatchHistory();
                case 6 -> {
                    System.out.println("Logged out. Goodbye!");
                    return;
                }
            }

        }
    }

    public void displayPlanTypes() {
        Subscription freeSubs = new FreeSubscription(user != null ? user.getUsername() : "user");
        Subscription premSubs = new PremiumSubscription(user != null ? user.getUsername() : "user", "monthly");
        System.out.println("Choose the plan that's right for you!");
        freeSubs.displayPlanDetails();
        premSubs.displayPlanDetails();
    }

    public void search(String title) {
        Media media = library.findByTitle(title);

        if (media == null) {
            System.out.println("No media found with the title: " + title);
            return;
        }

        boolean isFreeUser = platform.getCurrentSubscription().equals("free");
        if (isFreeUser && media.getIsPremium()) {
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║  ⛔ PREMIUM CONTENT ACCESS DENIED    ║");
            System.out.println("║  Upgrade your plan to watch this.     ║");
            System.out.println("╚════════════════════════════════════════╝");
            return;
        }

        System.out.println("=== Media Found ===");
        System.out.println(media.getDetails());
        String quality = isFreeUser ? "480p" : "4K";
        System.out.println("Available Quality: " + quality);

        System.out.print("Do you want to play it? (y/n): ");
        String playChoice = scan.nextLine();

        if (playChoice.equalsIgnoreCase("y")) {
            history.addRecord(title);
            history.addWatchedMedia(media);
            playMedia(media, isFreeUser);
        }
    }

    private void playMedia(Media media, boolean showAds) {
        if (showAds) displayAd();
        media.play();
    }

    private void displayAd() {
        System.out.println();
        System.out.println("╔═══════════════════════════════════════╗");
        System.out.println("║          📺 ADVERTISEMENT 📺          ║");
        System.out.println("╠═══════════════════════════════════════╣");
        System.out.println("║  Upgrade to PREMIUM — ad-free in 4K! ║");
        System.out.println("║  Only $12.99/month                    ║");
        System.out.println("╚═══════════════════════════════════════╝");
        System.out.println("  (Skipping ad in 3 seconds...)");
        System.out.println();
    }

    public void displayMovieTitles() {
        String subscription = platform.getCurrentSubscription();
        List<String> availableTitles = library.getAccessibleMovieTitles(subscription);

        System.out.println("=== All Movies Available to You ===");
        if (availableTitles.isEmpty()) {
            System.out.println("No movies available for your subscription tier.");
            return;
        }

        for (int i = 0; i < availableTitles.size(); i++)
            System.out.println((i + 1) + ". " + availableTitles.get(i));

        if (subscription.equals("free"))
            System.out.println("\n💡 Tip: Upgrade to Premium for exclusive content!");

        System.out.println("0. Back");

        while (true) {
            try {
                System.out.print("Choose a movie: ");
                int movieChoice = scan.nextInt();
                scan.nextLine();

                if (movieChoice == 0) return;
                if (movieChoice < 1 || movieChoice > availableTitles.size()) {
                    System.out.println("Invalid option. Choose again!");
                    continue;
                }
                search(availableTitles.get(movieChoice - 1));
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid option. Choose again!");
                scan.nextLine();
            }
        }
    }

    public void displaySeriesTitles() {
        String subscription = platform.getCurrentSubscription();
        List<String> availableTitles = library.getAccessibleSeriesTitles(subscription);

        System.out.println("=== All Series Available to You ===");
        if (availableTitles.isEmpty()) {
            System.out.println("No series available for your subscription tier.");
            return;
        }

        for (int i = 0; i < availableTitles.size(); i++)
            System.out.println((i + 1) + ". " + availableTitles.get(i));

        if (subscription.equals("free"))
            System.out.println("\n💡 Tip: Upgrade to Premium for exclusive content!");

        System.out.println("0. Back");

        while (true) {
            try {
                System.out.print("Choose a series: ");
                int seriesChoice = scan.nextInt();
                scan.nextLine();

                if (seriesChoice == 0) return;
                if (seriesChoice < 1 || seriesChoice > availableTitles.size()) {
                    System.out.println("Invalid option. Choose again!");
                    continue;
                }
                search(availableTitles.get(seriesChoice - 1));
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid option. Try Again!");
                scan.nextLine();
            }
        }
    }

    public void displayWatchHistory() {
        List<String> historyTitles = history.getHistoryList();

        System.out.println("Your Watch History");
        if (historyTitles.isEmpty()) {
            System.out.println("Nothing watched yet.");
            return;
        }

        for (int i = 0; i < historyTitles.size(); i++)
            System.out.println((i + 1) + ". " + historyTitles.get(i));

        System.out.println((historyTitles.size() + 1) + ". Clear History");
        System.out.println("0. Back");

        while (true) {
            try {
                System.out.print("Enter a number: ");
                int histChoice = scan.nextInt();
                scan.nextLine();

                if (histChoice == 0) return;
                if (histChoice == historyTitles.size() + 1) {
                    history.clearHistory();
                    System.out.println("History cleared.");
                    return;
                }
                if (histChoice < 1 || histChoice > historyTitles.size()) {
                    System.out.println("Invalid option. Choose again!");
                    continue;
                }
                search(historyTitles.get(histChoice - 1));
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid Choice. Try again!");
                scan.nextLine();
            }
        }
    }

    public void displayRecommendation() {
        List<Media> allMedia = platform.getContentLibrary().getAllMedia();
        // Bug #5 fixed: call static method directly, no instantiation
        List<Media> recommended = Recommendation.recommendMedia(user, allMedia);

        if (recommended == null || recommended.isEmpty()) {
            System.out.println("No recommendations yet. Watch something first!");
            return;
        }

        System.out.println("Recommended for you:");
        for (int i = 0; i < recommended.size(); i++) {
            Media m = recommended.get(i);
            System.out.println((i + 1) + ". " + m.getTitle() + " (" + m.getGenre() + ")");
        }
        System.out.println("0. Back");

        while (true) {
            try {
                System.out.print("Choose a title to play: ");
                int recChoice = scan.nextInt();
                scan.nextLine();

                if (recChoice == 0) return;
                if (recChoice < 1 || recChoice > recommended.size()) {
                    System.out.println("Invalid option. Try again!");
                    continue;
                }
                search(recommended.get(recChoice - 1).getTitle());
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
        System.out.println("║            Your Entertainment, Anytime, Anywhere            ║");
        System.out.println("║                                                              ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
        System.out.println();
    }
}