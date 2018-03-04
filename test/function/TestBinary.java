package function;

import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.Function;

import function.binary.XPlusY;

import static org.junit.Assert.assertEquals;

/**
 * Tests from the function.binary package.
 */
public class TestBinary {

  @Test
  public void testLamb() {
    Function<Double, Double> square = (x) -> (x * x);
    BiFunction<Double, Double, Double> xplusy = new XPlusY();

    assertEquals("ree", 10d, xplusy.apply(7d, 3d), .01);
    assertEquals("ree", 49d, square.apply(7d), .01);
  }
}
