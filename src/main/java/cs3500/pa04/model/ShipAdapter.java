package cs3500.pa04.model;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Adapts a ship to a JSON object.
 */
public class ShipAdapter {
  public static JsonNode adapt(Ship ship) {
    ShipJson shipJson =
        new ShipJson(ship.getCells()[0].getCoord(), ship.getLength(), ship.getDirection());
    return JsonUtils.serializeRecord(shipJson);
  }
}
