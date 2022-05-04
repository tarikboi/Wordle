module com.example.wordle {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.wordle to javafx.fxml;
    exports com.example.wordle;
}