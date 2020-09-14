package nl.ru.ai.p4ai.exercise1;

import static nl.ru.ai.karel.Karel.*;

public class Sowing {

  public static void main(String[] args) {
    /* add your code for exercise 1.2 here */
    speed(5);

    turnRight();

    while (true) {

      // Move East
      if (!inFrontOfWall() && east()) {
        putBallAndStep();
      }
      if (inFrontOfWall() && east()) {
        putBall();
        turnLeft();
        step();
        turnLeft();
      }

      // Move west
      if (!inFrontOfWall() && west()) {
        putBallAndStep();
      }
      if (inFrontOfWall() && west()) {
        putBall();
        turnRight();
        if (!inFrontOfWall()) {
          step();
          turnRight();
        } else {
          break;
        }
      }
    }
  }

  public static void putBallAndStep() {
    putBall();
    step();
  }

}
