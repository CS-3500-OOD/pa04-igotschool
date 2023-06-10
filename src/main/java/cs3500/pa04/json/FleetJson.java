package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a fleet in JSON format.
 *
 * @param fleet the ships in the fleet
 */
public record FleetJson(@JsonProperty("fleet") ShipJson[] fleet) {
}
