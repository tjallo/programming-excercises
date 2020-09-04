package nl.ru.ai.p4ai.exercise1;

import static nl.ru.ai.karel.Karel.*;

public class Church {

  public static void main(String[] args) {
    map("church.map");
    speed(100);
    /* Add your code for exercise 1.3 here */
    goToMeetFirstWall();
    loopRoundChurch();
  }

  public static void goToMeetFirstWall() {
    // Go and seek the first wall of the church
    while (!inFrontOfWall()) {
      step();
    }
    // If you have found the first wall turn right, since we will be going around anti-clockwise
    turnRight();
  }

  public static void loopRoundChurch() {

    while (true) {

      turnLeft();

      if (inFrontOfWall()) {
        // Check if you are in front of a well, if you are, make a right turn
        turnRight();        
        if (inFrontOfWall()) {
          // Check if you won't walk in to an adjecent wall, if there is still a wall, make another right turn 
          turnRight();          
        } else {
          // If no adjecent wall was found, it is safe to step
          step();          
        }
      } else {
        // If no wall was detected in the first place: step
        step();
      }
    }
  }

}
