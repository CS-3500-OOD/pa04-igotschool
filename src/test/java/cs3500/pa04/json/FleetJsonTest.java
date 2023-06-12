package cs3500.pa04.json;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import cs3500.pa04.model.Coord;
import org.junit.jupiter.api.Test;

/**
 * Tests the FleetJson record
 */
class FleetJsonTest {

  /**
   * Tests the FleetJson record
   */
  @Test
  void testFleetJsonRecord() {
    ShipJson sj = new ShipJson(new Coord(1, 2), 3, "horizontal");
    ShipJson sj2 = new ShipJson(new Coord(3, 4), 5, "vertical");
    ShipJson[] ships = new ShipJson[2];
    FleetJson fj = new FleetJson(ships);
    assertArrayEquals(ships, fj.fleet());
  }
}