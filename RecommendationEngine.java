import java.util.*;

public class RecommendationEngine {
    private RecommendationStrategy strategy;
    private MovieDatabase db;

    public RecommendationEngine(MovieDatabase db) {
        this.db = db;
    }

    public void setStrategy(RecommendationStrategy strategy) {
        this.strategy = strategy;
    }

    public List<Movie> getRecommendations(User user) {
        return strategy.recommendMovies(user, db.getAllMovies());
    }
}