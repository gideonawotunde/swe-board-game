package com.example.sweboardgame;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class BoardController {
  @FXML
  public GridPane board;
  private Player player1;
  private Player player2;
  private Integer currentPlayer;
  private String dateStarted;

  public void initializeGame(Player player1, Player player2) {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();

    this.player1 = player1;
    this.player2 = player2;
    this.currentPlayer = 1;
    this.dateStarted = dtf.format(now);
  }

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
      try {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("results.fxml"));
        Parent root = fxmlLoader.load();
        ResultsController results = fxmlLoader.getController();
        results.setPlayerNames(player1, player2, currentPlayer, dateStarted);

        StageController.setScene(root);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      return;
    }

    setCurrentPlayer(currentPlayer == 1 ? 2 : 1);

    if (currentPlayer == 1) {
      player1.setNumberOfTurns(player1.getNumberOfTurns() + 1);
    } else {
      player2.setNumberOfTurns(player2.getNumberOfTurns() + 1);
    }
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

  public void setCurrentPlayer(Integer currentPlayer) {
    this.currentPlayer = currentPlayer;
  }

  public Shape.ShapeType getShape() {
    return currentPlayer == 1 ? Shape.ShapeType.X : Shape.ShapeType.O;
  }

}
