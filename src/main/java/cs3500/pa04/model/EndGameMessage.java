package cs3500.pa04.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record EndGameMessage(@JsonProperty("end-game") GameResult result, String reason) {
}
