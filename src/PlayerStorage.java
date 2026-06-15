import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class PlayerStorage {
    private static final String FILE_NAME = "PlayerStorage.csv";

    public static void savePlayers(List<Player> players) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Player p : players) {
                String line = p.getName() + ";" + p.getWins() + ";" + p.getLosses() + ";" + p.getDraws();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving players: " + e.getMessage());
        }
    }

    public static List<Player> loadPlayers() {
        List<Player> players = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 4) {
                    Player p = new Player(parts[0]);
                    int wins = Integer.parseInt(parts[1]);
                    int losses = Integer.parseInt(parts[2]);
                    int draws = Integer.parseInt(parts[3]);
                    for (int i = 0; i < wins; i++) p.addWins();
                    for (int i = 0; i < losses; i++) p.addLosses();
                    for (int i = 0; i < draws; i++) p.addDraws();
                    players.add(p);
                }
            }
        } catch (IOException e) {
            System.out.println("No saved players found. Creat new Players.");
        }
        return players;
    }
}