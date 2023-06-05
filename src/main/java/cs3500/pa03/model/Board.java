package cs3500.pa03.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Board {
  private Cell[][] board;

  // Stores ships that have been placed on the board and that are not sunk
  private ArrayList<Ship> ships = new ArrayList<>();

  private Random rand;


  /**
   * Constructs a board with the given number of rows and columns.
   *
   * @param rows the number of rows in this board
   * @param cols the number of columns in this board
   */
  public Board(int rows, int cols) {
    if (rows > 15 || rows < 6 || cols > 15 || cols < 6) {
      throw new IllegalArgumentException("Invalid board size. Please enter a board size between "
          + "6x6 and 15x15.");
    }
    this.board = new Cell[rows][cols];
    this.rand = new Random();
    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        this.board[row][col] = new Cell(row, col);
      }
    }
  }

  /**
   * Constructs a board with the given number of rows and columns and a random seed for testing.
   *
   * @param rows the number of rows in this board
   * @param cols the number of columns in this board
   * @param seed the random seed to use
   */
  public Board(int rows, int cols, int seed) {
    this(rows, cols);
    this.rand = new Random(seed);
  }

  /**
   * Returns the cell of this board that corresponds to the given coordinate
   *
   * @param coord the coordinate of the cell to return
   */
  public Cell getCell(Coord coord) {
    return this.board[coord.getRow()][coord.getCol()];
  }

  /**
   * Returns an ArrayList of coords from the given list that hit a ship.
   *
   * @param coords the list of coords to check
   * @return the list of coords that hit a ship
   */
  public ArrayList<Coord> reportDamage(List<Coord> coords) {
    ArrayList<Coord> hits = new ArrayList<>();
    // set ship cells to hit
    for (Coord coord : coords) {
      if (!(getCell(coord).getShip() instanceof Empty)) {
        hits.add(coord);
        getCell(coord).hit();
      }
    }
    removeSunkShips();
    return hits;
  }

  /**
   * Removes and sunk ship from this board's list of ships.
   */
  private void removeSunkShips() {
    ArrayList<Ship> shipsToRemove = new ArrayList<>();
    for (Ship ship : ships) {
      if (ship.isSunk()) {
        shipsToRemove.add(ship);
      }
    }
    ships.removeAll(shipsToRemove);
  }

  /**
   * Given a list of coordinates, sets the hit status of each corresponding cell in this board
   *
   * @param coords the list of coordinates to set the hit status of
   */
  public void successfulHits(List<Coord> coords) {
    for (Coord coord : coords) {
      getCell(coord).hit();
    }
  }

  /**
   * Returns the ships on this board.
   *
   * @return the ships on this board
   */
  public ArrayList<Ship> getShips() {
    return ships;
  }

  /**
   * Sets the ships on this board to the given list
   */
  public void setShips(ArrayList<Ship> ships) {
    this.ships = ships;
  }

  /**
   * Sets up this board with the given specifications.
   *
   * @param specifications a map of ship type to the number of occurrences each ship should
   * @return the placements of each ship on the board
   */
  public ArrayList<Ship> setup(Map<ShipType, Integer> specifications) {
    if (!isValidNumberOfShips(specifications)) {
      throw new IllegalArgumentException("Invalid number of ships. Please enter a number of "
          + "ships no grater than the smallest dimension of the board and have at least one "
          + "of each ship.");
    }
    ArrayList<Ship> shipsToPlace = getShipsToPlace(specifications);
    for (Ship ship : shipsToPlace) {
      placeShip(ship);
      ships.add(ship);
    }
    return ships;
  }

  /**
   * Returns true if the number of ships given is valid for this board.
   *
   * @param specifications a map of ship type to the number of occurrences each ship should
   * @return true if the number of ships given is valid for this board
   */
  private boolean isValidNumberOfShips(Map<ShipType, Integer> specifications) {
    int totalShips = 0;
    for (ShipType type : specifications.keySet()) {
      if (specifications.get(type) <= 0) {
        System.out.println(specifications.get(type));
        return false;
      }
      totalShips += specifications.get(type);
    }
    return totalShips <= this.getNumRows() && totalShips <= this.getNumCols();
  }

  /**
   * Given a map of specifications, returns an arraylist of ships to place.
   *
   * @param specifications a map of ship type to the number of occurrences each ship should
   * @return an arraylist of ships to place
   */
  private ArrayList<Ship> getShipsToPlace(Map<ShipType, Integer> specifications) {
    ArrayList<Ship> shipsToPlace = new ArrayList<>();
    for (ShipType type : specifications.keySet()) {
      for (int i = 0; i < specifications.get(type); i++) {
        shipsToPlace.add(type.getShip());
      }
    }
    shipsToPlace.sort((s1, s2) -> s2.getLength() - s1.getLength());
    return shipsToPlace;
  }

  /**
   * Places a given ship on the board in a random, valid position.
   *
   * @param ship the ship to place
   */
  private void placeShip(Ship ship) {
    // Gets cells that are empty
    ArrayList<Cell> unpopulated = getUnpopulated();
    // While loop control
    boolean shipPlaced = false;
    while (!shipPlaced) {
      if (unpopulated.size() == 0) {
        throw new IllegalArgumentException("No more unpopulated cells");
      }
      // Random orientation
      boolean vertical = rand.nextBoolean();
      // Get random cell and generate possible ship locations from it
      Cell randomCell = unpopulated.remove(rand.nextInt(unpopulated.size()));
      // all possible cells that the ship can be placed in vertically and horizontally
      ArrayList<Cell> consecutiveVertical = getVerticalConsecutive(randomCell);
      ArrayList<Cell> consecutiveHorizontal = getHorizontalConsecutive(randomCell);

      // Remove these cells from possible cells to search
      for (Cell c : consecutiveVertical) {
        unpopulated.remove(c);
      }
      for (Cell c : consecutiveHorizontal) {
        unpopulated.remove(c);
      }

      // If horizontal or vertical are long enough, randomly place the ship within, else, try again
      int shipCellIdx = 0;
      // throw some randomness in there
      if (consecutiveVertical.size() >= ship.getLength() && vertical) {
        placeShipInArray(consecutiveVertical, ship);
        shipPlaced = true;
      } else if (consecutiveHorizontal.size() >= ship.getLength()) {
        placeShipInArray(consecutiveHorizontal, ship);
        shipPlaced = true;
      }
    }
  }

  private void placeShipInArray(ArrayList<Cell> list, Ship ship) {
    int shipCellIdx = 0;
    int index = rand.nextInt(list.size() - ship.getLength() + 1);
    // corrects for issues at low dimensions
    if (board[0].length == 6 || board.length == 6) {
      index = 0;
    }
    for (int i = index; i < index + ship.getLength(); i++) {
      list.get(i - index).setShip(ship);
      ship.setCell(shipCellIdx, list.get(i - index));
      shipCellIdx++;
    }
  }

  /**
   * Returns and ArrayList of cells that are horizontally consecutive to the given cell.
   *
   * @param cell the cell to check
   * @return an ArrayList of cells that are horizontally consecutive to the given cell
   */
  private ArrayList<Cell> getHorizontalConsecutive(Cell cell) {
    ArrayList<Cell> consecutive = new ArrayList<>();
    int row = cell.getCoord().getRow();
    int col = cell.getCoord().getCol() - 1;
    while (col >= 0 && board[row][col].getShip() instanceof Empty) {
      consecutive.add(board[row][col]);
      col--;
    }
    col = cell.getCoord().getCol();
    while (col < board[0].length && board[row][col].getShip() instanceof Empty) {
      consecutive.add(board[row][col]);
      col++;
    }
    return consecutive;
  }

  /**
   * Returns and ArrayList of cells that are horizontally consecutive to the given cell.
   *
   * @param cell the cell to check
   * @return an ArrayList of cells that are horizontally consecutive to the given cell
   */
  private ArrayList<Cell> getVerticalConsecutive(Cell cell) {
    ArrayList<Cell> consecutive = new ArrayList<>();
    int row = cell.getCoord().getRow() - 1;
    int col = cell.getCoord().getCol();
    while (row >= 0 && board[row][col].getShip() instanceof Empty) {
      consecutive.add(board[row][col]);
      row--;
    }
    row = cell.getCoord().getRow();
    while (row < board.length && board[row][col].getShip() instanceof Empty) {
      consecutive.add(board[row][col]);
      row++;
    }
    return consecutive;
  }

  /**
   * Returns the unpopulated cells on this board.
   *
   * @return the unpopulated cells on this board
   */
  private ArrayList<Cell> getUnpopulated() {
    ArrayList<Cell> unpopulated = new ArrayList<>();
    for (Cell[] row : board) {
      for (Cell cell : row) {
        if (cell.getShip() instanceof Empty) {
          unpopulated.add(cell);
        }
      }
    }
    return unpopulated;
  }

  /**
   * Returns this board
   *
   * @return this board
   */
  public Cell[][] getBoard() {
    return board;
  }

  /**
   * Returns the number of rows on this board.
   *
   * @return the number of rows on this board
   */
  public int getNumRows() {
    return board.length;
  }

  /**
   * Returns the number of columns on this board.
   *
   * @return the number of columns on this board
   */
  public int getNumCols() {
    return board[0].length;
  }
}
