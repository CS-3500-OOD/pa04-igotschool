package cs3500.pa04.model;

import cs3500.pa04.json.FleetJson;
import cs3500.pa04.json.ShipJson;
import cs3500.pa04.model.Ship;
import java.util.List;

/**
 * Adapts a ship to a JSON object.
 */
public class ShipAdapter {

  /**
   * Adapts a ship to a JSON object.
   *
   * @param ship the ship to adapt
   * @return the JSON object
   */
  public static ShipJson adapt(Ship ship) {
    String direction = ship.getDirection();

    // Find the front of the ship
    Coord frontCoord = ship.getFront().getCoord();

    return new ShipJson(frontCoord, ship.getLength(), direction);
  }

  /**
   * Adapts a list of ships to a JSON object.
   *
   * @param fleet the fleet to adapt
   * @return the JSON object
   */
  public static FleetJson adaptList(List<Ship> fleet) {
    ShipJson[] ships = new ShipJson[fleet.size()];
    for (int i = 0; i < fleet.size(); i++) {
      ships[i] = adapt(fleet.get(i));
    }
    return new FleetJson(ships);
  }
}
