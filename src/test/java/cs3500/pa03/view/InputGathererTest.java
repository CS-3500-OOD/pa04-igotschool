package cs3500.pa03.view;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.StringReader;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class InputGathererTest {
  /**
   * Tests the getShots method
   */
  @Test
  void testGetShots() {
    ArrayList<String> shots = new ArrayList<>();
    shots.add("1 1");
    shots.add("2 2");
    shots.add("1 3");
    assertEquals(shots, InputGatherer.getShots(3,
        new StringReader("1 1\n2 2\n1 3\n")));
  }

  /**
   * Tests the getLine method
   */
  @Test
  void testGetLine() {
    assertEquals("1 1", InputGatherer.getLine(new StringReader("1 1\n")));
  }
}