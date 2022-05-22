package com.example.sweboardgame;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StageController {
  static Stage stage = HelloApplication.stage;
  static Integer stageSize = 700;

  public static void setScene(String fxmlFileName, String title) {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxmlFileName + ".fxml"));
      generalLoad(fxmlLoader.load(), title);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static void generalLoad(Parent fxmlLoader, String title) throws IOException {
    Scene scene = new Scene(fxmlLoader, stageSize, stageSize);
    stage.setMinHeight(stageSize);
    stage.setMinWidth(stageSize);
    stage.setTitle(title);
    stage.setScene(scene);
    stage.show();
  }

  public static void setScene(Parent root, String title) {
    try {
      generalLoad(root, title);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
