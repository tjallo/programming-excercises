package nl.ru.ai.p4ai.exercise2;

import static nl.ru.ai.karel.Karel.*;

public class CatchThemAll {

  public static void main(String[] args) {
    map("pokemap.map");
    speed(50);

    collectPokeBalls();
    collectPokeMon();

  }

  private static void collectPokeBalls() {
    while (getNumberOfBalls() < 5) {

      lookForBalls();

    }

    goToStartPosition();

  }

  private static void collectPokeMon() {
    while (getNumberOfPokemon() < 5) {
      lookForPokeMon();
    }

    goToStartPosition();

  }

  private static void lookForPokeMon() {
    while (!onPokemon()) {

      traverseMap();

    }

    getPokemon();
    
  }

  private static void lookForBalls() {

    while (!onBall()) {

      traverseMap();

    }

    getBall();

  }

  private static void traverseMap() {

    if (!inFrontOfWall()) {

      step();

    } else {

      if (north()) {

        turnRight();
        if (!inFrontOfWall()) {

          step();

        }

        turnRight();

      } else {

        turnLeft();
        if (!inFrontOfWall()) {

          step();

        }

        turnLeft();

      }
    }

  }

  private static void goToStartPosition() {

    faceTowardsSouth();
    stepTilWallHit();
    turnRight();
    stepTilWallHit();
    turnRight();

  }

  private static void faceTowardsSouth() {
    if (!south()) {

      turnRight();
      faceTowardsSouth();

    }
  }

  private static void stepTilWallHit() {
    while (!inFrontOfWall()) {

      step();

    }
  }

}