import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;

public class Player1 extends Application {
    @Override
    public void start(Stage stage) {

        // Titel
        Text title = new Text("Spieler 1");
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

        // Alles untereinander
        VBox root = new VBox(40, title, buttons);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 400, 400);
        stage.setScene(scene);
        stage.setTitle("Spieler 1");
        stage.show();

        //Wenn erstellen gedrückt wird
        erstellenButton.setOnMouseClicked(e -> {
            System.out.println("Spieler erstellen...");

        });

        //Wenn laden gedrückt wird
        ladenButton.setOnMouseClicked(e -> {
            System.out.println("Spieler auswählen...");

        });

        // Wenn erstellen gedrückt wird
        erstellenButton.setOnMouseClicked(e -> {
            Player p1 = new Player("Spieler 1"); // später mit Eingabefeld
            Player p2 = new Player("Spieler 2");
            try {
                new GameBoard(p1, p2).start(new Stage());
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
}
