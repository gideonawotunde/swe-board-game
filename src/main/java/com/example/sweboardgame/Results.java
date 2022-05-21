package com.example.sweboardgame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;

import java.io.IOException;

public class Results {
  @FXML
  Label winner;
  private String player1Name;
  private String player2Name;

  @FXML
  protected void onRestartButtonClick() {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("board.fxml"));
      Parent root = fxmlLoader.load();
      BoardController boardController = fxmlLoader.getController();
      boardController.initializeGame(player1Name, player2Name);

      StageController.setScene(root);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @FXML
  protected void onNewGameButtonClick() {
    StageController.setScene("pregame-form");
  }

  public void setPlayerNames(String player1Name, String player2Name, Integer currentPlayer) {
    this.player1Name = player1Name;
    this.player2Name = player2Name;

    winner.setText((currentPlayer == 1 ? player1Name : player2Name) + " is the winner!");
  }
}
