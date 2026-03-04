package admin;

import java.util.ArrayList;
import java.util.List;

import content.Media;
import user.User;

public class Recommendation {

    public List<Media> recommendMedia(User user, List<Media> allMedia) {

        List<Media> recommended = new ArrayList<>();

        if (user.getWatchHistory() == null || 
            user.getWatchHistory().getWatchedMedia().isEmpty()) {
            return recommended;
        }

        Media lastWatched = user.getWatchHistory()
                                 .getWatchedMedia()
                                 .get(user.getWatchHistory()
                                          .getWatchedMedia().size() - 1);

        String preferredGenre = lastWatched.getGenre();

        for (Media media : allMedia) {
            if (!user.getWatchHistory().getWatchedMedia().contains(media) &&
                media.getGenre().equalsIgnoreCase(preferredGenre)) {

                recommended.add(media);
            }
        }

        return recommended;
    }
}