package cs3500.pa03.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ComputerPlayerTest {
  ComputerPlayer player;

  @BeforeEach
  void setUp() {
    player = new ComputerPlayer(12345);
    HashMap<ShipType, Integer> specifications = new HashMap<>();
    specifications.put(ShipType.BATTLESHIP, 1);
    specifications.put(ShipType.DESTROYER, 1);
    specifications.put(ShipType.SUBMARINE, 1);
    specifications.put(ShipType.CARRIER, 1);
    player.setup(10, 10, specifications);
  }

  /**
   * Tests the setup method
   */
  @Test
  void testSetup() {
    assertEquals(4, player.getBoard().getShips().size());
    assertEquals(10, player.getBoard().getBoard().length);
  }

  /**
   * Tests the getBoard method
   */
  @Test
  void testGetBoard() {
    assertEquals(4, player.getBoard().getShips().size());
    assertEquals(10, player.getBoard().getBoard().length);
  }

  /**
   * Tests the name method
   */
  @Test
  void testName() {
    assertEquals("AI", player.name());
  }

  /**
   * Tests the takeShots method
   */
  @Test
  void testTakeShots() {
    assertEquals(4, player.takeShots().size());
  }

  /**
   * Tests the reportDamage method
   */
  @Test
  void testReportDamage() {
    ArrayList<Coord> shots = new ArrayList<>();
    assertEquals(0, player.reportDamage(shots).size());
    shots.add(new Coord(0, 0));
    shots.add(new Coord(0, 1));
    shots.add(new Coord(0, 2));
    shots.add(new Coord(0, 3));
    shots.add(new Coord(0, 4));
    shots.add(new Coord(0, 5));
    shots.add(new Coord(5, 5));
    for (Coord shot : shots) {
      player.getBoard().getCell(shot).hit();
    }
    int ct = 0;
    for (Cell[] row : player.getBoard().getBoard()) {
      for (Cell cell : row) {
        if (cell.isHit() && !(cell.getShip() instanceof Empty)) {
          ct++;
        }
      }
    }
    assertEquals(ct, player.reportDamage(shots).size());
  }

  /**
   * Tests the successfulHits method
   */
  @Test
  public void testSuccessfulHits() {
    player.takeShots();
    ArrayList<Coord> shots = new ArrayList<>();
    shots.add(new Coord(0, 0));
    player.successfulHits(shots);
    assertTrue(player.getOpponentBoard().getCell(new Coord(0, 0)).isHit());
    assertFalse(player.getBoard().getCell(new Coord(0, 1)).isHit());
  }


}