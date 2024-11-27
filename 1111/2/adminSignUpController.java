package BTL.AdminClasses;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class adminSignUpController extends StartController {
    @FXML
    public TextField nameField;
    @FXML
    public TextField phoneField;
    @FXML
    public TextField emailField;
    @FXML
    public Button signUp;
    @FXML
    public Button back;

    public void signUp(ActionEvent event) throws IOException {
        // Tải giao diện xác nhận đăng ký
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/BTL/adminConfirmSignUpDetails.fxml"));
        Parent root = loader.load();
        BTL.AdminClasses.adminConfirmSignUpDetailsController controller = loader.getController();

        // Lấy stage hiện tại
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        // Kiểm tra tính hợp lệ của thông tin
        if(Database.isValidName(nameField.getText()) &&
                Database.isValidNumber(phoneField.getText()) &&
                Database.isValidEmail(emailField.getText())) {

            // Đặt các thông tin vào controller
            controller.name.setText(nameField.getText());
            controller.phone.setText(phoneField.getText());
            controller.email.setText(emailField.getText());

            // Thêm hiệu ứng mờ nền
            Pane glassPane = (Pane) root.lookup(".glass");
            GaussianBlur blur = new GaussianBlur(8);
            glassPane.setEffect(blur);

            // Thay đổi icon của stage
            Image icon = new Image(getClass().getResourceAsStream("/BTL/book.png"));
            stage.getIcons().add(icon);
            stage.setTitle("Member sign in");

            // Tạo và hiển thị scene mới
            Scene scene = new Scene(root, 900, 700);
            stage.setScene(scene);
            stage.show();
        } else {
            // Hiển thị cảnh báo nếu dữ liệu không hợp lệ
            String title = "Invalid data";
            String content = "Invalid data, please Sign Up again!";
            alert(event, title, content);
            adminSignUp(event);
        }
    }

    public void back(ActionEvent event) throws IOException {
        // Tải giao diện ban đầu
        Parent root = FXMLLoader.load(SystemLaunch.class.getResource("/BTL/start.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        // Thêm hiệu ứng mờ nền
        Pane glassPane = (Pane) root.lookup(".glass");
        GaussianBlur blur = new GaussianBlur(8);
        glassPane.setEffect(blur);

        // Thay đổi icon của stage
        Image icon = new Image(getClass().getResourceAsStream("/BTL/book.png"));
        stage.getIcons().add(icon);
        stage.setTitle("Library Management System");

        // Tạo và hiển thị scene mới
        Scene scene = new Scene(root, 900, 700);
        stage.setScene(scene);
        stage.show();
    }
}