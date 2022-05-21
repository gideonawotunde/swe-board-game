package com.example.sweboardgame;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class Shape extends Label {
  public Shape(ShapeType shape) {
    super(shape.toString());

    setFont(Font.font(Font.getDefault().getFamily(), 70));
  }

  public enum ShapeType {
    X,
    O
  }
}
