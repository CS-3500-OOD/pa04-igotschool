package cs3500.pa04.model;

import cs3500.pa04.json.FleetJson;
import cs3500.pa04.json.ShipJson;
import cs3500.pa04.model.Ship;
import java.util.List;

/**
 * Adapts a ship to a JSON object.
 */
public class ShipAdapter {
  public static ShipJson adapt(Ship ship) {
    String direction = ship.getDirection();

    Coord firstCoord = ship.getCells()[0].getCoord();
    Coord lastCoord = ship.getCells()[0].getCoord();

    // Find the front of the ship
    Coord frontCoord =
        firstCoord.getX() < lastCoord.getX() || firstCoord.getY() < lastCoord.getY() ? firstCoord :
            lastCoord;

    return new ShipJson(frontCoord, ship.getLength(), direction);
  }

  public static FleetJson adaptList(List<Ship> fleet) {
    ShipJson[] ships = new ShipJson[fleet.size()];
    for (int i = 0; i < fleet.size(); i++) {
      ships[i] = adapt(fleet.get(i));
    }
    return new FleetJson(ships);
  }
}
