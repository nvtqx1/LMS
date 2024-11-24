import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class AdminController {

    // Các nút giao diện (Button)
    @FXML private Button logout_btn;
    @FXML private Button manageMembers_btn;
    @FXML private Button manageBooks_btn;
    @FXML private Button viewBooks_btn;
    @FXML private Button deleteBooks_btn;
    @FXML private Button addMember_btn;
    @FXML private Button editMember_btn;
    @FXML private Button deleteMember_btn;
    @FXML private Button addBook_btn;
    @FXML private Button editBook_btn;
    @FXML private Button deleteBook_btn;


    // TableView cho Member
    @FXML private TableView<Member> members_tableView;
    @FXML private TableColumn<Member, Integer> col_memberId;
    @FXML private TableColumn<Member, String> col_memberName;
    @FXML private TableColumn<Member, String> col_memberEmail;
    @FXML private TableColumn<Member, String> col_memberPhone;

    // TableView cho Book
    @FXML private TableView<Book> books_tableView;
    @FXML private TableColumn<Book, Integer> col_bookId;
    @FXML private TableColumn<Book, String> col_bookTitle;
    @FXML private TableColumn<Book, String> col_bookAuthor;
    @FXML private TableColumn<Book, String> col_bookGenre;
    @FXML private TableColumn<Book, String> col_bookPublished;

    // Form quản lý thành viên và sách
    @FXML private AnchorPane manageMembers_form;
    @FXML private AnchorPane manageBooks_form;

    // Phương thức khởi tạo, cấu hình các cột của bảng
    @FXML
    public void initialize() {
        // Cấu hình các cột bảng thành viên
        col_memberId.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_memberName.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_memberEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_memberPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        // Cấu hình các cột bảng sách
        col_bookId.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_bookTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        col_bookAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        col_bookGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        col_bookPublished.setCellValueFactory(new PropertyValueFactory<>("publishedDate"));
    }

    // Phương thức chuyển đến màn hình quản lý thành viên
    @FXML
    public void manageMembers(ActionEvent event) {
        manageMembers_form.setVisible(true);
        manageBooks_form.setVisible(false);

        // Load danh sách thành viên
        ObservableList<Member> members = FXCollections.observableArrayList(MemberDAO.getAllMembers());
        members_tableView.setItems(members);
    }

    // Phương thức chuyển đến màn hình quản lý sách
    @FXML
    public void manageBooks(ActionEvent event) {
        manageMembers_form.setVisible(false);
        manageBooks_form.setVisible(true);

        // Load danh sách sách
        ObservableList<Book> books = FXCollections.observableArrayList(BookDAO.getAllBooks());
        books_tableView.setItems(books);
    }

    // Phương thức chuyển đến màn hình chỉ xem sách (không sửa hoặc xóa)
    @FXML
    public void viewBooks(ActionEvent event) {
        manageBooks(event);
    }

    // Phương thức xử lý sự kiện đăng xuất
    @FXML
    public void logout(ActionEvent event) {
        // Logic đăng xuất
        System.out.println("Đã đăng xuất");
    }

    // Các phương thức xử lý sự kiện thêm, chỉnh sửa, xóa thành viên hoặc sách sẽ được định nghĩa thêm
    @FXML
    public void addMember(ActionEvent event) {
        // Logic thêm thành viên mới
        System.out.println("Thêm thành viên");
    }

    @FXML
    public void editMember(ActionEvent event) {
        // Logic chỉnh sửa thông tin thành viên
        System.out.println("Chỉnh sửa thành viên");
    }

    @FXML
    public void deleteMember(ActionEvent event) {
        // Logic xóa thành viên
        System.out.println("Xóa thành viên");
    }

    @FXML
    public void addBook(ActionEvent event) {
        // Logic thêm sách mới
        System.out.println("Thêm sách");
    }

    @FXML
    public void editBook(ActionEvent event) {
        // Logic chỉnh sửa sách
        System.out.println("Chỉnh sửa sách");
    }

    @FXML
    public void deleteBook(ActionEvent event) {
        // Logic xóa sách
        System.out.println("Xóa sách");
    }
}
