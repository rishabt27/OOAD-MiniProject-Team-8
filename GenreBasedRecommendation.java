import java.util.*;

public class GenreBasedRecommendation implements RecommendationStrategy {

    public List<Movie> recommendMovies(User user, List<Movie> movies) {

        List<Movie> result = new ArrayList<>();

        for (Movie m : movies) {
            if (user.getFavouriteGenres().contains(m.getGenre())) {
                result.add(m);
            }
        }

        return result;
    }
}