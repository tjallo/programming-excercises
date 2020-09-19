package nl.ru.ai.p4ai.exercise2;

import static nl.ru.ai.karel.Karel.*;

public class CatchThemAll {

    public static void main(String[] args) {
        map("pokemap.map");
        speed(100);
        searchPokemon(5);
    }

    private static void searchPokemon(int pokemon) {
        while (getNumberOfPokemon() < pokemon) {
            unevenRow();
            evenRow();

        }

    }

    private static void evenRow() {
        while (!inFrontOfWall()) {
            if (onBall()) {
                getBall();
            }
            if (onPokemon()) {
                if (getNumberOfBalls() > 0) {
                    getPokemon();
                }
            }
            step();
        }
        turnLeft();
        if (inFrontOfWall()) {
            turnLeft();
        }
        step();
        turnLeft();


    }

    private static void unevenRow() {
        while (!inFrontOfWall()) {
            if (onBall()) {
                getBall();
            }
            if (onPokemon()) {
                if (getNumberOfBalls() > 0) {
                    getPokemon();
                }
            }
            step();
        }
        turnRight();
        if (inFrontOfWall()) {
            turnRight();
        }
        step();
        turnRight();
    }

}
