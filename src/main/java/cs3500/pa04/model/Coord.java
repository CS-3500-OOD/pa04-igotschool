package cs3500.pa04.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a coordinate on a board
 */
public class Coord {
  private int x;
  private int y;


  /**
   * Constructs a coordinate with the given row and column.
   *
   * @param col the column of this coordinate
   * @param row the row of this coordinate
   */
  public Coord(@JsonProperty("x") int col, @JsonProperty("y") int row) {
    this.x = col;
    this.y = row;
  }

  /**
   * Constructs a coordinate with the given string representation of the form of "[int] [int]"
   *
   * @param coord the string to be parsed
   */
  public Coord(String coord) {
    try {
      String[] split = coord.split(" ");
      if (split.length != 2) {
        throw new IllegalArgumentException("Invalid coordinate format.");
      }
      this.y = Integer.parseInt(split[0]);
      this.x = Integer.parseInt(split[1]);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid coordinate format.");
    }
  }

  /**
   * Gets the row of this coordinate.
   *
   * @return the row of this coordinate
   */
  public int getY() {
    return y;
  }

  /**
   * Gets the column of this coordinate.
   *
   * @return the column of this coordinate
   */
  public int getX() {
    return x;
  }

  /**
   * Compares this Coord for equality with the given Coord.
   *
   * @param o the Coord to compare to
   * @return true if the given Coord is equal to this Coord, false otherwise
   */
  public boolean equals(Coord o) {
    return o.y == this.y && o.x == this.x;
  }

  /**
   * Returns a string representation of this coordinate.
   */
  public String toString() {
    return this.y + " " + this.x;
  }
}
