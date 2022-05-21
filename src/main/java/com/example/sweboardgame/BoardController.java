package com.example.sweboardgame;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class BoardController {
  @FXML
  public GridPane board;

  @FXML
  private void initialize() {
    for (var i = 0; i < board.getRowCount(); i++) {
      for (var j = 0; j < board.getColumnCount(); j++) {
        var square = createSquare();
        board.add(square, j, i);
      }
    }
  }

  private StackPane createSquare() {
    StackPane square = new StackPane();
    square.getStyleClass().add("square");
    Circle piece = new Circle(30);
    piece.setFill(Color.TRANSPARENT);
    square.getChildren().add(piece);
    return square;
  }
}
