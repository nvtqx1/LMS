import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MemberListController {

    @FXML
    private TableView<Member> memberTable;

    @FXML
    private TableColumn<Member, Integer> idColumn;

    @FXML
    private TableColumn<Member, String> nameColumn;

    @FXML
    private TableColumn<Member, String> emailColumn;

    @FXML
    private TableColumn<Member, String> phoneColumn;

    @FXML
    public void initialize() {
        // Khởi tạo các cột trong TableView
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

        // Lấy danh sách thành viên từ cơ sở dữ liệu và hiển thị lên TableView
        ObservableList<Member> members = FXCollections.observableArrayList(MemberDAO.getAllMembers());
        memberTable.setItems(members);
    }
}
