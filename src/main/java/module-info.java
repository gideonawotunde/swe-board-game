module com.example.sweboardgame {
  requires javafx.controls;
  requires javafx.fxml;


  opens com.example.sweboardgame to javafx.fxml;
  exports com.example.sweboardgame;
}