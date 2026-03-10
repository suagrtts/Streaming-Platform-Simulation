package user;

import content.Media;
import java.util.List;

public class Admin extends User {

    public Admin(String username, String email, String password) {
        super(username, email, password);
        setSubscriptionType("admin");
    }

    public Admin() {
        super("", "", "");
        setSubscriptionType("admin");
    }

    // --- Permission flags ---
    public boolean isAdmin()              { return true; }
    public boolean hasContentModeration() { return true; }
    public boolean canManageUsers()       { return true; }

    @Override
    public String getAccessLevel() {
        return "Admin Access: Full control over the platform, including user management and content moderation.";
    }

    public void addMedia(List<Media> mediaList, Media media) {
        if (media != null) {
            mediaList.add(media);
            System.out.println("Media added: " + media.getTitle());
        } else {
            System.out.println("Cannot add null media.");
        }
    }

    public void removeMedia(List<Media> mediaList, String title) {
        if (title == null) {
            System.out.println("Title cannot be null.");
            return;
        }
        boolean found = mediaList.removeIf(m -> m.getTitle().equalsIgnoreCase(title));
        System.out.println(found ? "Media removed: " + title : "Media not found: " + title);
    }

    public void updateMedia(Media media, String newTitle) {
        if (media != null && newTitle != null) {
            media.setTitle(newTitle);
            System.out.println("Media updated to: " + newTitle);
        } else {
            System.out.println("Invalid media or title.");
        }
    }

    @Override
    public String toString() {
        return "Admin{" +
                "username='" + getUsername() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", subscriptionType='" + getSubscriptionType() + '\'' +
                '}';
    }
}