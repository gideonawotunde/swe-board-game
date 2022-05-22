package com.example.sweboardgame;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

public class HighScore {

  private final ObservableList<Result> data = FXCollections.observableArrayList();

  @FXML
  public TableView<Result> table;

  @FXML
  private TableColumn<Result, String> name;
  @FXML
  private TableColumn<Result, Integer> numWins;

  @FXML
  private void initialize() {
    table.setItems(data);

    File file = new File("results.json");

    if (file.exists()) {
      JSONParser jsonParser = new JSONParser();

      try (FileReader fileReader = new FileReader(file)) {
        Object obj = jsonParser.parse(fileReader);
        JSONArray winnersArray = (JSONArray) obj;

        for (Object o : winnersArray) {
          JSONObject hmm = (JSONObject) o;
          String winner = (String) hmm.get("winner");

          Result result = new Result(winner, 1);
          Optional<Result> existingResult = data.stream().filter(result1 -> result1.getWinner().equals(winner)).findAny();

          if (existingResult.isPresent()) {
            existingResult.get().setScore(existingResult.get().getScore() + 1);
          } else {
            data.add(result);
          }
        }

        name.setCellValueFactory(new PropertyValueFactory<>("winner"));
        numWins.setCellValueFactory(new PropertyValueFactory<>("score"));

      } catch (ParseException | IOException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
