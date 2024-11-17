import javafx.application.Application;
import javafx.stage.Stage;

public class LibraryManagementApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        showLoginStage(primaryStage);
    }

    private void showLoginStage(Stage primaryStage) {
        LoginScreen loginScreen = new LoginScreen();
        loginScreen.start(primaryStage, userRole -> showLibraryDashboard(primaryStage, userRole));
    }

    private void showLibraryDashboard(Stage primaryStage, String userRole) {
        LibraryDashboard dashboard = new LibraryDashboard(userRole);
        dashboard.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}