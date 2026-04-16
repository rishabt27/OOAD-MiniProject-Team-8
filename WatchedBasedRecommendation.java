import java.util.*;

public class WatchedBasedRecommendation implements RecommendationStrategy {

    public List<Movie> recommendMovies(User user, List<Movie> movies) {

        List<Movie> result = new ArrayList<>();
        List<Movie> watched = user.getWatchedMovies();

        Set<String> genres = new HashSet<>();

        for (Movie m : watched) {
            genres.add(m.getGenre());
        }

        for (Movie m : movies) {
            if (!watched.contains(m) && genres.contains(m.getGenre())) {
                result.add(m);
            }
        }

        return result;
    }
}