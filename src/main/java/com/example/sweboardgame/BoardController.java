package com.example.sweboardgame;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.util.Objects;

public class BoardController {
  private static Integer currentPlayer = 1;

  @FXML
  public GridPane board;

  @FXML
  private void initialize() {
    for (var i = 0; i < board.getRowCount(); i++) {
      for (var j = 0; j < board.getColumnCount(); j++) {
        StackPane square = createSquare();
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

  @FXML
  private void handleMouseClick(MouseEvent event) {
    StackPane selectedSquare = (StackPane) event.getSource();
    int numberOfChildNodes = selectedSquare.getChildren().size();

    if (numberOfChildNodes > 0) {
      System.out.println("Invalid move");
      return;
    }

    selectedSquare.getChildren().add(new Shape(getShape()));

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
    Integer numberOfEmptyCells = getNumberOfEmptyCells();

    if (numberOfEmptyCells == 0) {
      StageController.setScene("results");
      return;
    }

    setCurrentPlayer(currentPlayer == 1 ? 2 : 1);
  }

  public void fillVerticalCells(Integer row, Integer col) {
    StackPane cell = (StackPane) getNodeByRowColumnIndex(row, col);

    if (cell != null && cell.getChildren().size() < 1) {
      cell.getChildren().add(new Shape(getShape()));
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

  public Integer getNumberOfEmptyCells() {
    int totalCells = board.getRowCount() * board.getColumnCount();

    for (var i = 0; i < board.getRowCount(); i++) {
      for (var j = 0; j < board.getColumnCount(); j++) {
        StackPane node = (StackPane) getNodeByRowColumnIndex(i, j);
        int integer = node.getChildren().size();
        if (integer > 0) {
          totalCells = totalCells - 1;
        }
      }
    }

    return totalCells;
  }

  public static Integer getCurrentPlayer() {
    return currentPlayer;
  }

  public void setCurrentPlayer(Integer currentPlayer) {
    BoardController.currentPlayer = currentPlayer;
  }

  public Shape.ShapeType getShape() {
    return getCurrentPlayer() == 1 ? Shape.ShapeType.X : Shape.ShapeType.O;
  }

}
