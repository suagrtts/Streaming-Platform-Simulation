package admin;

import content.Media;
import user.User;
import java.util.ArrayList;
import java.util.List;

public class Recommendation {

    public static List<Media> recommendMedia(User user, List<Media> allMedia) {
        List<Media> recommended = new ArrayList<>();

        if (user == null || user.getWatchHistory() == null) return recommended;

        List<Media> watched = user.getWatchHistory().getWatchedMedia();

        if (watched == null || watched.isEmpty()) return recommended;

        String preferredGenre = watched.get(watched.size() - 1).getGenre();

        for (Media media : allMedia) {
            if (media != null
                    && !watched.contains(media)
                    && media.getGenre().equalsIgnoreCase(preferredGenre)) {
                recommended.add(media);
            }
        }
        return recommended;
    }
}