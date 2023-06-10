package cs3500.pa04.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShipTest {
  Ship ship;
  Ship vertShip;

  @BeforeEach
  void setUp() {
    ship = new Submarine(new Cell[2]);
    for (int i = 0; i < ship.getCells().length; i++) {
      ship.getCells()[i] = new Cell(0, i);
    }

    vertShip = new Carrier(new Cell[2]);
    for (int i = 0; i < ship.getCells().length; i++) {
      vertShip.getCells()[i] = new Cell(i, 0);
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

  /**
   * Tests the getDirection method
   */
  @Test
  void getDirection() {
    assertEquals("HORIZONTAL", ship.getDirection());
    assertEquals("VERTICAL", vertShip.getDirection());
  }

  /**
   * Tests the getFront method
   */
  @Test
  void getFront() {
    assertEquals(ship.getCells()[0], ship.getFront());
    assertEquals(vertShip.getCells()[0], vertShip.getFront());
  }
}