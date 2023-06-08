package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.model.Coord;

public record ShipJson(
    @JsonProperty("coord") Coord coord,
    @JsonProperty("length") int length,
    @JsonProperty("direction") String direction) {
}
