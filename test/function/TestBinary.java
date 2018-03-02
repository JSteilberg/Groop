package function;

import org.junit.Test;

import function.binary.XPlusY;

import static org.junit.Assert.assertEquals;

/**
 * Tests instances of the {@link BinaryFunc} class.
 */
public class TestBinary {

  @Test
  public void testLamb() {
    UnaryFunc<Double, Double> f = (x) -> (x * x);
    BinaryFunc<Double, Double, Double> fex = (x, y) -> x + y;
    BinaryFunc<Double, Double, Double> xPl = new XPlusY();
    assertEquals("ree", new Double(10d), new Double(xPl.apply(7d, 3d)));
    assertEquals("ree", new Double(49), f.apply(7d));
  }
}
