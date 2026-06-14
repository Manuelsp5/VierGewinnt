import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.List;

public class PlayerStatistic extends Application {

    @Override
    public void start(Stage stage) {

        Label title = new Label("Player Statistics");
        title.setFont(Font.font(30));

        VBox listBox = new VBox(10);
        listBox.setAlignment(Pos.CENTER);

        // Load players
        List<Player> players = PlayerStorage.loadPlayers();

        if (players.isEmpty()) {
            listBox.getChildren().add(new Label("No players saved."));
        } else {
            for (Player p : players) {
                Label entry = new Label(
                        p.getName() +
                                "  |  Wins: " + p.getWins() +
                                "  |  Losses: " + p.getLosses() +
                                "  |  Draws: " + p.getDraws()
                );
                entry.setFont(Font.font(18));
                listBox.getChildren().add(entry);
            }
        }

        VBox root = new VBox(20, title, listBox);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 450, 400);
        stage.setScene(scene);
        stage.setTitle("Player Statistics");
        stage.show();
    }
}
