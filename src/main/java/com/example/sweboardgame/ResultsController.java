package com.example.sweboardgame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ResultsController {

  @FXML
  Label winner;
  private Player player1;
  private Player player2;

  @FXML
  protected void onRestartButtonClick() {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("board.fxml"));
      Parent root = fxmlLoader.load();
      BoardController boardController = fxmlLoader.getController();
      boardController.initializeGame(player1, player2);

      StageController.setScene(root);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @FXML
  protected void onNewGameButtonClick() {
    StageController.setScene("pregame-form");
  }

  public void initResults(Player player1, Player player2, Integer currentPlayer, String dateStarted) throws IOException {
    this.player1 = player1;
    this.player2 = player2;


    File file = new File("results.json");


    System.out.println(file.exists());
    JSONArray jsonArray = new JSONArray();

    if (file.exists()) {
      JSONParser jsonParser = new JSONParser();
      try (FileReader fileReader = new FileReader("results.json")) {
        Object obj = jsonParser.parse(fileReader);
        jsonArray = (JSONArray) obj;
      } catch (ParseException e) {
        throw new RuntimeException(e);
      }
    }

    String player1Name = player1.getName();
    String player2Name = player2.getName();
    String winnerName = (currentPlayer == 1 ? player1Name : player2Name);

    winner.setText(winnerName + " is the winner!");

    JSONObject player1Object = new JSONObject();
    player1Object.put("name", player1.getName());
    player1Object.put("turns", player1.getNumberOfTurns());

    JSONObject player2Object = new JSONObject();
    player2Object.put("name", player2.getName());
    player2Object.put("turns", player2.getNumberOfTurns());

    JSONObject gameResult = new JSONObject();
    gameResult.put("player1", player1Object);
    gameResult.put("player2", player2Object);
    gameResult.put("winner", winnerName);
    gameResult.put("date_started", dateStarted);

    jsonArray.add(gameResult);

    try (FileWriter fileWriter = new FileWriter("results.json")) {
      fileWriter.write(jsonArray.toJSONString());
      fileWriter.flush();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
