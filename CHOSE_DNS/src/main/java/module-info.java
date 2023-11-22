module com.example.chose_dns {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.chose_dns to javafx.fxml;
    exports com.example.chose_dns;
}