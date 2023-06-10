package cs3500.pa04.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class GameResultTest {
  /**
   * Tests the fromString method
   */
  @Test
  void fromString() {
    assertEquals(GameResult.WIN, GameResult.fromString("WIN"));
    assertEquals(GameResult.LOSE, GameResult.fromString("LOSE"));
    assertEquals(GameResult.DRAW, GameResult.fromString("DRAW"));
    assertEquals(GameResult.IN_PROGRESS, GameResult.fromString("IN_PROGRESS"));
    assertThrows(IllegalArgumentException.class, () -> GameResult.fromString("INVALID"));
  }
}