module com.example.tictactoegame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tictactoegame to javafx.fxml;
    exports com.example.tictactoegame;
}