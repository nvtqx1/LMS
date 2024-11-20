import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewBooks {
    public void start(Stage primaryStage, LibraryDashboard dashboard) {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));

        Label headerLabel = new Label("Danh sách sách có sẵn:");

        ListView<Book> bookListView = new ListView<>();
        bookListView.getItems().addAll(DatabaseHelper.getBooks());

        // Nút quay lại Dashboard
        Button backButton = new Button("Quay lại");
        backButton.setOnAction(e -> dashboard.start(primaryStage));

        layout.getChildren().addAll(headerLabel, bookListView, backButton);

        Scene scene = new Scene(layout, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Xem sách");
    }
}
