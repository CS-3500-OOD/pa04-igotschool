package cs3500.pa03;


import cs3500.pa03.model.BattleSalvoGame;
import java.io.InputStreamReader;

/**
 * This is the main driver of this project.
 */
public class Driver {
  /**
   * Project entry point
   *
   * @param args - no command line args required
   */
  public static void main(String[] args) {
    BattleSalvoGame game = new BattleSalvoGame(new InputStreamReader(System.in));
    game.setup(System.out, new InputStreamReader(System.in), new InputStreamReader(System.in));
    game.playGame(System.out);
  }
}