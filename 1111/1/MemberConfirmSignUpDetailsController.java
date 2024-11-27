package BTL.MemberClasses;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class MemberConfirmSignUpDetailsController extends BTL.LaunchClasses.StartController {
    @FXML
    public Label name;

    @FXML
    public Label phone;

    @FXML
    public Label email;

    @FXML
    public Button confirm;

    @FXML
    public Button again;

    // Phương thức xác nhận đăng ký thành viên
    public void confirmSignUp(ActionEvent event) throws IOException {
        // Kiểm tra xem thành viên đã tồn tại chưa
        if(checkIfMemberExists(name.getText(), phone.getText(), email.getText())){
            String title = "Tồn tại";
            String content = "Bạn đã là thành viên, vui lòng đăng nhập!";
            alert(event, title, content);
            memberSignIn(event);
        } else {
            // Tăng số lượng thành viên
            SystemDataBase.memberCounter++;

            // Hiển thị ID của thành viên
            String title = "ID";
            String content = "ID của bạn là: " + SystemDataBase.memberCounter;
            alert(event, title, content);

            // Tạo và lưu thông tin thành viên mới
            Members member = new Members();
            member.setMemberId(SystemDataBase.memberCounter);
            member.setName(name.getText());
            member.setPhoneNumber(phone.getText());
            member.setEmail(email.getText());

            // Thêm thành viên vào cơ sở dữ liệu
            SystemDataBase.members.add(member);

            // Chuyển đến trang đăng nhập
            Parent root = FXMLLoader.load(SystemLaunch.class.getResource("/BTL/memberSignIn.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Thêm hiệu ứng mờ nền
            Pane glassPane = (Pane) root.lookup(".glass");
            GaussianBlur blur = new GaussianBlur(8);
            glassPane.setEffect(blur);

            // Đặt biểu tượng và tiêu đề cho cửa sổ
            Image icon = new Image(getClass().getResourceAsStream("/BTL/book.png"));
            stage.getIcons().add(icon);
            stage.setTitle("Đăng nhập thành viên");

            // Tạo và hiển thị scene mới
            Scene scene = new Scene(root, 900, 700);
            stage.setScene(scene);
            stage.show();
        }
    }

    // Phương thức đăng ký lại
    public void signUpAgain(ActionEvent event) throws IOException {
        // Tải lại trang đăng ký
        Parent root = FXMLLoader.load(SystemLaunch.class.getResource("/BTL/memberSignUp.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        // Thêm hiệu ứng mờ nền
        Pane glassPane = (Pane) root.lookup(".glass");
        GaussianBlur blur = new GaussianBlur(8);
        glassPane.setEffect(blur);

        // Đặt biểu tượng và tiêu đề cho cửa sổ
        Image icon = new Image(getClass().getResourceAsStream("/BTL/book.png"));
        stage.getIcons().add(icon);
        stage.setTitle("Đăng ký thành viên");

        // Tạo và hiển thị scene mới
        Scene scene = new Scene(root,900,700);
        stage.setScene(scene);
        stage.show();
    }
}