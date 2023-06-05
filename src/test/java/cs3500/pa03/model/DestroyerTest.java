package cs3500.pa03.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DestroyerTest {
  /**
   * Tests the constructor for Destroyer.
   */
  @Test
  void testConstructor() {
    Destroyer d = new Destroyer(new Cell[4]);
    assertEquals("Destroyer", d.getName());
    assertEquals("D", d.getSymbol());
    assertEquals(4, d.getLength());
  }


}