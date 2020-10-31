package nl.ru.ai.exercise6;

import nl.ru.ai.gameoflife.Cell;
import static nl.ru.ai.gameoflife.Universe.*;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class GameOfLife {

  public static void main(String[] args) {
    // Part 1
    // Cell[][] uni = readUniverseFile("gosperglidergun.txt");
    // showUniverse(uni);

    // Part 2
    // Simulate with colour
    // simulateUniverse("gosperglidergun.txt");
    // Simulate without Colour
    // simulateUniverseWithColourAge("gosperglidergun.txt");

    // Part 3
    userMenu();
  }

  /**
   * Show a menu to enter universe settings
   */
  static void userMenu() {

    assert true;

    Scanner scanner = new Scanner(System.in);

    String configfile;
    int runAmount, delay, withColour;

    System.out.print("Enter the name of the config file you want to load: ");
    configfile = scanner.nextLine();

    System.out.print("Enter the number of times you want to generate a new population: ");
    runAmount = scanner.nextInt();

    System.out.print("Enter the delay (in miliseconds) you want beteween population generation: ");
    delay = scanner.nextInt();

    System.out.print("Do you want to simulate with colour for age (1) or without colour for age (0): ");
    withColour = scanner.nextInt();

    System.out.println();
    System.out.println("Running with the following configuration:");
    System.out.println("Configfile: " + configfile);
    System.out.println("No. of populations you want to simulate: " + runAmount);
    System.out.println("Delay between population generation (ms): " + delay);
    if (withColour == 1) {
      System.out.println("With colour");
    } else {
      System.out.println("Without colour");
    }
    System.out.println();

    scanner.close();
    customUniverse(configfile, runAmount, delay, withColour);

    System.out.println("Done.");

  }

  /**
   * Allows the user to run a universe simulation with custom settings.
   * 
   * @param configfile
   * @param runAmount
   * @param delay
   */
  static void customUniverse(String configfile, int runAmount, int delay, int withColour) {

    assert configfile != null : "You should enter a valid configfile name";
    assert delay > 0 : "Delay should be a postivie integer";
    assert runAmount > 0 : "The amount of times a new generation has to be generated should be more than 0";
    assert withColour != 1 || withColour != 0 : "The colour config has to be either a one or a zero";

    Cell[][] universe = readUniverseFile(configfile);

    if (withColour == 1) {
      int[][] ageTable = createAgeTableWithZeroes();
      ageTable = determineAgeTable(ageTable, universe);

      for (int i = 0; i < runAmount; i++) {
        showUniverseWithAgeColouring(universe, ageTable);
        universe = nextGeneration(universe);
        ageTable = determineAgeTable(ageTable, universe);
        sleep(delay);
      }
    } else {
      for (int i = 0; i < runAmount; i++) {
        showUniverse(universe);
        universe = nextGeneration(universe);
        sleep(delay);
      }
    }

  }

  /**
   * Simulate the universe using text file
   * 
   * @param fileName
   */
  static void simulateUniverse(String fileName) {
    assert fileName != null : "Enter a valid filename";

    Cell[][] uni = readUniverseFile(fileName);

    while (true) {
      showUniverse(uni);
      uni = nextGeneration(uni);
      sleep(100);
    }
  }

  /**
   * same as Simulate universe but with added colour functionality
   * @param fileName
   */
  static void simulateUniverseWithColourAge(String fileName) {
    assert fileName != null : "Enter a valid filename";
    int[][] ageTable = createAgeTableWithZeroes();

    Cell[][] uni = readUniverseFile(fileName);
    ageTable = determineAgeTable(ageTable, uni);

    while (true) {
      showUniverseWithAgeColouring(uni, ageTable);
      uni = nextGeneration(uni);
      ageTable = determineAgeTable(ageTable, uni);
      sleep(100);
    }
  }

  /**
   * Initizialize a correct array to be used with ageTable
   * @return
   */
  static int[][] createAgeTableWithZeroes() {
    assert true;
    int[][] ageTable = new int[40][60];

    for (int row = 0; row < 40; row++) {
      for (int col = 0; col < 60; col++) {
        ageTable[row][col] = 0;
      }
    }

    return ageTable;
  }

  /**
   * Read a .txt and return a valid 2d array of the universe
   * 
   * @param fileName
   * @return 2d array of the universe
   */
  static Cell[][] readUniverseFile(String fileName) {

    assert fileName != null : "Enter a valid filename";

    // enter your code here

    Cell[][] result = new Cell[40][60];

    try {
      BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
      String line;

      int c = 0;
      while ((line = reader.readLine()) != null) {
        int lineLen = line.length();

        if (lineLen != 60) {
          System.out.println("The inputfile has a line which does not contain 60 characters");
          throw new IllegalArgumentException();
        }

        Cell[] cell = new Cell[lineLen];
        for (int i = 0; i < lineLen; i++) {
          char charAt = line.charAt(i);

          if (charAt == '.') {

            cell[i] = Cell.DEAD;

          } else if (charAt == '*') {

            if (i == 0 || i == 59) {

              System.out.println("The inputfile has a live cell in its border");
              throw new IllegalArgumentException();

            }

            cell[i] = Cell.LIVE;

          } else {
            System.out.println("The inputfile has an invalid character");
            throw new IllegalArgumentException();
          }
        }

        result[c] = cell;

        c++;
      }

      reader.close();

      if (c > 40) {
        System.out.println("The inputfile does not contain 40 lines");
        throw new IllegalArgumentException();
      }

    } catch (FileNotFoundException exception) {
      System.out.println("The inputfile does not exist");
      throw new IllegalArgumentException();
    } catch (IOException exception) {
      System.out.println("The input file has an invalid character in it");
      throw new IllegalArgumentException();
    }

    return result;

  }

  /**
   * Show the universe
   * 
   * @param universe
   */
  private static void showUniverse(Cell[][] universe) {
    assert universe != null : "Enter a valid universe";

    // enter your code here
    for (int row = 0; row < 40; row++) {
      for (int col = 0; col < 60; col++) {
        updateScreen(row, col, universe[row][col]);
      }
    }

  }

  /**
   * Determine colour for certain ages in agetable
   * @param age
   * @return
   */
  private static Color determineColour(int age) {
    switch (age) {
      case 0:
        return Color.ORANGE;
      case 1:
        return Color.BLUE;
      case 2:
        return Color.CYAN;
      case 3:
        return Color.GREEN;
      case 4:
        return Color.LIGHT_GRAY;
      case 5:
        return Color.GRAY;
      case 6:
        return Color.DARK_GRAY;
      default:
        return Color.BLACK;
    }
  }

  /**
   * same as showUniverse but with added functionality for age colouring
   * @param universe
   * @param ageTable
   */
  private static void showUniverseWithAgeColouring(Cell[][] universe, int[][] ageTable) {
    assert universe != null : "Enter a valid universe";

    // enter your code here
    for (int row = 0; row < 40; row++) {
      for (int col = 0; col < 60; col++) {
        Color color = determineColour(ageTable[row][col]);
        updateScreen(row, col, universe[row][col], color);
      }
    }

  }

  /**
   * Get an cell array of the surrounding neighbours coordinates
   * 
   * @param universe
   * @param row
   * @param col
   * @return The coordinates of the neighbours cells
   */
  private static Cell[] getNeighbours(Cell[][] universe, int row, int col) {
    assert row >= 0 : "Row must be zero or a postive integer";
    assert col >= 0 : "Col must be zero or a postive integer";

    Cell[] neighbours = new Cell[8];

    // Get relative postions of all neighbours
    int[][] neighboursPos = { { row - 1, col - 1 }, { row - 1, col }, { row - 1, col + 1 }, { row, col - 1 },
        { row, col + 1 }, { row + 1, col - 1 }, { row + 1, col }, { row + 1, col + 1 } };

    int cellCount = 0;

    for (int i = 0; i < neighboursPos.length; i++) {
      int cellRow = neighboursPos[i][0];
      int cellCol = neighboursPos[i][1];

      if (cellRow >= 0 && cellCol >= 0 && cellCol < 60 && cellRow < 40) {
        Cell cellVal = getCellValue(universe, cellRow, cellCol);
        neighbours[cellCount] = cellVal;
        cellCount++;
      }

    }

    return neighbours;
  }

  /**
   * Gets value of a Cell using universe and row and col
   * 
   * @param universe
   * @param row
   * @param col
   * @return Value of a cell
   */
  private static Cell getCellValue(Cell[][] universe, int row, int col) {
    assert row >= 0 : "Row must be zero or a postive integer";
    assert col >= 0 : "Col must be zero or a postive integer";

    return universe[row][col];

  }

  /**
   * Get amount of dead and alive from 2 dimensional array
   * 
   * @param neighbours
   * @return 1 dimensional array with 2 entries, int[0] will be amount of alive
   *         cells int[1] will be the amount of dead cells
   */
  private static int[] getDeadAliveCount(Cell[] neighbours) {
    assert neighbours != null : "Enter a valid neighbours array";

    int[] deadAliveCount = { 0, 0 };

    for (int i = 0; i < 8; i++) {
      Cell currentCell = neighbours[i];

      if (currentCell == Cell.LIVE) {

        deadAliveCount[0]++;

      } else if (currentCell == Cell.DEAD) {

        deadAliveCount[1]++;

      }
    }

    return deadAliveCount;

  }

  /**
   * Determin what the value of the cell has to be in the next round
   * 
   * @param cell
   * @param neighbours
   * @return the value of the next cell
   */
  private static Cell determineNextCell(Cell cell, Cell[] neighbours) {
    assert neighbours != null : "Enter a valid neighbours array";

    int[] deadAliveCount = getDeadAliveCount(neighbours);

    int aliveCount = deadAliveCount[0];

    if (aliveCount < 2 || aliveCount > 3) {
      cell = Cell.DEAD;
    } else if (aliveCount == 3) {
      cell = Cell.LIVE;
    }

    return cell;

  }

  /**
   * Creates a fully dead universe which can be used with the generation of the next universe
   * @return
   */
  private static Cell[][] createFullyDeadUniverse() {
    assert true;

    Cell[][] deadUniverse = new Cell[40][60];

    for (int row = 0; row < 40; row++) {
      for (int col = 0; col < 60; col++) {
        deadUniverse[row][col] = Cell.DEAD;
      }
    }

    return deadUniverse;

  }

  /**
   * Calculates the next generation of a universe
   * 
   * @param universe
   * @return next iteration of universe
   */
  private static Cell[][] nextGeneration(Cell[][] universe) {
    // enter your code here
    assert universe != null : "Enter a valid universe";

    Cell[][] newUniverse = createFullyDeadUniverse();

    for (int row = 1; row < 39; row++) {
      for (int col = 1; col < 59; col++) {
        Cell currentCell = universe[row][col];
        Cell[] neighbours = getNeighbours(universe, row, col);

        Cell nextCell = determineNextCell(currentCell, neighbours);

        newUniverse[row][col] = nextCell;
      }
    }

    return newUniverse;
  }

  /**
   * Determines the next generation of agetable using the old age table and the
   * new universe
   * 
   * @param ageTable
   * @param newUniverse
   * @return
   */
  private static int[][] determineAgeTable(int[][] ageTable, Cell[][] newUniverse) {
    assert ageTable != null : "ageTable must not be empty";
    assert ageTable.length > 0 : " ageTable must be initzialized";
    assert newUniverse != null : "newUniverse must not be empty";

    for (int row = 0; row < 40; row++) {
      for (int col = 0; col < 60; col++) {
        Cell currentNewCell = newUniverse[row][col];

        if (currentNewCell == Cell.DEAD) {
          ageTable[row][col] = 0;
        } else if (currentNewCell == Cell.LIVE) {
          ageTable[row][col]++;
        }

      }
    }

    return ageTable;

  }

}
