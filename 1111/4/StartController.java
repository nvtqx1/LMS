package BTL.LaunchClasses;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class StartController extends BTL.DatabaseClasses.SystemDatabase {
    // Các nút điều khiển được khai báo FXML
    @FXML
    public Button signInMember;
    @FXML
    public Button signUpMember;
    @FXML
    public Button signInAdmin;
    @FXML
    public Button signUpAdmin;

    // Phương thức đăng nhập cho thành viên
    public void memberSignIn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(BTL.LaunchClasses.SystemLaunch.class.getResource("/BTL/memberSignIn.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        // Thêm hiệu ứng mờ nền
        Pane glassPane = (Pane) root.lookup(".glass");
        GaussianBlur blur = new GaussianBlur(8);
        glassPane.setEffect(blur);

        // Thay đổi biểu tượng của cửa sổ
        Image icon = new Image(getClass().getResourceAsStream("/BTL/book.png"));
        stage.getIcons().add(icon);

        stage.setTitle("Đăng nhập thành viên");
        Scene scene = new Scene(root,900,700);
        stage.setScene(scene);
        stage.show();
    }

    // Phương thức đăng ký cho thành viên
    public void memberSignUp(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(BTL.LaunchClasses.SystemLaunch.class.getResource("/BTL/memberSignUp.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        // Thêm hiệu ứng mờ nền
        Pane glassPane = (Pane) root.lookup(".glass");
        GaussianBlur blur = new GaussianBlur(8);
        glassPane.setEffect(blur);

        // Thay đổi biểu tượng của cửa sổ
        Image icon = new Image(getClass().getResourceAsStream("/BTL/book.png"));
        stage.getIcons().add(icon);

        stage.setTitle("Đăng ký thành viên");
        Scene scene = new Scene(root,900,700);
        stage.setScene(scene);
        stage.show();
    }

    // Phương thức đăng nhập cho quản trị viên
    public void adminSignIn(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(BTL.LaunchClasses.SystemLaunch.class.getResource("/BTL/adminSignIn.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        // Thêm hiệu ứng mờ nền
        Pane glassPane = (Pane) root.lookup(".glass");
        GaussianBlur blur = new GaussianBlur(8);
        glassPane.setEffect(blur);

        // Thay đổi biểu tượng của cửa sổ
        Image icon = new Image(getClass().getResourceAsStream("/BTL/book.png"));
        stage.getIcons().add(icon);

        stage.setTitle("Đăng nhập quản trị viên");
        Scene scene = new Scene(root,900,700);
        stage.setScene(scene);
        stage.show();
    }

    // Phương thức đăng ký cho quản trị viên
    public void adminSignUp(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(BTL.LaunchClasses.SystemLaunch.class.getResource("/BTL/adminSignUp.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        // Thêm hiệu ứng mờ nền
        Pane glassPane = (Pane) root.lookup(".glass");
        GaussianBlur blur = new GaussianBlur(8);
        glassPane.setEffect(blur);

        // Thay đổi biểu tượng của cửa sổ
        Image icon = new Image(getClass().getResourceAsStream("/BTL/book.png"));
        stage.getIcons().add(icon);

        stage.setTitle("Đăng ký quản trị viên");
        Scene scene = new Scene(root,900,700);
        stage.setScene(scene);
        stage.show();
    }
}