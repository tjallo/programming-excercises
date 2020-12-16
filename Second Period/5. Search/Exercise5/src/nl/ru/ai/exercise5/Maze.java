package nl.ru.ai.exercise5;

public class Maze {
    static char[][] maze = {
            {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
            {'x', ' ', 'x', ' ', ' ', ' ', ' ', ' ', 'x'},
            {'x', ' ', 'x', ' ', 'x', 'x', ' ', ' ', 'x'},
            {'x', ' ', 'x', ' ', 'x', 'x', 'x', ' ', 'x'},
            {'x', ' ', 'x', ' ', ' ', ' ', 'x', ' ', 'x'},
            {'x', ' ', 'x', ' ', 'x', ' ', ' ', ' ', 'x'},
            {'x', ' ', 'x', ' ', 'x', 'x', 'x', ' ', 'x'},
            {'x', ' ', 'x', ' ', ' ', ' ', 'x', ' ', 'x'},
            {'x', ' ', ' ', ' ', 'x', ' ', ' ', ' ', 'x'},
            {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'}};

    static boolean[][] beenHere = {
            {false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, true, false},
            {false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false}};

    static boolean hasRabbit(int row, int col) {
        return row == 1 && col == 1;
    }

    static boolean hasWall(int row, int col) {
        return maze[row][col] == 'x';
    }

    static void visited(int row, int col) {
        beenHere[row][col] = true;
    }

    static boolean hasVisited(int row, int col) {
        return beenHere[row][col];
    }
}
