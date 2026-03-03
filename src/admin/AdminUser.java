package admin;

import java.util.List;

public class AdminUser extends User {

    public AdminUser(String username) {
        super(username);
    }

    public void addMedia(List<Media> mediaList, Media media) {
        if (media != null) {
            mediaList.add(media);
            System.out.println("Media added: " + media.getTitle());
        }
    }

    public void removeMedia(List<Media> mediaSightList, String title) {
        mediaList.removeIf(media -> media.getTitle().equalsIgnoreCase(title));
        System.out.println("Media removed: " + title);
    }

    public void updateMedia(Media media, String newTitle) {
        if (media != null && newTitle != null) {
            media.setTitle(newTitle);
            System.out.println("Media updated to: " + newTitle);
        }
    }
}