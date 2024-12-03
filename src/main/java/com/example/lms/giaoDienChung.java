package com.example.lms;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class giaoDienChung {
    public Connection connection;
    public PreparedStatement preparedStatement;
    public ResultSet resultSet;

    /**
     * chuyen canh.
     *
     * @param button   nut click de chuyen
     * @param fileName ten file fxml
     */
    public void chuyenCanh(Button button, String fileName) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fileName));

            Stage stage = new Stage();
            Scene scene = new Scene(root);

            root.setOnMousePressed((MouseEvent e) -> {
                x = e.getSceneX();
                y = e.getSceneY();
            });

            root.setOnMouseDragged((MouseEvent e) -> {

                stage.setX(e.getScreenX() - x);
                stage.setY(e.getScreenY() - y);

            });

            stage.initStyle(StageStyle.TRANSPARENT);

            stage.setScene(scene);
            stage.show();

            button.getScene().getWindow().hide();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private double x = 0;
    private double y = 0;

    public void chuyenCanhStatic(String fileName) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fileName));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            root.setOnMousePressed((MouseEvent event) -> {

                x = event.getSceneX();
                y = event.getSceneY();

            });
            root.setOnMouseDragged((MouseEvent event) -> {

                stage.setX(event.getScreenX() - x);
                stage.setY(event.getScreenY() - y);
            });

            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Clip clip;
    /**
     * thoat.
     */

    /**
     * thoatStatic.
     */

    public void tatAm(Button button1, Button button2,Clip x) {
        Platform.runLater(() -> {
            button1.setVisible(true);
            button2.setVisible(false);
            FloatControl gainControl = (FloatControl) x.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-80.0f);
        });
    }
    public void batAm(Button button1, Button button2, Clip x) {
        Platform.runLater(() -> {
            button1.setVisible(false);
            button2.setVisible(true);
            FloatControl gainControl = (FloatControl) x.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-15.0f);
        });
    }

}