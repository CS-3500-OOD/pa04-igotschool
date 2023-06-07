package cs3500.pa04.model;

import java.util.List;

/**
 * Adapts a ship to a JSON object.
 */
public class ShipAdapter {
  public static ShipJson adapt(Ship ship) {
    return new ShipJson(ship.getCells()[0].getCoord(), ship.getLength(), ship.getDirection());
  }

  public static FleetJson adaptList(List<Ship> fleet) {
    ShipJson[] ships = new ShipJson[fleet.size()];
    for (int i = 0; i < fleet.size(); i++) {
      ships[i] = new ShipJson(fleet.get(i).getCells()[0].getCoord(), fleet.get(i).getLength(),
          fleet.get(i).getDirection());
    }
    return new FleetJson(ships);
  }
}
