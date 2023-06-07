package cs3500.pa04.controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa04.model.JoinMessage;
import cs3500.pa04.model.JsonUtils;
import cs3500.pa04.model.MessageJson;
import cs3500.pa04.model.Player;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;

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
        break;
      case "take-shots":
        break;
      case "report-damage":
        break;
      case "successful-hits":
        break;
      case "end-game":
        break;
      default:
        throw new IllegalArgumentException("Invalid message name");
    }
  }
}
