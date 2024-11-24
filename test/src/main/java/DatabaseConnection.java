import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost/marcodata"; // Thay thế với cơ sở dữ liệu thực tế
    private static final String USER = "root"; // Tên người dùng MySQL
    private static final String PASSWORD = "190305"; // Mật khẩu của bạn

    // Phương thức kết nối tới cơ sở dữ liệu
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
