module com.example.ryhmatoo2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.ryhmatoo2 to javafx.fxml;
    exports com.example.ryhmatoo2;
}