import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ManageBooks {
    public void start(Stage primaryStage, String userRole, LibraryDashboard dashboard) {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));

        // Tiêu đề
        Label headerLabel = new Label("Quản lý sách");

        // Danh sách sách trong kho
        Label availableBooksLabel = new Label("Danh sách sách trong kho:");
        ListView<Book> bookListView = new ListView<>();
        bookListView.getItems().addAll(DatabaseHelper.getBooks());

        // Các trường nhập liệu cho thêm sách
        Label titleLabel = new Label("Tên sách:");
        TextField titleField = new TextField();
        Label authorLabel = new Label("Tác giả:");
        TextField authorField = new TextField();

        // Nút thêm sách
        Button addBookButton = new Button("Thêm sách");
        addBookButton.setOnAction(e -> {
            String title = titleField.getText().trim();
            String author = authorField.getText().trim();

            if (title.isEmpty() || author.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Cảnh báo", "Tên sách và tác giả không được để trống.");
                return;
            }

            if (DatabaseHelper.addBook(title, author)) {
                bookListView.getItems().clear();
                bookListView.getItems().addAll(DatabaseHelper.getBooks());
                titleField.clear();
                authorField.clear();
                showAlert(Alert.AlertType.INFORMATION, "Thành công", "Thêm sách thành công!");
            } else {
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Thêm sách thất bại!");
            }
        });

        // Nút làm mới danh sách sách trong kho
        Button refreshButton = new Button("Làm mới danh sách");
        refreshButton.setOnAction(e -> {
            bookListView.getItems().clear();
            bookListView.getItems().addAll(DatabaseHelper.getBooks());
        });

        // Hiển thị danh sách sách đã cho mượn
        Label borrowedBooksLabel = new Label("Danh sách sách đã cho mượn:");
        ListView<Book> borrowedBooksListView = new ListView<>();
        borrowedBooksListView.getItems().addAll(DatabaseHelper.getBorrowedBooks());

        // Nút làm mới danh sách sách đã cho mượn
        Button refreshBorrowedBooksButton = new Button("Làm mới danh sách sách đã cho mượn");
        refreshBorrowedBooksButton.setOnAction(e -> {
            borrowedBooksListView.getItems().clear();
            borrowedBooksListView.getItems().addAll(DatabaseHelper.getBorrowedBooks());
        });

        // Nút quay lại Dashboard
        Button backButton = new Button("Quay lại");
        backButton.setOnAction(e -> dashboard.goBack(primaryStage));

        // Bố cục giao diện
        layout.getChildren().addAll(
                headerLabel,
                availableBooksLabel,
                bookListView,
                refreshButton,
                titleLabel, titleField,
                authorLabel, authorField,
                addBookButton,
                borrowedBooksLabel,
                borrowedBooksListView,
                refreshBorrowedBooksButton,
                backButton
        );

        Scene scene = new Scene(layout, 400, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Quản lý sách");
        primaryStage.show();
    }

    // Phương thức hiển thị cảnh báo
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
