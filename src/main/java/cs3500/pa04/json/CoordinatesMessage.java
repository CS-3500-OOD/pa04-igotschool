package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.model.Coord;

/**
 * Represents a message containing coordinates.
 *
 * @param coordinates the coordinates
 */
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
