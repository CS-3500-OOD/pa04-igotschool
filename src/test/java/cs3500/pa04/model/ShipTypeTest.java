package cs3500.pa04.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ShipTypeTest {
  /**
   * Tests the getShip method.
   */
  @Test
  public void getShip() {
    assertEquals(ShipType.CARRIER.getShip().getName(), "Carrier");
    assertEquals(ShipType.BATTLESHIP.getShip().getName(), "Battleship");
    assertEquals(ShipType.DESTROYER.getShip().getName(), "Destroyer");
    assertEquals(ShipType.SUBMARINE.getShip().getName(), "Submarine");
    assertThrows(IllegalArgumentException.class, () -> ShipType.UNKNOWN.getShip());
  }

  /**
   * Tests the getTypeFromNumber method.
   */
  @Test
  public void getTypeFromNumber() {
    assertEquals(ShipType.getTypeFromNumber(0), ShipType.CARRIER);
    assertEquals(ShipType.getTypeFromNumber(1), ShipType.BATTLESHIP);
    assertEquals(ShipType.getTypeFromNumber(2), ShipType.DESTROYER);
    assertEquals(ShipType.getTypeFromNumber(3), ShipType.SUBMARINE);
    assertThrows(IllegalArgumentException.class, () -> ShipType.getTypeFromNumber(4));
  }
}