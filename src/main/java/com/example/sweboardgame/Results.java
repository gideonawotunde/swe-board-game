package com.example.sweboardgame;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Results {

  final Integer currentPlayer = BoardController.getCurrentPlayer();
  final String currentPlayerName = currentPlayer == 1 ? "John" : "Jane";

  @FXML
  Label winner;

  @FXML
  private void initialize() {
    winner.setText(currentPlayerName + " wins the game!");
  }
}
