package com.example.lms;

import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class adminDashboardController implements Initializable {

    @FXML
    private Button AddBook_btn;

    @FXML
    private Button addBooks_btn;

    @FXML
    private Button addUsers_btn;

    @FXML
    private TableColumn<?, ?> Author;

    @FXML
    private Button borrowMng_btn;

    @FXML
    private Button userMng_btn;

    @FXML
    private AnchorPane addBook_form;

    @FXML
    private TextField addUser_URLimage;

    @FXML
    private TextField add_ID;

    @FXML
    private TextField add_author;

    @FXML
    private TextField add_bookType;

    @FXML
    private Button add_bookURL_btn;

    @FXML
    private TextField add_date;

    @FXML
    private Button add_edit_url;

    @FXML
    private TextField add_imageURL;

    @FXML
    private ImageView add_imageView;

    @FXML
    private TextField add_pass;

    @FXML
    private TextField add_title;

    @FXML
    private Button userMng_addBtn;

    @FXML
    private Circle add_user_image;

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
    private TableColumn<?, ?> borrow_bookTitle;

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
    private Button halfNav_BorrowMng_btn;

    @FXML
    private Button halfNav_UserMng_btn;

    @FXML
    private Button halfNav_addBook_btn;

    @FXML
    private Button halfNav_addUser_btn;

    @FXML
    private Button halfNav_booksMng_btn;

    @FXML
    private AnchorPane halfNav_form;

    @FXML
    private Button half_logout_btn;

    @FXML
    private TableColumn<?, ?> image_URL;

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
    private Button userMng_revBtn;

    @FXML
    private Circle smallCircle_image;

    @FXML
    private Label studentId_label;

    @FXML
    private Label show_BookType;

    @FXML
    private Label show_author;

    @FXML
    private Label show_bookTitle;

    @FXML
    private Label show_date;

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
    private AnchorPane addUser_form;

    @FXML
    private TableView<adminBooksManagement> bookMng_tableView;
    @FXML
    private TableView<adminBorrowManagement> borrowMng_tableView;
    @FXML
    private TableView<adminUsersManagement> userMng_tableView;


    @FXML
    public void exit() {
        System.exit(0);
    }

    private double x = 0;
    private double y = 0;
    private Image image;
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

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
    void navButtonDesign(ActionEvent event) {
        if (event.getSource() == book_manage_btn) {
            bookManage_form.setVisible(true);
            borrowManager_form.setVisible(false);
            userManage_form.setVisible(false);
            addBook_form.setVisible(false);
            addUser_form.setVisible(false);

            book_manage_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            borrowMng_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            userMng_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            addBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            addUsers_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            halfNav_booksMng_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            halfNav_BorrowMng_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_UserMng_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_addBook_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_addUser_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            currentForm_label.setText("Books Management");
        } else if (event.getSource() == borrowMng_btn) {
            bookManage_form.setVisible(false);
            borrowManager_form.setVisible(true);
            userManage_form.setVisible(false);
            addBook_form.setVisible(false);
            addUser_form.setVisible(false);

            borrowMng_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            book_manage_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            userMng_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            addBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            addUsers_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            halfNav_BorrowMng_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            halfNav_booksMng_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_UserMng_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_addBook_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_addUser_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            currentForm_label.setText("Borrowed Management");
        } else if (event.getSource() == userMng_btn) {
            bookManage_form.setVisible(false);
            borrowManager_form.setVisible(false);
            userManage_form.setVisible(true);
            addBook_form.setVisible(false);
            addUser_form.setVisible(false);

            userMng_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            book_manage_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            borrowMng_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            addBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            addUsers_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            halfNav_UserMng_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            halfNav_booksMng_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_BorrowMng_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_addBook_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_addUser_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            currentForm_label.setText("Users Management");
        } else if (event.getSource() == addBooks_btn) {
            bookManage_form.setVisible(false);
            borrowManager_form.setVisible(false);
            userManage_form.setVisible(false);
            addBook_form.setVisible(true);
            addUser_form.setVisible(false);

            addBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            book_manage_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            borrowMng_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            userMng_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            addUsers_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            halfNav_addBook_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            halfNav_booksMng_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_BorrowMng_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_UserMng_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_addUser_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            currentForm_label.setText("Add Books");
        } else if (event.getSource() == addUsers_btn) {
            bookManage_form.setVisible(false);
            borrowManager_form.setVisible(false);
            userManage_form.setVisible(false);
            addBook_form.setVisible(false);
            addUser_form.setVisible(true);

            addUsers_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            book_manage_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            borrowMng_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            addBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            userMng_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            halfNav_addUser_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            halfNav_booksMng_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_BorrowMng_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_addBook_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_UserMng_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            currentForm_label.setText("Add Users");
        }

    }

    @FXML
    void sideNavButtonDesign(ActionEvent event) {

        if (event.getSource() == halfNav_booksMng_btn) {
            bookManage_form.setVisible(true);
            borrowManager_form.setVisible(false);
            userManage_form.setVisible(false);
            addBook_form.setVisible(false);
            addUser_form.setVisible(false);

            book_manage_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            borrowMng_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            userMng_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            addBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            addUsers_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            halfNav_booksMng_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            halfNav_BorrowMng_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_UserMng_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_addBook_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_addUser_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            currentForm_label.setText("Books Management");
        } else if (event.getSource() == halfNav_BorrowMng_btn) {
            bookManage_form.setVisible(false);
            borrowManager_form.setVisible(true);
            userManage_form.setVisible(false);
            addBook_form.setVisible(false);
            addUser_form.setVisible(false);

            borrowMng_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            book_manage_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            userMng_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            addBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            addUsers_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            halfNav_BorrowMng_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            halfNav_booksMng_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_UserMng_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_addBook_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_addUser_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            currentForm_label.setText("Borrowed Management");
        } else if (event.getSource() == halfNav_UserMng_btn) {
            bookManage_form.setVisible(false);
            borrowManager_form.setVisible(false);
            userManage_form.setVisible(true);
            addBook_form.setVisible(false);
            addUser_form.setVisible(false);

            userMng_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            book_manage_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            borrowMng_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            addBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            addUsers_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            halfNav_UserMng_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            halfNav_booksMng_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_BorrowMng_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_addBook_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_addUser_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            currentForm_label.setText("Users Management");
        } else if (event.getSource() == halfNav_addBook_btn) {
            bookManage_form.setVisible(false);
            borrowManager_form.setVisible(false);
            userManage_form.setVisible(false);
            addBook_form.setVisible(true);
            addUser_form.setVisible(false);

            addBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            book_manage_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            borrowMng_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            userMng_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            addUsers_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            halfNav_addBook_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            halfNav_booksMng_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_BorrowMng_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_UserMng_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_addUser_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            currentForm_label.setText("Add Books");
        } else if (event.getSource() == halfNav_addUser_btn) {
            bookManage_form.setVisible(false);
            borrowManager_form.setVisible(false);
            userManage_form.setVisible(false);
            addBook_form.setVisible(false);
            addUser_form.setVisible(true);

            addUsers_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            book_manage_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            borrowMng_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            addBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            userMng_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            halfNav_addUser_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            halfNav_booksMng_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_BorrowMng_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_addBook_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_UserMng_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            currentForm_label.setText("Add Users");
        }
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

    public ObservableList<adminBooksManagement> dataList() {

        ObservableList<adminBooksManagement> listBooks = FXCollections.observableArrayList();
        String sql = " Select * from book";

        connect = Database.connectDB();
        try {
            adminBooksManagement aBooks;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                aBooks = new adminBooksManagement(result.getString("bookTitle"),
                        result.getString("author"), result.getString("bookType"),
                        result.getString("image"), result.getDate("date"));
                listBooks.add(aBooks);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return listBooks;
    }

    private ObservableList<adminBooksManagement> listBook;

    public void showBook() {
        listBook = dataList();
        book_Title.setCellValueFactory(new PropertyValueFactory<>("title"));
        Author.setCellValueFactory(new PropertyValueFactory<>("author"));
        book_Type.setCellValueFactory(new PropertyValueFactory<>("bookType"));
        image_URL.setCellValueFactory(new PropertyValueFactory<>("image"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        bookMng_tableView.setItems(listBook);
    }

    public void selectionBooksShow() {

        adminBooksManagement bookData = bookMng_tableView.getSelectionModel().getSelectedItem();

        int num = bookMng_tableView.getSelectionModel().getFocusedIndex();

        if ((num - 1) < -1) {
            return;
        }

        show_bookTitle.setText(bookData.getTitle());
        show_author.setText(bookData.getAuthor());
        show_BookType.setText(bookData.getBookType());
        show_date.setText(bookData.getDate().toString());
        ;

        String uri = "file:" + bookData.getImage();

        image = new Image(uri, 150, 200, false, true);

        manage_book_imageView.setImage(image);
    }

    public ObservableList<adminBorrowManagement> borrowMngList() {
        ObservableList<adminBorrowManagement> listBorrow = FXCollections.observableArrayList();
        String sql = " Select * from take";
        connect = Database.connectDB();
        try {
            adminBorrowManagement bBooks;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                bBooks = new adminBorrowManagement(result.getString("studentNumber"),
                        result.getString("firstName"), result.getString("lastName"),
                        result.getString("gender"), result.getString("bookTitle"),
                        result.getString("author"), result.getString("bookType"),
                        result.getString("image"), result.getDate("date"),
                        result.getString("checkReturn"));
                listBorrow.add(bBooks);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return listBorrow;
    }

    private ObservableList<adminBorrowManagement> borrowedList;

    public void showBorrowBooks() {
        borrowedList = borrowMngList();
        borrow_Id.setCellValueFactory(new PropertyValueFactory<>("studentNumber"));
        borrow_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        borrow_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        borrow_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        borrow_bookTitle.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
        borrow_author.setCellValueFactory(new PropertyValueFactory<>("author"));
        borrow_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        borrow_status.setCellValueFactory(new PropertyValueFactory<>("checkReturn"));
        borrowMng_tableView.setItems(borrowedList);
    }

    public ObservableList<adminUsersManagement> userList() {
        ObservableList<adminUsersManagement> userL = FXCollections.observableArrayList();
        String sql = " Select * from student";
        connect = Database.connectDB();
        try {
            adminUsersManagement us;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                us = new adminUsersManagement(result.getString("studentNumber"),
                        result.getString("password"), result.getString("image"));
                userL.add(us);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userL;
    }

    public ObservableList<adminUsersManagement> listUser;

    public void showUser() {
        listUser = userList();
        user_studentID.setCellValueFactory(new PropertyValueFactory<>("studentNumber"));
        user_pass.setCellValueFactory(new PropertyValueFactory<>("password"));
        user_ava.setCellValueFactory(new PropertyValueFactory<>("image"));
        userMng_tableView.setItems(listUser);
    }

    public void selectUser() {
        adminUsersManagement bookData = userMng_tableView.getSelectionModel().getSelectedItem();

        int num = userMng_tableView.getSelectionModel().getFocusedIndex();

        if ((num - 1) < -1) {
            return;
        }
        String uri = "file:" + bookData.getImage();

        image = new Image(uri, 240, 140, false, true);
        user_image.setFill(new ImagePattern(image));
    }

    public void addUserBtn(ActionEvent e) {
        if (e.getSource() == userMng_addBtn) {
            addUser_form.setVisible(true);
            bookManage_form.setVisible(false);
            borrowManager_form.setVisible(false);
            userManage_form.setVisible(false);
            addBook_form.setVisible(false);


            addUsers_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            book_manage_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            borrowMng_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            addBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            userMng_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            halfNav_addUser_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            halfNav_booksMng_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_BorrowMng_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_addBook_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_UserMng_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            currentForm_label.setText("Add Users");
        }
    }

    public void removeUser(ActionEvent event) {
        // Lấy thông tin từ dòng được chọn
        adminUsersManagement userData = userMng_tableView.getSelectionModel().getSelectedItem();
        int num = userMng_tableView.getSelectionModel().getFocusedIndex();

        if (num < 0) { // Không có dòng nào được chọn
            return;
        }

        if (event.getSource() == userMng_revBtn) {
            String disableSafeUpdates = "SET SQL_SAFE_UPDATES = 0;";
            String deleteQuery = "DELETE FROM student WHERE studentNumber = ?";
            String enableSafeUpdates = "SET SQL_SAFE_UPDATES = 1;";

            // Lấy studentNumber từ đối tượng đã chọn
            String studentid = userData.getStudentNumber();
            connect = Database.connectDB();

            try (PreparedStatement disableStatement = connect.prepareStatement(disableSafeUpdates);
                 PreparedStatement deleteStatement = connect.prepareStatement(deleteQuery);
                 PreparedStatement enableStatement = connect.prepareStatement(enableSafeUpdates)) {

                // Tắt chế độ SQL_SAFE_UPDATES
                disableStatement.execute();

                // Thực hiện xóa
                deleteStatement.setString(1, studentid);
                int rowsAffected = deleteStatement.executeUpdate();

                // Bật lại SQL_SAFE_UPDATES
                enableStatement.execute();

                // Thông báo và làm mới bảng nếu xóa thành công
                if (rowsAffected > 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText(null);
                    alert.setContentText("Xóa Thành Công");
                    alert.showAndWait();

                    // Tải lại dữ liệu bảng
                    loadTableData();
                }
            } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi");
                alert.setHeaderText(null);
                alert.setContentText("Có lỗi xảy ra khi xóa!");
                alert.showAndWait();
            }
        }
    }

    // Phương thức tải lại dữ liệu
    private void loadTableData() {
        ObservableList<adminUsersManagement> dataList = FXCollections.observableArrayList();

        String query = "SELECT * FROM student";
        connect = Database.connectDB();

        try (PreparedStatement statement = connect.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                dataList.add(new adminUsersManagement(
                        resultSet.getString("studentNumber"),
                        resultSet.getString("password"),
                        resultSet.getString("image")
                ));
            }

            userMng_tableView.setItems(dataList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addBook() {
        String sql = "INSERT INTO `book` (`bookTitle`, `author`, `bookType`, `image`, `date`) VALUES(?, ?, ?, ?, ? ,?);";
        connect = Database.connectDB();
        try{
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, add_title.getText());
            prepare.setString(2, add_author.getText());
            prepare.setString(3, add_bookType.getText());
            prepare.setString(4, add_imageURL.getText());
            prepare.setDate(5, Date.valueOf(add_date.getText()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showBook();
        showBorrowBooks();
        showUser();
    }
}
