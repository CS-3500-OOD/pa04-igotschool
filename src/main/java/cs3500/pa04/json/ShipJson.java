package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.model.Coord;

/**
 * Represents a ship in JSON format.
 *
 * @param coord     the coordinate of the ship's bow
 * @param length    the length of the ship
 * @param direction the direction of the ship
 */
public record ShipJson(
    @JsonProperty("coord") Coord coord,
    @JsonProperty("length") int length,
    @JsonProperty("direction") String direction) {
}
