package BTL.AdminClasses;

import com.example.lms.availableBooks;
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

import java.io.IOException;
import java.util.Optional;
import java.util.regex.Pattern;

public class Admins extends User {
    // Các hằng số cho đường dẫn và thông báo thường dùng
    private static final String ADMIN_ACCESS_FXML = "/org/example/librarymanagementsystemgui/adminAccess.fxml";
    private static final String TITLE_INVALID_INPUT = "Đầu vào không hợp lệ";
    private static final String MESSAGE_INVALID_INPUT = "Đầu vào không hợp lệ, vui lòng thử lại!";

    // Khai báo các nút điều khiển FXML
    @FXML
    public Button ViewAllAvailableBooks, SearchForBookByTitle, SearchForBookByAuthorName,
            AddABook, RemoveABook, EditABook, SearchForAMember,
            AddAMember, RemoveAMember, EditAMember, ViewAllMembers,
            FineAMember, LogOut, Exit;

    // Biến tĩnh để truyền thông tin quản trị viên giữa các phương thức
    protected static Admins adminToPass;

    // Constructor
    public Admins(){}

    // Hàm khởi tạo với thông tin cơ bản
    public Admins(String name, String phone, String email){
        super(name, phone, email);
    }

    // Phương thức xử lý lỗi tập trung
    private void handleError(ActionEvent event, String title, String content) throws IOException {
        // Hiển thị thông báo lỗi và chuyển về trang quản trị
        alert(event, title, content);
        Parent root = FXMLLoader.load(SystemLaunch.class.getResource(ADMIN_ACCESS_FXML));
        setScene(event, root, "Admin access");
    }

    // Phương thức kiểm tra tính hợp lệ của đầu vào
    private boolean validateInput(String input, InputType type) {
        // Kiểm tra đầu vào rỗng
        if (input == null || input.trim().isEmpty()) {
            return false;
        }

        // Kiểm tra theo từng loại đầu vào
        switch (type) {
            case NUMBER:
                // Kiểm tra đầu vào là số
                return Pattern.matches("^\\d+$", input);
            case EMAIL:
                // Kiểm tra định dạng email
                return Pattern.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", input);
            case PHONE:
                // Kiểm tra số điện thoại (10 chữ số)
                return Pattern.matches("^\\d{10}$", input);
            default:
                return true;
        }
    }

    // Liệt kê các loại đầu vào để kiểm tra
    private enum InputType {
        TEXT, NUMBER, EMAIL, PHONE
    }

    // Phương thức dialog nhập liệu chung
    private Optional<String> showInputDialog(String title, String header, String content, InputType type) throws InputException {
        // Tạo dialog nhập liệu
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(title);
        dialog.setHeaderText(header);
        dialog.setContentText(content);

        // Lấy và kiểm tra đầu vào
        Optional<String> result = dialog.showAndWait();
        if (result.isEmpty() || !validateInput(result.get(), type)) {
            throw new InputException("Đầu vào không hợp lệ");
        }

        return result;
    }

    // Phương thức nhập tên thành viên
    public String inputMemberName(ActionEvent event) throws IOException {
        try {
            return showInputDialog(
                    "Nhập tên thành viên",
                    "Nhập tên của thành viên",
                    "Vui lòng nhập tên của thành viên",
                    InputType.TEXT
            ).get();
        } catch (InputException e) {
            handleError(event, TITLE_INVALID_INPUT, MESSAGE_INVALID_INPUT);
            return null;
        }
    }

    // Ví dụ về phương thức thêm sách với xử lý lỗi cải tiến
    public void AddABook(ActionEvent event) throws IOException {
        try {
            // Nhập chi tiết sách
            availableBooks bookToAdd = inputBookDetails(event);

            // Kiểm tra sách đã tồn tại chưa
            if(isBookInDB(bookToAdd.getTitle())==null){
                books.add(bookToAdd);
                alert(event, "Thêm sách", "Sách đã được thêm thành công!");
            } else {
                alert(event, "Sách đã tồn tại", "Sách đã có trong thư viện");
                handleError(event, "Lỗi sách", "Không thể thêm sách");
            }
        } catch (InputException e) {
            handleError(event, TITLE_INVALID_INPUT, MESSAGE_INVALID_INPUT);
        }
    }

    // Phương thức đăng xuất
    public void LogOut(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(SystemLaunch.class.getResource("/org/example/librarymanagementsystemgui/start.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        // Hiệu ứng mờ nền
        Pane glassPane = (Pane) root.lookup(".glass");
        GaussianBlur blur = new GaussianBlur(8);
        glassPane.setEffect(blur);

        // Đặt biểu tượng cho cửa sổ
        Image icon = new Image(getClass().getResourceAsStream("/org/example/librarymanagementsystemgui/book.png"));
        stage.getIcons().add(icon);

        stage.setTitle("Hệ Thống Quản Lý Thư Viện");
        Scene scene = new Scene(root,900,700);
        stage.setScene(scene);
        stage.show();
    }

    // Phương thức thoát ứng dụng
    public void Exit(ActionEvent event) {
        System.exit(0);
    }
}