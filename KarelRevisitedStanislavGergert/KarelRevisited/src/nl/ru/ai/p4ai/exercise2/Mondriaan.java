package nl.ru.ai.p4ai.exercise2;

import static nl.ru.ai.karel.Karel.*;


public class Mondriaan {

    public static void main(String[] args) {
        speed(10);
        numberOfRectangles(random(2, 4));

    }

    private static void goToStartPosition(int xPos, int yPos) {
        while (yPos > 1) {
            step();
            yPos--;
        }
        turnRight();
        while (xPos > 1) {
            step();
            xPos--;
        }
        turnLeft();
    }

    private static void generateRectangleShapeAndDrawIt(int width, int height, int xPos, int yPos) {
        System.out.println(xPos + " " + yPos + " " + width + " " + height + " ");


        goToStartPosition(xPos, yPos);
        drawLine(height - 1);
        turnRight();
        drawLine(width - 1);
        turnRight();
        drawLine(height - 1);
        turnRight();
        drawLine(width - 1);
        turnRight();

        returnToStart();

    }

    private static void returnToStart() {
        turnRight();
        while (!inFrontOfWall()) {
            step();
        }
        turnRight();
        while (!inFrontOfWall()) {
            step();
        }
        turnRight();
        while (!inFrontOfWall()) {
            step();
        }
        turnRight();
    }

    private static void numberOfRectangles(int number) {
        while (number > 0) {
            int xPos = random(1, 31);
            int yPos = random(1, 16);
            generateRectangleShapeAndDrawIt(random(1, 31 - xPos), random(1, 16 - yPos), xPos, yPos);
            number--;
        }
    }

    private static void drawLine(int ballPlaces) {
        while (ballPlaces > 0) {
            if (!onBall()) {
                putBall();
            }
            step();
            ballPlaces--;
        }
    }
}
