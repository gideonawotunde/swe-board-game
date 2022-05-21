package com.example.sweboardgame;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Objects;

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
    square.setOnMouseClicked(this::handleMouseClick);

    return square;
  }

  private Circle create0Shape() {
    Circle circle = new Circle(30);
    circle.setFill(Color.TRANSPARENT);

    return circle;
  }


  @FXML
  private void handleMouseClick(MouseEvent event) {
    StackPane selectedSquare = (StackPane) event.getSource();
    selectedSquare.getChildren().add(new Shape(Shape.Shapes.X));

    Integer row = GridPane.getRowIndex(selectedSquare);
    Integer col = GridPane.getColumnIndex(selectedSquare);
    Integer colLeft = col - 1;
    Integer colRight = col + 1;
    Integer cellAbove = row - 1;
    Integer cellBelow = row + 1;

    fillVerticalCells(cellAbove, col);
    fillVerticalCells(cellBelow, col);
    fillVerticalCells(row, colLeft);
    fillVerticalCells(row, colRight);

    //    System.out.printf("Click on square (%d,%d)%n", row, col);
  }

  public void fillVerticalCells(Integer row, Integer col) {
    StackPane cell = (StackPane) getNodeByRowColumnIndex(row, col);

    if (cell != null) {
      cell.getChildren().add(new Shape(Shape.Shapes.X));
    }
  }


  public Node getNodeByRowColumnIndex(final Integer row, final Integer column) {
    Node result = null;
    ObservableList<Node> children = board.getChildren();

    for (Node node : children) {
      if (Objects.equals(GridPane.getRowIndex(node), row) && Objects.equals(GridPane.getColumnIndex(node), column)) {
        result = node;
        break;
      }
    }

    return result;
  }
}
