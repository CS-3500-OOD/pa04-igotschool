package cs3500.pa03.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa03.model.Coord;
import cs3500.pa03.model.ManualPlayer;
import cs3500.pa03.model.ShipType;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.jupiter.api.Test;

class InputParserTest {
  /**
   * Tests the getListOfShots method
   */
  @Test
  public void testGetListOfShots() {
    HashMap<ShipType, Integer> specs = new HashMap<>();
    specs.put(ShipType.BATTLESHIP, 1);
    specs.put(ShipType.DESTROYER, 1);
    specs.put(ShipType.SUBMARINE, 1);
    specs.put(ShipType.CARRIER, 1);
    ManualPlayer player = new ManualPlayer("player", new InputStreamReader(System.in));
    player.setup(10, 10, specs);
    ArrayList<Coord> shots = InputParser.getListOfShots(player,
        new StringReader("1 1\n2 2\n3 3\n4 4\n"), System.out);
    assertEquals(4, shots.size());
    assertTrue(shots.get(0).equals(new Coord(1, 1)));
    assertTrue(shots.get(1).equals(new Coord(2, 2)));
    assertTrue(shots.get(2).equals(new Coord(3, 3)));
    assertTrue(shots.get(3).equals(new Coord(4, 4)));
  }

  /**
   * Tests the error state of getListOfShots
   */
  @Test
  public void testGetListOfShotsError() {
    HashMap<ShipType, Integer> specs = new HashMap<>();
    specs.put(ShipType.BATTLESHIP, 1);
    specs.put(ShipType.DESTROYER, 1);
    specs.put(ShipType.SUBMARINE, 1);
    specs.put(ShipType.CARRIER, 1);
    ManualPlayer player = new ManualPlayer("player", new InputStreamReader(System.in));
    player.setup(10, 10, specs);
    StringBuilder sb = new StringBuilder();
    try {
      InputParser.getListOfShots(player,
          new StringReader("1 1\n2 2\n3 3\n4 4\n5 5"), sb);
    } catch (Exception e) {
      assertEquals("Incorrect number of shots.", sb.toString());
    }
    StringBuilder sb2 = new StringBuilder();
    try {
      InputParser.getListOfShots(player,
          new StringReader("1 t\n2 2\n3 3\n4 4\n"), sb2);
    } catch (Exception e) {
      assertEquals("Invalid coordinate.", sb2.toString());
    }
  }

  /**
   * Tests the getDimensions method
   */
  @Test
  public void testGetDimensions() {
    ArrayList<Integer> dimensions =
        InputParser.getDimensions(new StringReader("10 10\n"), System.out);
    assertEquals(2, dimensions.size());
    assertEquals(10, dimensions.get(0));
    assertEquals(10, dimensions.get(1));

  }

  /**
   * Tests the error state of getDimensions
   */
  @Test
  public void testGetDimensionsError() {
    StringBuilder sb = new StringBuilder();
    try {
      InputParser.getDimensions(new StringReader("10 10 10\n"), sb);
    } catch (Exception e) {
      assertEquals("Incorrect number of dimensions.", sb.toString());
    }
    StringBuilder sb2 = new StringBuilder();
    try {
      InputParser.getDimensions(new StringReader("10 t\n"), sb2);
    } catch (Exception e) {
      assertEquals("Invalid dimension.", sb2.toString());
    }
  }

  /**
   * Tests the getSpecifications method
   */
  @Test
  public void testGetSpecifications() {
    HashMap<ShipType, Integer> specifications = InputParser.getSpecifications(
        new StringReader("1 1 1 1\n"), System.out);
    assertEquals(4, specifications.size());
    assertEquals(1, specifications.get(ShipType.CARRIER));
    assertEquals(1, specifications.get(ShipType.BATTLESHIP));
    assertEquals(1, specifications.get(ShipType.DESTROYER));
    assertEquals(1, specifications.get(ShipType.SUBMARINE));
  }

  /**
   * Tests the error state of getSpecifications
   */
  @Test
  public void testGetSpecificationsError() {
    StringBuilder sb = new StringBuilder();
    try {
      InputParser.getSpecifications(new StringReader("1 1 1 1 1\n"), sb);
    } catch (Exception e) {
      assertEquals("Incorrect number of specifications.", sb.toString());
    }
    StringBuilder sb2 = new StringBuilder();
    try {
      InputParser.getSpecifications(new StringReader("1 t 1 1\n"), sb2);
    } catch (Exception e) {
      assertEquals("Invalid specification.", sb2.toString());
    }
  }
}
