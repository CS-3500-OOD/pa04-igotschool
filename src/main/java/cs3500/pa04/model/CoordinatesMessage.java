package cs3500.pa04.model;

import com.fasterxml.jackson.annotation.JsonProperty;


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
