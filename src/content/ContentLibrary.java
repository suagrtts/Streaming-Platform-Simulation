package content;

import java.util.*;

public class ContentLibrary {
    private final List<Media> mediaList = new ArrayList<>();

    public ContentLibrary(){
        // --- Movies ---
        mediaList.add(new Movie(
                "Inception", "Sci-Fi", 8.8, 2010,
                "A thief who steals corporate secrets through dream-sharing tech.",
                148,
                "Leonardo DiCaprio, Joseph Gordon-Levitt",
                "Christopher Nolan"
        ));

        mediaList.add(new Movie(
                "The Matrix", "Action/Sci-Fi", 8.7, 1999,
                "A hacker discovers the shocking truth about reality.",
                136,
                "Keanu Reeves, Laurence Fishburne",
                "The Wachowskis"
        ));

        mediaList.add(new Movie(
                "Interstellar", "Sci-Fi/Drama", 8.6, 2014,
                "A team of explorers travel through a wormhole in space to ensure humanity's survival.",
                169,
                "Matthew McConaughey, Anne Hathaway, Jessica Chastain",
                "Christopher Nolan"
        ));

        mediaList.add(new Movie(
                "Spirited Away", "Animation/Fantasy", 8.6, 2001,
                "A young girl enters a world of spirits and must work to free herself and her parents.",
                125,
                "Rumi Hiiragi, Miyu Irino",
                "Hayao Miyazaki"
        ));

        mediaList.add(new Movie(
                "The Dark Knight", "Action/Crime", 9.0, 2008,
                "Batman faces the Joker, a criminal mastermind who plunges Gotham into chaos.",
                152,
                "Christian Bale, Heath Ledger, Aaron Eckhart",
                "Christopher Nolan"
        ));

        // --- Series ---
        mediaList.add(new Series(
                "Breaking Bad", "Crime/Drama", 9.5, 2008,
                "A chemistry teacher turns to making methamphetamine after a cancer diagnosis.",
                5,
                62
        ));

        mediaList.add(new Series(
                "Stranger Things", "Sci-Fi/Horror", 8.7, 2016,
                "A group of kids encounter supernatural forces and secret government experiments in their town.",
                4,
                34
        ));

        mediaList.add(new Series(
                "Game of Thrones", "Fantasy/Drama", 9.2, 2011,
                "Noble families vie for control of the Iron Throne in the land of Westeros.",
                8,
                73
        ));

        mediaList.add(new Series(
                "Avatar: The Last Airbender", "Animation/Adventure", 9.3, 2005,
                "A young Avatar must master all four elements and save the world from the Fire Nation.",
                3,
                61
        ));
    }

    public List<Media> getAllMedia() {
        return mediaList;
    }

    public void addMedia(Media media) {
        mediaList.add(media);
    }

    public Media findByTitle(String title) {
        for (Media m : mediaList) {
            if (m.getTitle().equalsIgnoreCase(title)) {
                return m;
            }
        }
        return null;
    }
}
