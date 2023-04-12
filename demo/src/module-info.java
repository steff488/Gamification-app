module GUI {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;
    requires org.xerial.sqlitejdbc;

    exports GUI;
    exports main;

    opens GUI to javafx.fxml;
}