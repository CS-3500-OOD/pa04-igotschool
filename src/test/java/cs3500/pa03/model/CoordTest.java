package cs3500.pa03.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CoordTest {

  /**
   * Tests the getRow method
   */
  @Test
  void testGetRow() {
    Coord c = new Coord(1, 2);
    assertEquals(1, c.getRow());
  }

  /**
   * Tests the getCol method
   */
  @Test
  void testGetCol() {
    Coord c = new Coord(1, 2);
    assertEquals(2, c.getCol());
  }

  /**
   * Tests the equals method
   */
  @Test
  void testEquals() {
    Coord c1 = new Coord(1, 2);
    Coord c2 = new Coord(1, 2);
    Coord c3 = new Coord(2, 1);
    assertTrue(c1.equals(c2));
    assertFalse(c1.equals(c3));
  }

  /**
   * Tests the toString method
   */
  @Test
  void testToString() {
    Coord c = new Coord(1, 2);
    assertEquals("1 2", c.toString());
  }
}