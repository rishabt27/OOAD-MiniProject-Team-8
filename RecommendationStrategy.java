import java.util.List;

public interface RecommendationStrategy {
    List<Movie> recommendMovies(User user, List<Movie> movies);
}