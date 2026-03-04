package admin;

import java.util.ArrayList;
import java.util.List;

import content.Media;
import user.User;

public class Recommendation {

    public List<Media> recommendMedia(User user, List<Media> allMedia) {

        List<Media> recommended = new ArrayList<>();

        if (user == null || user.getWatchHistory() == null) {
            return recommended;
        }

        List<Media> watched = user.getWatchHistory().getWatchedMedia();

        if (watched == null || watched.isEmpty()) {
            return recommended;
        }

        Media lastWatched = watched.get(watched.size() - 1);
        String preferredGenre = lastWatched.getGenre();

        for (Media media : allMedia) {
            if (media == null) continue;

            if (!watched.contains(media) &&
                media.getGenre().equalsIgnoreCase(preferredGenre)) {

                recommended.add(media);
            }
        }

        return recommended;
    }
}