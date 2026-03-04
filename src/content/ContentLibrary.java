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
                "Christopher Nolan",
                true
        ));

        mediaList.add(new Movie(
                "The Matrix", "Action/Sci-Fi", 8.7, 1999,
                "A hacker discovers the shocking truth about reality.",
                136,
                "Keanu Reeves, Laurence Fishburne",
                "The Wachowskis",
                true
        ));

        mediaList.add(new Movie(
                "Interstellar", "Sci-Fi/Drama", 8.6, 2014,
                "A team of explorers travel through a wormhole in space to ensure humanity's survival.",
                169,
                "Matthew McConaughey, Anne Hathaway, Jessica Chastain",
                "Christopher Nolan",
                true
        ));

        mediaList.add(new Movie(
                "Spirited Away", "Animation/Fantasy", 8.6, 2001,
                "A young girl enters a world of spirits and must work to free herself and her parents.",
                125,
                "Rumi Hiiragi, Miyu Irino",
                "Hayao Miyazaki",
                false
        ));

        mediaList.add(new Movie(
                "The Dark Knight", "Action/Crime", 9.0, 2008,
                "Batman faces the Joker, a criminal mastermind who plunges Gotham into chaos.",
                152,
                "Christian Bale, Heath Ledger, Aaron Eckhart",
                "Christopher Nolan",
                true
        ));

        // --- Series ---
        mediaList.add(new Series(
                "Breaking Bad", "Crime/Drama", 9.5, 2008,
                "A chemistry teacher turns to making methamphetamine after a cancer diagnosis.",
                5,
                62,
                true
        ));

        mediaList.add(new Series(
                "Stranger Things", "Sci-Fi/Horror", 8.7, 2016,
                "A group of kids encounter supernatural forces and secret government experiments in their town.",
                4,
                34,
                true
        ));

        mediaList.add(new Series(
                "Game of Thrones", "Fantasy/Drama", 9.2, 2011,
                "Noble families vie for control of the Iron Throne in the land of Westeros.",
                8,
                73,
                true
        ));

        mediaList.add(new Series(
                "Avatar: The Last Airbender", "Animation/Adventure", 9.3, 2005,
                "A young Avatar must master all four elements and save the world from the Fire Nation.",
                3,
                61,
                false
        ));

        mediaList.add(new Series(
                "The Office", "Comedy", 8.9, 2005,
                "A mockumentary on a group of typical office workers.",
                9,
                201,
                false
        ));

        mediaList.add(new Series(
                "The Mandalorian", "Sci-Fi/Adventure", 8.8, 2019,
                "A lone bounty hunter in the outer reaches of the galaxy protects a mysterious child.",
                2,
                16,
                true
        ));

        mediaList.add(new Series(
                "The Crown", "Historical Drama", 8.7, 2016,
                "A dramatized history of the reign of Queen Elizabeth II.",
                4,
                40,
                true
        ));

        mediaList.add(new Series(
                "Friends", "Comedy/Romance", 8.9, 1994,
                "Follows the lives of six friends in New York City.",
                10,
                236,
                false
        ));

        mediaList.add(new Series(
                "The Witcher", "Fantasy/Adventure", 8.2, 2019,
                "A monster hunter struggles to find his place in a world where people often prove more wicked than beasts.",
                2,
                16,
                true
        ));

        mediaList.add(new Series(
                "Black Mirror", "Sci-Fi/Thriller", 8.8, 2011,
                "An anthology series exploring a twisted, high-tech world where humanity's greatest innovations and darkest instincts collide.",
                5,
                22,
                true
        ));             

        // --- Additional Movies (15 more to reach 20 total) ---
        mediaList.add(new Movie(
                "Pulp Fiction", "Crime/Drama", 8.9, 1994,
                "The lives of two mobsters, a boxer, a gangster and his wife intertwine in four tales of violence.",
                154,
                "John Travolta, Uma Thurman, Samuel L. Jackson",
                "Quentin Tarantino",
                true
        ));

        mediaList.add(new Movie(
                "Forrest Gump", "Drama/Romance", 8.8, 1994,
                "The presidencies of Kennedy and Johnson unfold from the perspective of an Alabama man.",
                142,
                "Tom Hanks, Gary Sinise, Sally Field",
                "Robert Zemeckis",
                false
        ));

        mediaList.add(new Movie(
                "The Shawshank Redemption", "Drama", 9.3, 1994,
                "Two imprisoned men bond over a number of years to form a friendship.",
                142,
                "Tim Robbins, Morgan Freeman",
                "Frank Darabont",
                true
        ));

        mediaList.add(new Movie(
                "The Godfather", "Crime/Drama", 9.2, 1972,
                "The aging patriarch of an organized crime dynasty transfers control to his reluctant youngest son.",
                175,
                "Marlon Brando, Al Pacino, James Caan",
                "Francis Ford Coppola",
                true
        ));

        mediaList.add(new Movie(
                "The Godfather Part II", "Crime/Drama", 9.0, 1974,
                "The continuation of the Corleone saga as Michael consolidates his power.",
                202,
                "Al Pacino, Robert Duvall, Diane Keaton",
                "Francis Ford Coppola",
                true
        ));

        mediaList.add(new Movie(
                "Gladiator", "Action/Drama", 8.5, 2000,
                "A former Roman General sets out to exact vengeance against the Emperor who betrayed him.",
                155,
                "Russell Crowe, Joaquin Phoenix, Lucilla Romagna",
                "Ridley Scott",
                false
        ));

        mediaList.add(new Movie(
                "Avatar", "Sci-Fi/Adventure", 7.8, 2009,
                "A paraplegic Marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders.",
                162,
                "Sam Worthington, Zoe Saldana, Sigourney Weaver",
                "James Cameron",
                true
        ));

        mediaList.add(new Movie(
                "Titanic", "Romance/Drama", 7.9, 1997,
                "A seventeen-year-old aristocrat falls in love with a kind but poor artist aboard the luxurious, ill-fated R.M.S. Titanic.",
                194,
                "Leonardo DiCaprio, Kate Winslet",
                "James Cameron",
                false
        ));

        mediaList.add(new Movie(
                "The Avengers", "Action/Sci-Fi", 8.0, 2012,
                "Earth's mightiest heroes must come together and learn to fight as a team.",
                143,
                "Robert Downey Jr., Chris Evans, Chris Hemsworth",
                "Joss Whedon",
                true
        ));

        mediaList.add(new Movie(
                "Avengers: Endgame", "Action/Sci-Fi", 8.4, 2019,
                "After the devastating events, the Avengers assemble once more to reverse Thanos' actions.",
                181,
                "Robert Downey Jr., Chris Evans, Mark Ruffalo",
                "Anthony Russo, Joe Russo",
                true
        ));

        mediaList.add(new Movie(
                "Joker", "Crime/Drama", 8.4, 2019,
                "A struggling comedian finds himself adrift in society searching for his place.",
                122,
                "Joaquin Phoenix, Robert De Niro, Zazie Beetz",
                "Todd Phillips",
                true
        ));

        mediaList.add(new Movie(
                "Se7en", "Crime/Mystery", 8.6, 1995,
                "Two detectives hunt a serial killer who uses the seven deadly sins as his motives.",
                127,
                "Brad Pitt, Morgan Freeman, Kevin Spacey",
                "David Fincher",
                true
        ));

        mediaList.add(new Movie(
                "Fight Club", "Drama", 8.8, 1999,
                "An insomniac office worker and a devil-may-care soapmaker form an underground fight club.",
                139,
                "Brad Pitt, Edward Norton, Helena Bonham Carter",
                "David Fincher",
                true
        ));

        mediaList.add(new Movie(
                "The Silence of the Lambs", "Crime/Thriller", 8.6, 1991,
                "A young FBI cadet must receive the help of an incarcerated cannibal killer.",
                118,
                "Jodie Foster, Scott Glenn, Anthony Hopkins",
                "Jonathan Demme",
                true
        ));

        mediaList.add(new Movie(
                "Parasite", "Drama/Thriller", 8.6, 2019,
                "Greed and class discrimination threaten the newly formed symbiotic relationship.",
                132,
                "Song Kang-ho, Lee Sun-kyun, Cho Yeo-jeong",
                "Bong Joon-ho",
                true
        ));

        mediaList.add(new Movie(
                "The Departed", "Crime/Drama", 8.5, 2006,
                "An undercover cop and a mole in the police attempt to identify each other.",
                151,
                "Leonardo DiCaprio, Matt Damon, Jack Nicholson",
                "Martin Scorsese",
                true
        ));

        // --- Additional Series (5 more to reach 15 total) ---
        mediaList.add(new Series(
                "True Detective", "Crime/Mystery", 8.9, 2014,
                "Detectives pursue a ruthless killer in the rust belt.",
                4,
                24,
                true
        ));

        mediaList.add(new Series(
                "Westworld", "Sci-Fi/Drama", 8.5, 2016,
                "At a futuristic theme park populated by android hosts, wealthy guests live out their fantasies.",
                4,
                36,
                false
        ));

        mediaList.add(new Series(
                "Chernobyl", "Historical/Drama", 9.3, 2019,
                "A dramatization of the disaster at the Chernobyl nuclear power station.",
                1,
                5,
                false
        ));

        mediaList.add(new Series(
                "Mindhunter", "Crime/Drama", 8.6, 2017,
                "FBI agents explore criminal psychology to catch serial killers.",
                2,
                19,
                true
        ));

        mediaList.add(new Series(
                "The Boys", "Action/Comedy", 8.7, 2019,
                "A group of vigilantes set out to take down corrupt superheroes.",
                4,
                50,
                true
        ));

    }

    public List<Media> getAllMedia() {
        return mediaList;
    }

    public List<String> getAllMovieTitles() {
        List<String> titles = new ArrayList<>();

        for(Media m : mediaList){
            if(m instanceof Movie){
                titles.add(m.getTitle());
            }
        }
        return titles;
    }

    public List<String> getAllSeriesTitles() {
        List<String> titles = new ArrayList<>();

        for(Media m : mediaList){
            if(m instanceof Series){
                titles.add(m.getTitle());
            }
        }
        return titles;
    }

    public void addMedia(Media media) {
        mediaList.add(media);
    }

    public Media findByTitle(String title) {
        if (title == null) {
            return null;
        }

        String query = title.trim().toLowerCase();

        for (Media m : mediaList) {
            if (m.getTitle() != null &&
                    m.getTitle().toLowerCase().contains(query)) {
                return m;
            }
        }
        return null;
    }

    public List<String> getAllFreeMovieTitles(){
        List<String> titles = new ArrayList<>();

        for(Media m : mediaList){
            if(m instanceof Movie){
                if(!m.getIsPremium())
                titles.add(m.getTitle());
            }
        }
        return titles;
    }

    public List<String> getAllFreeSeriesTitles(){
        List<String> titles = new ArrayList<>();

        for(Media m : mediaList){
            if(m instanceof Series){
                if(!m.getIsPremium())
                titles.add(m.getTitle());
            }
        }
        return titles;
    }
}
