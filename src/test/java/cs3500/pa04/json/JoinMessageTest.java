package cs3500.pa04.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests the JoinMessage record
 */
class JoinMessageTest {
  @Test
  void testJoinMessageRecord() {
    JoinMessage jm = new JoinMessage("name", "game type");
    assertEquals("name", jm.name());
    assertEquals("game type", jm.gameType());
  }

}