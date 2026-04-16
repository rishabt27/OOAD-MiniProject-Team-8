import java.util.*;

public class Movie {
    private int movieId;
    private String title;
    private String genre;
    private String actors;
    private String directors;
    private double averageRating;
    private int releaseYear;
    private List<Integer> ratings = new ArrayList<>();

    public Movie(int id, String title, String genre, String actors, String directors, int year) {
        this.movieId = id;
        this.title = title;
        this.genre = genre;
        this.actors = actors;
        this.directors = directors;
        this.releaseYear = year;
    }

    public void updateRating(int rating) {
        ratings.add(rating);
        averageRating = ratings.stream().mapToInt(Integer::intValue).average().orElse(0.0);
    }

    public String getTitle() { return title; }
    public String getGenre() { return genre; }
    public String getActors() { return actors; }
    public String getDirectors() { return directors; }
    public double getAverageRating() { return averageRating; }

    public String getDetails() {
        return title + " | " + genre + " | ⭐ " + String.format("%.1f", averageRating);
    }
}