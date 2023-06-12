package cs3500.pa04.model;

/**
 * Represents a submarine.
 */
public class Submarine extends Ship {
  /**
   * Constructor for Submarine.
   *
   * @param cells the cells that the ship occupies
   */
  public Submarine(Cell[] cells) {
    super(cells, "Submarine", "S");
  }
}
