import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Admin extends User {
    public Admin(String username) {
        super(username, "admin"); // Gọi constructor lớp cha
    }

    public void start(Stage primaryStage) {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));

        Label headerLabel = new Label("Chào mừng Admin!");
        Button manageBooksButton = new Button("Quản lý sách");

        // Tạo đối tượng LibraryDashboard để quản lý quay lại
        LibraryDashboard dashboard = new LibraryDashboard("admin");

        // Chuyển đến giao diện quản lý sách
        manageBooksButton.setOnAction(e -> {
            ManageBooks manageBooks = new ManageBooks();
            manageBooks.start(primaryStage, "admin", dashboard); // Truyền thêm tham số
        });

        layout.getChildren().addAll(headerLabel, manageBooksButton);

        Scene scene = new Scene(layout, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Admin Dashboard");
        primaryStage.show();
    }
}
