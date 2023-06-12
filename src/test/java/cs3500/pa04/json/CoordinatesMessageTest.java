package cs3500.pa04.json;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa04.model.Coord;
import org.junit.jupiter.api.Test;

/**
 * Tests the CoordinatesMessage record
 */
class CoordinatesMessageTest {
  /**
   * Tests the getCoordinates method
   */
  @Test
  void testGetCoordinates() {
    Coord[] coords = new Coord[2];
    coords[0] = new Coord(1, 2);
    coords[1] = new Coord(3, 4);
    CoordinatesMessage cm = new CoordinatesMessage(coords);
    assertEquals(coords, cm.getCoordinates());
  }

  @Test
  void testCoordinatesMessageRecord() {
    Coord coord = new Coord(1, 2);
    Coord coord2 = new Coord(3, 4);
    Coord[] coords = new Coord[2];
    CoordinatesMessage cm = new CoordinatesMessage(coords);
    assertArrayEquals(coords, cm.coordinates());
  }
}