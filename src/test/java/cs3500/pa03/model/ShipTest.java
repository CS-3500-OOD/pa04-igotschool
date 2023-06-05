package cs3500.pa03.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShipTest {
  Ship ship;

  @BeforeEach
  void setUp() {
    ship = new Submarine(new Cell[1]);
    for (int i = 0; i < ship.getCells().length; i++) {
      ship.getCells()[i] = new Cell(0, 0);
    }
  }

  /**
   * Tests the setCell method
   */
  @Test
  void setCell() {
    Cell c = new Cell(0, 0);
    ship.setCell(0, c);
    assertEquals(c, ship.getCells()[0]);
  }

  /**
   * Tests the toString method
   */
  @Test
  void testToString() {
    assertEquals("S", ship.toString());
  }

  /**
   * Tests the isSunk method
   */
  @Test
  void isSunk() {
    assertFalse(ship.isSunk());
    for (Cell c : ship.getCells()) {
      c.hit();
    }
    assertTrue(ship.isSunk());
  }
}