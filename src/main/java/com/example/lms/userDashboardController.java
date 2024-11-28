package com.example.lms;


////import com.google.gson.JsonArray;
////import com.google.gson.JsonObject;

//import com.google.gson.JsonArray;
//import com.google.gson.JsonObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
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
import java.io.File;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.SwingUtilities;

import java.util.ArrayList;
import java.util.ResourceBundle;


public class userDashboardController implements Initializable {

    @FXML
    private Button availableBooks_btn;

    @FXML
    private AnchorPane game_form;

    @FXML
    private AnchorPane availableBooks_form;

    @FXML
    private ImageView availableBooks_imageView;

    @FXML
    private TableView<userAvailableBooks> availableBooks_tableView;

    @FXML
    private Label availableBooks_title;

    @FXML
    private Circle circle_image;

    @FXML
    private TableColumn<userAvailableBooks, String> col_ab_author;

    @FXML
    private TableColumn<userAvailableBooks, String> col_ab_bookTitle;

    @FXML
    private TableColumn<userAvailableBooks, String> col_ab_bookType;

    @FXML
    private TableColumn<userAvailableBooks, String> col_ab_publishedDate;

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
    private TableColumn<userReturnBook, String> return_Author;

    @FXML
    private TableColumn<userReturnBook, String> return_BookType;

    @FXML
    private Button return_Button;

    @FXML
    private TableColumn<userReturnBook, String> return_DateIssue;

    @FXML
    private TableColumn<userReturnBook, String> return_BookTitle;

    @FXML
    private ImageView return_imageView;

    @FXML
    private TableView<userReturnBook> return_tableView;

    @FXML
    private TableColumn<userSaveBook, String> col_saveAuthor;

    @FXML
    private TableColumn<userSaveBook, String> col_saveDate;

    @FXML
    private TableColumn<String, String> col_saveGenre;

    @FXML
    private TableColumn<String, String> col_saveTitle;

    @FXML
    private TableView<userSaveBook> save_tableView;

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

    @FXML
    private Button game_Btn;

    @FXML
    private Button halfNav_gameBtn;

    @FXML
    private Button half_logout_btn;

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

            // Kiểm tra nếu người dùng không nhập tiêu đề sách
            if (take_bookTitle.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Thông Báo");
                alert.setHeaderText(null);
                alert.setContentText("Vui lòng điền đầy đủ thông tin!");
                alert.showAndWait();
            } else {
                // Tìm trong cơ sở dữ liệu trước
                while (result.next()) {
                    take_titleLabel.setText(result.getString("bookTitle"));
                    take_authorLabel.setText(result.getString("author"));
                    take_genreLabel.setText(result.getString("bookType"));
                    take_dateLabel.setText(result.getString("date"));

                    getData.path = result.getString("image");
                    String uri = getData.path;
                    image = new Image(uri, 150, 200, false, true);
                    take_imageView.setImage(image);
                    check = true;
                }

                // Nếu không tìm thấy trong cơ sở dữ liệu, dùng Google Books API
                if (!check) {
                    // Tạo một instance của GoogleBooksAPI
                    GoogleBooksAPI googleBooksAPI = new GoogleBooksAPI();
                    try {
                        JsonObject googleBookData = googleBooksAPI.searchBook(take_bookTitle.getText());
                        JsonArray items = googleBookData.getAsJsonArray("items");

                        // Nếu tìm thấy sách trên Google Books
                        if (items.size() > 0) {
                            JsonObject bookInfo = items.get(0).getAsJsonObject().getAsJsonObject("volumeInfo");

                            // Lấy các thông tin về sách
                            String title = bookInfo.get("title").getAsString();
                            String author = bookInfo.getAsJsonArray("authors").get(0).getAsString();
                            String genre = bookInfo.getAsJsonArray("categories").get(0).getAsString();
                            String publishedDate = bookInfo.get("publishedDate").getAsString();

                            // Hiển thị thông tin sách
                            take_titleLabel.setText(title);
                            take_authorLabel.setText(author);
                            take_genreLabel.setText(genre);
                            take_dateLabel.setText(publishedDate);

                            // Lấy ảnh bìa từ Google Books API
                            if (bookInfo.has("imageLinks")) {
                                String imageUrl = bookInfo.getAsJsonObject("imageLinks").get("thumbnail").getAsString();
                                image = new Image(imageUrl, 150, 200, false, true);
                                take_imageView.setImage(image);
                            }

                            check = true;  // Đánh dấu đã tìm thấy sách trên Google Books
                        } else {
                            // Nếu không tìm thấy trong Google Books
                            take_titleLabel.setText("Quyển sách này không tồn tại!");
                        }

                    } catch (Exception apiException) {
                        apiException.printStackTrace();
                    }
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

    public ObservableList<userReturnBook> returnBook() {
        ObservableList<userReturnBook> bookReturnData = FXCollections.observableArrayList();
        String check = "Not Return";
        String sql = "select * from take where checkReturn ='" + check + "'and studentNumber ='" +
                getData.studentId + "'";
        connect = Database.connectDB();
        Alert alert;
        try {
            userReturnBook rBooks;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                rBooks = new userReturnBook(result.getString("bookTitle"),
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

    private ObservableList<userReturnBook> retBook;

    public void showReturnBooks() {
        retBook = returnBook();
        return_BookTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        return_Author.setCellValueFactory(new PropertyValueFactory<>("author"));
        return_BookType.setCellValueFactory(new PropertyValueFactory<>("genre"));
        return_DateIssue.setCellValueFactory(new PropertyValueFactory<>("date"));
        return_tableView.setItems(retBook);
    }

    public void selectReturnBook() {
        userReturnBook rBook = return_tableView.getSelectionModel().getSelectedItem();
        int num = return_tableView.getSelectionModel().getFocusedIndex();
        if ((num - 1) < -1) {
            return;
        }
<<<<<<< HEAD
        String uri = rBook.getImage();
=======
        String uri =  rBook.getImage();
>>>>>>> 184cc4d709fd19ee0b44f95ff7a59c8995c9c095

        image = new Image(uri, 150, 200, false, true);
        return_imageView.setImage(image);

        getData.takeBookTitle = rBook.getTitle();
    }

    public ObservableList<userAvailableBooks> dataList() {

        ObservableList<userAvailableBooks> listBooks = FXCollections.observableArrayList();
        String sql = " Select * from book";

        connect = Database.connectDB();
        try {
            userAvailableBooks aBooks;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                aBooks = new userAvailableBooks(result.getString("bookTitle"),
                        result.getString("author"), result.getString("bookType"),
                        result.getString("image"), result.getDate("date"));
                listBooks.add(aBooks);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return listBooks;
    }

    public ObservableList<userSaveBook> savedBooksData() {

        ObservableList<userSaveBook> listSaveData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM save WHERE studentNumber = '" + getData.studentId + "'";

        connect = Database.connectDB();

        try {
            userSaveBook sBook;

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {

                sBook = new userSaveBook(result.getString("bookTitle"), result.getString("author"),
                        result.getString("bookType"), result.getString("image"), result.getDate("date"));

                listSaveData.add(sBook);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listSaveData;
    }

    private ObservableList<userSaveBook> sBookList;

    public void showSavedBooks() {

        sBookList = savedBooksData();

        col_saveTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        col_saveAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        col_saveGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        col_saveDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        save_tableView.setItems(sBookList);

    }

    public void selectSavedBooks() {

        userSaveBook sBook = save_tableView.getSelectionModel().getSelectedItem();
        int num = save_tableView.getSelectionModel().getFocusedIndex();

        if ((num - 1) < -1) {
            return;
        }

        String uri = sBook.getImage();

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
                alert.setContentText("Vui lòng chọn sách!");
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


    private ObservableList<userAvailableBooks> listBook;

    public void showAvailableBooks() {
        listBook = dataList();
        col_ab_bookTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        col_ab_author.setCellValueFactory(new PropertyValueFactory<>("author"));
        col_ab_bookType.setCellValueFactory(new PropertyValueFactory<>("genre"));
        col_ab_publishedDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        availableBooks_tableView.setItems(listBook);
    }

    public void selectionAvailableBooks() {

        userAvailableBooks bookData = availableBooks_tableView.getSelectionModel().getSelectedItem();

        int num = availableBooks_tableView.getSelectionModel().getFocusedIndex();

        if ((num - 1) < -1) {
            return;
        }

        availableBooks_title.setText(bookData.getTitle());

        String uri = bookData.getImage();

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
            game_form.setVisible(false);

            issueBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            availableBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            returnBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            savedBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            game_Btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            halfNav_takeBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            halfNav_availableBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_returnBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_saveBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_gameBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            currentForm_label.setText("Issue Books");
            take_bookTitle.setText(getData.takeBookTitle);
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
            game_form.setVisible(false);

            availableBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            issueBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            returnBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            savedBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            game_Btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            halfNav_availableBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            halfNav_saveBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_returnBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_takeBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_gameBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            currentForm_label.setText("Available Books");
        } else if (event.getSource() == halfNav_takeBtn) {
            issue_form.setVisible(true);
            availableBooks_form.setVisible(false);
            returnBook_form.setVisible(false);
            saveBook_form.setVisible(false);
            game_form.setVisible(false);

            issueBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            availableBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            returnBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            savedBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            game_Btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            halfNav_takeBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            halfNav_availableBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_returnBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_saveBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_gameBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            currentForm_label.setText("Issue Books");
        } else if (event.getSource() == halfNav_returnBtn) {
            issue_form.setVisible(false);
            availableBooks_form.setVisible(false);
            returnBook_form.setVisible(true);
            saveBook_form.setVisible(false);
            game_form.setVisible(false);

            returnBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            availableBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            issueBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            savedBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            game_Btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            halfNav_returnBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            halfNav_availableBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_saveBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_takeBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_gameBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            currentForm_label.setText("Return Books");
            showReturnBooks();
        } else if (event.getSource() == halfNav_saveBtn) {
            issue_form.setVisible(false);
            availableBooks_form.setVisible(false);
            returnBook_form.setVisible(false);
            saveBook_form.setVisible(true);
            game_form.setVisible(false);

            savedBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            availableBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            returnBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            issueBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            game_Btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            halfNav_saveBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            halfNav_availableBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_returnBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_takeBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_gameBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            currentForm_label.setText("Saved Books");

            showSavedBooks();
        } else if (event.getSource() == halfNav_gameBtn) {
            issue_form.setVisible(false);
            availableBooks_form.setVisible(false);
            returnBook_form.setVisible(false);
            saveBook_form.setVisible(false);
            game_form.setVisible(true);
            loadGame();

            game_Btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            availableBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            returnBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            issueBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            save_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            halfNav_gameBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            halfNav_availableBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_returnBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_takeBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_saveBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            currentForm_label.setText("Game");
        }

    }

    public void navButtonDesign(ActionEvent event) {
        if (event.getSource() == availableBooks_btn) {
            issue_form.setVisible(false);
            availableBooks_form.setVisible(true);
            returnBook_form.setVisible(false);
            saveBook_form.setVisible(false);
            game_form.setVisible(false);

            availableBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            issueBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            returnBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            savedBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            game_Btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            halfNav_availableBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            halfNav_saveBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_returnBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_takeBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_gameBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            currentForm_label.setText("Available Books");
        } else if (event.getSource() == issueBooks_btn) {
            issue_form.setVisible(true);
            availableBooks_form.setVisible(false);
            returnBook_form.setVisible(false);
            saveBook_form.setVisible(false);
            game_form.setVisible(false);

            issueBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            availableBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            returnBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            savedBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            game_Btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            halfNav_takeBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            halfNav_availableBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_returnBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_saveBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_gameBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            currentForm_label.setText("Issue Books");
        } else if (event.getSource() == returnBooks_btn) {
            issue_form.setVisible(false);
            availableBooks_form.setVisible(false);
            returnBook_form.setVisible(true);
            saveBook_form.setVisible(false);
            game_form.setVisible(false);

            returnBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            availableBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            issueBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            savedBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            game_Btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            halfNav_returnBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            halfNav_availableBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_saveBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_takeBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_gameBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            currentForm_label.setText("Return Books");
            showReturnBooks();
        } else if (event.getSource() == savedBooks_btn) {
            issue_form.setVisible(false);
            availableBooks_form.setVisible(false);
            returnBook_form.setVisible(false);
            saveBook_form.setVisible(true);
            game_form.setVisible(false);

            savedBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            availableBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            returnBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            issueBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            game_Btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            halfNav_saveBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            halfNav_availableBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_returnBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_takeBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_gameBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            currentForm_label.setText("Saved Books");

            showSavedBooks();
        } else if (event.getSource() == game_Btn) {
            issue_form.setVisible(false);
            availableBooks_form.setVisible(false);
            returnBook_form.setVisible(false);
            saveBook_form.setVisible(false);
            game_form.setVisible(true);
            loadGame();

            game_Btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            availableBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            returnBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            issueBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            savedBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            halfNav_gameBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            halfNav_availableBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_returnBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_takeBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_saveBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

            currentForm_label.setText("Game");
        }

    }

    private double x = 0;
    private double y = 0;

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
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-15.0f);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void music_off() {
        Platform.runLater(() -> {
            music_on_btn.setVisible(true);
            music_off_btn.setVisible(false);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-80.0f);
        });
    }

    public void music_on() {
        Platform.runLater(() -> {
            music_on_btn.setVisible(false);
            music_off_btn.setVisible(true);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-15.0f);
        });
    }

    public void exit() {
        System.exit(0);
    }

    public void minimize() {
        Stage stage = (Stage) minimize.getScene().getWindow();
        stage.setIconified(true);
    }

    public void loadGame() {
        SwingNode swingNode = new SwingNode();
        createSwingContent(swingNode);

        // Đặt kích thước cho SwingNode
        swingNode.setManaged(false);
        swingNode.setLayoutX(0);
        swingNode.setLayoutY(0);
        swingNode.resize(game_form.getWidth(), game_form.getHeight());

        game_form.widthProperty().addListener((obs, oldVal, newVal) -> swingNode.prefWidth(newVal.doubleValue()));
        game_form.heightProperty().addListener((obs, oldVal, newVal) -> swingNode.prefHeight(newVal.doubleValue()));

        game_form.getChildren().clear();
        game_form.getChildren().add(swingNode);

        // Ép lấy focus ngay khi JavaFX xử lý giao diện
        Platform.runLater(() -> swingNode.requestFocus());
    }

    private void createSwingContent(SwingNode swingNode) {
        SwingUtilities.invokeLater(() -> {
            userGame game = new userGame();

            // Ép nhận focus và vẽ lại
            game.requestFocusInWindow();
            game.repaint();

            swingNode.setContent(game);

            // Cập nhật focus trên JavaFX thread
            Platform.runLater(() -> swingNode.requestFocus());
        });
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