import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    @FXML
    void login(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "All fields are required!");
            return;
        }

        try (Connection connection = DatabaseConnection.getConnection()) {
            // Query để kiểm tra tài khoản
            String query = "SELECT password FROM student WHERE studentNumber = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String storedPassword = resultSet.getString("password");

                // Kiểm tra mật khẩu (nên sử dụng mã hóa mật khẩu thực tế)
                if (storedPassword.equals(password)) {
                    // Đăng nhập thành công, chuyển sang dashboard
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Login successful!");
                    navigateToDashboard();
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "Invalid username or password!");
                }
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Invalid username or password!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Database error occurred!");
        }
    }

    @FXML
    void register(ActionEvent event) {
        // Logic đăng ký nếu cần
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.show();
    }

    private void navigateToDashboard() {
        try {
            // Tải Dashboard.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/dashboard.fxml"));
            Scene scene = new Scene(loader.load(), 800, 600);

            // Lấy Stage hiện tại
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Library Management System - Dashboard");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load Dashboard!");
        }
    }
}
