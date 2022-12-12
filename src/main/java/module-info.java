module com.example.fabrica_componentes {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.fabrica_componentes to javafx.fxml;
    exports com.example.fabrica_componentes;
}