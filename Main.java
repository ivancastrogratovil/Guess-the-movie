import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MovieManager movieManager = new MovieManager();
        Ranking ranking = new Ranking();

        if (movieManager.getRandomMovie() == null) {
            System.out.println("No se han encontrado películas en el archivo. Por favor, añade películas a 'movies.txt'.");
            return;
        }

        String selectedMovie = movieManager.getRandomMovie();
        Game game = new Game(selectedMovie);
        Scanner scanner = new Scanner(System.in);

        System.out.println("\u00a1Bienvenido al juego de adivinar la pel\u00edcula!");
        System.out.println("Tienes un m\u00e1ximo de 10 intentos para adivinar el t\u00edtulo.");
        System.out.println("Pel\u00edcula a adivinar: " + game.getHiddenTitle());

        while (!game.isGameOver()) {
            // Evitar imprimir el estado del juego si el usuario ya adivinó el título
            if (game.isGameWon()) {
                break;
            }

            System.out.println("\nOpciones:");
            System.out.println("[1] Adivinar una letra");
            System.out.println("[2] Adivinar el título completo");
            System.out.println("[3] Salir del juego");
            System.out.print("Selecciona una opción: ");

            int option;
            try {
                option = Integer.parseInt(scanner.nextLine()); // Validar que sea un número
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingresa un número válido (1, 2 o 3).\n");
                continue;
            }

            switch (option) {
                case 1:
                    System.out.print("Introduce una letra: ");
                    String input = scanner.nextLine();

                    // Validación de letra
                    if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                        System.out.println("Entrada inválida. Por favor, introduce una única letra [a-z].");
                        break;
                    }

                    char letter = Character.toLowerCase(input.charAt(0)); // Normalizar a minúscula
                    game.guessLetter(letter);
                    break;

                case 2:
                    System.out.print("Introduce el título completo: ");
                    String titleGuess = scanner.nextLine();

                    if (game.guessTitle(titleGuess)) {
                        System.out.println("\n\u00a1Felicidades! Has adivinado el t\u00edtulo correctamente: " + game.getMovieTitle());
                    } else {
                        System.out.println("\nNo has adivinado el t\u00edtulo.");
                    }
                    break;

                case 3:
                    System.out.println("Has decidido salir del juego.");
                    game.revealTitle();
                    return; // Salir del programa

                default:
                    System.out.println("Opción no válida. Por favor, selecciona 1, 2 o 3.");
            }

            // Mostrar estado del juego si no ha terminado
            if (!game.isGameWon() && !game.isGameOver()) {
                System.out.println("\nPelícula: " + game.getHiddenTitle());
                System.out.println("Letras incorrectas: " + game.getWrongLetters());
                System.out.println("Intentos restantes: " + game.getAttempts());
                System.out.println("Puntuación: " + game.getScore());
            }
        }

        // Fin del juego
        if (!game.isGameWon()) {
            System.out.println("\nFin del juego.");
            System.out.println("El título era: " + game.getMovieTitle());
        }
        System.out.println("Puntuación final: " + game.getScore());

        // Comprobar si la puntuación permite entrar en el ranking
        if (game.getScore() > 0) {
            System.out.print("\u00a1Enhorabuena! Tu puntuación es alta. Ingresa un nickname para el ranking: ");
            String nickname;

            while (true) {
                nickname = scanner.nextLine().trim(); // Leer entrada y eliminar espacios extra
                if (nickname.isEmpty()) {
                    System.out.print("El nickname no puede estar vacío. Ingresa otro: ");
                } else if (!ranking.isNicknameTaken(nickname)) {
                    break; // Nickname válido
                } else {
                    System.out.print("Ese nickname ya existe. Por favor, ingresa otro: ");
                }
            }

            // Añadir jugador al ranking
            ranking.addPlayer(new Player(nickname, game.getScore()));
            ranking.saveRanking(); // Guardar el ranking después de añadir el jugador
        } else {
            System.out.println("Tu puntuación no es suficiente para entrar en el ranking.");
        }

        // Mostrar el ranking actual
        System.out.println("\nRanking actual:");
        ranking.displayRanking();
    }
}
