package com.example.sweboardgame;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StageController {
  static Stage stage = HelloApplication.stage;

  public static void setScene(String fxmlFileName) {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxmlFileName + ".fxml"));
      Scene scene = new Scene(fxmlLoader.load(), 700, 700);
      stage.setTitle("Enter player names");
      stage.setMinHeight(700);
      stage.setMinWidth(700);
      stage.setMaxWidth(700);
      stage.setMaxHeight(700);
      stage.setScene(scene);
      stage.show();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
