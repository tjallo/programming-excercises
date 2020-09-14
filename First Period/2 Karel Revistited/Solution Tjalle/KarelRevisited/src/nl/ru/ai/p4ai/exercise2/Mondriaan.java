package nl.ru.ai.p4ai.exercise2;

import static nl.ru.ai.karel.Karel.*;

public class Mondriaan {
  public static void main(String[] args) {

    speed(5);
    drawRectangles(random(2, 4));

  }

  private static void drawRectangles(int noOfRectangles) {

    while (noOfRectangles > 0) {
      
      generateRectangle(random(0, 29), random(0, 14));
      noOfRectangles--;

    }

  }

  public static void generateRectangle(int xPos, int yPos) {

    goToPosition(xPos, yPos);
    drawRectangle(random(3, 32 - xPos), random(3, 17 - yPos));
    backToStartPos(xPos, yPos);

  }

  private static void goToPosition(int xPos, int yPos) {

    while (yPos > 1) {
      step();
      yPos--;
    }

    turnRight();

    while (xPos > 1) {
      step();
      xPos--;
    }

  }

  private static void backToStartPos(int xPos, int yPos) {

    while (yPos > 0) {
      step();
      yPos--;
    }

    turnRight();

    while (xPos > 1) {
      step();
      xPos--;
    }

    turnRight();

  }

  private static void leftStep() {

    turnLeft();
    step();

  }

  private static void drawRectangle(int width, int height) {

    drawLine(width);
    leftStep();
    drawLine(height - 1);
    leftStep();
    drawLine(width - 1);
    leftStep();
    drawLine(height - 2);

  }

  private static void drawLine(int length) {

    while (length > 1) {
      if (!onBall()) {
        putBall();
      }
      step();
      length--;
    }

    if (!onBall()) {
      putBall();
    }
  }
}
