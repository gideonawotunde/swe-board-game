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
  protected void onHighScoreButtonClick() {
    StageController.setScene("high-score", "High score");
  }

  @FXML
  protected void onStartButtonClick() throws IOException {
    String player1Name = player1.getText();
    String player2Name = player2.getText();
    Player player1 = new Player(player1Name, 0);
    Player player2 = new Player(player2Name, 0);
    Boolean player1IsInvalid = player1Name.isEmpty();
    Boolean player2IsInvalid = player2Name.isEmpty();

    if (player1IsInvalid || player2IsInvalid) {
      System.out.println("Show invalid form error message.");
      return;
    }

    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("board.fxml"));
    Parent root = fxmlLoader.load();
    BoardController boardController = fxmlLoader.getController();
    boardController.initializeGame(player1, player2);

    StageController.setScene(root, "Board game");
  }
}
