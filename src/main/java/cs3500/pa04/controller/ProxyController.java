package cs3500.pa04.controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa04.model.Coord;
import cs3500.pa04.model.FleetJson;
import cs3500.pa04.model.JoinMessage;
import cs3500.pa04.model.JsonUtils;
import cs3500.pa04.model.MessageJson;
import cs3500.pa04.model.Player;
import cs3500.pa04.model.Ship;
import cs3500.pa04.model.ShipAdapter;
import cs3500.pa04.model.ShipType;
import cs3500.pa04.model.CoordinatesMessage;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Proxy class for a server that hosts a game of Battleship.
 */
public class ProxyController {
  Player player;

  Socket server;

  private final ObjectMapper mapper = new ObjectMapper();

  private final InputStream in;
  private final PrintStream out;

  /**
   * Constructor for ProxyServer.
   *
   * @param player the player connecting to this proxy server
   */
  public ProxyController(Player player, Socket server) throws IOException {
    this.player = player;
    this.server = server;
    this.in = server.getInputStream();
    this.out = new PrintStream(server.getOutputStream());
  }

  /**
   * Runs the proxy server and listens for and sends JSON messages.
   */
  public void run() {
    try {
      JsonParser parser = this.mapper.getFactory().createParser(this.in);

      while (!this.server.isClosed()) {
        MessageJson message = parser.readValueAs(MessageJson.class);
        delegateMessage(message);
      }
    } catch (IOException e) {
      // Disconnected from server or parsing exception
    }
  }

  /**
   * Delegates a message from the server to the correct player method
   */
  private void delegateMessage(MessageJson message) {
    String messageName = message.messageName();
    JsonNode arguments = message.arguments();

    switch (messageName) {
      case "join":
        MessageJson joinResponse =
            new MessageJson("join",
                JsonUtils.serializeRecord(new JoinMessage(this.player.name(), "SINGLE")));
        this.out.println(joinResponse);
        break;
      case "setup":
        MessageJson setupResponse =
            new MessageJson("setup", JsonUtils.serializeRecord(setup(arguments)));
        this.out.println(setupResponse);
        break;
      case "take-shots":
        MessageJson takeShotsResponse =
            new MessageJson("take-shots", JsonUtils.serializeRecord(takeShots()));
        this.out.println(takeShotsResponse);
        break;
      case "report-damage":
        MessageJson reportDamageResponse =
            new MessageJson("report-damage",
                JsonUtils.serializeRecord(reportDamage(arguments)));
        break;
      case "successful-hits":
        break;
      case "end-game":
        break;
      default:
        throw new IllegalArgumentException("Invalid message name");
    }
  }

  /**
   * Returns a list of shots this player take
   *
   * @return the list of shots the player takes
   */
  private CoordinatesMessage takeShots() {
    List<Coord> shots = this.player.takeShots();
    Coord[] shotsArray = shots.toArray(new Coord[0]);
    return new CoordinatesMessage(shotsArray);
  }

  /**
   * Returns a list of successful hits on the player's ships from the given arguments
   *
   * @param arguments the arguments for the report damage request
   * @return the list of successful hits on the player's ships
   */
  private CoordinatesMessage reportDamage(JsonNode arguments) {
    CoordinatesMessage coords =
        new ObjectMapper().convertValue(arguments, CoordinatesMessage.class);
    ArrayList<Coord> shotsOnPlayer = new ArrayList<>();
    for (Coord coord : coords.getCoordinates()) {
      shotsOnPlayer.add(coord);
    }

    List<Coord> damage = this.player.reportDamage(shotsOnPlayer);
    Coord[] damageArray = damage.toArray(new Coord[0]);

    return new CoordinatesMessage(damageArray);
  }

  /**
   * Returns a FleetJson of the player's fleet after setup
   *
   * @param arguments the arguments for the setup request
   * @return the FleetJson representing the players placements
   */
  private FleetJson setup(JsonNode arguments) {
    int height = arguments.get("height").asInt();
    int width = arguments.get("width").asInt();

    int numCarrier = arguments.get("fleet-spec").get("CARRIER").asInt();
    int numBattleship = arguments.get("fleet-spec").get("BATTLESHIP").asInt();
    int numDestroyer = arguments.get("fleet-spec").get("DESTROYER").asInt();
    int numSubmarine = arguments.get("fleet-spec").get("SUBMARINE").asInt();

    HashMap<ShipType, Integer> specs = new HashMap<>();

    specs.put(ShipType.CARRIER, numCarrier);
    specs.put(ShipType.BATTLESHIP, numBattleship);
    specs.put(ShipType.DESTROYER, numDestroyer);
    specs.put(ShipType.SUBMARINE, numSubmarine);

    List<Ship> placements = this.player.setup(height, width, specs);

    return ShipAdapter.adaptList(placements);
  }
}
