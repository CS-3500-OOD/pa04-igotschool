package cs3500.pa04.model;

import cs3500.pa04.controller.OutputParser;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ComputerPlayer implements Player {
  private Board board;

  private Board opponentBoard;

  private Random rand = new Random();

  private ArrayList<Coord> shotsMade = new ArrayList<>();

  private Appendable out;

  /**
   * Constructs a computer player
   */
  public ComputerPlayer(Appendable out) {
    this.out = out;
  }


  /**
   * Constructs a computer player with the given random see for testing
   *
   * @param seed the random seed
   */
  public ComputerPlayer(int seed) {
    this.rand = new Random(seed);
  }

  @Override
  public String name() {
    return "marbleville";
  }

  @Override
  public List<Ship> setup(int height, int width, Map<ShipType, Integer> specifications) {
    this.opponentBoard = new Board(height, width);
    this.board = new Board(height, width);
    return this.board.setup(specifications);
  }

  @Override
  public List<Coord> takeShots() {
    ArrayList<Coord> shots = new ArrayList<>();
    ArrayList<Cell> possibleShots = this.board.getUnfiredCells();

    int numShots = Math.min(this.board.getShips().size(), possibleShots.size());

    for (int i = 0; i < numShots; i++) {
      shots.add(possibleShots.get(rand.nextInt(possibleShots.size())).getCoord());
    }
    return shots;
  }

  @Override
  public List<Coord> reportDamage(List<Coord> opponentShotsOnBoard) {
    return board.reportDamage(opponentShotsOnBoard);
  }

  @Override
  public void successfulHits(List<Coord> shotsThatHitOpponentShips) {
    this.opponentBoard.successfulHits(shotsThatHitOpponentShips);
  }

  @Override
  public void endGame(GameResult result, String reason) {
    OutputParser.show("Your AI " + result.toString() + " because " + reason, out);
  }

  /**
   * Returns this players board
   *
   * @return this players board
   */
  public Board getBoard() {
    return this.board;
  }

  /**
   * Returns this players opponent board
   *
   * @return this players opponent board
   */
  public Board getOpponentBoard() {
    return this.opponentBoard;
  }
}
