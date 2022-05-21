package com.example.sweboardgame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;

import java.io.IOException;

public class PregameFormController {

  @FXML
  public TextField player1;
  public TextField player2;

  @FXML
  protected void onStartButtonClick() throws IOException {
    String player1Name = player1.getText();
    String player2Name = player2.getText();
    Boolean player1IsInvalid = player1Name.isEmpty();
    Boolean player2IsInvalid = player2Name.isEmpty();

    if (player1IsInvalid || player2IsInvalid) {
      System.out.println("Show invalid form error message.");
      return;
    }

    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("board.fxml"));
    Parent root = fxmlLoader.load();
    BoardController boardController = fxmlLoader.getController();
    boardController.initializeGame(player1Name, player2Name);

    StageController.setScene(root);
  }
}
