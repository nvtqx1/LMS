package BTL.MemberClasses;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import BTL.DatabaseClasses.Books;
import BTL.DatabaseClasses.BorrowedBook;
import BTL.DatabaseClasses.User;
import BTL.Exceptions.InputException;
import BTL.LaunchClasses.SystemLaunch;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class Members extends User {
    // Các nút điều khiển giao diện người dùng
    @FXML
    public Button ViewAllAvailableBooks, SearchForBookByTitle, SearchForBookByAuthorName,
            PlaceAnOrder, BorrowABook, ReturnABook, CheckForBorrowStatus,
            ViewYourBorrowHistory, CalculateYourFine, PayYourFine,
            ProceedInDays, LogOut, Exit;

    // Thuộc tính riêng của thành viên thư viện
    private int memberId;
    private ArrayList<BorrowedBook> borrowedBooks;
    private ArrayList<BorrowedBook> borrowHistory;
    private double fineAmount;

    // Thuộc tính tĩnh để truyền thông tin thành viên giữa các phương thức
    protected static Members memberToPass;

    // Constructor mặc định
    public Members(){
        this.setMemberId(memberCounter);
        this.setFineAmount(0);
        borrowedBooks = new ArrayList<>();
        borrowHistory = new ArrayList<>();
    }

    // Constructor với thông tin chi tiết
    public Members(String name, String phoneNumber, String email) {
        super(name, phoneNumber, email);
        incrementID();
        this.setMemberId(memberCounter);
        this.setFineAmount(0);
        borrowedBooks = new ArrayList<>();
        borrowHistory = new ArrayList<>();
    }

    // Các getter và setter

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public ArrayList<BorrowedBook> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(ArrayList<BorrowedBook> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public double getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(double fineAmount) {
        this.fineAmount = fineAmount;
    }

    // Phương thức nhận và truyền thông tin thành viên
    public void receiveMember(ActionEvent event, Members member){
        memberToPass = member;
    }

    // Phương thức điều hướng các chức năng dựa trên sự kiện nút được nhấn
    public void passMemberToMethods(ActionEvent event) throws IOException {
        System.out.println(memberToPass);

        // Sử dụng switch case để cải thiện đọc code
        switch (event.getSource().toString()) {
            case "ViewAllAvailableBooks": memberToPass.ViewAllAvailableBooks(event); break;
            case "SearchForBookByTitle": memberToPass.SearchForBookByTitle(event); break;
            case "SearchForBookByAuthorName": memberToPass.SearchForBookByAuthorName(event); break;
            case "PlaceAnOrder": memberToPass.PlaceAnOrder(event); break;
            case "BorrowABook": memberToPass.BorrowABook(event); break;
            case "ReturnABook": memberToPass.ReturnABook(event); break;
            case "CheckForBorrowStatus": memberToPass.CheckForBorrowStatus(event); break;
            case "ViewYourBorrowHistory": memberToPass.ViewYourBorrowHistory(event); break;
            case "CalculateYourFine": memberToPass.CalculateYourFine(event); break;
            case "PayYourFine": memberToPass.PayYourFine(event); break;
            case "ProceedInDays": memberToPass.ProceedInDays(event); break;
        }
    }

    // Phương thức đặt mua sách
    public void PlaceAnOrder(ActionEvent event) throws IOException {
        String bookName = inputBookName(event);
        Books book = isBookInDB(bookName);

        if(book == null) {
            // Thông báo khi sách không tồn tại
            alert(event, "Sách không tìm thấy", "Sách không tồn tại trong cơ sở dữ liệu");
            Parent root = FXMLLoader.load(SystemLaunch.class.getResource("/BTL/memberAccess.fxml"));
            setScene(event, root, "Truy cập thành viên");
        } else {
            if(book.isAvailableToBuy()) {
                // Đặt mua sách thành công
                alert(event, "Đặt mua thành công",
                        "Đã đặt mua sách: \"" + book.getTitle() + "\" với giá: " + book.getPriceToBuy() + "$");
                book.setNumAvailableToBuy(book.getNumAvailableToBuy() - 1);
            } else {
                // Sách hết hàng
                alert(event, "Hết hàng", "Sách hiện tại không còn trong kho để mua");
            }
        }
    }

    // Phương thức mượn sách
    public void BorrowABook(ActionEvent event) throws IOException {
        String bookName = inputBookName(event);
        Books book = isBookInDB(bookName);

        if(book == null) {
            alert(event, "Sách không tìm thấy", "Sách không tồn tại trong cơ sở dữ liệu");
            Parent root = FXMLLoader.load(SystemLaunch.class.getResource("/BTL/memberAccess.fxml"));
            setScene(event, root, "Truy cập thành viên");
        } else {
            if(book.isAvailableToBorrow()) {
                BorrowedBook borrowedBook = new BorrowedBook(book);
                book.setNumAvailableToBorrow(book.getNumAvailableToBorrow() - 1);
                borrowedBooks.add(borrowedBook);
                borrowHistory.add(borrowedBook);
                borrowedBook.setBorrowDate(LocalDate.now());
                borrowedBook.setDueDate(borrowedBook.getBorrowDate().plusDays(7));

                alert(event, "Mượn sách thành công",
                        "\nĐã mượn sách: \"" + book.getTitle() + "\" với giá: " + book.getPriceToBorrow() + "$ vào ngày: " + borrowedBook.getBorrowDate() +
                                "\nBạn có 7 ngày để trả sách, nếu trả muộn sẽ bị phạt " + borrowedBook.getFineAmount() + "$");
            } else {
                alert(event, "Hết sách", "Sách hiện tại không còn để mượn");
            }
        }
    }

    // Các phương thức khác như ReturnABook, CheckForBorrowStatus, v.v. được giữ nguyên logic
    // ... (các phương thức còn lại được giữ nguyên như ban đầu)

    // Phương thức đăng xuất
    public void LogOut(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(SystemLaunch.class.getResource("/BTL/start.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        // Thêm hiệu ứng mờ nền
        Pane glassPane = (Pane) root.lookup(".glass");
        GaussianBlur blur = new GaussianBlur(8);
        glassPane.setEffect(blur);

        // Đặt biểu tượng cho cửa sổ
        Image icon = new Image(getClass().getResourceAsStream("/BTL/book.png"));
        stage.getIcons().add(icon);

        stage.setTitle("Hệ Thống Quản Lý Thư Viện");
        Scene scene = new Scene(root, 900, 700);
        stage.setScene(scene);
        stage.show();
    }

    // Phương thức thoát ứng dụng
    public void Exit(ActionEvent event) {
        System.exit(0);
    }
}