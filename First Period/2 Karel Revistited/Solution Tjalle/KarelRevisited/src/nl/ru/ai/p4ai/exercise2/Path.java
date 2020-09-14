package nl.ru.ai.p4ai.exercise2;

import static nl.ru.ai.karel.Karel.*;

public class Path {
  public static void main(String[] args) {
    map("path.map");
    speed(50);
    // your code here
    followPath();
  }

  private static void followPath() {

    

  }

  public static void do180Turn() {
    turnRight();
    turnRight();
  }
}
