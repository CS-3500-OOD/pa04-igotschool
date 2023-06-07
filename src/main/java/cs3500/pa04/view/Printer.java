package cs3500.pa04.view;

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
