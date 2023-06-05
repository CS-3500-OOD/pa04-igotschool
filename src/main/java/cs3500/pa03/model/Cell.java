package cs3500.pa03.model;

import java.util.ArrayList;

public class Cell {
  private Ship ship;

  private Coord coord;

  private boolean hit = false;

  private boolean firedUpon = false;

  /**
   * Constructs a cell with no ship.
   */
  public Cell(int row, int col) {
    Coord coord1 = new Coord(row, col);
    this.coord = coord1;
    Cell[] c = new Cell[1];
    c[0] = this;
    this.ship = new Empty(c);
  }

  /**
   * Sets the status of this Cell to hit
   */
  public void hit() {
    this.hit = true;
  }

  /**
   * Sets the status of this Cell to firedUpon
   */
  public void firedUpon() {
    this.firedUpon = true;
  }

  /**
   * Returns true if this cell is fired upon
   *
   * @return true if this cell is fired upon
   */
  public boolean isFiredUpon() {
    return this.firedUpon;
  }

  /**
   * Returns true if this cell is hit
   *
   * @return true if this cell is hit
   */
  public boolean isHit() {
    return this.hit;
  }

  /**
   * Gets the ship that is on this cell.
   */
  public Ship getShip() {
    return ship;
  }

  /**
   * Sets the ship that is on this cell.
   *
   * @param ship the ship to set
   */
  public void setShip(Ship ship) {
    this.ship = ship;
  }

  /**
   * Returns this Cell's coordinate.
   *
   * @return this Cell's coordinate
   */
  public Coord getCoord() {
    return coord;
  }

  /**
   * Returns a string representation of this Cell.
   *
   * @return a string representation of this Cell
   */
  public String toString() {
    if (this.firedUpon && !this.hit) {
      return "M";
    } else if (this.hit) {
      return "X";
    } else {
      return this.ship.toString();
    }
  }
}
