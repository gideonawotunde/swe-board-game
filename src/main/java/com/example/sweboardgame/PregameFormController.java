package com.example.sweboardgame;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PregameFormController {
  @FXML
  public TextField player1;

  @FXML
  public TextField player2;

  @FXML
  protected void onStartButtonClick() {
    Boolean player1IsInvalid = player1.getText().isEmpty();
    Boolean player2IsInvalid = player2.getText().isEmpty();

    if (player1IsInvalid || player2IsInvalid) {
      System.out.println("Show invalid form error message.");
      return;
    }

    StageController.setScene("board");
  }
}
