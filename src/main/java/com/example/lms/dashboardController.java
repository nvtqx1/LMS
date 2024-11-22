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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class dashboardController implements Initializable {

    @FXML
    private Button availableBooks_btn;

    @FXML
    private AnchorPane availableBooks_form;

    @FXML
    private ImageView availableBooks_imageView;

    @FXML
    private TableView<availableBooks> availableBooks_tableView;

    @FXML
    private Label availableBooks_title;

    @FXML
    private Circle circle_image;

    @FXML
    private TableColumn<availableBooks, String> col_ab_author;

    @FXML
    private TableColumn<availableBooks, String> col_ab_bookTitle;

    @FXML
    private TableColumn<availableBooks, String> col_ab_bookType;

    @FXML
    private TableColumn<availableBooks, String> col_ab_publishedDate;

    @FXML
    private Button edit_btn;

    @FXML
    private Button issueBooks_btn;

    @FXML
    private Button logout_btn;

    @FXML
    private Button returnBooks_btn;

    @FXML
    private Button save_btn;

    @FXML
    private Button savedBooks_btn;

    @FXML
    private Label studentId_label;

    @FXML
    private Button take_btn;

    @FXML
    private Button close;

    @FXML
    private Button minimize;

    @FXML
    private Button arrow_btn;

    @FXML
    private Button bars_btn;

    @FXML
    private AnchorPane nav_form;

    @FXML
    private AnchorPane mainCenter_form;


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
    private Circle smallCircle_image;

    @FXML
    private AnchorPane issue_form;

    @FXML
    private AnchorPane saveBook_form;

    @FXML
    private AnchorPane returnBook_form;

    @FXML
    private Label currentForm_label;

    @FXML
    private ComboBox<?> take_Gender;

    @FXML
    private Label take_authorLabel;

    @FXML
    private TextField take_bookTitle;

    @FXML
    private Button take_clearBtn;

    @FXML
    private Label take_dateLabel;

    @FXML
    private TextField take_firstName;

    @FXML
    private Label take_genreLabel;

    @FXML
    private ImageView take_imageView;

    @FXML
    private Label take_issueDate;

    @FXML
    private TextField take_lastName;

    @FXML
    private Label take_studentId;

    @FXML
    private Button take_takeBtn;

    @FXML
    private Label take_titleLabel;


    private Image image;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    public String comboBox[] = {"Nam", "Nữ", "Khác"};

    public void gender() {
        List<String> combo = new ArrayList<>();

        for (String data : comboBox) {
            combo.add(data);
        }
        ObservableList list = FXCollections.observableList(combo);
        take_Gender.setItems(list);
    }

    public void takeBook() {
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        String sql = "insert into take VALUES(?,?,?,?,?,?,?,?)";
        connect = Database.connectDB();
        try {
            Alert alert;
            if (take_firstName.getText().isEmpty() ||
                    take_lastName.getText().isEmpty() ||
                    take_Gender.getSelectionModel().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Thông Báo");
                alert.setHeaderText(null);
                alert.setContentText("Vui lòng điền đầy đủ thông tin.");
                alert.showAndWait();
            } else if (take_titleLabel.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Thông Báo");
                alert.setHeaderText(null);
                alert.setContentText("Quyển sách này không tồn tại!");
                alert.showAndWait();
            } else {
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, take_studentId.getText());
                prepare.setString(2, take_firstName.getText());
                prepare.setString(3, take_lastName.getText());
                prepare.setString(4, (String) take_Gender.getSelectionModel().getSelectedItem());
                prepare.setString(5, take_titleLabel.getText());
                prepare.setString(6, getData.path);
                prepare.setDate(7, sqlDate);

                String check = "Not Return";

                prepare.setString(8, check);
                prepare.executeUpdate();
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Thông Báo");
                alert.setHeaderText(null);
                alert.setContentText("Thành Công!");
                alert.showAndWait();
                clearFindData();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void findBook(ActionEvent event) {
        clearFindData();
        String sql = "Select * from book where bookTitle = '" + take_bookTitle.getText() + "'";
        connect = Database.connectDB();
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            boolean check = false;
            Alert alert;
            if (take_bookTitle.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Thông Báo");
                alert.setHeaderText(null);
                alert.setContentText("Vui lòng điền đầy đủ thông tin.");
                alert.showAndWait();
            } /*else if (take_firstName.getText().isEmpty() ||
                    take_lastName.getText().isEmpty() ||
                    take_Gender.getSelectionModel().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Thông Báo");
                alert.setHeaderText(null);
                alert.setContentText("Vui lòng điền đầy đủ thông tin.");
                alert.showAndWait();
            } */ else {
                while (result.next()) {
                    take_titleLabel.setText(result.getString("bookTitle"));
                    take_authorLabel.setText(result.getString("author"));
                    take_genreLabel.setText(result.getString("bookType"));
                    take_dateLabel.setText(result.getString("date"));

                    getData.path = result.getString("image");

                    String uri = "file" + getData.path;
                    image = new javafx.scene.image.Image(uri, 150, 200, false, true);
                    take_imageView.setImage(image);
                    check = true;
                }
                if (!check) {
                    take_titleLabel.setText("Quyển sách này không tồn tại!");
                    take_authorLabel.setText(result.getString(""));
                    take_genreLabel.setText(result.getString(""));
                    take_dateLabel.setText(result.getString(""));
                    take_imageView.setImage(null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void studentIdLabel() {
        take_studentId.setText(getData.studentId);
    }

    public void clearTakeData() {
        take_bookTitle.setText("");
        take_authorLabel.setText("");
        take_genreLabel.setText("");
        take_dateLabel.setText("");
        take_imageView.setImage(null);
    }

    public void clearFindData() {
        take_titleLabel.setText("");
        take_authorLabel.setText("");
        take_genreLabel.setText("");
        take_dateLabel.setText("");
        take_imageView.setImage(null);
    }

    public void displayDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(new Date());
        take_issueDate.setText(date);
    }

    public ObservableList<availableBooks> dataList() {

        ObservableList<availableBooks> listBooks = FXCollections.observableArrayList();
        String sql = " Select * from book";

        connect = Database.connectDB();
        try {
            availableBooks aBooks;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                aBooks = new availableBooks(result.getString("bookTitle"),
                        result.getString("author"), result.getString("bookType"),
                        result.getString("image"), result.getDate("date"));
                listBooks.add(aBooks);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return listBooks;
    }


    private ObservableList<availableBooks> listBook;

    public void showAvailableBooks() {
        listBook = dataList();
        //col_ab_bookTitle.getCellValueFactory(new PropertyValueFactory(""))
        col_ab_bookTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        col_ab_author.setCellValueFactory(new PropertyValueFactory<>("author"));
        col_ab_bookType.setCellValueFactory(new PropertyValueFactory<>("genre"));
        col_ab_publishedDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        availableBooks_tableView.setItems(listBook);
    }

    public void selectionAvailableBooks() {
        availableBooks bookData = availableBooks_tableView.getSelectionModel().getSelectedItem();
        int num = availableBooks_tableView.getSelectionModel().getFocusedIndex();
        if ((num - 1) < -1) {
            return;
        }
        availableBooks_title.setText(bookData.getTitle());
        String uri = "file" + bookData.getImage();
        image = new javafx.scene.image.Image(uri, 170, 220, false, true);
        availableBooks_imageView.setImage(image);
    }

    public void abTakeButton(ActionEvent event) {
        if (event.getSource() == take_btn) {
            issue_form.setVisible(true);
            availableBooks_form.setVisible(false);
            returnBook_form.setVisible(false);
            saveBook_form.setVisible(false);

            issueBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            availableBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            returnBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            savedBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            halfNav_takeBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            halfNav_availableBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_returnBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_saveBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            currentForm_label.setText("Issue Books");
        }

    }

    public void studentId() {
        studentId_label.setText(getData.studentId);
    }

    public void sideNavButtonDesign(ActionEvent event) {
        if (event.getSource() == halfNav_availableBtn) {
            issue_form.setVisible(false);
            availableBooks_form.setVisible(true);
            returnBook_form.setVisible(false);
            saveBook_form.setVisible(false);

            availableBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            issueBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            returnBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            savedBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            halfNav_availableBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            halfNav_saveBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_returnBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_takeBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            currentForm_label.setText("Available Books");
        } else if (event.getSource() == halfNav_takeBtn) {
            issue_form.setVisible(true);
            availableBooks_form.setVisible(false);
            returnBook_form.setVisible(false);
            saveBook_form.setVisible(false);

            issueBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            availableBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            returnBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            savedBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            halfNav_takeBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            halfNav_availableBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_returnBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_saveBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            currentForm_label.setText("Issue Books");
        } else if (event.getSource() == halfNav_returnBtn) {
            issue_form.setVisible(false);
            availableBooks_form.setVisible(false);
            returnBook_form.setVisible(true);
            saveBook_form.setVisible(false);

            returnBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            availableBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            issueBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            savedBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            halfNav_returnBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            halfNav_availableBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_saveBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_takeBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            currentForm_label.setText("Return Books");
        } else if (event.getSource() == halfNav_saveBtn) {
            issue_form.setVisible(false);
            availableBooks_form.setVisible(false);
            returnBook_form.setVisible(false);
            saveBook_form.setVisible(true);

            savedBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            availableBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            returnBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            issueBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            halfNav_saveBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            halfNav_availableBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_returnBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_takeBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            currentForm_label.setText("Saved Books");
        }
    }

    public void navButtonDesign(ActionEvent event) {
        if (event.getSource() == availableBooks_btn) {
            issue_form.setVisible(false);
            availableBooks_form.setVisible(true);
            returnBook_form.setVisible(false);
            saveBook_form.setVisible(false);

            availableBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            issueBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            returnBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            savedBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            halfNav_availableBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            halfNav_saveBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_returnBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_takeBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            currentForm_label.setText("Available Books");
        } else if (event.getSource() == issueBooks_btn) {
            issue_form.setVisible(true);
            availableBooks_form.setVisible(false);
            returnBook_form.setVisible(false);
            saveBook_form.setVisible(false);

            issueBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            availableBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            returnBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            savedBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            halfNav_takeBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            halfNav_availableBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_returnBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_saveBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            currentForm_label.setText("Issue Books");
        } else if (event.getSource() == returnBooks_btn) {
            issue_form.setVisible(false);
            availableBooks_form.setVisible(false);
            returnBook_form.setVisible(true);
            saveBook_form.setVisible(false);

            returnBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            availableBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            issueBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            savedBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            halfNav_returnBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            halfNav_availableBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_saveBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_takeBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            currentForm_label.setText("Return Books");
        } else if (event.getSource() == savedBooks_btn) {
            issue_form.setVisible(false);
            availableBooks_form.setVisible(false);
            returnBook_form.setVisible(false);
            saveBook_form.setVisible(true);

            savedBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            availableBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            returnBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            issueBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            halfNav_saveBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            halfNav_availableBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_returnBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_takeBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            currentForm_label.setText("Saved Books");
        }
    }

    private double x = 0;
    private double y = 0;

    public void logout(javafx.event.ActionEvent event) {
        try {
            if (event.getSource() == logout_btn) {
                Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

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


    public void exit() {
        System.exit(0);
    }

    public void minimize() {
        Stage stage = (Stage) minimize.getScene().getWindow();
        stage.setIconified(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showAvailableBooks();
        studentId();
        studentIdLabel();
        displayDate();
        gender();

    }
}
