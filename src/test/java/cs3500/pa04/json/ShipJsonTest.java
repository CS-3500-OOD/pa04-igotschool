package cs3500.pa04.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa04.model.Coord;
import org.junit.jupiter.api.Test;

/**
 * Tests the ShipJson record
 */
class ShipJsonTest {

  /**
   * Tests the ShipJson record
   */
  @Test
  void testShipJsonRecord() {
    Coord coord = new Coord(1, 2);
    ShipJson sj = new ShipJson(coord, 3, "horizontal");
    assertEquals(coord, sj.coord());
    assertEquals(3, sj.length());
    assertEquals("horizontal", sj.direction());
  }
}