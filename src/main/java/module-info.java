module com.example.lab2java {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lab2java to javafx.fxml;
    exports com.example.lab2java;
}