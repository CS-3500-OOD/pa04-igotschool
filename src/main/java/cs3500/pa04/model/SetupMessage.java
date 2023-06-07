package cs3500.pa04.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SetupMessage(@JsonProperty("fleet") Ship[] fleet) {
}
