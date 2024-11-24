import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class BookListController {

    @FXML
    private TableView<Book> bookTable;

    @FXML
    private TableColumn<Book, Integer> idColumn;

    @FXML
    private TableColumn<Book, String> titleColumn;

    @FXML
    private TableColumn<Book, String> authorColumn;

    @FXML
    private TableColumn<Book, String> genreColumn;

    @FXML
    private TableColumn<Book, String> publishedDateColumn;

    @FXML
    public void initialize() {
        // Khởi tạo các cột trong TableView
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        publishedDateColumn.setCellValueFactory(new PropertyValueFactory<>("publishedDate"));

        // Lấy danh sách sách từ cơ sở dữ liệu và hiển thị lên TableView
        ObservableList<Book> books = FXCollections.observableArrayList(BookDAO.getAllBooks());
        bookTable.setItems(books);
    }
}
