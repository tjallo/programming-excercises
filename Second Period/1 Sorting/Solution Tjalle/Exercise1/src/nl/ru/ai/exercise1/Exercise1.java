package nl.ru.ai.exercise1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/*

Part 3 counting comparisons:
Bubble sort ran the compare function 5771911 times.

Insertion sort ran the compare function 4758 times.

Selection sort ran the compare function 27018 times.

So it seems that for this type of sorting Insertion sort is by far the fastest, 
Selection sort is about 5.7X slower than Insertion sort in this case,
and Bubble sort is way slower than both of the aforementioned sorting algorithms (About 1213X slower...)

*/

public class Exercise1 {
  private static final String DATABASE_FILENAME = "songs.txt";
  public static int compareToCounter = 0;

  /*
   * Here we go
   */
  public static void main(String[] args) {
    try {
      ArrayList<Track> database = new ArrayList<Track>();
      /*
       * Read database
       */
      readDatabase(database);
      System.out.printf("%d songs read from datatabase '%s'\n", database.size(), DATABASE_FILENAME);

      /*
       * Ask for sorting method
       */
      Scanner input = new Scanner(System.in);
      SortingMethod method = askForSortingMethod(input);
      /*
       * Sort
       */
      System.out.printf("Sorting with %s\n", method);
      switch (method) {
        case BUBBLE_SORT:
          bubbleSort(database);
          break;
        case INSERTION_SORT:
          insertionSort(database);
          break;
        case SELECTION_SORT:
          selectionSort(database);
          break;
      }
      System.out.println("Sorted!");
      /*
       * Show result
       */
      // dumpDatabase(database);
      System.out.println("The compare to function was ran " + compareToCounter + " times");

    } catch (FileNotFoundException exception) {
      System.out.printf("Error opening database file '%s': file not found\n", DATABASE_FILENAME);
    }
  }

  /**
   * Prints out the sorted database
   * 
   * @param database
   */
  private static void dumpDatabase(ArrayList<Track> database) {
    assert database != null : "Database must be initzialized";
    assert database.size() != 0 : "Database must have at least one entry";

    for (int i = database.size(); i < database.size(); i++) {
      System.out.printf("%-26s %-32s %4d %s\n", database.get(i).artist, database.get(i).cd, database.get(i).track,
          database.get(i).time);
    }
  }

  /**
   * Ask the user for a sorting method
   * 
   * @param input scanner used for asking
   * @return sorting method
   */
  private static SortingMethod askForSortingMethod(Scanner input) {
    /*
     * Show possible sorting methods
     */
    for (SortingMethod method : SortingMethod.values())
      System.out.printf("%d : %s\n", method.ordinal(), method);
    /*
     * Loop until valid choice
     */
    SortingMethod choice = null;
    while (choice == null) {
      System.out.println("Enter choice: ");
      int selection = input.nextInt();
      if (selection >= 0 && selection < SortingMethod.values().length)
        choice = SortingMethod.values()[selection];
      else
        System.out.println("Invalid choice, try again!");
    }
    return choice;
  }

  /**
   * Reads the cd database from the file 'songs.txt' into the specified track
   * array
   * 
   * @param database this is the database that will be filled with the input.
   * @return number of tracks read
   * @throws FileNotFoundException
   */
  static void readDatabase(ArrayList<Track> database) throws FileNotFoundException {
    assert database != null : "Database must be initzialized";
    assert database.size() != 0 : "Database must have at least one entry";

    FileInputStream inputStream = new FileInputStream(DATABASE_FILENAME);
    Scanner scanner = new Scanner(inputStream);
    while (scanner.hasNext()) {
      Track track = new Track();
      track.artist = scanner.nextLine();
      track.cd = scanner.nextLine();
      track.year = scanner.nextInt();
      scanner.nextLine();
      track.track = scanner.nextInt();
      scanner.nextLine();
      track.title = scanner.nextLine();
      track.tags = scanner.nextLine();
      track.time = new Length(scanner.nextLine());
      track.country = scanner.nextLine();
      database.add(track);
    }
    scanner.close();
  }

  /*************** Auxiliary array routines from lecture ***************/
  /**
   * Checks if the slice of the specified array is sorted
   * 
   * @param array
   * @param slice
   * @return true if the slice of the array is in ascending order, false otherwise
   */
  static <T extends Comparable<T>> boolean isSorted(ArrayList<T> array, Slice slice) {
    assert array != null : "Array should be initialized";
    assert slice.isValid() : "Slice should be valid";
    for (int i = slice.from; i < slice.upto - 1; i++)
      if (array.get(i).compareTo(array.get(i + 1)) > 0) {
        compareToCounter++;
        return false;
      }
    return true;
  }

  /**
   * Find position in array slice where to insert new element
   * 
   * @param array
   * @param slice
   * @param y     element for which the position should be returned
   * @return position where to insert
   */
  static <T extends Comparable<T>> int findInsertPosition(ArrayList<T> array, Slice slice, T y) {
    assert array != null : "Array should be initialized";
    assert slice.isValid() : "Slice should be valid";
    assert isSorted(array, slice);
    for (int i = slice.from; i < slice.upto; i++)
      if (array.get(i).compareTo(y) >= 0) {
        compareToCounter++;
        return i;
      }
    return slice.upto;
  }

  /**
   * Shifts all elements in the slice one position to the right
   * 
   * @param array
   * @param slice
   */
  static <T extends Comparable<T>> void shiftRight(ArrayList<T> array, Slice slice) {
    assert array != null : "Array should be initialized";
    assert slice.isValid() && slice.from < array.size() : "Slice should be valid";
    for (int i = slice.upto; i > slice.from; i--)
      array.set(i, array.get(i - 1));
  }

  /**
   * Insert an element to a sorted array and keep it sorted
   * 
   * @param array
   * @param length length
   * @param y      element to be added
   * @return new length
   */
  static <T extends Comparable<T>> int insert(ArrayList<T> array, int length, T y) {
    assert array != null : "Array should be initialized";
    assert length >= 0 : "Length cannot be negative";
    assert isSorted(array, new Slice(0, length)) : "Array should be sorted";
    int position = findInsertPosition(array, new Slice(0, length), y);
    shiftRight(array, new Slice(position, length));
    array.set(position, y);
    return length + 1;
  }

  /**
   * Swap two elements in an array
   * 
   * @param array
   * @param i
   * @param j
   */
  private static <T extends Comparable<T>> void swap(ArrayList<T> array, int i, int j) {
    assert array != null : "Array should be initialized";
    assert i >= 0 && i < array.size() : "First index is invalid";
    assert j >= 0 && j < array.size() : "Second index is invalid";
    T help = array.get(i);
    array.set(i, array.get(j));
    array.set(j, help);
  }

  /*************** Array based Sorting routines from lecture ***************/
  /**
   * Sorts an array in situ in ascending order using selection sort
   * 
   * @param array
   * @oaram length
   */
  static <T extends Comparable<T>> void selectionSort(ArrayList<T> array) {
    assert array != null : "array should be initialized";
    for (int i = 0; i < array.size(); i++) {
      int j = indexOfSmallestValue(array, new Slice(i, array.size()));
      swap(array, i, j);
    }
  }

  /**
   * Finds index of smallest value in array slice
   * 
   * @param array
   * @param slice
   * @return index of smallest value
   */
  static <T extends Comparable<T>> int indexOfSmallestValue(ArrayList<T> array, Slice slice) {
    assert array != null : "Array should be initialized";
    assert slice.isValid() && slice.upto <= array.size() : "Slice should be valid";
    assert slice.upto - slice.from > 0 : "Slice should be non-empty";
    int index = slice.from;
    for (int i = slice.from + 1; i < slice.upto; i++)
      if (array.get(i).compareTo(array.get(index)) < 0) {
        compareToCounter++;
        index = i;
      }
    return index;
  }

  /**
   * Sorts an array in situ in ascending order using bubble sort
   * 
   * @param array
   *
   */
  static <T extends Comparable<T>> void bubbleSort(ArrayList<T> array) {
    assert array != null : "array should be initialized";
    int length = array.size();
    while (!bubble(array, new Slice(0, length)))
      length--;
  }

  /**
   * Swap all adjacent pairs in the array slice that are not in the right order
   * 
   * @param array
   * @param slice
   * @return array slice is sorted
   */
  static <T extends Comparable<T>> boolean bubble(ArrayList<T> array, Slice slice) {
    assert array != null : "Array should be initialized";
    assert slice.isValid() && slice.upto <= array.size() : "Slice should be valid";
    boolean isSorted = true;
    for (int i = slice.from; i < slice.upto - 1; i++)
      if (array.get(i).compareTo(array.get(i + 1)) > 0) {
        compareToCounter++;
        swap(array, i, i + 1);
        isSorted = false;
      }
    return isSorted;
  }

  /**
   * Sorts an array in situ in ascending order using insertion sort
   * 
   * @param array
   *
   */
  static <T extends Comparable<T>> void insertionSort(ArrayList<T> array) {
    assert array != null : "array should be initialized";
    for (int i = 0; i < array.size(); i++)
      insert(array, i, array.get(i));
  }
}
