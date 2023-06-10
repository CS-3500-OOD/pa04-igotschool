package cs3500.pa04.view;

/**
 * Displays information to the user about the board
 */
public class BoardDisplayer {
  /**
   * Displays the board to the user
   *
   * @param myBoard       the board of the player
   * @param opponentBoard the board of the opponent
   * @param out           the output
   */
  public static void displayBoard(String[][] myBoard, String[][] opponentBoard, Appendable out) {
    Printer.show("Opponent's Board:\n", out);
    for (String[] row : opponentBoard) {
      for (String cell : row) {
        Printer.show(cell + " ", out);
      }
      Printer.show("\n", out);
    }
    Printer.show("\n", out);
    Printer.show("Your Board:\n", out);
    for (String[] row : myBoard) {
      for (String cell : row) {
        Printer.show(cell + " ", out);
      }
      Printer.show("\n", out);
    }
  }
}
