package content;

public class Series extends Media {

    // --- Private Attributes ---
    private int totalSeasons;
    private int totalEpisodes;
    private int currentEpisode;
    private int currentSeason;

    // --- Constructor ---
    public Series(String title, String genre, double rating, int releaseYear,
                  String description, int totalSeasons, int totalEpisodes) {
        super(title, genre, rating, releaseYear, description);
        this.totalSeasons = totalSeasons;
        this.totalEpisodes = totalEpisodes;
        this.currentEpisode = 1;
        this.currentSeason = 1;
    }

    @Override
    public void play() {
        System.out.println("============================================");
        System.out.println("  NOW PLAYING: " + getTitle() + " (" + getReleaseYear() + ")");
        System.out.println("  Genre: " + getGenre() + " | Rating: " + getRating());
        System.out.println("  Season " + currentSeason + " — Episode " + currentEpisode);
        System.out.println("  " + getDescription());
        System.out.println("============================================");
        System.out.println("  Playing episode...");
        nextEpisode();
        System.out.println("  Up next: Season " + currentSeason + ", Episode " + currentEpisode);
    }

    /**
     * Returns title, genre, seasons, and episodes.
     */
    @Override
    public String getDetails() {
        return "SERIES: " + getTitle() +
                "\nGenre: " + getGenre() +
                "\nRating: " + getRating() +
                "\nRelease Year: " + getReleaseYear() +
                "\nTotal Seasons: " + totalSeasons +
                "\nTotal Episodes: " + totalEpisodes +
                "\nCurrently On: Season " + currentSeason + ", Episode " + currentEpisode +
                "\nDescription: " + getDescription();
    }

    public void nextEpisode() {
        int episodesPerSeason = totalEpisodes / totalSeasons;
        if (currentEpisode < episodesPerSeason) {
            currentEpisode++;
        } else if (currentSeason < totalSeasons) {
            currentSeason++;
            currentEpisode = 1;
        } else {
            System.out.println("  You have finished all episodes of " + getTitle() + "!");
        }
    }


    public int getTotalSeasons() {
        return totalSeasons;
    }

    public int getTotalEpisodes() {
        return totalEpisodes;
    }

    public int getCurrentEpisode() {
        return currentEpisode;
    }

    public int getCurrentSeason() {
        return currentSeason;
    }
}