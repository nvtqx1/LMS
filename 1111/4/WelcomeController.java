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
import BTL.AdminClasses.Admins;
import BTL.DatabaseClasses.Books;
import BTL.MemberClasses.Members;

import java.io.IOException;

public class WelcomeController extends BTL.DatabaseClasses.SystemDatabase {
    @FXML
    public Button startSystem;

    public void startSystem(ActionEvent event) throws IOException {
        setDB();
        Parent root = FXMLLoader.load(BTL.LaunchClasses.SystemLaunch.class.getResource("/BTL/start.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Thêm hiệu ứng làm mờ cho nền
        Pane glassPane = (Pane) root.lookup(".glass");
        GaussianBlur blur = new GaussianBlur(8);
        glassPane.setEffect(blur);

        // Thay đổi biểu tượng của cửa sổ ứng dụng
        Image icon = new Image(getClass().getResourceAsStream("/BTL/book.png"));
        stage.getIcons().add(icon);

        stage.setTitle("Member sign in");
        Scene scene = new Scene(root, 900, 700);
        stage.setScene(scene);
        stage.show();
    }

    public void setDB(){
        Books book1 = new Books("To Kill a Mockingbird", "Harper Lee", 5, 5, 50, 20);
        Books book2 = new Books("Pride and Prejudice", "Jane Austen", 5, 5, 50, 20);
        Books book3 = new Books("Crime and Punishment", "Fyodor Dostoevsky", 5, 5, 50, 20);
//        books.add(book1);
//        books.add(book2);
//        books.add(book3);

        Members member1 = new Members("Long", "20242024", "longuet@gmail.com");
        Members member2 = new Members("Dũng", "20242023", "dung90@gmail.com");
        Members member3 = new Members("Quang", "20242022", "quangbeo@gmail.com");
//        members.add(member1);
//        members.add(member2);
//        members.add(member3);

        Admins admin1 = new Admins("Dũng", "20242023", "dung90@gmail.com");
//        admins.add(admin1);
    }

}
