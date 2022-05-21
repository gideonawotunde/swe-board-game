module com.example.sweboardgame {
  requires javafx.controls;
  requires javafx.fxml;
  requires com.google.gson;
  requires json.simple;


  opens com.example.sweboardgame to javafx.fxml;
  exports com.example.sweboardgame;
}