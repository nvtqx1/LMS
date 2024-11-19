import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {
    private static final String DB_URL = "jdbc:sqlite:library.db";

    // Phương thức lấy danh sách sách đang được mượn
    public static List<Book> getBorrowedBooks() {
        List<Book> borrowedBooks = new ArrayList<>();
        String query = "SELECT id, title, author FROM books WHERE is_borrowed = 1";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                borrowedBooks.add(new Book(id, title, author));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return borrowedBooks;
    }

    // Phương thức mượn sách
    public static boolean borrowBook(int bookId) {
        return updateBookStatus(bookId, true); // Đặt trạng thái is_borrowed = true
    }

    // Phương thức trả sách
    public static boolean returnBook(int bookId) {
        return updateBookStatus(bookId, false); // Đặt trạng thái is_borrowed = false
    }

    // Các phương thức khác trong DatabaseHelper
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

    public static boolean deleteBook(int bookId) {
        String query = "DELETE FROM books WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, bookId);
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean updateBookStatus(int bookId, boolean isBorrowed) {
        String query = "UPDATE books SET is_borrowed = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setBoolean(1, isBorrowed);
            stmt.setInt(2, bookId);
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static User login(String username, String password) {
        String query = "SELECT role FROM users WHERE username = ? AND password = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String role = rs.getString("role");
                return new User(username, role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<User> getUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT username, role FROM users";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String username = rs.getString("username");
                String role = rs.getString("role");
                users.add(new User(username, role));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public static boolean addUser(String username, String password, String role) {
        String query = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, role);
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean deleteUser(String username) {
        String query = "DELETE FROM users WHERE username = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean returnBook(Book selectedBook) {
        if (selectedBook == null) return false; // Kiểm tra đối tượng null

        String query = "UPDATE books SET is_borrowed = 0 WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, selectedBook.getId()); // Lấy id từ đối tượng Book
            int rowsUpdated = stmt.executeUpdate();

            return rowsUpdated > 0; // Trả về true nếu cập nhật thành công

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // Trả về false nếu xảy ra lỗi
    }


    public static boolean borrowBook(Book selectedBook) {
        if (selectedBook == null) return false; // Kiểm tra đối tượng null

        String query = "UPDATE books SET is_borrowed = 1 WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, selectedBook.getId()); // Lấy id từ đối tượng Book
            int rowsUpdated = stmt.executeUpdate();

            return rowsUpdated > 0; // Trả về true nếu cập nhật thành công

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // Trả về false nếu xảy ra lỗi
    }

}
