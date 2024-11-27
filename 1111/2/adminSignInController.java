package org.example.librarymanagementsystemgui.AdminClasses;

import com.example.lms.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class adminSignInController extends Database {
    @FXML
    private TextField nameField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField emailField;

    @FXML
    public void signIn(ActionEvent event) throws IOException {
        // Lấy thông tin từ các trường nhập liệu
        String name = nameField.getText();
        String phone = phoneField.getText();
        String email = emailField.getText();

        // Tìm kiếm quản trị viên phù hợp
        Optional<BTL.AdminClasses.Admins> matchedAdmin = admins.stream()
                .filter(admin ->
                        admin.getName().equals(name) &&
                                admin.getPhoneNumber().equals(phone) &&
                                admin.getEmail().equals(email)
                )
                .findFirst();

        if (matchedAdmin.isPresent()) {
            // Nếu tìm thấy quản trị viên, chuyển đến trang quản trị
            matchedAdmin.get().receiveAdmin(event, matchedAdmin.get());
            navigateToAdminAccessPage(event);
        } else {
            // Hiển thị thông báo lỗi nếu không tìm thấy quản trị viên
            showInvalidAdminAlert(event);
        }
    }

    private void navigateToAdminAccessPage(ActionEvent event) throws IOException {
        Parent root = loadFXMLPage("/BTL/adminAccess.fxml");
        setupStage(event, root);
    }

    private void showInvalidAdminAlert(ActionEvent event) throws IOException {
        Parent root = loadFXMLPage("/BTL/adminSignIn.fxml");
        setupStage(event, root);
        alert(event, "Dữ liệu không hợp lệ", "Quản trị viên không tồn tại, vui lòng đăng nhập lại!");
    }

    private Parent loadFXMLPage(String resourcePath) throws IOException {
        return FXMLLoader.load(SystemLaunch.class.getResource(resourcePath));
    }

    private void setupStage(ActionEvent event, Parent root) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        // Thêm hiệu ứng mờ nền
        Pane glassPane = (Pane) root.lookup(".glass");
        GaussianBlur blur = new GaussianBlur(8);
        glassPane.setEffect(blur);

        // Đặt biểu tượng cho cửa sổ
        Image icon = new Image(getClass().getResourceAsStream("/BTL/book.png"));
        stage.getIcons().add(icon);

        stage.setTitle("Hệ thống Quản lý Thư viện");
        Scene scene = new Scene(root, 900, 700);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void back(ActionEvent event) throws IOException {
        Parent root = loadFXMLPage("/BTL/start.fxml");
        setupStage(event, root);
    }
}