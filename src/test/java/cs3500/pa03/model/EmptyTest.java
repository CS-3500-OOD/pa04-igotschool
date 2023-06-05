package cs3500.pa03.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class EmptyTest {
  /**
   * Tests constructor for Empty.
   */
  @Test
  void testConstructor() {
    Cell[] c = new Cell[1];
    c[0] = new Cell(0, 0);
    Empty e = new Empty(c);
    assertEquals("0", e.getSymbol());
  }

  /**
   * Tests that the correct methods throw UnsupportedOperationException.
   */
  @Test
  void testUnsupported() {
    Cell[] c = new Cell[1];
    c[0] = new Cell(0, 0);
    Empty e = new Empty(c);
    assertThrows(UnsupportedOperationException.class, () -> e.getLength());
    assertThrows(UnsupportedOperationException.class, () -> e.getName());
  }
}