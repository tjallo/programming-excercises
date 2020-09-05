package nl.ru.ai.p4ai.exercise1;

import static nl.ru.ai.karel.Karel.*;

public class Sowing_copy {

  public static void main(String[] args) {
    /* add your code for exercise 1.2 here */
    speed(5);
    sowField();
  }

  public static void sowField() {
    // I count the bottom row as row 1 (Unlike row 0 which is customary in CS)
    turnRight();
    sowUnevenRow();
    sowEvenRow();
    sowUnevenRow();
    sowEvenRow();
    sowUnevenRow();
    sowEvenRow();
    sowUnevenRow();
    sowEvenRow();
    sowUnevenRow();
    sowEvenRow();
    sowUnevenRow();
    sowEvenRow();
    sowUnevenRow();
    sowEvenRow();
    sowUnevenRow();
    sowOneRow();
  }

  public static void putBallAndStep() {
    putBall();
    step();
  }

  public static void sowOneRow() {
    putBallAndStep();
    putBallAndStep();
    putBallAndStep();
    putBallAndStep();
    putBallAndStep();
    putBallAndStep();
    putBallAndStep();
    putBallAndStep();
    putBallAndStep();
    putBallAndStep();
    putBallAndStep();
    putBallAndStep();
    putBallAndStep();
    putBallAndStep();
    putBallAndStep();
    putBallAndStep();
    putBallAndStep();
    putBallAndStep();
    putBallAndStep();
    putBallAndStep();
    putBallAndStep();
    putBallAndStep();
    putBallAndStep();
    putBallAndStep();
    putBallAndStep();
    putBallAndStep();
    putBallAndStep();
    putBallAndStep();
    putBallAndStep();
    putBallAndStep();
    putBall();
  }

  public static void moveUpToEvenRow() {
    turnLeft();
    step();
    turnLeft();
  }

  public static void moveUpToUnevenRow() {
    turnRight();
    step();
    turnRight();
  }

  public static void sowUnevenRow() {
    sowOneRow();
    moveUpToEvenRow();
  }

  public static void sowEvenRow() {
    sowOneRow();
    moveUpToUnevenRow();
  }

}
