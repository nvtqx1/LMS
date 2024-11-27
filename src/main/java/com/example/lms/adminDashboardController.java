package com.example.lms;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class adminDashboardController {

    @FXML
    private TableColumn<?, ?> Author;

    @FXML
    private AnchorPane addBook_form;

    @FXML
    private TextField add_author;

    @FXML
    private TextField add_bookType;

    @FXML
    private TextField add_date;

    @FXML
    private TextField add_imageURL;

    @FXML
    private ImageView add_imageView;

    @FXML
    private TextField add_title;

    @FXML
    private Button add_user_btn;

    @FXML
    private Button arrow_btn;

    @FXML
    private Button bars_btn;

    @FXML
    private AnchorPane bookManage_form;

    @FXML
    private TableColumn<?, ?> book_Title;

    @FXML
    private TableColumn<?, ?> book_Type;

    @FXML
    private Button book_manage_btn;

    @FXML
    private AnchorPane borrowManager_form;

    @FXML
    private TableColumn<?, ?> borrow_Id;

    @FXML
    private TableColumn<?, ?> borrow_author;

    @FXML
    private TableColumn<?, ?> borrow_bookType;

    @FXML
    private TableColumn<?, ?> borrow_date;

    @FXML
    private TableColumn<?, ?> borrow_firstName;

    @FXML
    private TableColumn<?, ?> borrow_gender;

    @FXML
    private TableColumn<?, ?> borrow_lastName;

    @FXML
    private TableColumn<?, ?> borrow_status;

    @FXML
    private Circle circle_image;

    @FXML
    private Button close;

    @FXML
    private Label currentForm_label;

    @FXML
    private TableColumn<?, ?> date;

    @FXML
    private Button edit_btn;

    @FXML
    private Button halfNav_availableBtn;

    @FXML
    private AnchorPane halfNav_form;

    @FXML
    private Button halfNav_returnBtn;

    @FXML
    private Button halfNav_saveBtn;

    @FXML
    private Button halfNav_takeBtn;

    @FXML
    private Button half_logout_btn;

    @FXML
    private TableColumn<?, ?> image_URL;

    @FXML
    private Button issueBooks_btn;

    @FXML
    private Button logout_btn;

    @FXML
    private AnchorPane mainCenter_form;

    @FXML
    private ImageView manage_book_imageView;

    @FXML
    private Button minimize;

    @FXML
    private Button music_on_btn;

    @FXML
    private AnchorPane nav_form;

    @FXML
    private Button remove_user_btn;

    @FXML
    private Button returnBooks_btn;

    @FXML
    private Button savedBooks_btn;

    @FXML
    private Circle smallCircle_image;

    @FXML
    private Label studentId_label;

    @FXML
    private Label take_authorLabel;

    @FXML
    private Label take_dateLabel;

    @FXML
    private Label take_genreLabel;

    @FXML
    private Label take_titleLabel;

    @FXML
    private AnchorPane userManage_form;

    @FXML
    private TableColumn<?, ?> user_ava;

    @FXML
    private Circle user_image;

    @FXML
    private TableColumn<?, ?> user_pass;

    @FXML
    private TableColumn<?, ?> user_studentID;

    private PreparedStatement prepare;
    private Connection connect;
    private Statement statement;
    private double x = 0;
    private double y = 0;

    @FXML
    public void exit() {
        System.exit(0);
    }

    @FXML
    public void half_logout(javafx.event.ActionEvent event) {
        try {
            if (event.getSource() == half_logout_btn) {
                Parent root = FXMLLoader.load(getClass().getResource("loginSignUp.fxml"));

                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent e) -> {
                    x = e.getSceneX();
                    y = e.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent e) -> {

                    stage.setX(e.getScreenX() - x);
                    stage.setY(e.getScreenY() - y);

                });

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();

                half_logout_btn.getScene().getWindow().hide();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Image image;

    @FXML
    public void insertImage() {

        FileChooser open = new FileChooser();
        open.setTitle("Image File");
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image file", "*png", "*jpg"));
        Stage stage = (Stage) nav_form.getScene().getWindow();

        File file = open.showOpenDialog(stage);

        if (file != null) {

            image = new Image(file.toURI().toString(), 180, 114, false, true);
            circle_image.setFill(new ImagePattern(image));
            smallCircle_image.setFill(new ImagePattern(image));

            getData.path = file.getAbsolutePath();

            changeProfile();

        }
    }

    public void changeProfile() {

        String uri = getData.path;
        uri = uri.replace("\\", "\\\\");

        String sql = "UPDATE student SET image = '" + uri + "' WHERE studentNumber = '" + getData.studentId + "'";

        connect = Database.connectDB();

        try {

            statement = connect.createStatement();
            statement.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void logout(javafx.event.ActionEvent event) {
        try {
            if (event.getSource() == logout_btn) {
                Parent root = FXMLLoader.load(getClass().getResource("loginSignUp.fxml"));

                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent e) -> {
                    x = e.getSceneX();
                    y = e.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent e) -> {

                    stage.setX(e.getScreenX() - x);
                    stage.setY(e.getScreenY() - y);

                });

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();

                logout_btn.getScene().getWindow().hide();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void minimize() {
        Stage stage = (Stage) minimize.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void music_on(ActionEvent event) {

    }

    @FXML
    void navButtonDesign(ActionEvent event) {

    }

    @FXML
    void sideNavButtonDesign(ActionEvent event) {

    }

    @FXML
    public void sliderArrow() {

        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(.5));
        slide.setNode(nav_form);
        slide.setToX(-360);

        TranslateTransition slide1 = new TranslateTransition();
        slide1.setDuration(Duration.seconds(.5));
        slide1.setNode(mainCenter_form);
        slide1.setToX(-360 + 100);

        TranslateTransition slide2 = new TranslateTransition();
        slide2.setDuration(Duration.seconds(.5));
        slide2.setNode(halfNav_form);
        slide2.setToX(0);

        slide.setOnFinished((javafx.event.ActionEvent event) -> {
            bars_btn.setVisible(true);
            arrow_btn.setVisible(false);
        });

        slide2.play();
        slide1.play();
        slide.play();

    }

    @FXML
    public void sliderBars() {

        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(.5));
        slide.setNode(nav_form);
        slide.setToX(0);

        TranslateTransition slide1 = new TranslateTransition();
        slide1.setDuration(Duration.seconds(.5));
        slide1.setNode(mainCenter_form);
        slide1.setToX(0);

        TranslateTransition slide2 = new TranslateTransition();
        slide2.setDuration(Duration.seconds(.5));
        slide2.setNode(halfNav_form);
        slide2.setToX(-77);

        slide.setOnFinished((javafx.event.ActionEvent event) -> {
            arrow_btn.setVisible(true);
            bars_btn.setVisible(false);
        });

        slide2.play();
        slide1.play();
        slide.play();
    }

}

