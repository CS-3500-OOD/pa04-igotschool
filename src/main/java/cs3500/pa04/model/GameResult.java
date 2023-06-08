package cs3500.pa04.model;

public enum GameResult {
  WIN,
  LOSE,
  DRAW,
  IN_PROGRESS;

  /**
   * Returns a GameResult from a String
   *
   * @param result the String to convert to a GameResult
   * @return the GameResult
   */
  public static GameResult fromString(String result) {
    switch (result) {
      case "WIN":
        return WIN;
      case "LOSE":
        return LOSE;
      case "DRAW":
        return DRAW;
      case "IN_PROGRESS":
        return IN_PROGRESS;
      default:
        throw new IllegalArgumentException("Invalid GameResult");
    }
  }
}
