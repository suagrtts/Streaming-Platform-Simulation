package admin;

import java.util.List;
import content.Media;
import user.User;

public class AdminUser extends User {

    public AdminUser(String username) {
        super(username);
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

        boolean found = false;

        for (int i = 0; i < mediaList.size(); i++) {
            if (mediaList.get(i).getTitle().equalsIgnoreCase(title)) {
                mediaList.remove(i);
                found = true;
                break; 
            }
        }

        if (found) {
            System.out.println("Media removed: " + title);
        } else {
            System.out.println("Media not found: " + title);
        }
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
    public String getAccessLevel() {
        return "Admin Access: Full control over media management.";
    }
}