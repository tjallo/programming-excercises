package nl.ru.ai.exercise1;

public class Track implements Comparable<Track> {
  public String artist; // name of artist
  public String cd; // cd title
  public int year; // year production
  public int track; // track number
  public String title; // track title
  public String tags; // track tags
  public Length time; // track length
  public String country; // artist country

  /**
   * Compare this track with an other
   * 
   * @return -1 if this track is smaller, 0 if equal and 1 if this track is larger
   */

  // 2A
  // public int compareTo(Track other) {
  // assert other != null : "other Track must be initizialized";

  // if (artist.compareTo(other.artist) != 0) {
  // return artist.compareTo(other.artist);
  // }
  // if (cd.compareTo(other.cd) != 0) {
  // return cd.compareTo(other.cd);
  // }
  // if (Integer.compare(year, other.year) != 0) {
  // return Integer.compare(year, other.year);
  // }

  // return Integer.compare(track, other.track);
  // }

  /**
   * Compare this track with an other
   * 
   * @return -1 if this track is smaller, 0 if equal and 1 if this track is larger
   */

  // 2B
  public int compareTo(Track other) {
    assert other != null : "other Track must be initizialized";

    return time.compareTo(other.time);

  }

}
