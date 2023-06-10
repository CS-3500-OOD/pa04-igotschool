package cs3500.pa04.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;

/**
 * Tests the MessageJson record
 */
class MessageJsonTest {
  /**
   * Tests the MessageJson record
   */
  @Test
  void testMessageJsonRecord() {
    JsonNode arguments = createJsonNode();
    MessageJson mj = new MessageJson("method name", arguments);
    assertEquals("method name", mj.messageName());
    assertEquals(arguments, mj.arguments());
  }

  /**
   * Creates a JsonNode for testing
   * @return a JsonNode
   */
  private JsonNode createJsonNode() {
    ObjectNode obj = new ObjectNode(JsonNodeFactory.instance);
    obj.put("property1", "value1");
    obj.put("property2", 4);
    obj.put("property3", true);
    return obj;
  }


}