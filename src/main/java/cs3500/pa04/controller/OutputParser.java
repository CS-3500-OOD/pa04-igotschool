package cs3500.pa04.controller;

import cs3500.pa04.model.Board;
import cs3500.pa04.model.ManualPlayer;
import cs3500.pa04.view.BoardDisplayer;
import cs3500.pa04.view.Printer;

/**
 * Parses and validates outputs for the view to display
 */
public class OutputParser {

  /**
   * Displays the boards associated with the given player
   *
   * @param player the player whose boards to display
   */
  public static void showBoards(ManualPlayer player, Appendable out) {
    Board myBoard = player.getBoard();
    Board opponentBoard = player.getOpponentBoard();
    String[][] myBoardRepresentation = new String[myBoard.getNumRows()][myBoard.getNumCols()];
    String[][] opponentBoardRepresentation =
        new String[opponentBoard.getNumRows()][opponentBoard.getNumCols()];

    for (int i = 0; i < myBoard.getNumRows(); i++) {
      for (int j = 0; j < myBoard.getNumCols(); j++) {
        myBoardRepresentation[i][j] = myBoard.getBoard()[i][j].toString();
        if (myBoardRepresentation[i][j].equals("M")) {
          myBoardRepresentation[i][j] = "0";
        }
      }
    }

    for (int i = 0; i < opponentBoard.getNumRows(); i++) {
      for (int j = 0; j < opponentBoard.getNumCols(); j++) {
        opponentBoardRepresentation[i][j] = opponentBoard.getBoard()[i][j].toString();
      }
    }
    BoardDisplayer.displayBoard(myBoardRepresentation, opponentBoardRepresentation, out);
  }

  /**
   * Displays a message to the user
   *
   * @param message the message to display
   */
  public static void show(String message, Appendable out) {
    Printer.show(message, out);
  }

}
