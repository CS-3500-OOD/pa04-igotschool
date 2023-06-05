package cs3500.pa03.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardTest {
  Board board;

  @BeforeEach
  void setUp() {
    board = new Board(6, 6, 12345);
    HashMap<ShipType, Integer> specifications = new HashMap<>();
    specifications.put(ShipType.BATTLESHIP, 1);
    specifications.put(ShipType.DESTROYER, 1);
    specifications.put(ShipType.SUBMARINE, 1);
    specifications.put(ShipType.CARRIER, 1);
    board.setup(specifications);
  }

  /**
   * Tests that the constructor throws
   */
  @Test
  public void testConstructorThrows() {
    assertThrows(IllegalArgumentException.class, () -> new Board(8, 17));
    assertThrows(IllegalArgumentException.class, () -> new Board(8, 5));
    assertThrows(IllegalArgumentException.class, () -> new Board(4, 10));
    assertThrows(IllegalArgumentException.class, () -> new Board(18, 10));
  }

  /**
   * Tests the getCell method
   */
  @Test
  public void testGetCell() {
    assertEquals(board.getCell(new Coord(0, 0)), board.getBoard()[0][0]);
  }

  /**
   * Tests the reportDamage method
   */
  @Test
  public void testReportDamage() {
    ArrayList<Coord> shots = new ArrayList<>();
    assertEquals(0, board.reportDamage(shots).size());
    shots.add(new Coord(0, 0));
    shots.add(new Coord(0, 1));
    shots.add(new Coord(0, 2));
    shots.add(new Coord(0, 3));
    shots.add(new Coord(0, 4));
    shots.add(new Coord(0, 5));
    assertEquals(6, board.reportDamage(shots).size());
    assertEquals(3, board.getShips().size());
  }

  /**
   * Tests the successfulHits method
   */
  @Test
  public void testSuccessfultHits() {
    ArrayList<Coord> shots = new ArrayList<>();
    shots.add(new Coord(0, 0));
    board.successfulHits(shots);
    assertTrue(board.getCell(new Coord(0, 0)).isHit());
    assertFalse(board.getCell(new Coord(0, 1)).isHit());
  }

  /**
   * Tests the getShips and setShips methods
   */
  @Test
  public void testGetShips() {
    assertEquals(4, board.getShips().size());
    ArrayList<Ship> newShips = new ArrayList<>();
    board.setShips(newShips);
    assertEquals(newShips, board.getShips());
  }

  /**
   * Tests the setup method
   */
  @Test
  public void testSetup() {
    // I call board.setup in the @BeforeEach method, so ill test the effects here
    assertEquals(4, board.getShips().size());
    for (Ship ship : board.getShips()) {
      assertTrue(ship.getLength() == 6 || ship.getLength() == 5 || ship.getLength() == 4
          || ship.getLength() == 3);
    }
  }

  /**
   * Tests that the setup method throws
   */
  @Test
  public void testSetupThrows() {
    HashMap<ShipType, Integer> specifications = new HashMap<>();
    specifications.put(ShipType.BATTLESHIP, 4);
    specifications.put(ShipType.DESTROYER, 1);
    specifications.put(ShipType.SUBMARINE, 1);
    specifications.put(ShipType.CARRIER, 1);
    Board board2 = new Board(6, 6, 12345);
    assertThrows(IllegalArgumentException.class, () -> board2.setup(specifications));
    HashMap<ShipType, Integer> specifications2 = new HashMap<>();
    specifications2.put(ShipType.BATTLESHIP, 0);
    specifications2.put(ShipType.DESTROYER, 0);
    specifications2.put(ShipType.SUBMARINE, 0);
    specifications2.put(ShipType.CARRIER, 0);
    assertThrows(IllegalArgumentException.class, () -> board2.setup(specifications2));
    HashMap<ShipType, Integer> specifications3 = new HashMap<>();
    specifications3.put(ShipType.BATTLESHIP, 1);
    specifications3.put(ShipType.DESTROYER, 1);
    specifications3.put(ShipType.SUBMARINE, 1);
    specifications3.put(ShipType.CARRIER, 1);
    for (Cell[] row : board2.getBoard()) {
      for (Cell cell : row) {
        cell.setShip(ShipType.BATTLESHIP.getShip());
      }
    }
    assertThrows(IllegalArgumentException.class, () -> board2.setup(specifications3));
  }

  /**
   * Tests the getBoard method
   */
  @Test
  public void testGetBoard() {
    assertEquals(6, board.getBoard().length);
    assertEquals(6, board.getBoard()[0].length);
    assertEquals(board.getCell(new Coord(0, 0)), board.getBoard()[0][0]);
  }

  /**
   * Tests the getNumRows method
   */
  @Test
  public void testGetNumRows() {
    assertEquals(6, board.getNumRows());
  }

  /**
   * Tests the getNumCols method
   */
  @Test
  public void testGetNumCols() {
    assertEquals(6, board.getNumCols());
  }
}