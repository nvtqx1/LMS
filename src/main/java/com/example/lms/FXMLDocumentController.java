package com.example.lms;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.io.IOException;
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

    @FXML
    private Text createAccount;

    @FXML
    private Text logIntoText;

    @FXML
    private Text logIntoText1;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    private boolean switched = false;

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
                    Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
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
    private void createAnAccountClicked(MouseEvent mouseEvent) {

        // clear the text fields if changing scenes
        studentid.clear();
        password.clear();
        //errorMessage.setVisible(false);

        switched = !switched;

        if (switched) {
            // if "Create an Account" is clicked, change the content of the texts
            logIntoText.setText("CREATE A");
            logIntoText1.setText("NEW ACCOUNT");
            createAccount.setText("or Log Into Your Account now!");
            login_Btn.setText("SIGNUP");
        } else {
            // if "Log into your Account" is clicked
            logIntoText.setText("LOG INTO");
            logIntoText1.setText("YOUR ACCOUNT");
            createAccount.setText("or Create an Account now!");
            login_Btn.setText("LOGIN");
        }
    }
    public void initialize() throws IOException {
        createAccount.hoverProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean newVal, Boolean oldVal) {
                if (newVal) {
                    createAccount.setFill(Color.WHITE);
                } else {
                    createAccount.setFill(Color.valueOf("#d1413f"));
                }
            }
        });
    }

    @FXML
    public void exit() {
        System.exit(0);
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}