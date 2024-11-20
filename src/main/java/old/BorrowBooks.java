import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BorrowBooks {
    public void start(Stage primaryStage, LibraryDashboard dashboard) {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));

        Label headerLabel = new Label("Mượn sách:");

        // Danh sách sách có sẵn để mượn
        ListView<Book> bookListView = new ListView<>();
        bookListView.getItems().addAll(DatabaseHelper.getBooks());

        // Nút mượn sách
        Button borrowButton = new Button("Mượn sách");
        borrowButton.setOnAction(e -> {
            Book selectedBook = bookListView.getSelectionModel().getSelectedItem();
            if (selectedBook == null) {
                showAlert(Alert.AlertType.WARNING, "Cảnh báo", "Vui lòng chọn một cuốn sách để mượn.");
                return;
            }

            if (DatabaseHelper.borrowBook(selectedBook)) {
                bookListView.getItems().clear();
                bookListView.getItems().addAll(DatabaseHelper.getBooks());
                showAlert(Alert.AlertType.INFORMATION, "Thành công", "Mượn sách thành công!");
            } else {
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Mượn sách thất bại!");
            }
        });

        // Nút quay lại Dashboard
        Button backButton = new Button("Quay lại");
        backButton.setOnAction(e -> dashboard.start(primaryStage));

        layout.getChildren().addAll(headerLabel, bookListView, borrowButton, backButton);

        Scene scene = new Scene(layout, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Mượn sách");
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
