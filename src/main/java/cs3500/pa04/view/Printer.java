package cs3500.pa04.view;

import java.io.IOException;

/**
 * Displays information to the user
 */
public class Printer {
  /**
   * Shows a massage to the user
   *
   * @param message the message to show
   * @param out     the output stream to write to
   */
  public static void show(String message, Appendable out) {
    try {
      out.append(message);
    } catch (IOException e) {
      throw new IllegalArgumentException("Invalid output stream");
    }
  }
}
