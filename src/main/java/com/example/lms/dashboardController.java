package com.example.lms;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

import javax.sound.sampled.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.List;
import javafx.fxml.Initializable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;
import static sun.swing.SwingUtilities2.getFontMetrics;

public class dashboardController implements Initializable {

    @FXML
    private Button availableBooks_btn;

    @FXML
    private AnchorPane game_form;

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

    @FXML
    private TableColumn<returnBook, String> return_Author;

    @FXML
    private TableColumn<returnBook, String> return_BookType;

    @FXML
    private Button return_Button;

    @FXML
    private TableColumn<returnBook, String> return_DateIssue;

    @FXML
    private TableColumn<returnBook, String> return_BookTitle;

    @FXML
    private ImageView return_imageView;

    @FXML
    private TableView<returnBook> return_tableView;

    @FXML
    private TableColumn<saveBook, String> col_saveAuthor;

    @FXML
    private TableColumn<saveBook, String> col_saveDate;

    @FXML
    private TableColumn<String, String> col_saveGenre;

    @FXML
    private TableColumn<String, String> col_saveTitle;

    @FXML
    private TableView<saveBook> save_tableView;

    @FXML
    private Button unsaveBtn;

    @FXML
    private ImageView save_imageView;

    @FXML
    private AnchorPane saveBook_form;

    @FXML
    private Button music_off_btn;

    @FXML
    private Button music_on_btn;


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
        String sql = "insert into take VALUES(?,?,?,?,?,?,?,?,?,?)";
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
                prepare.setString(6, take_authorLabel.getText());
                prepare.setString(7, take_genreLabel.getText());
                prepare.setString(8, getData.path);
                prepare.setDate(9, sqlDate);

                String check = "Not Return";

                prepare.setString(10, check);
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
                alert.setContentText("Vui lòng điền đầy đủ thông tin!");
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

                    String uri = "file:" + getData.path;

                    image = new Image(uri, 150, 200, false, true);
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

    public ObservableList<returnBook> returnBook() {
        ObservableList<returnBook> bookReturnData = FXCollections.observableArrayList();
        String check = "Not Return";
        String sql = "select * from take where checkReturn ='" + check + "'and studentNumber ='" +
                getData.studentId + "'";
        connect = Database.connectDB();
        Alert alert;
        try {
            returnBook rBooks;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                rBooks = new returnBook(result.getString("bookTitle"),
                        result.getString("author"), result.getString("bookType"),
                        result.getString("image"), result.getDate("date"));
                bookReturnData.add(rBooks);

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookReturnData;
    }

    public void returnBooks() {
        String sql = "UPDATE take set checkReturn='Returned' Where bookTitle ='" + getData.takeBookTitle + "'";
        connect = Database.connectDB();
        Alert alert;
        try {
            if (return_imageView.getImage() == null) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Thông Báo");
                alert.setHeaderText(null);
                alert.setContentText("Vui lòng thao tác lại!");
                alert.showAndWait();
            } else {
                statement = connect.createStatement();
                statement.executeUpdate(sql);
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông Báo");
                alert.setHeaderText(null);
                alert.setContentText("Thành Công");
                alert.showAndWait();
                showReturnBooks();
                return_imageView.setImage(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private ObservableList<returnBook> retBook;

    public void showReturnBooks() {
        retBook = returnBook();
        return_BookTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        return_Author.setCellValueFactory(new PropertyValueFactory<>("author"));
        return_BookType.setCellValueFactory(new PropertyValueFactory<>("genre"));
        return_DateIssue.setCellValueFactory(new PropertyValueFactory<>("date"));
        return_tableView.setItems(retBook);
    }

    public void selectReturnBook() {
        returnBook rBook = return_tableView.getSelectionModel().getSelectedItem();
        int num = return_tableView.getSelectionModel().getFocusedIndex();
        if ((num - 1) < -1) {
            return;
        }
        String uri = "file:" + rBook.getImage();

        image = new Image(uri, 150, 200, false, true);
        return_imageView.setImage(image);

        getData.takeBookTitle = rBook.getTitle();
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

    public ObservableList<saveBook> savedBooksData() {

        ObservableList<saveBook> listSaveData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM save WHERE studentNumber = '" + getData.studentId + "'";

        connect = Database.connectDB();

        try {
            saveBook sBook;

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {

                sBook = new saveBook(result.getString("bookTitle"), result.getString("author"),
                        result.getString("bookType"), result.getString("image"), result.getDate("date"));

                listSaveData.add(sBook);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listSaveData;
    }

    private ObservableList<saveBook> sBookList;

    public void showSavedBooks() {

        sBookList = savedBooksData();

        col_saveTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        col_saveAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        col_saveGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        col_saveDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        save_tableView.setItems(sBookList);

    }

    public void selectSavedBooks() {

        saveBook sBook = save_tableView.getSelectionModel().getSelectedItem();
        int num = save_tableView.getSelectionModel().getFocusedIndex();

        if ((num - 1) < -1) {
            return;
        }

        String uri = "file:" + sBook.getImage();

        image = new Image(uri, 150, 200, false, true);
        save_imageView.setImage(image);

        getData.savedImage = sBook.getImage();
        getData.savedTitle = sBook.getTitle();

    }

    public void saveBooks() {

        String sql = "INSERT INTO save VALUES (?,?,?,?,?,?)";

        connect = Database.connectDB();

        try {

            Alert alert;

            if (availableBooks_title.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Admin Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the book");
                alert.showAndWait();
            } else {
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, getData.studentId);
                prepare.setString(2, getData.savedTitle);
                prepare.setString(3, getData.savedAuthor);
                prepare.setString(4, getData.savedGenre);
                prepare.setString(5, getData.savedImage);
                prepare.setDate(6, getData.savedDate);
                prepare.executeUpdate();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Admin Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Saved.");
                alert.showAndWait();

                showSavedBooks();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void unsaveBooks() {

        String sql = "DELETE FROM save WHERE bookTitle = '" + getData.savedTitle + "'"
                + " and studentNumber = '" + getData.studentId + "'";

        connect = Database.connectDB();

        try {

            Alert alert;

            if (save_imageView.getImage() == null) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Thông Báo");
                alert.setHeaderText(null);
                alert.setContentText("Vui lòng điền đầy đủ thông tin!");
                alert.showAndWait();

            } else {

                statement = connect.createStatement();
                statement.executeUpdate(sql);

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông Báo");
                alert.setHeaderText(null);
                alert.setContentText("Bỏ Lưu Thành Công.");
                alert.showAndWait();

                showSavedBooks();

                save_imageView.setImage(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private ObservableList<availableBooks> listBook;

    public void showAvailableBooks() {
        listBook = dataList();
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

        String uri = "file:" + bookData.getImage();

        image = new Image(uri, 150, 200, false, true);

        availableBooks_imageView.setImage(image);

        getData.takeBookTitle = bookData.getTitle();

        getData.savedTitle = bookData.getTitle();
        getData.savedAuthor = bookData.getAuthor();
        getData.savedGenre = bookData.getGenre();
        getData.savedImage = bookData.getImage();
        getData.savedDate = bookData.getDate();

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



    public void showProfile() {
        String uri = "file:" + getData.path;

        image = new Image(uri, 180, 114, false, true);
        circle_image.setFill(new ImagePattern(image));
        smallCircle_image.setFill(new ImagePattern(image));
    }

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
            showReturnBooks();
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

            showSavedBooks();
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
            showReturnBooks();
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

            showSavedBooks();
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

    Clip clip;
    public void audio() {
        try {
            File musicPath = new File("E:\\LMS\\src\\main\\resources\\com\\example\\lms\\image\\music.wav");
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void music_off() {
        Platform.runLater(() -> {
            // Code để tắt nhạc và cập nhật giao diện
            music_on_btn.setVisible(true);
            music_off_btn.setVisible(false);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-80.0f);
        });
    }

    public void music_on() {
        Platform.runLater(() -> {
            // Code để tắt nhạc và cập nhật giao diện
            music_on_btn.setVisible(false);
            music_off_btn.setVisible(true);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(0);
        });
    }

    private static final Color BG_COLOR = new Color(0xbbada0);
    private static final String FONT_NAME = "Arial";
    private static final int TILE_SIZE = 64;
    private static final int TILES_MARGIN = 16;

    private Game.Tile[] myTiles;
    boolean myWin = false;
    boolean myLose = false;
    int myScore = 0;

    public void gameDemo() {
            setFocusable(true);
            addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        resetGame();
                    }
                    if (!canMove()) {
                        myLose = true;
                    }

                    if (!myWin && !myLose) {
                        switch (e.getKeyCode()) {
                            case KeyEvent.VK_LEFT:
                                left();
                                break;
                            case KeyEvent.VK_RIGHT:
                                right();
                                break;
                            case KeyEvent.VK_DOWN:
                                down();
                                break;
                            case KeyEvent.VK_UP:
                                up();
                                break;
                        }
                    }

                    if (!myWin && !canMove()) {
                        myLose = true;
                    }

                    repaint();
                }
            });
            resetGame();
        }

        public void resetGame () {
            myScore = 0;
            myWin = false;
            myLose = false;
            myTiles = new Game.Tile[4 * 4];
            for (int i = 0; i < myTiles.length; i++) {
                myTiles[i] = new Game.Tile();
            }
            addTile();
            addTile();
        }


        public void left () {
            boolean needAddTile = false;
            for (int i = 0; i < 4; i++) {
                Game.Tile[] line = getLine(i);
                Game.Tile[] merged = mergeLine(moveLine(line));
                setLine(i, merged);
                if (!needAddTile && !compare(line, merged)) {
                    needAddTile = true;
                }
            }

            if (needAddTile) {
                addTile();
            }
        }

        public void right () {
            myTiles = rotate(180);
            left();
            myTiles = rotate(180);
        }

        public void up () {
            myTiles = rotate(270);
            left();
            myTiles = rotate(90);
        }

        public void down () {
            myTiles = rotate(90);
            left();
            myTiles = rotate(270);
        }

        private Game.Tile tileAt ( int x, int y){
            return myTiles[x + y * 4];
        }

        private void addTile () {
            List<Game.Tile> list = availableSpace();
            if (!availableSpace().isEmpty()) {
                int index = (int) (Math.random() * list.size()) % list.size();
                Game.Tile emptyTime = list.get(index);
                emptyTime.value = Math.random() < 0.9 ? 2 : 4;
            }
        }

        private List<Game.Tile> availableSpace () {
            final List<Game.Tile> list = new ArrayList<Game.Tile>(16);
            for (Game.Tile t : myTiles) {
                if (t.isEmpty()) {
                    list.add(t);
                }
            }
            return list;
        }

        private boolean isFull () {
            return availableSpace().size() == 0;
        }

        boolean canMove () {
            if (!isFull()) {
                return true;
            }
            for (int x = 0; x < 4; x++) {
                for (int y = 0; y < 4; y++) {
                    Game.Tile t = tileAt(x, y);
                    if ((x < 3 && t.value == tileAt(x + 1, y).value)
                            || ((y < 3) && t.value == tileAt(x, y + 1).value)) {
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean compare (Game.Tile[]line1, Game.Tile[]line2){
            if (line1 == line2) {
                return true;
            } else if (line1.length != line2.length) {
                return false;
            }

            for (int i = 0; i < line1.length; i++) {
                if (line1[i].value != line2[i].value) {
                    return false;
                }
            }
            return true;
        }

        private Game.Tile[] rotate ( int angle){
            Game.Tile[] newTiles = new Game.Tile[4 * 4];
            int offsetX = 3, offsetY = 3;
            if (angle == 90) {
                offsetY = 0;
            } else if (angle == 270) {
                offsetX = 0;
            }

            double rad = Math.toRadians(angle);
            int cos = (int) Math.cos(rad);
            int sin = (int) Math.sin(rad);
            for (int x = 0; x < 4; x++) {
                for (int y = 0; y < 4; y++) {
                    int newX = (x * cos) - (y * sin) + offsetX;
                    int newY = (x * sin) + (y * cos) + offsetY;
                    newTiles[(newX) + (newY) * 4] = tileAt(x, y);
                }
            }
            return newTiles;
        }

        private Game.Tile[] moveLine (Game.Tile[]oldLine){
            LinkedList<Game.Tile> l = new LinkedList<Game.Tile>();
            for (int i = 0; i < 4; i++) {
                if (!oldLine[i].isEmpty())
                    l.addLast(oldLine[i]);
            }
            if (l.size() == 0) {
                return oldLine;
            } else {
                Game.Tile[] newLine = new Game.Tile[4];
                ensureSize(l, 4);
                for (int i = 0; i < 4; i++) {
                    newLine[i] = l.removeFirst();
                }
                return newLine;
            }
        }

        private Game.Tile[] mergeLine (Game.Tile[]oldLine){
            LinkedList<Game.Tile> list = new LinkedList<Game.Tile>();
            for (int i = 0; i < 4 && !oldLine[i].isEmpty(); i++) {
                int num = oldLine[i].value;
                if (i < 3 && oldLine[i].value == oldLine[i + 1].value) {
                    num *= 2;
                    myScore += num;
                    int ourTarget = 2048;
                    if (num == ourTarget) {
                        myWin = true;
                    }
                    i++;
                }
                list.add(new Game.Tile(num));
            }
            if (list.size() == 0) {
                return oldLine;
            } else {
                ensureSize(list, 4);
                return list.toArray(new Game.Tile[4]);
            }
        }

        private static void ensureSize (java.util.List < Game.Tile > l,int s){
            while (l.size() != s) {
                l.add(new Game.Tile());
            }
        }

        private Game.Tile[] getLine ( int index){
            Game.Tile[] result = new Game.Tile[4];
            for (int i = 0; i < 4; i++) {
                result[i] = tileAt(i, index);
            }
            return result;
        }

        private void setLine ( int index, Tile[] re){
            System.arraycopy(re, 0, myTiles, index * 4, 4);
        }

        public void paint (Graphics g){
            super.paint(g);
            g.setColor(BG_COLOR);
            g.fillRect(0, 0, this.getSize().width, this.getSize().height);
            for (int y = 0; y < 4; y++) {
                for (int x = 0; x < 4; x++) {
                    drawTile(g, myTiles[x + y * 4], x, y);
                }
            }
        }

        private void drawTile (Graphics g2, Game.Tile tile,int x, int y){
            Graphics2D g = ((Graphics2D) g2);
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
            int value = tile.value;
            int xOffset = offsetCoors(x);
            int yOffset = offsetCoors(y);
            g.setColor(tile.getBackground());
            g.fillRoundRect(xOffset, yOffset, TILE_SIZE, TILE_SIZE, 14, 14);
            g.setColor(tile.getForeground());
            final int size = value < 100 ? 36 : value < 1000 ? 32 : 24;
            final Font font = new Font(FONT_NAME, Font.BOLD, size);
            g.setFont(font);

            String s = String.valueOf(value);
            final FontMetrics fm = getFontMetrics(font);

            final int w = fm.stringWidth(s);
            final int h = -(int) fm.getLineMetrics(s, g).getBaselineOffsets()[2];

            if (value != 0)
                g.drawString(s, xOffset + (TILE_SIZE - w) / 2, yOffset + TILE_SIZE - (TILE_SIZE - h) / 2 - 2);

            if (myWin || myLose) {
                g.setColor(new Color(255, 255, 255, 30));
                g.fillRect(0, 0, getWidth(), getHeight());
                g.setColor(new Color(78, 139, 202));
                g.setFont(new Font(FONT_NAME, Font.BOLD, 48));
                if (myWin) {
                    g.drawString("Chiến thắng!", 68, 150);
                }
                if (myLose) {
                    g.drawString("Game over!", 50, 130);
                    g.drawString("Bạn thua!", 64, 200);
                }
                if (myWin || myLose) {
                    g.setFont(new Font(FONT_NAME, Font.PLAIN, 16));
                    g.setColor(new Color(128, 128, 128, 128));
                    g.drawString("Nhấn ESC để chơi lại", 80, getHeight() - 40);
                }
            }
            g.setFont(new Font(FONT_NAME, Font.PLAIN, 18));
            g.drawString("Điểm: " + myScore, 200, 365);

        }

        private static int offsetCoors ( int arg){
            return arg * (TILES_MARGIN + TILE_SIZE) + TILES_MARGIN;
        }


        static class Tile {
            int value;

            public Tile() {
                this(0);
            }

            public Tile(int num) {
                value = num;
            }

            public boolean isEmpty() {
                return value == 0;
            }

            public Color getForeground() {
                return value < 16 ? new Color(0x776e65) : new Color(0xf9f6f2);
            }

            public Color getBackground() {
                switch (value) {
                    case 2:
                        return new Color(0xeee4da);
                    case 4:
                        return new Color(0xede0c8);
                    case 8:
                        return new Color(0xf2b179);
                    case 16:
                        return new Color(0xf59563);
                    case 32:
                        return new Color(0xf67c5f);
                    case 64:
                        return new Color(0xf65e3b);
                    case 128:
                        return new Color(0xedcf72);
                    case 256:
                        return new Color(0xedcc61);
                    case 512:
                        return new Color(0xedc850);
                    case 1024:
                        return new Color(0xedc53f);
                    case 2048:
                        return new Color(0xedc22e);
                }
                return new Color(0xcdc1b4);
            }
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
        audio();
        showProfile();
        showAvailableBooks();
        studentId();
        studentIdLabel();
        displayDate();
        gender();
        showReturnBooks();
        showSavedBooks();

    }
}
