package cs3500.pa03.view;

public class BoardDisplayer {
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
