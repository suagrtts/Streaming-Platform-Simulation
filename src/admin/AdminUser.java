package admin;

import content.*;
import user.*;
import java.util.List;

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
        if (title != null) {
            boolean removed = mediaList.removeIf(
                media -> media.getTitle().equalsIgnoreCase(title)
            );

            if (removed) {
                System.out.println("Media removed: " + title);
            } else {
                System.out.println("Media not found: " + title);
            }
        } else {
            System.out.println("Title cannot be null.");
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
}