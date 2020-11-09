package nl.ru.ai.exercise0;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Exercise0 {
    private static final int MAX_NR_OF_TRACKS = 5000;

    /**
     * Program main entry point
     *
     * @param args arguments to the program (ignored)
     */
    public static void main(String[] args) {
        //Exercise 1a)
/*      Length length1 = fromString("12:34");
        Length length2 = fromString("23:45");
        Length sum = add(length1, length2);
        System.out.println(toString(sum));

*/      // Exercise 1b)
        Track[] database = new Track[MAX_NR_OF_TRACKS];
        int nr = readDatabase(database);
        //System.out.println(nr + " tracks read");

        // Exercise 2)
        Scanner comScanner = new Scanner(System.in);
        console(database, comScanner, nr);
        //System.out.println(removeDuplicates(database, nr));
    }


    /**
     * Calculate the sum of the specified length arguments
     *
     * @param a Length of song a
     * @param b Length of song b
     * @return sum
     */
    static Length add(Length a, Length b) {
        assert a != null : "the song a has to have a length";
        assert b != null : "the song b has to have a length";
        Length added = new Length();
        int addedMinutes = a.minutes + b.minutes;
        int addedSeconds = a.seconds + b.seconds;
        while (addedSeconds >= 60) {
            addedMinutes++;
            addedSeconds -= 60;
        }

        added.minutes = addedMinutes;
        added.seconds = addedSeconds;
        return added;
    }

    /**
     * Converts the given string in the format m..m:ss to a Length object
     *
     * @param string the string which has to be converted into the type Track
     * @return corresponding length object
     */
    static Length fromString(String string) {
        assert string != null : "string cannot be null";
        Length toLength = new Length();
        int minutes;
        int seconds;
        String minutesStr;
        String secondsStr;
        minutesStr = string.substring(0, string.indexOf(':'));
        secondsStr = string.substring(string.indexOf(':') + 1).trim();
        minutes = Integer.parseInt(minutesStr);
        seconds = Integer.parseInt(secondsStr);
        toLength.minutes = minutes;
        toLength.seconds = seconds;
        return toLength;
    }

    /**
     * Converts a given length object into a string in the format mm:ss
     *
     * @param length length of a song given in type Length
     * @return string representation
     */
    static String toString(Length length) {
        assert length != null : "length cannot be null";
        int minutes = length.minutes;
        int seconds = length.seconds;
        String minutesStr = Integer.toString(minutes);
        String secondsStr = Integer.toString(seconds);
        String finalString = minutesStr + ":" + secondsStr;
        return finalString;
    }


    /**
     * Reads the cd database from the file 'songs.txt' and puts it into the specified track array
     *
     * @param database the array with the properties of the songs in it
     * @return number of tracks read
     */
    static int readDatabase(Track[] database) {
        assert database != null;
        Scanner scanner;
        int i = 0;
        try {
            scanner = new Scanner(new FileReader("songs.txt"));
            while (scanner.hasNext()) {
                database[i] = new Track();
                database[i].artist = scanner.nextLine();
                database[i].cd = scanner.nextLine();
                database[i].year = scanner.nextInt();
                scanner.nextLine();
                database[i].track = scanner.nextInt();
                scanner.nextLine();
                database[i].title = scanner.nextLine();
                database[i].tags = scanner.nextLine();
                database[i].time = fromString(scanner.nextLine());
                database[i].country = scanner.nextLine();
                i++;
            }
            scanner.close();
        } catch (FileNotFoundException exception) {
            System.out.println(exception.getMessage() + "something went wrong when opening the file");
        }
        int j = getDatabaseLength(database);

        return j;
    }

    /**
     * This function calculates hom much tracks are stored in the database
     *
     * @param database The array database
     * @return j   The index of the array and when returned the amount of tracks in the array. The array length
     */
    static int getDatabaseLength(Track[] database) {
        assert database != null;
        int j;
        for (j = 0; j < database.length; j++) {
            if (database[j] == null) {
                return j;
            }
        }
        return j;
    }

    /**
     * displays a console on which the user can type in commands and outputs the wanted components of the file "songs.txt"
     *
     * @param database   the array with the properties of the songs in it
     * @param comScanner the Scanner which is used to read the users input
     * @param nr         the amount of songs in the 'songs.txt' file
     */
    static void console(Track[] database, Scanner comScanner, int nr) {
        String name;
        String comAndName;
        String command;
        System.out.println("Write your command in the following way: \"command name\".");
        System.out.println("If your command does not contain any names write: \"command ''\"");
        do {
            System.out.println(">>");
            comAndName = comScanner.nextLine();
            command = comAndName.substring(0, comAndName.indexOf(' '));
            name = comAndName.substring(comAndName.indexOf(' ') + 1).trim();

            switch (command) {
                case "track":
                    System.out.println("following tracks found:");
                    displayTrack(searchTrack(database, name));
                    break;
                case "artist":
                    System.out.println("following artists found:");
                    displayArtist(searchArtist(database, name));
                    break;
                case "cds":
                    displayCds(database, name);
                    break;
                case "#cds":
                    displayTotalCdNum(database);
                    break;
                case "time":
                    addedTime(database, nr);
                    break;
                case "stop":
                    System.out.println("Thanks for using the CD analyzer!");
                    break;
                default:
                    System.out.println("The input could not be interpreted");
            }
        } while (!command.equals("stop"));
    }

    /**
     * displays the total amount of CD's in the file 'songs.txt'
     *
     * @param database
     */
    private static void displayTotalCdNum(Track[] database) {
        int len= database.length;
        len = removeDuplicates(database, len);
        System.out.println("the database contains "+len+ " cd's");
    }

    /**
     * displays the artists that contain the name put in in the console
     *
     * @param artists the array with the artists matching the search
     */
    static void displayArtist(Track[] artists) {

        int len = artists.length;
        len = removeDuplicates(artists, len);
        for (int a = 0; a < len - 1; a++) {
            System.out.println(artists[a].artist);
        }
    }

    /**
     * searches for artists which contain the input
     *
     * @param database the array with the properties of the songs in it
     * @param name     the name of the type
     * @return the array artists with all of the matching artists stored in it
     */
    static Track[] searchArtist(Track[] database, String name) {
        assert database != null : "array must be initialized";
        assert name != null : "type in the name of the artist";
        Track[] artists = new Track[MAX_NR_OF_TRACKS];
        int f = 0;
        int b = 0;
        while (database[f] != null) {
            if ((database[f].artist).contains(name)) {
                artists[b] = database[f];
                b++;
            }
            f++;
        }
        return artists;
    }

    /**
     * adds the time of all the tracks in the database
     *
     * @param database the array with the properties of the songs in it
     * @param nr       the number of songs in the file 'songs.txt'
     */
    static void addedTime(Track[] database, int nr) {
        Length previousAddedTime = fromString("00:00");
        int k;
        for (k = 0; k <= nr - 1; k++) {
            previousAddedTime = add(previousAddedTime, database[k].time);
        }
        System.out.println("The total playing time of the CD is "
                + previousAddedTime.minutes + ":" + previousAddedTime.seconds + " minutes or "
                + previousAddedTime.minutes / 60 + " hours and " + previousAddedTime.seconds + "seconds");
    }

    /**
     * displays the cds with the artist and the year of recording matching the search
     *
     * @param database the array with the properties of the songs in it
     * @param name     the name of the artist
     */
    static void displayCds(Track[] database, String name) {
        Track[] cds = searchArtist(database, name);
        int len = cds.length;
        len = removeDuplicates(cds, len);
        for (int i = 0; i < len - 1; i++) {
            System.out.println(cds[i].artist);
            System.out.println(cds[i].cd);
            System.out.println(cds[i].year);
            System.out.println('\n');
        }
        System.out.println();
    }

    /**
     * removes duplicates from a array and returns the size of the array without duplicates
     *
     * @param array     array from which the redundant
     * @param arrLength length of the array with the duplicates
     * @return the size of the array without the duplicates
     */
    static int removeDuplicates(Track[] array, int arrLength) {

        Track[] temp = new Track[arrLength];
        int j = 0;

        for (int i = 0; i < arrLength - 1; i++) {
            if (array[i] != array[i + 1]) {
                temp[j++] = array[i];
            }
        }

        temp[j++] = array[arrLength - 1];

        for (int i = 0; i < j; i++) {
            array[i] = temp[i];
        }
        return j;
    }

    /**
     * searches for tracks which contain the input name
     *
     * @param database
     * @param name
     * @return Track array with the found Tracks in it
     */
    static Track[] searchTrack(Track[] database, String name) {
        assert database != null : "array must be initialized";
        assert name != null : "type in the name of the track";
        Track[] tracks = new Track[MAX_NR_OF_TRACKS];
        int f = 0;
        int q = 0;
        while (database[f] != null) {
            if ((database[f].title).contains(name)) {
                tracks[q] = database[f];
                q++;
            }
            f++;
        }
        return tracks;
    }

    /**
     * displays the artist, CD title, year of recording, track number, track title, track length, tags,
     * and country of the found tracks
     *
     * @param tracks
     */
    static void displayTrack(Track[] tracks) {
        assert tracks != null : "array must be initialized";
        int amountOfTracks = getDatabaseLength(tracks);
        int w;
        for (w = 0; w < amountOfTracks; w++) {
            System.out.println('\n');
            System.out.println(tracks[w].artist);
            System.out.println(tracks[w].cd);
            System.out.println(tracks[w].year);
            System.out.println(tracks[w].track);
            System.out.println(tracks[w].title);
            System.out.println(tracks[w].tags);
            System.out.println(tracks[w].time.minutes + ":" + tracks[w].time.seconds);
            System.out.println(tracks[w].country);
        }
    }
}
