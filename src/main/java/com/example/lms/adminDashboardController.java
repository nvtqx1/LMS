package com.example.lms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

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

    @FXML
    void exit(ActionEvent event) {

    }

    @FXML
    void half_logout(ActionEvent event) {

    }

    @FXML
    void insertImage(ActionEvent event) {

    }

    @FXML
    void logout(ActionEvent event) {

    }

    @FXML
    void minimize(ActionEvent event) {

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
    void sliderArrow(ActionEvent event) {

    }

    @FXML
    void sliderBars(ActionEvent event) {

    }

}

