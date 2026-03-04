package content;

public abstract class Media {

    // --- Private Attributes (Encapsulation) ---
    private String title;
    private String genre;
    private double rating;
    private int releaseYear;
    private String description;
    private boolean isPremium;

    // --- Constructor ---
    public Media(String title, String genre, double rating, int releaseYear, String description, boolean isPremium) {
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.releaseYear = releaseYear;
        this.description = description;
        this.isPremium = isPremium;
    }

    // --- Abstract Methods (Abstraction) ---
    // Subclasses MUST implement these

    public abstract void play();

    public abstract String getDetails();

    // --- Getters ---

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public double getRating() {
        return rating;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getDescription() {
        return description;
    }

    public boolean getIsPremium(){
        return isPremium;
    }

    // --- toString ---

    @Override
    public String toString() {
        return title + " (" + releaseYear + ") | Genre: " + genre + " | Rating: " + rating;
    }
}