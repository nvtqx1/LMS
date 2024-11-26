module com.example.lms {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.swing;
    requires javafx.media;
    requires javafx.base;
    requires javafx.swt;
    requires javafx.graphics;

//    requires org.controlsfx.controls;
//    requires com.dlsc.formsfx;
//    requires net.synedra.validatorfx;
//    requires org.kordamp.ikonli.javafx;
//    requires org.kordamp.bootstrapfx.core;
//    //requires eu.hansolo.tilesfx;
//    requires com.almasb.fxgl.all;
    requires java.sql;
    requires jdk.accessibility;

    opens com.example.lms to javafx.fxml;
    exports com.example.lms;
}