package com.example.sweboardgame;

public class Player {
  private final String name;
  private Integer numberOfTurns;

  public Player(String name, Integer numberOfTurns) {
    this.name = name;
    this.numberOfTurns = numberOfTurns;
  }

  public String getName() {
    return name;
  }

  public Integer getNumberOfTurns() {
    return numberOfTurns;
  }

  public void setNumberOfTurns(Integer numberOfTurns) {
    this.numberOfTurns = numberOfTurns;
  }
}
