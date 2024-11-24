import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    public static List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books"; // Thay đổi bảng và trường theo cơ sở dữ liệu của bạn

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String genre = resultSet.getString("genre");
                String publishedDate = resultSet.getString("publishedDate");

                books.add(new Book(id, title, author, genre, publishedDate));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }
}
