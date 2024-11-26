package com.example.lms;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.IOException;
import java.net.URL;
import java.sql.*;
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
    private Statement statement;
    private ResultSet result;
    private boolean switched = false;
    private double x = 0;
    private double y = 0;

    public void login() {
        if (login_Btn.getText().equals("LOGIN")) {
            String sql = "SELECT * FROM student WHERE studentNumber = ? and password = ?";
            connect = Database.connectDB();
            try {
                PreparedStatement prepare = connect.prepareStatement(sql);
                prepare.setString(1, studentid.getText());
                prepare.setString(2, password.getText());
                result = prepare.executeQuery();
                Alert alert;
                if (studentid.getText().isEmpty() || password.getText().isEmpty()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Thông báo!");
                    alert.setHeaderText(null);
                    alert.setContentText("Vui lòng điền đầy đủ thông tin.");
                    alert.showAndWait();
                } else {
                    if (result.next()) {
                        getData.studentId = studentid.getText();
                        getData.path = result.getString("image");

                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Thông báo");
                        alert.setHeaderText(null);
                        alert.setContentText("Đăng nhập thành công");
                        alert.showAndWait();

                        login_Btn.getScene().getWindow().hide();
                        Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
                        Stage stage = new Stage();
                        Scene scene = new Scene(root);
                        root.setOnMousePressed((MouseEvent event) -> {

                            x = event.getSceneX();
                            y = event.getSceneY();

                        });
                        root.setOnMouseDragged((MouseEvent event) -> {

                            stage.setX(event.getScreenX() - x);
                            stage.setY(event.getScreenY() - y);
                        });

                        stage.initStyle(StageStyle.TRANSPARENT);
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
        } else {

            try {
                Alert alert;
                String sql = "INSERT INTO `student` (`studentNumber`, `password`,`image`) VALUES(?, ?, ?);";
                String sql1 = "SELECT * FROM student WHERE studentNumber = ?";
                connect = Database.connectDB();
                PreparedStatement prepare = connect.prepareStatement(sql);
                PreparedStatement statement = connect.prepareStatement(sql1);
                statement.setString(1, studentid.getText());
                ResultSet resultSet = statement.executeQuery();
                prepare.setString(1, studentid.getText());
                prepare.setString(2, password.getText());
                prepare.setString(3,"E:\\LMS\\src\\main\\resources\\com\\example\\lms\\image\\ava.png");
                int rowsInserted = prepare.executeUpdate();
                if (resultSet.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText(null);
                    alert.setContentText("Tài khoản đã tồn tại");
                    alert.showAndWait();
                } else if (studentid.getText().length() <= 3) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText(null);
                    alert.setContentText("Tài khoản quá ngắn");
                    alert.showAndWait();
                } else if (studentid.getText().contains(" ")) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText(null);
                    alert.setContentText("Tài khoản không được chứa dấu cách");
                    alert.showAndWait();
                } else if (password.getText().contains(" ")) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText(null);
                    alert.setContentText("Mật khẩu không được chứa dấu cách");
                    alert.showAndWait();
                } else if (password.getText().length() <= 3) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText(null);
                    alert.setContentText("Mật khẩu quá ngắn");
                    alert.showAndWait();
                } else if (rowsInserted >= 1) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText(null);
                    alert.setContentText("Tạo Tài Khoản Thành Công");
                    alert.showAndWait();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void numbersOnly(KeyEvent event) {
        if (event.getCharacter().matches("[^\\e\t\r\\d+$]")) {
            event.consume();
            studentid.setStyle("-fx-border-color:#e04040");
        } else {
            studentid.setStyle("-fx-border-color:#fff");
        }
    }

    @FXML
    public void minimize() {
        Stage stage = (Stage) minimize.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void createAnAccountClicked(MouseEvent mouseEvent) {
        // clear the text fields if changing scenes
        studentid.clear();
        password.clear();
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
