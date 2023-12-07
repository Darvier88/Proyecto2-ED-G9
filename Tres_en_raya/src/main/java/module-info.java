module ec.edu.espol.tres_en_raya {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    exports ec.edu.espol.tres_en_raya;
    exports ec.edu.espol.controllers;
    opens ec.edu.espol.tres_en_raya to javafx.fxml;
    opens ec.edu.espol.controllers to javafx.fxml;
}
