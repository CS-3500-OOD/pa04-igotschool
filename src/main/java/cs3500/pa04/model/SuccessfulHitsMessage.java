package cs3500.pa04.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SuccessfulHitsMessage(@JsonProperty("successful-hits") Coord[] coordinates) {
}
