import java.util.*;

public class TopRatedRecommendation implements RecommendationStrategy {

    public List<Movie> recommendMovies(User user, List<Movie> movies) {

        movies.sort((a, b) -> Double.compare(b.getAverageRating(), a.getAverageRating()));

        return movies.subList(0, Math.min(10, movies.size()));
    }
}