import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Startmenu extends Application {

    @Override
    public void start(Stage stage) {

        // Titel
        Text title = new Text("4 Gewinnt");
        title.setFont(Font.font(40));

        // --- Start Button ---
        Circle startCircle = new Circle(80);
        startCircle.setFill(Color.WHITE);
        startCircle.setStroke(Color.BLACK);
        startCircle.setStrokeWidth(3);

        Text startText = new Text("Start");
        startText.setFont(Font.font(30));

        StackPane startButton = new StackPane(startCircle, startText);

        // --- Statistik Button ---
        Circle statsCircle = new Circle(80);
        statsCircle.setFill(Color.WHITE);
        statsCircle.setStroke(Color.BLACK);
        statsCircle.setStrokeWidth(3);

        Text statsText = new Text("Statistik");
        statsText.setFont(Font.font(26));

        StackPane statsButton = new StackPane(statsCircle, statsText);

        // Buttons nebeneinander
        HBox buttonRow = new HBox(60, statsButton, startButton);
        buttonRow.setAlignment(Pos.CENTER);

        // Alles untereinander
        VBox root = new VBox(50, title, buttonRow);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 500, 400);

        stage.setScene(scene);
        stage.setTitle("4 Gewinnt – Startmenü");
        stage.show();

        // --- Events ---
        startButton.setOnMouseClicked(e -> {
            try {
                new Player1().start(new Stage());
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        statsButton.setOnMouseClicked(e -> {
            try {
                new PlayerStatistic().start(new Stage()); // Beispielklasse
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}
