import java.util.*;

public class UnwatchedRecommendation implements RecommendationStrategy {

    public List<Movie> recommendMovies(User user, List<Movie> movies) {

        List<Movie> result = new ArrayList<>();

        for (Movie m : movies) {
            if (!user.getWatchedMovies().contains(m)) {
                result.add(m);
            }
        }

        return result;
    }
}