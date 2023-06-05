package cs3500.pa03.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class BattleshipTest {
  /**
   * Tests construction of a battleship
   */
  @Test
  public void testBattleship() {
    Battleship b = new Battleship(new Cell[5]);
    assertEquals("Battleship", b.getName());
    assertEquals("B", b.getSymbol());
  }
}