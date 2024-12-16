import java.io.*;
import java.util.*;

public class MovieManager {
    private static final String FILE_NAME = "movies.txt";
    private List<String> movies;

    public MovieManager() {
        movies = new ArrayList<>();
        loadMovies();
    }

    public void loadMovies() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                movies.add(line.trim());
            }
        } catch (IOException e) {
            System.out.println("Error al cargar las películas.");
        }
    }

    public String getRandomMovie() {
        Random random = new Random();
        return movies.get(random.nextInt(movies.size()));
    }
}
