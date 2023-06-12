package cs3500.pa04.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa04.json.FleetJson;
import cs3500.pa04.json.ShipJson;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShipAdapterTest {
  Ship ship;
  Ship vertShip;

  @BeforeEach
  void setUp() {
    ship = new Submarine(new Cell[2]);
    for (int i = 0; i < ship.getCells().length; i++) {
      ship.getCells()[i] = new Cell(0, i);
    }

    vertShip = new Carrier(new Cell[2]);
    for (int i = 0; i < ship.getCells().length; i++) {
      vertShip.getCells()[i] = new Cell(i, 0);
    }
  }

  /**
   * Tests the adapt method
   */
  @Test
  void adapt() {
    ShipJson shipJson = ShipAdapter.adapt(ship);

    assertEquals(0, shipJson.coord().getX());
    assertEquals(0, shipJson.coord().getY());
    assertEquals(2, shipJson.length());
    assertEquals("HORIZONTAL", shipJson.direction());

    ShipJson vertShipJson = ShipAdapter.adapt(vertShip);

    assertEquals(0, vertShipJson.coord().getX());
    assertEquals(0, vertShipJson.coord().getY());
    assertEquals(2, vertShipJson.length());
    assertEquals("VERTICAL", vertShipJson.direction());
  }

  /**
   * Tests the adaptList method
   */
  @Test
  public void testAdaptList() {
    ArrayList<Ship> fleet = new ArrayList<>();
    fleet.add(ship);
    fleet.add(vertShip);

    FleetJson fleetJson = ShipAdapter.adaptList(fleet);

    assertEquals(2, fleetJson.fleet().length);

    assertEquals(0, fleetJson.fleet()[0].coord().getX());
    assertEquals(0, fleetJson.fleet()[0].coord().getY());
    assertEquals(2, fleetJson.fleet()[0].length());
    assertEquals("HORIZONTAL", fleetJson.fleet()[0].direction());

    assertEquals(0, fleetJson.fleet()[1].coord().getX());
    assertEquals(0, fleetJson.fleet()[1].coord().getY());
    assertEquals(2, fleetJson.fleet()[1].length());
    assertEquals("VERTICAL", fleetJson.fleet()[1].direction());
  }
}