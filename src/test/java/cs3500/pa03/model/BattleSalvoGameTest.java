package cs3500.pa03.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.StringReader;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BattleSalvoGameTest {
  BattleSalvoGame game;

  @BeforeEach
  void setUp() {
    StringReader in = new StringReader("1 1\n2 2\n1 3\n1 4");
    game = new BattleSalvoGame(in);
  }

  /**
   * Tests the getManualPlayer method
   */
  @Test
  void testGetManualPlayer() {
    assertEquals("Player", game.getManualPlayer().name());
  }

  /**
   * Tests the getComputerPlayer method
   */
  @Test
  void testGetComputerPlayer() {
    assertEquals("AI", game.getComputerPlayer().name());
  }


  /**
   * Tests the setup method
   */
  @Test
  void testSetup() {
    StringBuilder out = new StringBuilder();
    StringReader in = new StringReader("10 10");
    StringReader in2 = new StringReader("1 1 1 1");
    game.setup(out, in, in2);
    assertEquals(4, game.getComputerPlayer().getBoard().getShips().size());
    assertEquals(4, game.getManualPlayer().getBoard().getShips().size());
    assertEquals(10, game.getManualPlayer().getBoard().getBoard().length);
    assertEquals(10, game.getComputerPlayer().getBoard().getBoard().length);
    assertEquals("Welcome to BattleSalvo!\n"
            + "Please enter a valid height and width:\n"
            + "Please enter the number of each type of ship in order of [Carrier, Battleship, "
            + "Destroyer, Submarine]:\n",
        out.toString());
  }

  /**
   * Tests the playGame and setResult methods
   */
  @Test
  public void testPlayGame() {
    StringBuilder out = new StringBuilder();
    StringReader in = new StringReader("10 10");
    StringReader in2 = new StringReader("1 1 1 1");
    game.setup(out, in, in2);
    try {
      game.getComputerPlayer().getBoard().getBoard()[1][1].setShip(ShipType.SUBMARINE.getShip());
      game.playGame(out);
    } catch (Exception e) {
      boolean hasX = false;
      for (Cell[] c : game.getComputerPlayer().getBoard().getBoard()) {
        for (Cell cell : c) {
          hasX = hasX || cell.toString().equals("X");
        }
      }
      assertTrue(hasX);
    }
  }

  /**
   * Tests game end conditions and setResult method
   */
  @Test
  public void testEndGameLose() {
    BattleSalvoGame g = new BattleSalvoGame(new StringReader(""));
    StringBuilder out = new StringBuilder();
    StringReader in = new StringReader("10 10");
    StringReader in2 = new StringReader("1 1 1 1");
    g.setup(out, in, in2);
    g.getManualPlayer().getBoard().setShips(new ArrayList<Ship>());
    g.playGame(out);
    assertEquals(GameResult.LOSE, g.getResult());
  }

  /**
   * Tests game end conditions and setResult method
   */
  @Test
  public void testEndGameWin() {
    BattleSalvoGame g = new BattleSalvoGame(new StringReader(""));
    StringBuilder out = new StringBuilder();
    StringReader in = new StringReader("10 10");
    StringReader in2 = new StringReader("1 1 1 1");
    g.setup(out, in, in2);
    g.getComputerPlayer().getBoard().setShips(new ArrayList<Ship>());
    g.playGame(out);
    assertEquals(GameResult.WIN, g.getResult());
  }
}