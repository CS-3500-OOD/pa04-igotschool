package cs3500.pa04.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fasterxml.jackson.databind.JsonNode;
import cs3500.pa04.model.Coord;
import org.junit.jupiter.api.Test;

/**
 * Tests the JsonUtils class
 */
class JsonUtilsTest {
  /**
   * Tests the serializeRecord method
   */
  @Test
  void testSerializeRecord() {
    JoinMessage jm = new JoinMessage("name", "single");
    JsonNode jn = JsonUtils.serializeRecord(jm);
    assertNotNull(jn);
    String name = jn.get("name").asText();
    assertEquals("name", name);
  }
}