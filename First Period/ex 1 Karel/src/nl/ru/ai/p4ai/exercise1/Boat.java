package nl.ru.ai.p4ai.exercise1;

import static nl.ru.ai.karel.Karel.*;

import java.security.PublicKey;

public class Boat {

  public static void main(String[] args) {
    /* Add your code for exercise 1.1 here */
    speed(5);
    drawBoat();
  }

  public static void drawBoat() {
    getToStartPosition();
    putBoatFoundation();
    putLeftUpperDiagonal();
    putMastAndMoveBackToTop();
    putRightUpperDiagonal();
    moveOutOfBoat();
  }

  public static void getToStartPosition() {
    step();
    step();
    turnRight();
    step();
    step();
    step();
  }

  public static void putBallAndStep() {
    putBall();
    step();
  }

  public static void putSevenBallsInAROw() {
    putBallAndStep();
    putBallAndStep();
    putBallAndStep();
    putBallAndStep();
    putBallAndStep();
    putBallAndStep();
    putBallAndStep();
  }

  public static void putElevenBallsInARow() {
    putSevenBallsInAROw();
    putBallAndStep();
    putBallAndStep();
    putBallAndStep();
    putBall();
  }

  public static void putBoatFoundation() {
    putBall();
    step();
    turnRight();
    step();
    turnLeft();
    putSevenBallsInAROw();
    turnLeft();
    step();
    putBall();
    step();
    turnRight();
    step();
    turnLeft();
    turnLeft();
    putElevenBallsInARow();
  }

  public static void MoveUpDiagonalAndPutBall() {
    step();
    turnRight();
    step();
    putBall();
    turnLeft();
  }

  public static void putLeftUpperDiagonal() {
    turnRight();
    MoveUpDiagonalAndPutBall();
    MoveUpDiagonalAndPutBall();
    MoveUpDiagonalAndPutBall();
    MoveUpDiagonalAndPutBall();
    MoveUpDiagonalAndPutBall();
  }

  public static void putMastAndMoveBackToTop() {
    turnRight(); 
    turnRight();
    step();
    putBallAndStep();
    putBallAndStep();
    putBallAndStep();
    putBallAndStep();
    turnRight();
    turnRight();
    step();
    step();
    step();
    step();
    step();
    turnRight();
    turnRight();
  }

  public static void MoveDownDiagonalAndPutBall() {
    step();
    turnLeft();
    step();
    putBall();
    turnRight();
  }

  public static void putRightUpperDiagonal() {
    MoveDownDiagonalAndPutBall();
    MoveDownDiagonalAndPutBall();
    MoveDownDiagonalAndPutBall();
    MoveDownDiagonalAndPutBall();
  }

  public static void moveOutOfBoat() {
    turnLeft();
    step();
    step();
    step();
  } 
}
