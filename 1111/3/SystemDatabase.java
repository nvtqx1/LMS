package BTL.DatabaseClasses;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Pattern;

public class SystemDatabase {
    private static final ArrayList<Members> members = new ArrayList<>();
    private static final ArrayList<Admins> admins = new ArrayList<>();
    private static final ArrayList<Books> books = new ArrayList<>();

    private static int memberCounter = 0;

    public static BTL.DatabaseClasses.SystemDatabase getInstance() {
        return new BTL.DatabaseClasses.SystemDatabase();
    }

    public static synchronized void incrementID() {
        memberCounter++;
    }

    public boolean checkIfMemberExists(String name, String number, String email) {
        return members.stream()
                .anyMatch(member ->
                        member.getName().equals(name) &&
                                member.getPhoneNumber().equals(number) &&
                                member.getEmail().equals(email)
                );
    }

    public boolean checkIfAdminExists(String name, String number, String email) {
        return admins.stream()
                .anyMatch(admin ->
                        admin.getName().equals(name) &&
                                admin.getPhoneNumber().equals(number) &&
                                admin.getEmail().equals(email)
                );
    }

    public Optional<Members> returnMemberFromDB(String name, String number, String email) {
        return members.stream()
                .filter(member ->
                        member.getName().equals(name) &&
                                member.getPhoneNumber().equals(number) &&
                                member.getEmail().equals(email)
                )
                .findFirst();
    }

    public Optional<Books> isBookInDB(String bookName) {
        return books.stream()
                .filter(book -> book.getTitle().equals(bookName))
                .findFirst();
    }

    public static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    public static boolean isValidNumber(String phoneNumber) {
        String regex = "^01[0125]\\d{8}$";
        return phoneNumber != null && Pattern.matches(regex, phoneNumber);
    }

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email != null && Pattern.matches(emailRegex, email);
    }

    public void alert(ActionEvent event, String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void setScene(ActionEvent event, Parent root, String title) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Pane glassPane = (Pane) root.lookup(".glass");
        GaussianBlur blur = new GaussianBlur(8);
        glassPane.setEffect(blur);

        Image icon = new Image(getClass().getResourceAsStream("/BTL/book.png"));
        stage.getIcons().add(icon);
        stage.setTitle("Quyền quản trị");

        Scene scene = new Scene(root, 900, 700);
        stage.setScene(scene);
        stage.show();
    }
}