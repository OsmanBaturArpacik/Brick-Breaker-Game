module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.brickbreaker to javafx.fxml;
    exports com.example.brickbreaker;
}