import java.util.function.Consumer;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginScreen {
    public void start(Stage primaryStage, Consumer<String> onSuccess) {
        VBox layout = new VBox(10.0);
        layout.setPadding(new Insets(10.0));
        Label headerLabel = new Label("Quản lý thư viện");
        Label usernameLabel = new Label("Tên đăng nhập:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Mật khẩu:");
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Đăng nhập");
        Label loginStatusLabel = new Label();

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            // Kiểm tra đăng nhập thông qua DatabaseHelper
            User user = DatabaseHelper.login(username, password);
            if (user != null) {
                loginStatusLabel.setText("Đăng nhập thành công!");
                onSuccess.accept(user.getRole()); // Truyền role (admin/reader) sang màn hình tiếp theo
            } else {
                loginStatusLabel.setText("Đăng nhập thất bại.");
            }
        });

        layout.getChildren().addAll(headerLabel, usernameLabel, usernameField, passwordLabel, passwordField, loginButton, loginStatusLabel);
        Scene loginScene = new Scene(layout, 400.0, 300.0);
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Quản lý thư viện");
        primaryStage.show();
    }
}
