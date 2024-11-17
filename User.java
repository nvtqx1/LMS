public class User {
    protected String username;
    protected String role; // "reader" hoặc "admin"

    public User(String username, String role) {
        this.username = username;
        this.role = role;
    }

    public String getUsername() { return username; }
    public String getRole() { return role; }
}
