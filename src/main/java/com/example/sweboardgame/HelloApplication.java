package com.example.sweboardgame;

import javafx.application.Application;
import javafx.stage.Stage;

public class HelloApplication extends Application {
  public static Stage stage;

  public static void main(String[] args) {
    launch();
  }

  @Override
  public void start(Stage stage) {
    HelloApplication.stage = stage;

    StageController.setScene("pregame-form", "Enter player names");
  }
}