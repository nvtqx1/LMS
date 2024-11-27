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
import BTL.LaunchClasses.SystemLaunch;
import BTL.MemberClasses.Members;

import java.io.IOException;

public class MemberSignInController extends SystemDataBase {
    // Khai báo các trường nhập liệu FXML
    @FXML
    public TextField idField;        // Trường nhập mã thành viên
    @FXML
    public TextField nameField;      // Trường nhập tên
    @FXML
    public TextField phoneField;     // Trường nhập số điện thoại
    @FXML
    public TextField emailField;     // Trường nhập email
    @FXML
    public Button signIn;            // Nút đăng nhập
    @FXML
    public Button back;              // Nút quay lại

    // Phương thức xử lý sự kiện đăng nhập
    public void signIn(ActionEvent event) throws IOException {
        // Kiểm tra thông tin thành viên
        boolean isMember = false;
        for (Members member : members) {
            // Kiểm tra thông tin đăng nhập có khớp với thành viên nào không
            if (member.getMemberId() == Integer.parseInt(idField.getText())
                    && member.getName().equals(nameField.getText())
                    && member.getPhoneNumber().equals(phoneField.getText())
                    && member.getEmail().equals(emailField.getText())) {

                // Nếu khớp, nhận thông tin thành viên và chuyển màn hình
                member.receiveMember(event, member);
                isMember = true;
                break;
            }
        }

        if (!isMember) {
            // Nếu không tìm thấy thành viên, hiển thị thông báo lỗi
            String title = "Dữ liệu không hợp lệ";
            String content = "Thành viên không tồn tại!";
            alert(event, title, content);

            // Tải lại màn hình đăng nhập
            Parent root = FXMLLoader.load(SystemLaunch.class.getResource("/BTL/memberSignIn.fxml"));
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

            // Thêm hiệu ứng mờ nền
            Pane glassPane = (Pane) root.lookup(".glass");
            GaussianBlur blur = new GaussianBlur(8);
            glassPane.setEffect(blur);

            // Đặt biểu tượng cho cửa sổ
            Image icon = new Image(getClass().getResourceAsStream("/BTL/book.png"));
            stage.getIcons().add(icon);

            // Cấu hình và hiển thị màn hình
            stage.setTitle("Hệ Thống Quản Lý Thư Viện");
            Scene scene = new Scene(root, 900, 700);
            stage.setScene(scene);
            stage.show();
        } else {
            // Nếu đăng nhập thành công, chuyển đến màn hình truy cập thành viên
            Parent root = FXMLLoader.load(SystemLaunch.class.getResource("/BTL/memberAccess.fxml"));
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

            // Thêm hiệu ứng mờ nền
            Pane glassPane = (Pane) root.lookup(".glass");
            GaussianBlur blur = new GaussianBlur(8);
            glassPane.setEffect(blur);

            // Đặt biểu tượng cho cửa sổ
            Image icon = new Image(getClass().getResourceAsStream("/BTL/book.png"));
            stage.getIcons().add(icon);

            // Cấu hình và hiển thị màn hình
            stage.setTitle("Hệ Thống Quản Lý Thư Viện");
            Scene scene = new Scene(root, 900, 700);
            stage.setScene(scene);
            stage.show();
        }
    }

    // Phương thức xử lý sự kiện quay lại
    public void back(ActionEvent event) throws IOException {
        // Tải màn hình bắt đầu
        Parent root = FXMLLoader.load(SystemLaunch.class.getResource("/BTL/start.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        // Thêm hiệu ứng mờ nền
        Pane glassPane = (Pane) root.lookup(".glass");
        GaussianBlur blur = new GaussianBlur(8);
        glassPane.setEffect(blur);

        // Đặt biểu tượng cho cửa sổ
        Image icon = new Image(getClass().getResourceAsStream("/BTL/book.png"));
        stage.getIcons().add(icon);

        // Cấu hình và hiển thị màn hình
        stage.setTitle("Hệ Thống Quản Lý Thư Viện");
        Scene scene = new Scene(root, 900, 700);
        stage.setScene(scene);
        stage.show();
    }
}