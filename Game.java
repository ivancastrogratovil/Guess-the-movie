import java.util.HashSet;
import java.util.Set;

public class Game {
    private final String movieTitle;
    private final StringBuilder hiddenTitle;
    private final Set<Character> guessedLetters;
    private final Set<Character> wrongLetters;
    private int attempts;
    private int score;
    private boolean isGameWon;

    public Game(String movieTitle) {
        this.movieTitle = movieTitle.toLowerCase();
        this.hiddenTitle = new StringBuilder();
        this.guessedLetters = new HashSet<>();
        this.wrongLetters = new HashSet<>();
        this.attempts = 10;
        this.score = 0;

        for (char c : movieTitle.toCharArray()) {
            if (Character.isLetter(c)) {
                hiddenTitle.append('*');
            } else {
                hiddenTitle.append(c);
            }
        }
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getHiddenTitle() {
        return hiddenTitle.toString();
    }

    public Set<Character> getWrongLetters() {
        return wrongLetters;
    }

    public int getAttempts() {
        return attempts;
    }

    public int getScore() {
        return score;
    }

    public boolean isGameOver() {
        return attempts == 0 || hiddenTitle.toString().equalsIgnoreCase(movieTitle);
    }

    public void guessLetter(char letter) {
        letter = Character.toLowerCase(letter);

        if (guessedLetters.contains(letter)) {
            System.out.println("Ya intentaste con la letra '" + letter + "'. Intenta con otra.");
            return;
        }

        guessedLetters.add(letter);

        if (movieTitle.contains(String.valueOf(letter))) {
            for (int i = 0; i < movieTitle.length(); i++) {
                if (movieTitle.charAt(i) == letter) {
                    hiddenTitle.setCharAt(i, letter);
                }
            }
            attempts--;
            score += 10;
            System.out.println("¡Correcto! La letra '" + letter + "' está en el título.");
        } else {
            wrongLetters.add(letter);
            attempts--;
            score -= 10;
            System.out.println("Incorrecto. La letra '" + letter + "' no está en el título.");
        }

        if (hiddenTitle.toString().equalsIgnoreCase(movieTitle)) {
            attempts = 0; // Terminar el juego
        }
    }

    public boolean isGameWon() {
        return isGameWon;
    }

    public boolean guessTitle(String titleGuess) {
        if (titleGuess.equalsIgnoreCase(movieTitle)) {
            score += 20;
            isGameWon = true; // Marcar como ganado
            attempts = 0; // Terminar el juego
            return true;
        } else {
            score -= 20;
            attempts = 0; // Terminar el juego
            return false;
        }
    }


    public void revealTitle() {
        System.out.println("El título completo de la película era: " + movieTitle);
    }
}
