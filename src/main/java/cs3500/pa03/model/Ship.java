package cs3500.pa03.model;

public abstract class Ship {

  private Cell[] cells;

  private final String name;

  private final String symbol;

  /**
   * Constructs a ship with the given cells.
   *
   * @param cells the cells that this ship occupies
   */
  public Ship(Cell[] cells, String name, String symbol) {
    this.cells = cells;
    this.name = name;
    this.symbol = symbol;
  }

  /**
   * Gets the length of this ship.
   *
   * @return the length of this ship
   */
  public int getLength() {
    return this.cells.length;
  }

  /**
   * Gets the cells that this ship occupies.
   *
   * @return the cells that this ship occupies
   */
  public Cell[] getCells() {
    return this.cells;
  }

  /**
   * Gets the name of this ship.
   *
   * @return the name of this ship
   */
  public String getName() {
    return this.name;
  }

  /**
   * Gets the symbol representing this ship.
   *
   * @return the symbol representing this ship
   */
  public String getSymbol() {
    return this.symbol;
  }

  /**
   * Sets the cell at the given index to the given cell.
   *
   * @param index the index of the cell to set
   * @param cell  the cell to set
   */
  public void setCell(int index, Cell cell) {
    this.cells[index] = cell;
  }

  /**
   * Returns a string representation of this ship.
   *
   * @return a string representation of this ship
   */
  public String toString() {
    return this.symbol;
  }

  /**
   * Returns weather or not this ship is sunk
   *
   * @return true if this ship is sunk
   */
  public boolean isSunk() {
    for (Cell cell : this.cells) {
      if (!cell.isHit()) {
        return false;
      }
    }
    return true;
  }
}


