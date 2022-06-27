module ui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires transitive javafx.graphics;

    opens ui to javafx.fxml;
    exports ui;
}
