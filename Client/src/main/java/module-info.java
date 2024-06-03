module com.ais.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.ais.client to javafx.fxml;
    exports com.ais.client;
}
