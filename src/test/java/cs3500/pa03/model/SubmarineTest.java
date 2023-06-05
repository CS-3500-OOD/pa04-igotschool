package cs3500.pa03.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SubmarineTest {
  /**
   * TestS the constructor
   */
  @Test
  void testConstructor() {
    Cell[] cells = new Cell[3];
    cells[0] = new Cell(0, 0);
    cells[1] = new Cell(0, 1);
    cells[2] = new Cell(0, 2);
    Submarine s = new Submarine(cells);
    assertEquals("Submarine", s.getName());
    assertEquals("S", s.getSymbol());
    assertEquals(3, s.getLength());
    assertEquals(cells, s.getCells());
  }
}