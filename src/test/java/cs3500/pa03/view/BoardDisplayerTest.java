package cs3500.pa03.view;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class BoardDisplayerTest {
  /**
   * Tests the displayBoard method
   */
  @Test
  void testDisplayBoard() {
    StringBuilder out = new StringBuilder();
    String[][] myBoard = new String[][] {{"X", "X", "X"}, {"X", "X", "X"}, {"X", "X", "X"}};
    String[][] oppBoard = new String[][] {{"X", "X", "X"}, {"X", "X", "X"}, {"X", "X", "X"}};
    BoardDisplayer.displayBoard(myBoard, oppBoard, out);
    assertEquals(
        "Opponent's Board:\nX X X \nX X X \nX X X \n\nYour Board:\nX X X \nX X X \nX X X \n",
        out.toString());
  }
}