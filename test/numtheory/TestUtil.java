package numtheory;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class TestUtil {

  @Test
  public void testSieve() {
    Util.generateSieve(30);
    final boolean f = false;
    final boolean t = true;

    //                    0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14
    boolean[] expSieve = {f, f, t, t, f, t, f, t, f, f, f,  t,  f,  t,  f,
            //15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29
              f,  f,  t,  f,  t,  f,  f,  f,  t,  f,  f,  f,  f,  f,  t};

    int[] expPrimes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29};

    assertArrayEquals("Failed to generate correct prime numbers under 30",
            expPrimes, Util.getPrimes());
    assertArrayEquals("Failed to generate a correct Sieve of Eratosthenes under 30",
            expSieve, Util.getSieve());
  }

  @Test
  public void testFactor() {
    Util.generateSieve(5000);

    assertEquals("Failed to factor 234",
            "[2^1, 3^2, 13^1]", Arrays.toString(Util.factor(234).toArray()));
    assertEquals("Failed to factor 1",
            "[]", Arrays.toString(Util.factor(1).toArray()));
    assertEquals("Failed to factor 239 (a prime number)",
            "[239^1]", Arrays.toString(Util.factor(239).toArray()));
    assertEquals("Failed to factor 256",
            "[2^8]", Arrays.toString(Util.factor(256).toArray()));
  }
}
