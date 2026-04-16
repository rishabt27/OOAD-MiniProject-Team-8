import java.io.*;
import java.util.*;

public class MovieDatabase {

    private List<Movie> movieList = new ArrayList<>();

    public void addMovie(Movie m) {
        movieList.add(m);
    }

    public List<Movie> getAllMovies() {
        return movieList;
    }

    // 🔍 Advanced search
    public List<Movie> searchMovie(String query) {

        List<Movie> result = new ArrayList<>();
        query = query.toLowerCase();

        for (Movie m : movieList) {
            if (m.getTitle().toLowerCase().contains(query) ||
                m.getGenre().toLowerCase().contains(query) ||
                m.getActors().toLowerCase().contains(query) ||
                m.getDirectors().toLowerCase().contains(query)) {

                result.add(m);
            }
        }
        return result;
    }

    // 🔥 LOAD CSV (FINAL FIXED)
    public void loadMoviesOnlyCSV(String filePath) {

        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line = br.readLine(); // skip header

            while ((line = br.readLine()) != null) {

                // Handle commas inside quotes
                String[] parts = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                if (parts.length < 6) continue;

                try {
                    int id = Integer.parseInt(parts[0].trim());

                    String rawTitle = parts[1].replace("\"", "").trim().toLowerCase();

                    // 🎯 FIX REVERSED TITLES
                    String title = fixTitle(rawTitle);

                    int year = Integer.parseInt(parts[2].trim());
                    String directors = parts[3].trim();
                    String actors = parts[4].trim();
                    String genre = parts[5].trim();

                    Movie m = new Movie(id, title, genre, actors, directors, year);
                    addMovie(m);

                    // ⭐ Generate realistic ratings
                    int numRatings = 3 + (int)(Math.random() * 5);
                    for (int i = 0; i < numRatings; i++) {
                        int rating = 2 + (int)(Math.random() * 4);
                        m.updateRating(rating);
                    }

                } catch (Exception inner) {
                    continue;
                }
            }

            br.close();
            System.out.println("✅ Movies loaded successfully!");

        } catch (Exception e) {
            System.out.println("❌ Error loading dataset: " + e);
        }
    }

    // 🎯 FIX TITLE FUNCTION
    private String fixTitle(String title) {

        String[] words = title.split(" ");

        if (words.length > 1) {
            String lastWord = words[words.length - 1];

            if (lastWord.equals("the") || lastWord.equals("a") || lastWord.equals("an")) {

                StringBuilder newTitle = new StringBuilder(capitalize(lastWord));

                for (int i = 0; i < words.length - 1; i++) {
                    newTitle.append(" ").append(words[i]);
                }

                return capitalizeWords(newTitle.toString());
            }
        }

        return capitalizeWords(title);
    }

    // Capitalize first letter of each word
    private String capitalizeWords(String str) {
        String[] words = str.split(" ");
        StringBuilder result = new StringBuilder();

        for (String w : words) {
            if (w.length() > 0) {
                result.append(capitalize(w)).append(" ");
            }
        }

        return result.toString().trim();
    }

    // Capitalize single word
    private String capitalize(String word) {
        return Character.toUpperCase(word.charAt(0)) + word.substring(1);
    }
}