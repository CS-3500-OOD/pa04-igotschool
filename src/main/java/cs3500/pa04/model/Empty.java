package cs3500.pa04.model;

public class Empty extends Ship {
  /**
   * Constructor for Empty.
   *
   * @param c the cells that the ship occupies
   */
  public Empty(Cell[] c) {
    super(c, "Empty", "0");
  }

  @Override
  public int getLength() {
    throw new UnsupportedOperationException("Empty does not have a length");
  }

  @Override
  public boolean isSunk() {
    throw new UnsupportedOperationException("Empty cannot be sunk");
  }

  @Override
  public String getName() {
    throw new UnsupportedOperationException("Empty does not have a name");
  }
}
