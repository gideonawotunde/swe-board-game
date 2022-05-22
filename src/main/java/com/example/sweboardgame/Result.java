package com.example.sweboardgame;

public class Result {
  private final String winner;
  private Integer score;

  public Result(String winner, Integer score) {
    this.winner = winner;
    this.score = score;
  }

  public String getWinner() {
    return winner;
  }

  public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }
}
