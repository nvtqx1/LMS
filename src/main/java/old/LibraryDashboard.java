import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Stack;

public class LibraryDashboard {
    private String userRole;
    private Stack<Scene> sceneStack = new Stack<>(); // Ngăn xếp lưu trữ các Scene

    public LibraryDashboard(String userRole) {
        this.userRole = userRole;
    }

    public void start(Stage primaryStage) {
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(10));
        layout.setAlignment(Pos.TOP_CENTER);

        Label roleLabel = new Label("Đăng nhập với quyền: " + userRole);

        // Nút chức năng cho admin
        Button manageBooksButton = new Button("Quản lý sách");
        Button manageMembersButton = new Button("Quản lý thành viên");

        // Nút chức năng cho reader
        Button viewBooksButton = new Button("Xem sách");
        Button borrowBookButton = new Button("Mượn sách");
        Button returnBookButton = new Button("Trả sách");

        // Tùy theo quyền hiển thị chức năng
        if ("admin".equals(userRole)) {
            // Nút Quản lý sách
            manageBooksButton.setOnAction(e -> showManageBooksScreen(primaryStage));

            // Nút Quản lý thành viên
            manageMembersButton.setOnAction(e -> {
                ManageUsers manageUsers = new ManageUsers();
                manageUsers.start(primaryStage, this);
            });

            layout.getChildren().addAll(roleLabel, manageBooksButton, manageMembersButton);
        } else if ("reader".equals(userRole)) {
            // Xử lý các nút cho reader
            viewBooksButton.setOnAction(e -> {
                ViewBooks viewBooks = new ViewBooks();
                viewBooks.start(primaryStage, this);
            });

            borrowBookButton.setOnAction(e -> {
                BorrowBooks borrowBooks = new BorrowBooks();
                borrowBooks.start(primaryStage, this);
            });

            returnBookButton.setOnAction(e -> {
                ReturnBooks returnBooks = new ReturnBooks();
                returnBooks.start(primaryStage, this);
            });

            layout.getChildren().addAll(roleLabel, viewBooksButton, borrowBookButton, returnBookButton);
        }

        // Lưu Scene hiện tại để có thể quay lại sau này
        Scene currentScene = new Scene(layout, 400, 300);
        primaryStage.setScene(currentScene);
        primaryStage.setTitle("Quản lý thư viện");
        primaryStage.show();

        sceneStack.push(currentScene); // Lưu Scene hiện tại vào stack
    }

    // Phương thức công khai để lấy ngăn xếp scene
    public Stack<Scene> getSceneStack() {
        return sceneStack;
    }

    // Phương thức quay lại màn hình trước đó
    public void goBack(Stage primaryStage) {
        if (!sceneStack.isEmpty()) {
            primaryStage.setScene(sceneStack.pop()); // Quay lại scene trước đó
        }
    }

    // Phương thức hiển thị màn hình quản lý sách
    private void showManageBooksScreen(Stage primaryStage) {
        VBox manageBooksLayout = new VBox(20);
        manageBooksLayout.setPadding(new Insets(10));
        manageBooksLayout.setAlignment(Pos.TOP_CENTER);

        // Nút chức năng trong màn hình Quản lý sách
        Button addBookButton = new Button("Thêm sách");
        Button deleteBookButton = new Button("Xóa sách");

        // Sự kiện cho các nút
        addBookButton.setOnAction(e -> {
            System.out.println("Chuyển tới màn hình thêm sách...");
        });

        deleteBookButton.setOnAction(e -> {
            System.out.println("Chuyển tới màn hình xóa sách...");
        });

        // Nút quay lại màn hình chính
        Button backButton = new Button("Quay lại");
        backButton.setOnAction(e -> goBack(primaryStage));

        manageBooksLayout.getChildren().addAll(addBookButton, deleteBookButton, backButton);

        // Hiển thị màn hình quản lý sách
        Scene manageBooksScene = new Scene(manageBooksLayout, 400, 300);
        primaryStage.setScene(manageBooksScene);
        primaryStage.setTitle("Quản lý sách");
        primaryStage.show();

        sceneStack.push(manageBooksScene); // Lưu scene quản lý sách vào ngăn xếp
    }
}
