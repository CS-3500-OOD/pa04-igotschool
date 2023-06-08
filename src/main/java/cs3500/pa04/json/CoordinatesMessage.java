package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.model.Coord;


public record CoordinatesMessage(@JsonProperty("coordinates") Coord[] coordinates) {

  /**
   * Gets the coordinates of this message
   *
   * @return the coordinates
   */
  public Coord[] getCoordinates() {
    return coordinates;
  }
}
