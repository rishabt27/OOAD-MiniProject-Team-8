import java.util.*;

public class MovieController {

    private MovieDatabase db;
    private User user;
    private RecommendationEngine engine;

    public MovieController() {
        db = new MovieDatabase();
        user = new User(1, "User", "user@email.com");

        db.loadMoviesOnlyCSV("movies.csv");
        user.addFavouriteGenre("Sci-Fi");

        engine = new RecommendationEngine(db);
    }

    public List<Movie> getAllMovies() {
        return db.getAllMovies();
    }

    public List<Movie> searchMovies(String text, String filter) {

        text = text.toLowerCase();
        List<Movie> result = new ArrayList<>();

        for (Movie m : db.getAllMovies()) {

            boolean match = false;

            switch (filter) {
                case "Title":
                    match = m.getTitle().toLowerCase().contains(text);
                    break;
                case "Genre":
                    match = m.getGenre().toLowerCase().contains(text);
                    break;
                case "Actor":
                    match = m.getActors().toLowerCase().contains(text);
                    break;
                case "Director":
                    match = m.getDirectors().toLowerCase().contains(text);
                    break;
                default:
                    match = m.getTitle().toLowerCase().contains(text);
            }

            if (match) result.add(m);
        }

        return result;
    }

    public List<Movie> getRecommendations(String strategyType) {

        switch (strategyType) {
            case "Genre Based":
                engine.setStrategy(new GenreBasedRecommendation());
                break;
            case "Top Rated":
                engine.setStrategy(new TopRatedRecommendation());
                break;
            case "Unwatched":
                engine.setStrategy(new UnwatchedRecommendation());
                break;
            default:
                engine.setStrategy(new WatchedBasedRecommendation());
        }

        return engine.getRecommendations(user);
    }

    public void rateMovie(Movie m, int rating) {
        user.rateMovie(m, rating);
    }

    public void markWatched(Movie m) {
        user.markAsWatched(m);
    }
}