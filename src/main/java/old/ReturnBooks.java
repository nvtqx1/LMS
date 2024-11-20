import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ReturnBooks {
    public void start(Stage primaryStage, LibraryDashboard dashboard) {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));

        Label headerLabel = new Label("Trả sách:");

        // Danh sách sách đã mượn
        ListView<Book> borrowedBooksListView = new ListView<>();
        borrowedBooksListView.getItems().addAll(DatabaseHelper.getBorrowedBooks());

        // Nút trả sách
        Button returnButton = new Button("Trả sách");
        returnButton.setOnAction(e -> {
            Book selectedBook = borrowedBooksListView.getSelectionModel().getSelectedItem();
            if (selectedBook == null) {
                showAlert(Alert.AlertType.WARNING, "Cảnh báo", "Vui lòng chọn một cuốn sách để trả.");
                return;
            }

            if (DatabaseHelper.returnBook(selectedBook)) {
                borrowedBooksListView.getItems().clear();
                borrowedBooksListView.getItems().addAll(DatabaseHelper.getBorrowedBooks());
                showAlert(Alert.AlertType.INFORMATION, "Thành công", "Trả sách thành công!");
            } else {
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Trả sách thất bại!");
            }
        });

        // Nút quay lại Dashboard
        Button backButton = new Button("Quay lại");
        backButton.setOnAction(e -> dashboard.start(primaryStage));

        layout.getChildren().addAll(headerLabel, borrowedBooksListView, returnButton, backButton);

        Scene scene = new Scene(layout, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Trả sách");
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
