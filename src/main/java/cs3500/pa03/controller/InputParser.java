package cs3500.pa03.controller;

import cs3500.pa03.model.Coord;
import cs3500.pa03.model.ManualPlayer;
import cs3500.pa03.model.ShipType;
import cs3500.pa03.view.InputGatherer;
import cs3500.pa03.view.Printer;
import java.util.ArrayList;
import java.util.HashMap;

public class InputParser {
  /**
   * Pareses and validates inputs gathered from the view into a list of coordinates.
   *
   * @return a list of coordinates
   */
  public static ArrayList<Coord> getListOfShots(ManualPlayer player, Readable in, Appendable out) {
    ArrayList<String> input =
        InputGatherer.getShots(player.getBoard().getShips().size(), in);
    ArrayList<Coord> coords = new ArrayList<>();
    if (input.size() != player.getBoard().getShips().size()) {
      Printer.show("Incorrect number of shots.", out);
      return InputParser.getListOfShots(player, in, out);
    } else {
      for (String s : input) {
        try {
          coords.add(new Coord(s));
        } catch (IllegalArgumentException e) {
          Printer.show("Invalid coordinate.", out);
          return InputParser.getListOfShots(player, in, out);
        }
      }
      return coords;
    }
  }

  /**
   * Parses and validates inputs gathered from the view a height and with to set up the game.
   *
   * @return an array of integers representing the height and width
   */
  public static ArrayList<Integer> getDimensions(Readable in, Appendable out) {
    String input = InputGatherer.getLine(in);
    ArrayList<Integer> dimensions = new ArrayList<>();
    if (input.split(" ").length != 2) {
      Printer.show("Incorrect number of dimensions.", out);
      return InputParser.getDimensions(in, out);
    } else {
      for (String s : input.split(" ")) {
        try {
          dimensions.add(Integer.parseInt(s));
        } catch (NumberFormatException e) {
          Printer.show("Invalid dimension.", out);
          return InputParser.getDimensions(in, out);
        }
      }
      return dimensions;
    }
  }

  /**
   * Parses and validates inputs gathered from the view into a map of ship specifications
   *
   * @return a map of ship specifications
   */
  public static HashMap<ShipType, Integer> getSpecifications(Readable in, Appendable out) {
    String input = InputGatherer.getLine(in);
    HashMap<ShipType, Integer> specifications = new HashMap<ShipType, Integer>();
    if (input.split(" ").length != 4) {
      Printer.show("Incorrect number of specifications.", out);
      return InputParser.getSpecifications(in, out);
    } else {
      int idx = 0;
      for (String s : input.split(" ")) {
        try {
          specifications.put(ShipType.getTypeFromNumber(idx), Integer.parseInt(s));
          idx++;
        } catch (NumberFormatException e) {
          Printer.show("Invalid specification.", out);
          return InputParser.getSpecifications(in, out);
        }
      }
      return specifications;
    }
  }
}
