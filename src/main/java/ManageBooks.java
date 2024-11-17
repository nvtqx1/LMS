import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ManageBooks {
    public void start(Stage primaryStage) {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));

        Label headerLabel = new Label("Quản lý sách");
        ListView<Book> bookListView = new ListView<>();
        bookListView.getItems().addAll(DatabaseHelper.getBooks());

        Label titleLabel = new Label("Tên sách:");
        TextField titleField = new TextField();
        Label authorLabel = new Label("Tác giả:");
        TextField authorField = new TextField();

        Button addBookButton = new Button("Thêm sách");
        addBookButton.setOnAction(e -> {
            String title = titleField.getText();
            String author = authorField.getText();

            if (DatabaseHelper.addBook(title, author)) {
                bookListView.getItems().clear();
                bookListView.getItems().addAll(DatabaseHelper.getBooks());
                titleField.clear();
                authorField.clear();
            }
        });

        layout.getChildren().addAll(headerLabel, bookListView, titleLabel, titleField, authorLabel, authorField, addBookButton);

        Scene scene = new Scene(layout, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Quản lý sách");
        primaryStage.show();
    }
}
