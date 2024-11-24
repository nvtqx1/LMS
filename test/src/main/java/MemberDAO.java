import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {

    public static List<Member> getAllMembers() {
        List<Member> members = new ArrayList<>();
        String query = "SELECT * FROM members"; // Thay đổi bảng và trường theo cơ sở dữ liệu của bạn

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");

                members.add(new Member(id, name, email, phone));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return members;
    }
}
