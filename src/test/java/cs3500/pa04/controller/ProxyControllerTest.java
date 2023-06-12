package cs3500.pa04.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa04.json.JoinMessage;
import cs3500.pa04.json.JsonUtils;
import cs3500.pa04.json.MessageJson;
import cs3500.pa04.model.Cell;
import cs3500.pa04.model.ComputerPlayer;
import cs3500.pa04.model.Player;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProxyControllerTest {
  private ByteArrayOutputStream testLog;
  private ProxyController proxyController;
  Player player;
  StringBuilder playerOut;


  /**
   * Reset the test log before each test is run.
   */
  @BeforeEach
  public void setup() {
    this.playerOut = new StringBuilder();
    this.player = new ComputerPlayer(playerOut);
    this.testLog = new ByteArrayOutputStream(2048);
    assertEquals("", logToString());
  }

  /**
   * Create a MessageJson for some name and arguments.
   *
   * @param messageName   name of the type of message; "hint" or "win"
   * @param messageObject object to embed in a message json
   * @return a MessageJson for the object
   */
  private JsonNode createSampleMessage(String messageName, Record messageObject) {
    MessageJson messageJson =
        new MessageJson(messageName, JsonUtils.serializeRecord(messageObject));
    return JsonUtils.serializeRecord(messageJson);
  }

  /**
   * Converts the ByteArrayOutputStream log to a string in UTF_8 format
   *
   * @return String representing the current log buffer
   */
  private String logToString() {
    return testLog.toString(StandardCharsets.UTF_8);
  }

  /**
   * Tests that the controller returns a valid join message when requested
   */
  @Test
  public void testJoin() {
    ArrayList<String> toSend = new ArrayList<>();
    toSend.add("{\"method-name\":\"join\",\"arguments\":{}}\n");
    Socket mocket = new Mocket(this.testLog, toSend);
    try {
      this.proxyController = new ProxyController(player, mocket);
      this.proxyController.run();
      assertEquals(
          "{\"method-name\":\"join\",\"arguments\":{\"name\":\"marbleville\","
              + "\"game-type\":\"SINGLE\"}}\n", logToString());
    } catch (IOException e) {
      fail();
    }
  }

  /**
   * Tests that the controller returns a valid setup message when requested
   */
  @Test
  public void testSetup() {
    ArrayList<String> toSend = new ArrayList<>();
    toSend.add("{"
        + "\"method-name\": \"setup\","
        + "\"arguments\": {"
        + "\"width\": 10,"
        + "\"height\": 10,"
        + "\"fleet-spec\": {"
        + "\"CARRIER\": 2,"
        + "\"BATTLESHIP\": 4,"
        + "\"DESTROYER\": 1,"
        + "\"SUBMARINE\": 3}}}\n");
    Socket mocket = new Mocket(this.testLog, toSend);
    try {
      this.proxyController = new ProxyController(player, mocket);
      this.proxyController.run();
      assertEquals(player.getBoard().getShips().size(), 10);
      // 11 because splitting array like this adds one erroneous element
      assertEquals(11, logToString().split("fleet")[1].split("coord").length);
    } catch (IOException e) {
      fail();
    }
  }

  /**
   * Tests the controller returns a valid take-shots message when requested
   */
  @Test
  public void testTakeShots() {
    ArrayList<String> toSend = new ArrayList<>();
    // setup player
    toSend.add("{"
        + "\"method-name\": \"setup\","
        + "\"arguments\": {"
        + "\"width\": 10,"
        + "\"height\": 10,"
        + "\"fleet-spec\": {"
        + "\"CARRIER\": 2,"
        + "\"BATTLESHIP\": 4,"
        + "\"DESTROYER\": 1,"
        + "\"SUBMARINE\": 3}}}\n");

    toSend.add("{\"method-name\": \"take-shots\",\"arguments\": {}}\n");
    Socket mocket = new Mocket(this.testLog, toSend);
    try {
      this.proxyController = new ProxyController(player, mocket);
      this.proxyController.run();
      assertEquals(11,
          logToString().split("coordinates")[1].split("x").length);
    } catch (IOException e) {
      fail();
    }
  }

  /**
   * Tests that the controller returns a valid report-damage message when requested
   */
  @Test
  public void testReportDamage() {
    ArrayList<String> toSend = new ArrayList<>();
    // setup player
    toSend.add("{"
        +
        "\"method-name\": \"setup\","
        + "\"arguments\": {"
        + "\"width\": 10,"
        + "\"height\": 10,"
        + "\"fleet-spec\": {"
        + "\"CARRIER\": 2,"
        + "\"BATTLESHIP\": 4,"
        + "\"DESTROYER\": 1,"
        + "\"SUBMARINE\": 3}}}\n");

    toSend.add("{\n"
        + "\"method-name\": \"report-damage\",\n"
        + "\"arguments\": {\n"
        + "\"coordinates\": [\n"
        + "{\"x\": 0, \"y\": 1},\n"
        + "{\"x\": 3, \"y\": 2}]}}\n");
    Socket mocket = new Mocket(this.testLog, toSend);
    try {
      this.proxyController = new ProxyController(player, mocket);
      this.proxyController.run();

      // Calculate number of hit ships
      int hitShips = 0;
      for (Cell[] cell : player.getBoard().getBoard()) {
        for (Cell c : cell) {
          if (c.isHit()) {
            hitShips++;
          }
        }
      }

      assertEquals(hitShips + 1,
          logToString().split("coordinates")[1].split("x").length);
    } catch (IOException e) {
      fail();
    }
  }

  /**
   * Tests that the controller returns a valid successful-hits message when requested
   */
  @Test
  public void testSuccessfulHits() {
    ArrayList<String> toSend = new ArrayList<>();
    // setup player
    toSend.add("{"
        + "\"method-name\": \"setup\","
        + "\"arguments\": {"
        + "\"width\": 10,"
        + "\"height\": 10,"
        + "\"fleet-spec\": {"
        + "\"CARRIER\": 2,"
        + "\"BATTLESHIP\": 4,"
        + "\"DESTROYER\": 1,"
        + "\"SUBMARINE\": 3}}}\n");

    toSend.add("{\n"
        + "\"method-name\": \"successful-hits\",\n"
        + "\"arguments\": {\n"
        + "\"coordinates\": [\n"
        + "{\"x\": 0, \"y\": 1},\n"
        + "{\"x\": 3, \"y\": 2}]}}");
    Socket mocket = new Mocket(this.testLog, toSend);
    try {
      this.proxyController = new ProxyController(player, mocket);
      this.proxyController.run();
      assertEquals("{\"method-name\":\"successful-hits\",\"arguments\":{}}",
          logToString().split("\n")[1]);
    } catch (IOException e) {
      fail();
    }
  }

  /**
   * Tests that the controller returns a valid end-game message when requested
   */
  @Test
  public void testendGame() {
    ArrayList<String> toSend = new ArrayList<>();

    toSend.add("{\n"
        + "\"method-name\": \"end-game\",\n"
        + "\"arguments\": {\n"
        + "\"result\": \"WIN\",\n"
        + "\"reason\": \"Player 1 sank all of Player 2's ships\"}}");
    Socket mocket = new Mocket(this.testLog, toSend);
    try {
      this.proxyController = new ProxyController(player, mocket);
      this.proxyController.run();
      assertEquals("{\"method-name\":\"end-game\",\"arguments\":{}}\n",
          logToString());
    } catch (IOException e) {
      fail();
    }
  }

  /**
   * Tests the that controller throws an exception when given an invalid message
   */
  @Test
  public void testInvalidMessage() {
    ArrayList<String> toSend = new ArrayList<>();

    // Invalid method name
    toSend.add("{\"method-name\":\"successful-hit\",\"arguments\":{}}");
    Socket mocket = new Mocket(this.testLog, toSend);
    try {
      this.proxyController = new ProxyController(player, mocket);
      assertThrows(IllegalArgumentException.class, () -> {
        this.proxyController.run();
      });
    } catch (Exception e) {
      fail();
    }
  }
}