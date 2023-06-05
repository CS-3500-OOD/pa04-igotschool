package cs3500.pa03.model;

import cs3500.pa03.controller.InputParser;
import cs3500.pa03.controller.OutputParser;
import cs3500.pa03.view.Printer;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ManualPlayer implements Player {
  private String name;

  private Board board;

  private Board opponentBoard;

  private ArrayList<Coord> lastVolley;

  private Readable in;

  private Appendable out = System.out;

  /**
   * Constructs a manual player with the given name.
   *
   * @param name the name of the player
   */
  public ManualPlayer(String name, Readable in) {
    this.name = name;
    this.in = in;
  }


  /**
   * Returns this player's board
   *
   * @return the board
   */
  public Board getBoard() {
    return this.board;
  }

  /**
   * Returns this player's opponent's board
   *
   * @return the opponent's board
   */
  public Board getOpponentBoard() {
    return this.opponentBoard;
  }

  @Override
  public String name() {
    return name;
  }

  @Override
  public List<Ship> setup(int height, int width, Map<ShipType, Integer> specifications) {
    this.opponentBoard = new Board(height, width);
    this.board = new Board(height, width);
    return this.board.setup(specifications);
  }

  @Override
  public List<Coord> takeShots() {
    ArrayList<Coord> shots = InputParser.getListOfShots(this,
        this.in, this.out);
    for (Coord c : shots) {
      if (c.getRow() >= this.opponentBoard.getNumRows() ||
          c.getCol() >= this.opponentBoard.getNumCols() ||
          c.getRow() < 0 || c.getCol() < 0) {
        OutputParser.show("Invalid coordinate: " + c, out);
        return takeShots();
      }
    }
    this.lastVolley = shots;
    return shots;
  }

  @Override
  public List<Coord> reportDamage(List<Coord> opponentShotsOnBoard) {
    return board.reportDamage(opponentShotsOnBoard);
  }

  @Override
  public void successfulHits(List<Coord> shotsThatHitOpponentShips) {
    for (Coord c : this.lastVolley) {
      this.opponentBoard.getCell(c).firedUpon();
    }
    this.opponentBoard.successfulHits(shotsThatHitOpponentShips);
  }

  @Override
  public void endGame(GameResult result, String reason) {
    Printer.show(this.name + " " + result + " because " + reason, out);
  }

  /**
   * Sets the out of this player to the given appendable
   */
  public void setOut(Appendable out) {
    this.out = out;
  }
}
