package cs3500.pa03.view;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;

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
    } catch (Exception e) {
      throw new IllegalArgumentException("Invalid output stream");
    }
  }
}
