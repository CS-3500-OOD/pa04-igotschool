package cs3500.pa04.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ShipJson(
    @JsonProperty("coord") Coord coord,
    @JsonProperty("length") int length,
    @JsonProperty("direction") String direction) {
}
