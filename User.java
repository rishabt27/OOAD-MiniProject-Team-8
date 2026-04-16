import java.util.ArrayList;
import java.util.List;

public class User {

    private int userId;
    private String username;
    private String email;

    private List<String> favouriteGenres = new ArrayList<>();
    private List<Movie> watchedMovies = new ArrayList<>();

    public User(int id, String name, String email) {
        this.userId = id;
        this.username = name;
        this.email = email;
    }

    public void addFavouriteGenre(String genre) {
        favouriteGenres.add(genre);
    }

    public List<String> getFavouriteGenres() {
        return favouriteGenres;
    }

    public void rateMovie(Movie m, int rating) {
        m.updateRating(rating);
    }

    public void markAsWatched(Movie m) {
        if (!watchedMovies.contains(m)) {
            watchedMovies.add(m);
        }
    }

    public List<Movie> getWatchedMovies() {
        return watchedMovies;
    }
}