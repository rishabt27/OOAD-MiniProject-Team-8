import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.io.*;

public class MovieUI {

    private static final String API_KEY = "e02fc8cac9d2fd3890ab2c2a66363307";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MovieUI::createUI);
    }

    private static void createUI() {

        MovieController controller = new MovieController();

        JFrame frame = new JFrame("Movie System");
        frame.setSize(1100, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.BLACK);

        JPanel topBar = new JPanel();
        topBar.setBackground(Color.BLACK);

        JTextField searchField = new JTextField(15);
        JButton searchBtn = new JButton("Search");

        String[] filters = {"All", "Title", "Genre", "Actor", "Director"};
        JComboBox<String> filterBox = new JComboBox<>(filters);

        String[] strategies = {"Genre Based", "Top Rated", "Unwatched", "Watched Based"};
        JComboBox<String> strategyBox = new JComboBox<>(strategies);

        JButton recommendBtn = new JButton("Recommend");

        topBar.add(searchField);
        topBar.add(filterBox);
        topBar.add(searchBtn);
        topBar.add(strategyBox);
        topBar.add(recommendBtn);

        JPanel moviePanel = new JPanel(new GridLayout(0, 5, 15, 15));
        moviePanel.setBackground(Color.BLACK);

        JScrollPane scroll = new JScrollPane(moviePanel);

        for (Movie m : controller.getAllMovies().subList(0, 50)) {
            moviePanel.add(createCard(m, controller));
        }

        searchBtn.addActionListener(e -> {
            moviePanel.removeAll();
            List<Movie> results = controller.searchMovies(
                    searchField.getText(),
                    (String) filterBox.getSelectedItem()
            );
            for (Movie m : results) {
                moviePanel.add(createCard(m, controller));
            }
            moviePanel.revalidate();
            moviePanel.repaint();
        });

        recommendBtn.addActionListener(e -> {
            moviePanel.removeAll();
            List<Movie> recs = controller.getRecommendations(
                    (String) strategyBox.getSelectedItem()
            );
            for (Movie m : recs) {
                moviePanel.add(createCard(m, controller));
            }
            moviePanel.revalidate();
            moviePanel.repaint();
        });

        mainPanel.add(topBar, BorderLayout.NORTH);
        mainPanel.add(scroll, BorderLayout.CENTER);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private static JPanel createCard(Movie m, MovieController controller) {

        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(new Color(30, 30, 30));
        card.setPreferredSize(new Dimension(160, 260));
        card.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));

        JLabel poster = createPosterLabel(m.getTitle());
        poster.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel title = new JLabel("<html><center>" + m.getTitle() + "</center></html>");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 12));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel rating = new JLabel("⭐ " + String.format("%.1f", m.getAverageRating()));
        rating.setForeground(new Color(0, 255, 100));
        rating.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel btnPanel = new JPanel(new GridLayout(1,2,5,0));
        btnPanel.setBackground(new Color(30,30,30));
        btnPanel.setMaximumSize(new Dimension(140, 30));

        JButton rateBtn = new JButton("Rate");
        JButton watchedBtn = new JButton("Watched");

        rateBtn.setFocusPainted(false);
        watchedBtn.setFocusPainted(false);

        rateBtn.addActionListener(e -> {
            try {
                int r = Integer.parseInt(JOptionPane.showInputDialog("Rate " + m.getTitle()));
                controller.rateMovie(m, r);
                rating.setText("⭐ " + String.format("%.1f", m.getAverageRating()));
            } catch (Exception ignored) {}
        });

        watchedBtn.addActionListener(e -> controller.markWatched(m));

        btnPanel.add(rateBtn);
        btnPanel.add(watchedBtn);

        card.add(poster);
        card.add(Box.createVerticalStrut(8));
        card.add(title);
        card.add(Box.createVerticalStrut(5));
        card.add(rating);
        card.add(Box.createVerticalStrut(5));
        card.add(btnPanel);

        return card;
    }

    private static JLabel createPosterLabel(String title) {

        JLabel label = new JLabel("🎬", JLabel.CENTER);
        label.setPreferredSize(new Dimension(120,160));
        label.setMinimumSize(new Dimension(120,160));
        label.setMaximumSize(new Dimension(120,160));
        label.setOpaque(true);
        label.setBackground(new Color(60,60,60));

        new Thread(() -> {
            try {
                String safeName = title.replaceAll("[^a-zA-Z0-9]", "_");
                String filePath = "posters/" + safeName + ".jpg";

                File file = new File(filePath);

                if (file.exists()) {
                    ImageIcon icon = new ImageIcon(filePath);
                    Image img = icon.getImage().getScaledInstance(120, 160, Image.SCALE_SMOOTH);

                    SwingUtilities.invokeLater(() -> {
                        label.setIcon(new ImageIcon(img));
                        label.setText("");
                    });
                    return;
                }

                String query = title.replace(" ", "%20");

                String urlStr = "https://api.themoviedb.org/3/search/movie?api_key="
                        + API_KEY + "&query=" + query;

                BufferedReader br = new BufferedReader(
                        new InputStreamReader(new java.net.URL(urlStr).openStream()));

                StringBuilder jsonBuilder = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    jsonBuilder.append(line);
                }
                br.close();

                String json = jsonBuilder.toString();

                int index = json.indexOf("\"poster_path\":\"");
                if (index == -1) return;

                int start = index + 15;
                int end = json.indexOf("\"", start);

                String posterPath = json.substring(start, end);

                String imageUrl = "https://image.tmdb.org/t/p/w200" + posterPath;

                java.awt.image.BufferedImage image =
                        javax.imageio.ImageIO.read(new java.net.URL(imageUrl));

                new File("posters").mkdirs();
                javax.imageio.ImageIO.write(image, "jpg", file);

                Image scaled = image.getScaledInstance(120, 160, Image.SCALE_SMOOTH);

                SwingUtilities.invokeLater(() -> {
                    label.setIcon(new ImageIcon(scaled));
                    label.setText("");
                });

            } catch (Exception ignored) {}
        }).start();

        return label;
    }
}