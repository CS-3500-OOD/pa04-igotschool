package cs3500.pa04.model;

/**
 * Represents a destroyer.
 */
public class Destroyer extends Ship {
  /**
   * Constructor for Destroyer.
   *
   * @param cells the cells that the ship occupies
   */
  public Destroyer(Cell[] cells) {
    super(cells, "Destroyer", "D");
  }
}
