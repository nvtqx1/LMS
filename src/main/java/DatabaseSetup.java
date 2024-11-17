import java.sql.*;

public class DatabaseSetup {
    private static final String DB_URL = "jdbc:sqlite:library.db";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {

            // Tạo bảng 'users'
            String createTableSQL = """
                CREATE TABLE IF NOT EXISTS users (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    username TEXT NOT NULL UNIQUE,
                    password TEXT NOT NULL,
                    role TEXT NOT NULL
                );
            """;
            stmt.execute(createTableSQL);

            // Thêm tài khoản admin
            String insertAdminSQL = """
                INSERT OR IGNORE INTO users (username, password, role) 
                VALUES ('admin', 'admin', 'admin');
            """;
            stmt.execute(insertAdminSQL);

            // Thêm 100 tài khoản user
            String insertUserSQL = "INSERT OR IGNORE INTO users (username, password, role) VALUES (?, ?, 'reader')";
            try (PreparedStatement pstmt = conn.prepareStatement(insertUserSQL)) {
                for (int i = 1; i <= 100; i++) {
                    pstmt.setString(1, "user" + i); // username: user1, user2, ..., user100
                    pstmt.setString(2, "1");       // password: "1"
                    pstmt.executeUpdate();
                }
            }

            System.out.println("Database and users setup complete!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
