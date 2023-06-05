package cs3500.pa03.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ManualPlayerTest {
  ManualPlayer player;

  @BeforeEach
  void setUp() {
    player =
        new ManualPlayer("Player", new StringReader("1 1\n2 2\n1 3\n1 4"));
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
   * Tests the getOpponentBoard method
   */
  @Test
  void testGetOpponentBoard() {
    assertEquals(10, player.getOpponentBoard().getBoard().length);
  }

  /**
   * Tests the name method
   */
  @Test
  void testName() {
    assertEquals("Player", player.name());
  }

  /**
   * Tests the takeShots method
   */
  @Test
  void testTakeShots() {
    List<Coord> shots = player.takeShots();
    assertTrue(shots.get(0).equals(new Coord(1, 1)));
    assertTrue(shots.get(1).equals(new Coord(2, 2)));
    assertTrue(shots.get(2).equals(new Coord(1, 3)));
    assertTrue(shots.get(3).equals(new Coord(1, 4)));
  }

  /**
   * Tests takeShots with invalid coords
   */
  @Test
  void testTakeShotsInvalid() {
    HashMap<ShipType, Integer> specifications = new HashMap<>();
    specifications.put(ShipType.BATTLESHIP, 1);
    specifications.put(ShipType.DESTROYER, 1);
    specifications.put(ShipType.SUBMARINE, 1);
    specifications.put(ShipType.CARRIER, 1);

    ManualPlayer p =
        new ManualPlayer("Player", new StringReader("1 11\n1 1\n2 2\n3 3"));
    p.setup(10, 10, specifications);
    StringBuilder sb = new StringBuilder();
    p.setOut(sb);
    try {
      p.takeShots();
    } catch (Exception e) {
      assertEquals("Invalid coordinate: 1 11", sb.toString());
    }
  }

  /**
   * Tests the reportDamage method
   */
  @Test
  public void testReportDamage() {
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

  /**
   * Tests the endGame and setOut method
   */
  @Test
  public void testEndGame() {
    StringBuilder out = new StringBuilder();
    player.setOut(out);
    player.endGame(GameResult.WIN, "You sunk all their ships!");
    assertEquals("Player WIN because You sunk all their ships!", out.toString());
    out = new StringBuilder();
    player.setOut(out);
    player.endGame(GameResult.LOSE, "They sunk all your ships!");
    assertEquals("Player LOSE because They sunk all your ships!", out.toString());
  }
}