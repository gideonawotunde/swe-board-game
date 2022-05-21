package com.example.sweboardgame;

import javafx.scene.control.Label;
import javafx.scene.text.Font;



public class Shape extends Label {
  public enum Shapes {
    X,
    O
  }

  public Shape(Shapes shape) {
    super(shape.toString());

    setFont(Font.font(Font.getDefault().getFamily(), 70));
  }
}
