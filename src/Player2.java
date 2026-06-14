import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;

import java.util.List;

public class Player2 extends Application {

    private VBox root; // für Ansichtenwechsel
    private Stage mainStage; // um Stages schließen zu können

    @Override
    public void start(Stage stage) {
        this.mainStage = stage;

        root = new VBox(40);
        root.setAlignment(Pos.CENTER);

        zeigeStartAnsicht(); // Startmenü anzeigen

        Scene scene = new Scene(root, 400, 400);
        stage.setScene(scene);
        stage.setTitle("Spieler 2");
        stage.show();
    }

    // START-ANSICHT
    private void zeigeStartAnsicht() {
        root.getChildren().clear();

        // Titel
        Text title = new Text("Spieler 2");
        title.setFont(Font.font(35));

        // Kreis 1: Erstellen
        Circle c1 = new Circle(60);
        c1.setFill(Color.WHITE);
        c1.setStroke(Color.BLACK);
        c1.setStrokeWidth(3);

        Text t1 = new Text("Erstellen");
        t1.setFont(Font.font(18));

        StackPane erstellenButton = new StackPane(c1, t1);

        // Kreis 2: Laden
        Circle c2 = new Circle(60);
        c2.setFill(Color.WHITE);
        c2.setStroke(Color.BLACK);
        c2.setStrokeWidth(3);

        Text t2 = new Text("Laden");
        t2.setFont(Font.font(18));

        StackPane ladenButton = new StackPane(c2, t2);

        // Kreise nebeneinander
        HBox buttons = new HBox(50, erstellenButton, ladenButton);
        buttons.setAlignment(Pos.CENTER);

        root.getChildren().addAll(title, buttons);

        erstellenButton.setOnMouseClicked(e -> zeigeErstellenAnsicht());
        ladenButton.setOnMouseClicked(e -> zeigeLadenAnsicht());
    }


    // ERSTELLEN-ANSICHT
    private void zeigeErstellenAnsicht() {
        root.getChildren().clear();

        Text title = new Text("Spieler 2 erstellen");
        title.setFont(Font.font(30));

        Text nameLabel = new Text("Name:");
        nameLabel.setFont(Font.font(20));

        TextField nameField = new TextField();
        nameField.setMaxWidth(200);

        Button erstellen = new Button("Erstellen");
        erstellen.setOnAction(e -> {
            String name = nameField.getText();

            if (name.isEmpty()) {
                System.out.println("Name darf nicht leer sein");
                return;
            }

            System.out.println("Spieler erstellt: " + name);

            Player p = new Player(name);

            List<Player> players = PlayerStorage.loadPlayers();
            players.add(p);
            PlayerStorage.savePlayers(players);

            System.out.println("Spieler 2 erstellt: " + name);

            try {
                new GameBoard().start(new Stage());
                mainStage.close(); // Player2 schließen
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Button back = new Button("Zurück");
        back.setOnAction(e -> zeigeStartAnsicht());

        root.getChildren().addAll(title, nameLabel, nameField, erstellen, back);
    }

    private void zeigeLadenAnsicht() {
        root.getChildren().clear();

        Text title = new Text("Spieler 2 laden");
        title.setFont(Font.font(30));

        Text info = new Text("Spieler auswählen:");
        info.setFont(Font.font(18));

        VBox liste = new VBox(10);
        liste.setAlignment(Pos.CENTER);

        // Spieler aus Datei laden
        List<Player> players = PlayerStorage.loadPlayers();

        if (players.isEmpty()) {
            liste.getChildren().add(new Text("Keine Spieler gespeichert."));
        } else {
            for (Player p : players) {
                Text t = new Text(p.getName());
                t.setFont(Font.font(18));

                // Spieler anklicken -> Player2 öffnen
                t.setOnMouseClicked(e -> {
                    System.out.println("Spieler 2 geladen: " + p.getName());

                    try {
                        new GameBoard().start(new Stage());
                        mainStage.close(); // Player2 schließen
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });

                liste.getChildren().add(t);
            }
        }

        Button back = new Button("Zurück");
        back.setOnAction(e -> zeigeStartAnsicht());

        root.getChildren().addAll(title, info, liste, back);
    }
}
