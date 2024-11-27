package BTL.LaunchClasses;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;

public class SystemLaunch extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        // Tải giao diện FXML từ vị trí mới "BTL/welcome.fxml"
        Parent root = FXMLLoader.load(SystemLaunch.class.getResource("/BTL/welcome.fxml"));

        // Áp dụng hiệu ứng mờ cho một Pane trong giao diện
        Pane glassPane = (Pane) root.lookup(".glass");
        GaussianBlur blur = new GaussianBlur(8);
        glassPane.setEffect(blur);

        // Đổi biểu tượng của cửa sổ
        Image icon = new Image(getClass().getResourceAsStream("/BTL/book.png"));
        primaryStage.getIcons().add(icon);

        // Thiết lập tiêu đề và kích thước cho cửa sổ chính
        primaryStage.setTitle("Library Management System");
        primaryStage.setScene(new Scene(root, 900, 700));
        primaryStage.show();
    }

    // Hàm main để chạy ứng dụng
    public static void main(String[] args) {
        launch();
    }
}
