package nl.ru.ai.exercise0;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Exercise0 {
  private static final int MAX_NR_OF_TRACKS = 5000;

  /**
   * Program main entry point
   * 
   * @param args arguments to the program (ignored)
   */
  public static void main(String[] args) {
    // Part 1A
    // Length test1 = new Length();
    // test1.minutes = 2;
    // test1.seconds = 40;

    // Length test2 = new Length();
    // test2.minutes = 5;
    // test2.seconds = 10;

    // Length result = add(test1, test2);

    // System.out.println(result.minutes + " " + result.seconds);

    // System.out.println(toString(result));

    // Part 1B
    Track[] database = new Track[MAX_NR_OF_TRACKS];
    int nrOfTracksInDB = readDatabase(database);
    System.out.println(nrOfTracksInDB + " tracks read");

    // Part 2
    consoleInterface(database, nrOfTracksInDB);
  }

  /**
   * Requests command and query from user, and proceeds with commands accordingly
   * 
   * @param database
   */
  private static void consoleInterface(Track[] database, int nrOfTracks) {
    assert database != null : "Database must be initizialized";
    assert database[0] != null : "Database must have at least one entry in it";

    Scanner scanner = new Scanner(System.in);

    boolean cont = true;

    while (cont) {
      System.out.println("Please enter one of the following commands: ");
      System.out.println("track (string) - Find every track of which the string matches track.");
      System.out.println("artist (string) - Find every artist whose string matches artist. ");
      System.out.println("cds (string) - Find every CD title of artists of which the artist’s string matches artist.");
      System.out.println("#cds - Display the total number of CDs.");
      System.out.println("time - Display the total running time of all tracks.");
      System.out.println("stop - Terminate the program.");

      String[] commandArr = scanner.nextLine().split(" ");

      String command = getCommand(commandArr);
      String query = getQuery(commandArr);

      cont = commandParser(command, query, database, nrOfTracks);
    }

    scanner.close();
  }

  /**
   * Handles command and query inputs and passes them to the correct functions
   * 
   * @param command
   * @param query
   */
  private static boolean commandParser(String command, String query, Track[] database, int nrOfTracks) {
    assert database != null : "Database must be initzialized";
    assert command != null : "Command must be initzialized";
    assert query != null : "Query must be initzialized";

    switch (command) {
      case "track":
        trackCommand(query, database, nrOfTracks);
        break;
      case "artist":
        artistCommand(query, database, nrOfTracks);
        break;
      case "cds":
        cdsCommand(query, database, nrOfTracks);
        break;
      case "#cds":
        hashtagCDsCommand(database, nrOfTracks);
        break;
      case "time":
        timeCommand(database, nrOfTracks);
        break;
      case "stop":
        return false;
      default:
        System.out.println("Please enter a valid command!");
        break;
    }
    System.out.println("\n");
    return true;
  }

  private static void timeCommand(Track[] database, int nrOfTracks) {
    assert database != null : "Database must be initzialized";
    assert nrOfTracks > 0 : "There has to be at least one track in the database";

    Length totalTime = database[0].time;

    for (int i = 1; i < nrOfTracks; i++) {
      totalTime = add(totalTime, database[i].time);
    }

    System.out.println(toString(totalTime));
  }

  /**
   * Display the total number of CDs
   * 
   * @param query
   * @param database
   * @param nrOfTracks
   */
  private static void hashtagCDsCommand(Track[] database, int nrOfTracks) {
    assert database != null : "Database must be initzialized";
    assert nrOfTracks > 0 : "There has to be at least one track in the database";

    String[] allCds = new String[nrOfTracks];

    for (int i = 0; i < nrOfTracks; i++) {
      allCds[i] = database[i].cd;
    }

    String[] noDupes = removeDuplicates(allCds, nrOfTracks);

    System.out.println(noDupes.length);
  }

  /**
   * Find every CD title of artists of which the artist’s string matches artist.
   * Display the artist name, CD title, and year of recording. Each CD title is
   * displayed at most once. Terminate the output with the number of found CD
   * titles.
   * 
   * @param query
   * @param database
   * @param nrOfTracks
   */
  private static void cdsCommand(String query, Track[] database, int nrOfTracks) {
    assert database != null : "Database must be initzialized";
    assert query != null : "Query must be initzialized";
    assert nrOfTracks > 0 : "There has to be at least one track in the database";

    int noOfFoundCDs = 0;
    String[] matchingCDs = new String[nrOfTracks];

    for (int i = 0; i < nrOfTracks; i++) {
      Track currentTrack = database[i];
      if (currentTrack.artist.toLowerCase().indexOf(query.toLowerCase()) != -1) {
        matchingCDs[noOfFoundCDs] = currentTrack.cd;
        noOfFoundCDs++;
      }
    }

    String[] noDupes = removeDuplicates(matchingCDs, noOfFoundCDs);

    for (int k = 0; k < noDupes.length; k++) {
      System.out.println((k + 1) + ": " + noDupes[k]);
    }

    System.out.println("\n" + noDupes.length + " maching cd(s) where found.");

  }

  /**
   * Find every artist whose string matches artist. Display the artist. Each
   * artist is displayed at most once. At the end, show the number of found
   * artists
   * 
   * @param query
   * @param database
   * @param nrOfTracks
   */
  private static void artistCommand(String query, Track[] database, int nrOfTracks) {
    assert database != null : "Database must be initzialized";
    assert query != null : "Query must be initzialized";
    assert nrOfTracks > 0 : "There has to be at least one track in the database";

    int noOfFoundArtists = 0;
    String[] matchingArtists = new String[nrOfTracks];

    for (int i = 0; i < nrOfTracks; i++) {
      Track currentTrack = database[i];
      if (currentTrack.artist.toLowerCase().indexOf(query.toLowerCase()) != -1) {
        matchingArtists[noOfFoundArtists] = currentTrack.artist;
        noOfFoundArtists++;
      }
    }

    String[] noDupes = removeDuplicates(matchingArtists, noOfFoundArtists);

    for (int k = 0; k < noDupes.length; k++) {
      System.out.println((k + 1) + ": " + noDupes[k]);
    }

    System.out.println("\n" + noDupes.length + " maching artist(s) where found.");
  }

  /**
   * Removes duplicates from a string array
   * 
   * @param originalArray
   * @param arrayLen
   * @return
   */
  private static String[] removeDuplicates(String[] originalArray, int arrayLen) {
    assert arrayLen < 1 : "Array must have at least one element";
    assert originalArray != null : "Array must be initizalized";

    String allNames = originalArray[0];

    for (int i = 0; i < arrayLen; i++) {
      if (allNames.indexOf(originalArray[i]) == -1) {
        allNames += "<>" + originalArray[i];
      }
    }

    String noDupeArray[] = new String[allNames.split("<>").length];
    noDupeArray = allNames.split("<>");

    return noDupeArray;
  }

  /**
   * Find every track of which the string matches track. In this and following
   * exercises matching means that it occurs somewhere in the string. Display the
   * artist, CD title, year of recording, track number, track title, track length,
   * tags, and country. At the end, show the number of found tracks. assert
   * nrOfTracks > 0: "There has to be at least one track in the database";
   * 
   * @param query
   * @param database
   */
  private static void trackCommand(String query, Track[] database, int nrOfTracks) {
    assert database != null : "Database must be initzialized";
    assert query != null : "Query must be initzialized";
    assert nrOfTracks > 0 : "There has to be at least one track in the database";

    int noOfFoundTracks = 0;
    Track[] matchingTracks = new Track[nrOfTracks];

    for (int i = 0; i < nrOfTracks; i++) {

      String trackname = database[i].title;

      if (trackname.toLowerCase().indexOf(query.toLowerCase()) != -1) {
        matchingTracks[noOfFoundTracks] = database[i];
        noOfFoundTracks++;
      }

    }

    for (int j = 0; j < noOfFoundTracks; j++) {
      Track currentTrack = matchingTracks[j];
      System.out.println((j + 1) + ": " + currentTrack.artist + ", " + currentTrack.title + ", " + currentTrack.year
          + ", " + currentTrack.track + ", " + currentTrack.cd + ", " + toString(currentTrack.time) + ", "
          + currentTrack.tags + ", " + currentTrack.country);
    }

    System.out.println("\n" + "There were " + noOfFoundTracks + " matching track(s)");

  }

  /**
   * Get command from commandArray
   * 
   * @param commandArr
   * @return
   */
  private static String getCommand(String[] commandArr) {
    assert commandArr != null : "Please supply valid command array";
    return commandArr[0];
  }

  /**
   * Get query from command array
   * 
   * @param commandArr
   * @return returns corrosponding query for command, even if the query is empty
   */
  private static String getQuery(String[] commandArr) {
    assert commandArr != null : "Please supply valid command array";
    String query = "";

    if (commandArr.length == 2) {
      query = commandArr[1];
    } else if (commandArr.length > 2) {
      query = commandArr[1];
      for (int i = 2; i < commandArr.length; i++) {
        query += " " + commandArr[i];
      }
    }

    return query;
  }

  /**
   * Calculate the sum of the specified length arguments
   * 
   * @param a
   * @param b
   * @return sum
   */
  static Length add(Length a, Length b) {

    int minutes = a.minutes + b.minutes;
    int allSeconds = a.seconds + b.seconds;

    int leftOverseconds = allSeconds % 60;
    int extraMinutes = (int) Math.floor(allSeconds / 60);

    minutes += extraMinutes;

    Length totalLength = new Length();

    totalLength.minutes = minutes;
    totalLength.seconds = leftOverseconds;

    return totalLength;
  }

  /**
   * Converts the given string in the format m..m:ss to a Length object
   * 
   * @param string
   * @return corresponding length object
   */
  static Length fromString(String string) {
    // your code here
    Length stringToLength = new Length();

    stringToLength.minutes = Integer.parseInt(string.split(":")[0]);
    stringToLength.seconds = Integer.parseInt(string.split(":")[1]);

    return stringToLength;
  }

  /**
   * Converts a given length object into a string in the format m..m:ss
   * 
   * @param length
   * @return string representation
   */
  static String toString(Length length) {
    // your code here
    return String.format("%02d:%02d", length.minutes, length.seconds);
  }

  /**
   * Reads the cd database from the file 'Nummers.txt' into the specified track
   * array
   * 
   * @param database
   * @return number of tracks read
   */
  static int readDatabase(Track[] database) {
    assert database != null : "Database must be initzialized";
    // your code here
    int c = 0;
    try {
      File songFile = new File("songs.txt");

      Scanner scanner = new Scanner(songFile);

      while (scanner.hasNext()) {
        database[c] = new Track();
        database[c].artist = scanner.nextLine();
        database[c].cd = scanner.nextLine();
        database[c].year = Integer.parseInt(scanner.nextLine());
        database[c].track = Integer.parseInt(scanner.nextLine());
        database[c].title = scanner.nextLine();
        database[c].tags = scanner.nextLine();
        database[c].time = fromString(scanner.nextLine());
        database[c].country = scanner.nextLine();
        c++;
      }

      scanner.close();

    } catch (FileNotFoundException exception) {

      System.out.println(exception.getMessage());

    }

    return c;
  }
}
