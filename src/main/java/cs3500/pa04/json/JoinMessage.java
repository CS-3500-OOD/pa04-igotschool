package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a message to join a game.
 *
 * @param name     the name of the player
 * @param gameType the type of game to join (SINGLE, MULTI)
 */
public record JoinMessage(@JsonProperty("name") String name,
                          @JsonProperty("game-type") String gameType) {

}
