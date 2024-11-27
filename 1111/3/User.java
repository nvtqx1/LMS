package BTL.DatabaseClasses;

import src.Exceptions.InputException;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;

import java.io.IOException;
import java.util.Optional;

public class User extends SystemDataBase {
    // Các thuộc tính cá nhân của người dùng
    private String name;           // Tên người dùng
    private String phoneNumber;    // Số điện thoại
    private String email;           // Địa chỉ email

    // Hàm khởi tạo không tham số
    public User() {
    }

    // Hàm khởi tạo với đầy đủ thông tin người dùng
    public User(String name, String phoneNumber, String email) {
        this.setName(name);
        this.setPhoneNumber(phoneNumber);
        this.setEmail(email);
    }

    // Các phương thức getter để truy xuất thông tin
    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    // Các phương thức setter với kiểm tra dữ liệu không rỗng
    public void setName(String name) {
        if (!name.isEmpty()) {
            this.name = name;
        }
    }

    public void setPhoneNumber(String phoneNumber) {
        if (!phoneNumber.isEmpty()) {
            this.phoneNumber = phoneNumber;
        }
    }

    public void setEmail(String email) {
        if (!email.isEmpty()) {
            this.email = email;
        }
    }

    // Xem danh sách tất cả các sách có sẵn
    public void ViewAllAvailableBooks(ActionEvent event) {
        // Kiểm tra nếu danh sách sách trống
        if (Books.isEmpty()) {
            String title = "Không có sách";
            String content = "Không có sách nào có sẵn!";
            alert(event, title, content);
        } else {
            // Hiển thị thông tin chi tiết từng cuốn sách
            int counter = 1;
            for (Books book : books) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sách " + counter);
                alert.setHeaderText(null);
                alert.setContentText(counter + ")\nTiêu đề: " + book.getTitle() +
                        "\nTên tác giả: " + book.getAuthorName() +
                        "\nSố lượng có thể mua: " + book.getNumAvailableToBuy() +
                        "\nSố lượng có thể mượn: " + book.getNumAvailableToBorrow() +
                        "\nGiá mua: " + book.getPriceToBuy() +
                        "\nGiá mượn: " + book.getPriceToBorrow());
                alert.showAndWait();
                counter++;
            }
        }
    }

    // Tìm kiếm sách theo tiêu đề
    public void SearchForBookByTitle(ActionEvent event) throws IOException {
        // Nhập tên sách từ người dùng
        String bookName = inputBookName(event);

        // Kiểm tra sách có trong cơ sở dữ liệu không
        Books book = isBookInDB(bookName);

        // Hiển thị thông tin sách hoặc thông báo không tìm thấy
        if (book == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Không tìm thấy sách");
            alert.setHeaderText(null);
            alert.setContentText("Không có sách nào với tên này!");
            alert.showAndWait();
        } else {
            book.printBookDetails(1);
        }
    }

    // Tìm kiếm sách theo tên tác giả
    public void SearchForBookByAuthorName(ActionEvent event) throws IOException {
        // Nhập tên tác giả từ người dùng
        String authorName = inputAuthorName(event);

        // Biến để theo dõi có tìm thấy sách không
        boolean bookFound = false;
        int counter = 1;

        // Duyệt qua danh sách sách để tìm sách của tác giả
        for (Books book : books) {
            if (book.getAuthorName().equals(authorName)) {
                book.printBookDetails(counter);
                counter++;
                bookFound = true;
            }
        }

        // Hiển thị thông báo nếu không tìm thấy sách
        if (!bookFound) {
            String title = "Không tìm thấy sách";
            String content = "Không có sách nào của tác giả này!";
            alert(event, title, content);
        }
    }

    // Phương thức nhập tên sách từ người dùng
    public String inputBookName(ActionEvent event) throws IOException {
        // Tạo hộp thoại nhập tên sách
        TextInputDialog bookNameDialogue = new TextInputDialog();
        Optional<String> bookName = null;
        try {
            // Cài đặt thông tin hộp thoại
            bookNameDialogue.setTitle("Nhập tên sách");
            bookNameDialogue.setHeaderText("Nhập tên của sách:");
            bookNameDialogue.setContentText("Vui lòng nhập tên sách:");
            bookName = bookNameDialogue.showAndWait();

            // Kiểm tra xem đã nhập tên sách chưa
            if (bookName.isEmpty()) {
                throw new InputException("Dữ liệu trống");
            }
        } catch (Exception e) {
            // Hiển thị thông báo lỗi nếu nhập sai
            String title = "Nhập không hợp lệ";
            String content = "Dữ liệu không hợp lệ, vui lòng thử lại!";
            alert(event, title, content);
        }

        return bookName.get();
    }

    // Phương thức nhập tên tác giả từ người dùng
    public String inputAuthorName(ActionEvent event) throws IOException {
        // Tạo hộp thoại nhập tên tác giả
        TextInputDialog dialog = new TextInputDialog();
        Optional<String> authorName = null;
        try {
            // Cài đặt thông tin hộp thoại
            dialog.setTitle("Nhập tên tác giả");
            dialog.setHeaderText("Nhập tên của tác giả:");
            dialog.setContentText("Vui lòng nhập tên tác giả:");
            authorName = dialog.showAndWait();

            // Kiểm tra xem đã nhập tên tác giả chưa
            if (authorName.isEmpty()) {
                throw new InputException("Dữ liệu trống");
            }
        } catch (Exception e) {
            // Hiển thị thông báo lỗi nếu nhập sai
            String title = "Nhập không hợp lệ";
            String content = "Dữ liệu không hợp lệ, vui lòng thử lại!";
            alert(event, title, content);
        }

        // Trả về tên tác giả đã nhập
        return authorName.get();
    }
}