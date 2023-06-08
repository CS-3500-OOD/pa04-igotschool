package cs3500.pa04;


import cs3500.pa04.controller.ProxyController;
import cs3500.pa04.model.BattleSalvoGame;
import cs3500.pa04.model.ComputerPlayer;
import cs3500.pa04.model.Player;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

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
    if (args.length == 0) {
      BattleSalvoGame game = new BattleSalvoGame(new InputStreamReader(System.in));
      game.setup(System.out, new InputStreamReader(System.in), new InputStreamReader(System.in));
      game.playGame(System.out);
    } else if (args.length == 2) {
      try {
        ComputerPlayer player = new ComputerPlayer(System.out);

        String host = args[0];
        int port = Integer.parseInt(args[1]);
        Socket socket = new Socket(host, port);

        ProxyController proxyController = new ProxyController(player, socket);

        proxyController.run();
      } catch (IOException e) {
        System.out.println("Error connecting to server");
      }
    } else {
      System.out.println("Invalid number of arguments");
    }
  }
}