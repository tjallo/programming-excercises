package nl.ru.ai.exercise1;

public class Length {
  public int minutes; // #minutes (0..)
  public int seconds; // #seconds (00.59)

  /**
   * Construct a Length object from a string
   * 
   * @param string
   */
  public Length(String string) {
    assert string != null : "String cannot be empty";

    int position = string.indexOf(':');
    if (position < 0)
      throw new RuntimeException(String.format("Invalid length format '%s'", string));
    minutes = Integer.parseInt(string.substring(0, position));
    seconds = Integer.parseInt(string.substring(position + 1, string.length()));
  }

  /**
   * Convert Length object to string
   */
  public String toString() {
    return String.format("%d:%02d", minutes, seconds);
  }

  /**
   * Compare this track with an other
   * 
   * @param other
   * @return -1 if this track is smaller, 0 if equal and 1 if this track is larger
   */
  public int compareTo(Length other) {
    assert other != null : "Lenght of other cannot be null";

    int localSeconds = minutes * 60 + seconds;
    int otherSeconds = other.minutes * 60 + other.seconds;

    if (localSeconds == otherSeconds) {
      return 0;
    } else if (localSeconds < otherSeconds) {
      return -1;
    } else {
      return 1;
    }
  }
}
