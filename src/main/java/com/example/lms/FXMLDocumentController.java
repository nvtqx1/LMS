package com.example.lms;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class FXMLDocumentController implements Initializable {

    @FXML
    private Button close;

    @FXML
    private Button login_Btn;

    @FXML
    private Button minimize;

    @FXML
    private PasswordField password;

    @FXML
    private TextField studentid;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    public void login() {
        //String sql = "SELECT * FROM accounts WHERE studentid = ? and password = ?";
        String sql = "SELECT * FROM student WHERE studentNumber = ? and password = ?";
        connect = Database.connectDB();
        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, studentid.getText());
            prepare.setString(2, password.getText());
            result = prepare.executeQuery();
            Alert alert;
            if (studentid.getText().isEmpty() || password.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Thông báo!");
                alert.setHeaderText(null);
                alert.setContentText("Vui lòng điền các trường trống.");
                alert.showAndWait();
            } else {
                if (result.next()) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText(null);
                    alert.setContentText("Đăng nhập thành công");
                    alert.showAndWait();

                    login_Btn.getScene().getWindow().hide();
                    Parent root = FXMLLoader.load(getClass().getResource("dashBoard.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);

                    stage.setScene(scene);
                    stage.show();
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText(null);
                    alert.setContentText("Tài Khoản hoặc Mật Khẩu không chính xác");
                    alert.showAndWait();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void numbersOnly(KeyEvent event){
        if(event.getCharacter().matches("[^\\e\t\r\\d+$]")){
            event.consume();
            studentid.setStyle("-fx-border-color:#e04040");
        }else{
            studentid.setStyle("-fx-border-color:#fff");
        }
    }

    @FXML
    public void mimimize() {
        Stage stage = (Stage) minimize.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    public void exit() {
        System.exit(0);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}