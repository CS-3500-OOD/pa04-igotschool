package cs3500.pa04.model;

/**
 * Represents a carrier.
 */
public class Carrier extends Ship {
  /**
   * Constructor for Carrier.
   *
   * @param cells the cells that the ship occupies
   */
  public Carrier(Cell[] cells) {
    super(cells, "Carrier", "C");
  }
}
