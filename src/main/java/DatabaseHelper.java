import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {
    private static final String DB_URL = "jdbc:sqlite:library.db";

    // Phương thức lấy danh sách sách từ cơ sở dữ liệu
    public static List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        String query = "SELECT id, title, author FROM books";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                books.add(new Book(id, title, author));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

    // Phương thức thêm sách vào cơ sở dữ liệu
    public static boolean addBook(String title, String author) {
        String query = "INSERT INTO books (title, author) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Phương thức kiểm tra đăng nhập
    public static User login(String username, String password) {
        String query = "SELECT role FROM users WHERE username = ? AND password = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String role = rs.getString("role");
                return new User(username, role); // Trả về đối tượng User nếu đăng nhập thành công
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Trả về null nếu đăng nhập thất bại
    }
}
