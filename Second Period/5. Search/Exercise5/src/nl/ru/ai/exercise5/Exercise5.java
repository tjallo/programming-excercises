package nl.ru.ai.exercise5;

import static nl.ru.ai.exercise5.Maze.*;

import java.util.ArrayList;

public class Exercise5 {
    public static void main(String[] arguments) {
        //Exercise 1

        ArrayList<Candidate> candidates = new ArrayList<Candidate>();
        candidates.add(new Candidate(new Attempt(1, 7), -1));

        boolean found = false;
        int c = 0;
        solve(candidates, found, c);
        //showPath(candidates);
        System.out.println(candidates.get(0).attempt.col);
        System.out.println(candidates.get(0).attempt.row);

    }

    /**
     * finds the shortest way from the cow to the rabbit
     *
     * @param candidates  ArrayList with shortest path
     * @param found       boolean which indicates whether the rabbit is found
     * @param candidateNr number steps  the cow makes
     */
    private static void solve(ArrayList<Candidate> candidates, boolean found, int candidateNr) {
        assert candidates != null : "arrayList must be initialized";
        assert candidateNr >= 0 : "number must be at least 0";
        while (candidateNr < candidates.size() & !found) {
            Attempt attempt = candidates.get(candidateNr).attempt;
            if (solution(attempt)) {
                showPath(candidates, candidates.get(candidateNr).parentCandidate);
                found = true;
            } else {
                checkMaze(candidates, candidateNr, attempt);
                candidateNr++;
            }
        }
    }

    /**
     * checks in which directions the cow can go
     *
     * @param candidates  ArrayList with shortest path
     * @param candidateNr number steps the cow makes
     * @param attempt     coordinates of cow
     */
    private static void checkMaze(ArrayList<Candidate> candidates, int candidateNr, Attempt attempt) {
        assert candidates != null : "ArrayList must be initialized";
        assert candidateNr >= 0 : "number of steps must be bigger then 0";
        assert attempt != null : "attempt must be initialized";
        if (goNorth(attempt)) {
            add(candidates, candidateNr, north(attempt));
        }
        if (goSouth(attempt)) {
            add(candidates, candidateNr, South(attempt));
        }
        if (goWest(attempt)) {
            add(candidates, candidateNr, west(attempt));
        }
        if (goEast(attempt)) {
            add(candidates, candidateNr, east(attempt));
        }
    }

    /**
     * returns the attempt after moving to the east
     *
     * @param attempt coordinates of the attempt
     * @return the position of the attempt moved to the north
     */
    private static Attempt east(Attempt attempt) {
        assert attempt != null : "attempt must be initialized";
        return new Attempt(attempt.row, attempt.col += 1);
    }

    /**
     * returns the attempt after moving to the west
     *
     * @param attempt coordinates of the attempt
     * @return the position of the attempt moved to the north
     */
    private static Attempt west(Attempt attempt) {
        assert attempt != null : "attempt must be initialized";
        return new Attempt(attempt.row, attempt.col -= 1);
    }

    /**
     * returns the attempt after moving to the south
     *
     * @param attempt coordinates of the attempt
     * @return the position of the attempt moved to the north
     */
    private static Attempt South(Attempt attempt) {
        assert attempt != null : "attempt must be initialized";
        return new Attempt(attempt.row += 1, attempt.col);
    }

    /**
     * returns the attempt after moving to the north
     *
     * @param attempt coordinates of the attempt
     * @return the position of the attempt moved to the north
     */
    private static Attempt north(Attempt attempt) {
        assert attempt != null : "attempt must be initialized";
        return new Attempt(attempt.row -= 1, attempt.col);
    }

    /**
     * adds the position of the cow when going to the south to the ArrayList
     *
     * @param candidates  ArrayList with shortest path
     * @param candidateNr number steps the cow makes
     * @param next        position next attempt
     */
    private static void add(ArrayList<Candidate> candidates, int candidateNr, Attempt next) {
        assert candidates != null : "arrayList must be initialized";
        assert candidateNr >= 0 : "candidate number must be at least 0";
        assert next != null : "attempt must be initialized";
        Candidate n = new Candidate(next, candidateNr);
        if (!hasVisited(n.attempt.row, n.attempt.col)) {
            visited(n.attempt.row, n.attempt.col);
            candidates.add(n);
        }
    }


    /**
     * checks whether the cow is able to go west
     *
     * @param attempt attempt
     * @return possibility to go east
     */
    private static boolean goEast(Attempt attempt) {
        assert attempt != null : "attempt must be initialized";
        return !hasWall(attempt.row, attempt.col + 1) && !beenHere[attempt.row][attempt.col + 1];
    }

    /**
     * checks whether the cow is able to go west
     *
     * @param attempt attempt
     * @return possibility to go west
     */
    private static boolean goWest(Attempt attempt) {
        assert attempt != null : "attempt must be initialized";
        return !hasWall(attempt.row, attempt.col - 1) && !beenHere[attempt.row][attempt.col - 1];
    }

    /**
     * checks whether the cow is able to go to the south
     *
     * @param attempt attempt
     * @return possibility to go south
     */
    private static boolean goSouth(Attempt attempt) {
        assert attempt != null : "attempt must be initialized";
        return !hasWall(attempt.row + 1, attempt.col) && !beenHere[attempt.row + 1][attempt.col];
    }

    /**
     * checks whether the cow is able to go to the north
     *
     * @param attempt attempt
     * @return possibility to go north
     */
    private static boolean goNorth(Attempt attempt) {
        assert attempt != null : "attempt must be initialized";
        return !hasWall(attempt.row - 1, attempt.col) && !beenHere[attempt.row - 1][attempt.col];
    }

    /**
     * shows the path from the cow to the rabbit
     *
     * @param candidates ArrayList with the path to the rabbit
     * @param last       index of the last candidate
     */
    private static void showPath(ArrayList<Candidate> candidates, int last) {
        assert candidates != null : "arrayList must be initialized";
        if (last != -1) {
            showPath(candidates, last - 1);
            System.out.print(candidates.get(last).parentCandidate+"\n");
            System.out.print("row: " + candidates.get(last).attempt.row + " ");
            System.out.print("col: " + candidates.get(last).attempt.col+"\n");
        }
    }


    /**
     * returns whether the rabbit is found
     *
     * @param attempt the attempt
     * @return whether rabbit is on the current position
     */
    private static boolean solution(Attempt attempt) {
        assert attempt != null : "attempt must be initialized";
        return hasRabbit(attempt.row, attempt.col);
    }
}
