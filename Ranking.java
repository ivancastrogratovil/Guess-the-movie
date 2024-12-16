import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Ranking {
    private List<Player> players = new ArrayList<>();
    private static final String RANKING_FILE = "ranking.dat";

    // Constructor: Cargar el ranking desde el archivo al iniciar
    public Ranking() {
        loadRanking();
    }

    public void addPlayer(Player player) {
        players.add(player);
        players.sort((p1, p2) -> Integer.compare(p2.getScore(), p1.getScore())); // Orden descendente
        if (players.size() > 5) {
            players = new ArrayList<>(players.subList(0, 5)); // Crear una nueva lista con los 5 mejores
        }
    }

    public boolean isNicknameTaken(String nickname) {
        return players.stream().anyMatch(p -> p.getNickname().equalsIgnoreCase(nickname));
    }

    public void displayRanking() {
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            System.out.println((i + 1) + ". " + player.getNickname() + " - " + player.getScore());
        }
    }

    public void saveRanking() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RANKING_FILE))) {
            oos.writeObject(players);
        } catch (IOException e) {
            System.err.println("Error al guardar el ranking: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void loadRanking() {
        File file = new File(RANKING_FILE);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                players = (List<Player>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error al cargar el ranking: " + e.getMessage());
            }
        }
    }
}
