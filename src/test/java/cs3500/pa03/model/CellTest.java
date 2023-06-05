package cs3500.pa03.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CellTest {
  Cell cell;

  @BeforeEach
  public void setup() {
    cell = new Cell(0, 0);
  }

  /**
   * Tests the hit and isHit methods
   */
  @Test
  public void testHit() {
    assertFalse(cell.isHit());
    cell.hit();
    assertTrue(cell.isHit());
  }

  /**
   * Tests the firedUpon and isFiredUpon methods
   */
  @Test
  public void testFiredUpon() {
    assertFalse(cell.isFiredUpon());
    cell.firedUpon();
    assertTrue(cell.isFiredUpon());
  }

  /**
   * Tests the setShip and getShip methods
   */
  @Test
  public void testSetShip() {
    Ship s = new Submarine(new Cell[] {cell});
    cell.setShip(s);
    assertEquals(s, cell.getShip());
  }

  /**
   * Tests the get coord method
   */
  @Test
  public void testGetCoord() {
    assertEquals(0, cell.getCoord().getRow());
    assertEquals(0, cell.getCoord().getCol());
  }

  /**
   * Tests the toString method
   */
  @Test
  public void testToString() {
    cell.setShip(new Submarine(new Cell[3]));
    assertEquals("S", cell.toString());
    cell.firedUpon();
    assertEquals("M", cell.toString());
    cell.hit();
    assertEquals("X", cell.toString());
  }
}