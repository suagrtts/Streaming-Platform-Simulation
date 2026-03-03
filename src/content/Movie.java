package content;

public class Movie extends Media {

    // --- Private Attributes ---
    private int duration;       // Length of the movie in minutes
    private String cast;        // Main cast members as a single string
    private String director;    // Name of the director

    // --- Constructor ---
    public Movie(String title, String genre, double rating, int releaseYear,
                 String description, int duration, String cast, String director) {
        super(title, genre, rating, releaseYear, description);
        this.duration = duration;
        this.cast = cast;
        this.director = director;
    }

    // --- Implemented Abstract Methods ---

    /**
     * Plays the full movie from start.
     */
    @Override
    public void play() {
        System.out.println("============================================");
        System.out.println("  NOW PLAYING: " + getTitle() + " (" + getReleaseYear() + ")");
        System.out.println("  Genre: " + getGenre() + " | Rating: " + getRating());
        System.out.println("  Duration: " + duration + " minutes");
        System.out.println("  Director: " + director);
        System.out.println("  Cast: " + cast);
        System.out.println("  " + getDescription());
        System.out.println("============================================");
        System.out.println("  Playing full movie from start...");
    }

    /**
     * Returns title, genre, duration, cast, and director.
     */
    @Override
    public String getDetails() {
        return "MOVIE: " + getTitle() +
                "\nGenre: " + getGenre() +
                "\nRating: " + getRating() +
                "\nRelease Year: " + getReleaseYear() +
                "\nDuration: " + duration + " minutes" +
                "\nCast: " + cast +
                "\nDirector: " + director +
                "\nDescription: " + getDescription();
    }

    // --- Getters ---

    public int getDuration() {
        return duration;
    }

    public String getCast() {
        return cast;
    }

    public String getDirector() {
        return director;
    }
}