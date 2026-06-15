import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameBoard extends Application {

    private static final int ROWS = 6;
    private static final int COLS = 7;

    private int[][] board = new int[ROWS][COLS]; // 0 = leer, 1 = Spieler1, 2 = Spieler2
    private int currentPlayer = 1;
    private Circle[][] circles = new Circle[ROWS][COLS];

    private Player player1;
    private Player player2;
    private Text turnText = new Text();

    public GameBoard(Player p1, Player p2) {
        this.player1 = p1;
        this.player2 = p2;
    }

    public GameBoard(){
        this.player1 = new Player("Spieler1");
        this.player2 = new Player("Spieler2");
    }


    @Override
    public void start(Stage stage) {

        turnText.setFont(Font.font(18));
        turnText.setText(player1.getName() + " ist dran (Rot)");

        GridPane grid = new GridPane();
        grid.setHgap(8);
        grid.setVgap(8);
        grid.setAlignment(Pos.CENTER);

        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {

                final int row = r;
                final int col = c;

                Circle circle = new Circle(35);
                circle.setFill(Color.LIGHTGRAY);
                circle.setStroke(Color.DARKGRAY);
                circle.setStrokeWidth(2);

                // Klick
                circle.setOnMouseClicked(e -> handleClick(col, stage));

                circles[r][c] = circle;
                grid.add(circle, c, r);
            }
        }

        VBox root = new VBox(16, turnText, grid);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-padding: 20; -fx-background-color: #1a56a0;");

        stage.setScene(new Scene(root, 560, 520));
        stage.setTitle("4 Gewinnt");
        stage.show();
    }

    private void handleClick(int col, Stage stage) {
        int row = getNextRow(col);
        if (row == -1) return; // Spalte voll

        board[row][col] = currentPlayer;
        circles[row][col].setFill(currentPlayer == 1 ? Color.RED : Color.GOLD);

        if (checkWin(row, col)) {
            Player winner = currentPlayer == 1 ? player1 : player2;
            Player loser  = currentPlayer == 1 ? player2 : player1;
            winner.addWins();
            loser.addLosses();
            PlayerStorage.savePlayers(java.util.List.of(player1, player2));
            showAlert(winner.getName() + " gewinnt!", stage);
            return;
        }

        if (isBoardFull()) {
            player1.addDraws();
            player2.addDraws();
            PlayerStorage.savePlayers(java.util.List.of(player1, player2));
            showAlert("Unentschieden!", stage);
            return;
        }

        currentPlayer = currentPlayer == 1 ? 2 : 1;
        String name = currentPlayer == 1 ? player1.getName() : player2.getName();
        String farbe = currentPlayer == 1 ? "Rot" : "Gelb";
        turnText.setText(name + " ist dran (" + farbe + ")");
    }

    private int getNextRow(int col) {
        for (int r = ROWS - 1; r >= 0; r--) {
            if (board[r][col] == 0) return r;
        }
        return -1;
    }

    private boolean checkWin(int row, int col) {
        return checkDir(row, col, 0, 1) || checkDir(row, col, 1, 0)
                || checkDir(row, col, 1, 1) || checkDir(row, col, 1, -1);
    }

    private boolean checkDir(int row, int col, int dr, int dc) {
        int count = 1;
        int p = board[row][col];
        for (int d = 1; d < 4; d++) {
            int r = row + dr * d, c = col + dc * d;
            if (r < 0 || r >= ROWS || c < 0 || c >= COLS || board[r][c] != p) break;
            count++;
        }
        for (int d = 1; d < 4; d++) {
            int r = row - dr * d, c = col - dc * d;
            if (r < 0 || r >= ROWS || c < 0 || c >= COLS || board[r][c] != p) break;
            count++;
        }
        return count >= 4;
    }

    private boolean isBoardFull() {
        for (int c = 0; c < COLS; c++)
            if (board[0][c] == 0) return false;
        return true;
    }

    private void showAlert(String msg, Stage stage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Spielende");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
        stage.close();
    }

    public static void main(String[] args) {
        launch();
    }
}
