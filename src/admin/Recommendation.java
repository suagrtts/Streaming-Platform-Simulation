package admin;

import content.Media;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

        // Improved Algorithm: Consider multiple genres and ratings
        Map<String, Integer> genreFrequency = new HashMap<>();
        double avgRating = 0.0;

        // Analyze watched content to identify preferences
        for (Media media : watched) {
            if (media != null) {
                String genre = media.getGenre();
                genreFrequency.put(genre, genreFrequency.getOrDefault(genre, 0) + 1);
                avgRating += media.getRating();
            }
        }

        if (!watched.isEmpty()) {
            avgRating /= watched.size();
        }

        // Find the most watched genre
        String preferredGenre = null;
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : genreFrequency.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                preferredGenre = entry.getKey();
            }
        }

        // Recommend media based on preferred genre and similar rating
        for (Media media : allMedia) {
            if (media == null) continue;

            boolean notWatched = !watched.contains(media);
            boolean genreMatch = preferredGenre != null && 
                                 media.getGenre().equalsIgnoreCase(preferredGenre);
            boolean ratingMatch = media.getRating() >= (avgRating - 1.0); // Within 1 point of avg

            if (notWatched && (genreMatch || ratingMatch)) {
                recommended.add(media);
            }
        }

        // If no recommendations yet, suggest highly-rated unwatched content
        if (recommended.isEmpty()) {
            for (Media media : allMedia) {
                if (media != null && !watched.contains(media) && media.getRating() >= 8.0) {
                    recommended.add(media);
                }
            }
        }

        return recommended;
    }
}