package nl.ru.ai.exercise4;

import java.util.ArrayList;


public class Exercise4 {
    private static int nrOfFails = 0;
    private static int nrOfPrunes = 0;


    public static void main(String[] args) {
        //Exercise 1.a,b,c)
        System.out.println("Exercise 1 \n");
        ArrayList<Integer> solutions = new ArrayList<Integer>();
        System.out.println("the possible solutions for purse 1 are:");
        int[] money0 = {};
        int target0 = 0;
        showSolutions(solutions, money0, target0);
        /*
        The number of fails we have in money0 is 0 because there is no money in the array. We only have
        one solution which is the empty set and there is no possibility for a coin to fail because
        there are no coins.
        fails/prunes: 0/0
         */
        nrOfFails = 0;
        nrOfPrunes = 0;

        System.out.println("the possible solutions for purse 2 are:");
        int[] money1 = {2, 2, 2, 5, 10, 10, 20};
        int target1 = 1;
        showSolutions(solutions, money1, target1);
        /*
        The number of fails we have in money1 is 8 because we check 7 times whether the target is smaller
        than the coin and 7 times the coins are bigger than the target value. The 8th fail is added when
        we check whether c is bigger than the amount of coins we have.
        fails/prunes: 0/1
         */
        nrOfFails = 0;
        nrOfPrunes = 0;

        System.out.println("the possible solutions for purse 3 are:");
        int[] money2 = {20, 10, 10, 5, 2, 2, 2};
        int target2 = 42;
        showSolutions(solutions, money2, target2);
        /*
        We have 114 fails, mostly they occur because we have to check whether c is bigger than the amount
        of coins very much.
        fails/prunes: 2/3
         */
        nrOfFails = 0;
        nrOfPrunes = 0;

        System.out.println("the possible solutions for purse 4 are:");
        int[] money3 = {20, 50, 1000, 1000, 2000};
        int target3 = 2021;
        showSolutions(solutions, money3, target3);
        nrOfFails = 0;
        nrOfPrunes = 0;
        /*
        We check for every coin whether there are ways to pay the target, but there is no possibility so we
        fail nearly 2^5 times, 30 times
        fails/prunes: 0/14
         */
        //Exercise 1.d
        /*
        When we are pruning we are not traversing a path we beforehand know is wrong. Thus we are reducing the
        amount fails drastically.
         */

        //Exercise 2
        System.out.println("Exercise 2 \n");
        int[] persons = {15, 24, 32, 40, 50, 60, 72, 80, 90};
        int limit = 250;
        ArrayList<Integer> s = new ArrayList<Integer>();

        System.out.println("number of solutions: " + lift(persons, 0, limit, s, 0));
        System.out.println("number of fails: " + nrOfFails);
        System.out.println("number of prunes: " + nrOfPrunes);
        System.out.println("\n");


        //Exercise 3
        System.out.println("Exercise 3");
        System.out.println("\n");

        int[] puzzle = {5, 3, 0, 0, 7, 0, 0, 0, 0,
                6, 0, 0, 1, 9, 5, 0, 0, 0,
                9, 8, 0, 0, 0, 0, 0, 6, 0,
                8, 0, 0, 0, 6, 0, 0, 0, 3,
                4, 0, 0, 8, 0, 3, 0, 0, 1,
                7, 0, 0, 0, 2, 0, 0, 0, 6,
                0, 6, 0, 0, 0, 0, 2, 8, 0,
                0, 0, 0, 4, 1, 9, 0, 0, 5,
                0, 0, 0, 0, 8, 0, 0, 7, 9};

        //solve(puzzle);
        //dump(puzzle);
        //System.out.print(findFirstFreePosition(puzzle));
        //System.out.println(isValid(puzzle));
        //solve(puzzle);
        SudokuGui.drawSudoku(puzzle);
    }

    /**
     * outprints solutions and amount of fails and prunes
     * @param solutions
     * @param money
     * @param target
     */
    private static void showSolutions(ArrayList<Integer> solutions, int[] money, int target) {
        System.out.println("number of solutions: " + solutions(money, 0, target, solutions));
        System.out.println("number of fails:     " + nrOfFails);
        System.out.println("number of prunes:    " + nrOfPrunes + "\n");
    }

    /**
     * Returns the number of ways of creating specified target value as a sum of money starting with c
     *
     * @param money  array with coins
     * @param c      index of coin
     * @param target value which remains to pay
     * @return number of ways
     */
    private static int solutions(int[] money, int c, int target, ArrayList<Integer> s) {
        assert money != null : "array should be initialized";
        assert c >= 0 && c <= money.length;
        if (target == 0) {
            showSolution(s);
            return 1;
        }
        if (target < 0) {
            nrOfFails++;
            return 0;
        }
        if (c >= money.length) {
            nrOfFails++;
            return 0;
        }
        if (sumTooSmall(money, target, c)) {
            nrOfPrunes++;
            return 0;
        }
        if (numberTooBig(money, target, c)) {
            nrOfPrunes++;
            return 0;
        } else {
            s.add(money[c]);
            int with = solutions(money, c + 1, target - money[c], s);
            s.remove(s.size() - 1);
            int without = solutions(money, c + 1, target, s);
            return with + without;
        }
    }

    /**
     * returns whether the smallest number in the array is bigger than the target
     *
     * @param money  array with coins
     * @param target amount of money to pay
     * @param c      index of coin
     * @return
     */
    private static boolean numberTooBig(int[] money, int target, int c) {
        int smallestNum;
        if (money[c] < money[money.length - 1]) {
            smallestNum = money[c];
        } else smallestNum = money[money.length - 1];
        return smallestNum > target;
    }

    /**
     * checks whether the sum of untried coins is smaller then the target
     *
     * @param money  array with coins
     * @param target value we still need to pay
     * @param c      the index of coin
     * @return true if sum if too small to pay the target
     */
    private static boolean sumTooSmall(int[] money, int target, int c) {
        assert money != null : "array must be initialized";
        assert c >= 0 : "index must be bigger then zero";
        return target > sum(money, c);
    }

    /**
     * calculates the sum of all coins in the array from index c onwards
     *
     * @param money array with coins
     * @param c     index of coin
     * @return the sum of all coins in array money from c onwards
     */
    private static int sum(int[] money, int c) {
        int sum = 0;
        for (int i = c; i < money.length; i++) {
            sum += money[i];
        }
        return sum;
    }


    /**
     * shows the current solution
     *
     * @param s the arrayList with the possible solutions
     */
    private static void showSolution(ArrayList<Integer> s) {
        assert s != null : "arrayList must be initialized";
        for (Integer integer : s) {
            System.out.print(integer + " ");
        }
        System.out.println("\n");
    }

    /**
     * @param persons array with persons
     * @param p       index of person
     * @param limit   the maximum load
     * @param l       arrayList with possible combinations for the lift
     * @return amount of possible combinations
     */
    private static int lift(int[] persons, int p, int limit, ArrayList<Integer> l, int passengers) {
        assert persons != null : "array should be initialized";
        assert p >= 0 && p <= persons.length;

        if (limit >= 0 && passengers == 6) {
            showSolution(l);
            return 1;
        }
        if (limit < 0) {
            nrOfFails++;
            return 0;
        }
        if (p >= persons.length) {
            nrOfFails++;
            return 0;
        }
        if (sumTooSmall(persons, limit, p)) {
            nrOfPrunes++;
            return 0;
        }
        if (numberTooBig(persons, limit, p)) {
            nrOfPrunes++;
            return 0;
        } else {
            l.add(persons[p]);
            int with = lift(persons, p + 1, limit - persons[p], l, passengers + 1);
            l.remove(l.size() - 1);
            int without = lift(persons, p + 1, limit, l, passengers);
            return with + without;
        }
    }

    /**
     * solves a given sudoku and prints the solved sudoku
     *
     * @param puzzle array with the sudoku coded inside
     */
    static void solve(int[] puzzle) {
        int free = findFirstFreePosition(puzzle);
        if (free == puzzle.length)
            dump(puzzle);
        else
            for (int digit = 1; digit <= 9; digit++) {
                puzzle[free] = digit; // prepare
                if (isValid(puzzle)) {
                    solve(puzzle); // recurse
                }
                puzzle[free] = 0; // repair
            }
    }

    /**
     * @param puzzle array with the sudoku coded inside
     * @return
     */
    private static boolean isValid(int[] puzzle) {

        for (int row = 0; row <= 72; row += 9) {
            for (int col = row; col < row+8; col++) {
                if (puzzle[row] == puzzle[col]) {
                    return false;
                }
            }
        }
        for (int col = 0; col <= 8; col++) {
            for (int row = 9; row <= 72; row += 9) {
                if (puzzle[col] == puzzle[row]) {
                    return false;
                }
            }
        }

        for (int row = 18; row >= 9; row -= 9) {
            if (puzzle[0] == puzzle[row + 1] || puzzle[0] == puzzle[row + 2]
                    || puzzle[0] == puzzle[2]) {
                return false;
            }
        }
        if(puzzle[0]==puzzle[10]||puzzle[0]==puzzle[11]||puzzle[0]==puzzle[18]){

        }

        return true;
    }

    /**
     * @param puzzle the array with the sudoku coded inside
     * @return
     */
    private static int findFirstFreePosition(int[] puzzle) {
        int freePos = 81;
        for (int i = 0; i < puzzle.length; i++) {
            if (puzzle[i] == 0) {
                freePos = i;
                break;
            }
        }
        return freePos;
    }

    /**
     * outprints the array
     *
     * @param puzzle the array with the sudoku coded inside
     */
    private static void dump(int[] puzzle) {
        System.out.println("the solved Sudoku: \n");
        for (int i = 0; i <= 72; i += 9) {
            System.out.print(puzzle[i] + " ");
            System.out.print(puzzle[i + 1] + " ");
            System.out.print(puzzle[i + 2] + " ");
            System.out.print(puzzle[i + 3] + " ");
            System.out.print(puzzle[i + 4] + " ");
            System.out.print(puzzle[i + 5] + " ");
            System.out.print(puzzle[i + 6] + " ");
            System.out.print(puzzle[i + 7] + " ");
            System.out.print(puzzle[i + 8] + " ");
            System.out.print("\n");
        }
    }
}
