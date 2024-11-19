import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.List;

public class ManageUsers {
    public void start(Stage primaryStage, LibraryDashboard dashboard) {
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(10));
        layout.setAlignment(Pos.TOP_CENTER);

        Label headerLabel = new Label("Quản lý thành viên");

        // Lấy danh sách người dùng từ cơ sở dữ liệu
        List<User> users = DatabaseHelper.getUsers();

        // Hiển thị danh sách người dùng
        StringBuilder userList = new StringBuilder();
        for (User user : users) {
            userList.append("Username: ").append(user.getUsername())
                    .append(" | Role: ").append(user.getRole()).append("\n");
        }

        Label userListLabel = new Label(userList.toString());

        // Tạo ScrollPane để cuộn qua danh sách
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(userListLabel);
        scrollPane.setFitToWidth(true);

        // Nút quay lại
        Button backButton = new Button("Quay lại");
        backButton.setOnAction(e -> dashboard.goBack(primaryStage));

        layout.getChildren().addAll(headerLabel, scrollPane, backButton);

        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Quản lý thành viên");
        primaryStage.show();

        // Lưu scene vào stack
        dashboard.getSceneStack().push(scene);
    }
}
