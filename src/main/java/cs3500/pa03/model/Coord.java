package cs3500.pa03.model;

public class Coord {
  private int row;
  private int col;

  /**
   * Constructs a coordinate with the given row and column.
   *
   * @param row the row of this coordinate
   * @param col the column of this coordinate
   */
  public Coord(int row, int col) {
    this.row = row;
    this.col = col;
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
      this.row = Integer.parseInt(split[0]);
      this.col = Integer.parseInt(split[1]);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid coordinate format.");
    }
  }

  /**
   * Gets the row of this coordinate.
   *
   * @return the row of this coordinate
   */
  public int getRow() {
    return row;
  }

  /**
   * Gets the column of this coordinate.
   *
   * @return the column of this coordinate
   */
  public int getCol() {
    return col;
  }

  /**
   * Compares this Coord for equality with the given Coord.
   *
   * @param o the Coord to compare to
   * @return true if the given Coord is equal to this Coord, false otherwise
   */
  public boolean equals(Coord o) {
    return o.row == this.row && o.col == this.col;
  }

  /**
   * Returns a string representation of this coordinate.
   */
  public String toString() {
    return this.row + " " + this.col;
  }
}
