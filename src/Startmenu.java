import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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

            // Kreis
            Circle circle = new Circle(80);
            circle.setFill(Color.WHITE);
            circle.setStroke(Color.BLACK);
            circle.setStrokeWidth(3);

            // Start-Text im Kreis
            Text startText = new Text("Start");
            startText.setFont(Font.font(30));

            // Kreis + Text übereinander
            StackPane circleButton = new StackPane(circle, startText);

            // Alles untereinander
            VBox root = new VBox(40, title, circleButton);
            root.setAlignment(Pos.CENTER);

            Scene scene = new Scene(root, 400, 400);

            stage.setScene(scene);
            stage.setTitle("4 Gewinnt – Startmenü");
            stage.show();

            //Event wenn gedrückt wird
            circleButton.setOnMouseClicked(e -> {
                System.out.println("Spieler 1 ...");
                try {
                    new Player1().start(new Stage());
                    stage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
        }

        public static void main(String[] args) {
            launch();
        }
    }


