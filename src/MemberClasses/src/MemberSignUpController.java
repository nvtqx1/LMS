package BTL.MemberClasses;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import BTL.DatabaseClasses.SystemDataBase;
import BTL.LaunchClasses.StartController;
import BTL.LaunchClasses.SystemLaunch;

import java.io.IOException;

public class mebmerSignUpController extends StartController {
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

    // Phương thức đăng ký thành viên
    public void signUp(ActionEvent event) throws IOException {
        // Tải giao diện xác nhận đăng ký
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/BTL/memberConfirmSignUpDetails.fxml"));
        Parent root = loader.load();
        memberConfirmSignUpDetailsController controller = loader.getController();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Kiểm tra tính hợp lệ của thông tin nhập vào
        if (SystemDataBase.isValidName(nameField.getText()) &&
                SystemDataBase.isValidNumber(phoneField.getText()) &&
                SystemDataBase.isValidEmail(emailField.getText())) {

            // Đặt thông tin vào giao diện xác nhận
            controller.name.setText(nameField.getText());
            controller.phone.setText(phoneField.getText());
            controller.email.setText(emailField.getText());

            // Thêm hiệu ứng mờ nền
            Pane glassPane = (Pane) root.lookup(".glass");
            GaussianBlur blur = new GaussianBlur(8);
            glassPane.setEffect(blur);

            // Thay đổi biểu tượng của cửa sổ
            Image icon = new Image(getClass().getResourceAsStream("/BTL/book.png"));
            stage.getIcons().add(icon);
            stage.setTitle("Đăng ký thành viên");

            // Tạo và hiển thị scene mới
            Scene scene = new Scene(root, 900, 700);
            stage.setScene(scene);
            stage.show();
        } else {
            // Hiển thị thông báo lỗi nếu dữ liệu không hợp lệ
            String title = "Dữ liệu không hợp lệ";
            String content = "Dữ liệu không hợp lệ, vui lòng đăng ký lại!";
            alert(event, title, content);
            memberSignUp(event);
        }
    }

    // Phương thức quay lại trang trước
    public void back(ActionEvent event) throws IOException {
        // Tải giao diện ban đầu
        Parent root = FXMLLoader.load(SystemLaunch.class.getResource("/BTL/start.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Thêm hiệu ứng mờ nền
        Pane glassPane = (Pane) root.lookup(".glass");
        GaussianBlur blur = new GaussianBlur(8);
        glassPane.setEffect(blur);

        // Thay đổi biểu tượng của cửa sổ
        Image icon = new Image(getClass().getResourceAsStream("/BTL/book.png"));
        stage.getIcons().add(icon);
        stage.setTitle("Hệ thống quản lý thư viện");

        // Tạo và hiển thị scene mới
        Scene scene = new Scene(root, 900, 700);
        stage.setScene(scene);
        stage.show();
    }
}