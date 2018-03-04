package function;

import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.Function;

import function.binary.ExponentMod;
import function.binary.XPlusY;
import numtheory.Util;

import static org.junit.Assert.assertEquals;

/**
 * Tests from the function.binary package.
 */
public class TestBinary {

  @Test
  public void testLamb() {
    Util.generateSieve(5000);

    Function<Double, Double> square = (x) -> (x * x);
    BiFunction<Double, Double, Double> xplusy = new XPlusY();

    assertEquals("Adding function should add 7 + 3 to get 10",
            10d, xplusy.apply(7d, 3d), .01);
    assertEquals("Squaring function should square 7 to get 49",
            49d, square.apply(7d), .01);

    BiFunction<Integer, Integer, Integer> expMod = new ExponentMod(853);

    assertEquals("1 to any power is congruent to 1 in any modulo",
            1, (int) expMod.apply(1, 90000));
    assertEquals("Any base to the 1th power is congruent to base mod modulo",
            404, (int) expMod.apply(23435, 1));

    assertEquals("7^327 is congruent to 286 mod 853",
            286, (int) expMod.apply(7, 327));
    assertEquals("Adding a multiple of the modulus to the base or a multiple of " +
                    "phi(modulus) to the exponent shouldn't change anything",
            286, (int) expMod.apply(7 + 853, 327 + Util.phi(853)));
    assertEquals("Euler's identity to find inverse. a*a^(phi(m)-1) === 1 mod(m)",
            1, (7 * expMod.apply(7, Util.phi(853) - 1)) % 853);
  }
}
