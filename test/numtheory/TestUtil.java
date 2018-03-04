package numtheory;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestUtil {

  public TestUtil() {
    Util.generateSieve(5000);
  }

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
    assertEquals("Failed to factor 234",
            "[2^1, 3^2, 13^1]", Arrays.toString(Util.factor(234).toArray()));
    assertEquals("Failed to factor 1",
            "[]", Arrays.toString(Util.factor(1).toArray()));
    assertEquals("Failed to factor 239 (a prime number)",
            "[239^1]", Arrays.toString(Util.factor(239).toArray()));
    assertEquals("Failed to factor 256",
            "[2^8]", Arrays.toString(Util.factor(256).toArray()));

    assertEquals("Failed to factor 1",
            "[]", Arrays.toString(Util.factorNoRepeats(1).toArray()));
    assertEquals("Failed to factor 239 (a prime number)",
            "[239]", Arrays.toString(Util.factorNoRepeats(239).toArray()));
    assertEquals("Failed to factor 256 without repeats",
            "[2]", Arrays.toString(Util.factorNoRepeats(256).toArray()));
  }

  @Test
  public void testIsPrime() {
    assertFalse(Util.isPrime(1));
    assertFalse(Util.isPrime(4));
    assertFalse(Util.isPrime(2349));

    assertTrue(Util.isPrime(2));
    assertTrue(Util.isPrime(3));
    assertTrue(Util.isPrime(2347));
  }

  @Test
  public void testRad() {
    assertEquals("Radical of 1 should be 1",
            1, Util.radical(1));
    assertEquals("Radical of 13 should be 13",
            13, Util.radical(13));
    assertEquals("Radical of 13*13*13 = 2197 should be 13",
            13, Util.radical(2197));
    assertEquals("Radical of 2*3*5*7 = 210 should be 210",
            210, Util.radical(210));
    assertEquals("Radical of 2*2*3*3*3*5*7 = 3780 should be 210",
            210, Util.radical(3780));
  }

  @Test
  public void testPhi() {
    assertEquals("Phi of 1 should be 1",
            1, Util.phi(1));
    assertEquals("Phi of 2 should be 1",
            1, Util.phi(2));
    assertEquals("Phi of 13 should be 12",
            12, Util.phi(13));
    assertEquals("Phi of 16 should be 8",
            8, Util.phi(16));
    assertEquals("Phi of 1227 should be 816",
            816, Util.phi(1227));
    assertEquals("Phi of 3128 should be 1408",
            1408, Util.phi(3128));
  }

  @Test
  public void testSigma() {
    assertEquals("Sigma of 1 should be 1",
            1, Util.sigma(1));
    assertEquals("Sigma of 2 should be 3",
            3, Util.sigma(2));
    assertEquals("Sigma of 64 = 2^6 should be 127",
            127, Util.sigma(64));
    assertEquals("Sigma of 60 should be 168",
            168, Util.sigma(60));
    assertEquals("Sigma of 496 should be 992",
            496 * 2, Util.sigma(496));
  }

  @Test
  public void testGcd() {
    assertEquals(3, Util.gcd(3, 9));
    assertEquals(3, Util.gcd(3,3));
    assertEquals(1, Util.gcd(9, 8));
    assertEquals(1, Util.gcd(2342,3239));
    assertEquals(385, Util.gcd(2310, 5005));
  }

  @Test
  public void testSomeNumTheory() {

  }
}
