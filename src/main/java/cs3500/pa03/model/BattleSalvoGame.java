package cs3500.pa03.model;

import cs3500.pa03.controller.InputParser;
import cs3500.pa03.controller.OutputParser;
import cs3500.pa03.view.BoardDisplayer;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Represents and initializes a game of BattleSalvo.
 */
public class BattleSalvoGame {
  private ManualPlayer manualPlayer;
  private ComputerPlayer computerPlayer;

  private GameResult result = GameResult.IN_PROGRESS;

  /**
   * Constructs a BattleSalvoGame.
   */
  public BattleSalvoGame(Readable in) {
    this.manualPlayer = new ManualPlayer("Player", in);
    this.computerPlayer = new ComputerPlayer();
  }

  /**
   * Initializes the game boards for each player
   */
  public void setup(Appendable out, Readable in1, Readable in2) {
    OutputParser.show("Welcome to BattleSalvo!\nPlease enter a valid height and width:\n",
        out);
    ArrayList<Integer> dimensions = InputParser.getDimensions(in1, out);
    OutputParser.show("Please enter the number of each type of ship in order of [Carrier, "
        + "Battleship, Destroyer, Submarine]:\n", out);
    HashMap<ShipType, Integer> specifications =
        InputParser.getSpecifications(in2, out);
    this.manualPlayer.setup(dimensions.get(0), dimensions.get(1), specifications);
    this.computerPlayer.setup(dimensions.get(0), dimensions.get(1), specifications);
  }

  public void playGame(Appendable out) {
    while (this.result == GameResult.IN_PROGRESS) {
      OutputParser.showBoards(manualPlayer, out);
      if (this.manualPlayer.getBoard().getShips().size() == 0) {
        this.result = GameResult.LOSE;
        manualPlayer.endGame(this.result, "Your ships sunk!");
        break;
      }
      if (this.computerPlayer.getBoard().getShips().size() == 0) {
        this.result = GameResult.WIN;
        manualPlayer.endGame(this.result, "You sunk all their ships!");
        break;
      }
      OutputParser.show("Please enter valid coordinates to fire at:\n", out);
      List<Coord> playerShots = manualPlayer.takeShots();
      OutputParser.show("Shots firing...\n", out);
      List<Coord> computerShots = computerPlayer.takeShots();
      manualPlayer.successfulHits(computerPlayer.reportDamage(playerShots));
      computerPlayer.successfulHits(manualPlayer.reportDamage(computerShots));
    }
  }

  /**
   * Returns the manual player
   *
   * @return the manual player
   */
  public ManualPlayer getManualPlayer() {
    return this.manualPlayer;
  }

  /**
   * Returns the computer player
   *
   * @return the computer player
   */
  public ComputerPlayer getComputerPlayer() {
    return this.computerPlayer;
  }

  /**
   * Gets the game result
   *
   * @return the game result
   */
  public GameResult getResult() {
    return this.result;
  }

}
