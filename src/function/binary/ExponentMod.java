package function.binary;

import java.util.Objects;
import java.util.function.BinaryOperator;

import numtheory.Util;

public class ExponentMod implements BinaryOperator<Integer> {
  private final int modulus;
  private final int phiModulus;

  public ExponentMod(int modulus) {
    if (modulus <= 0) {
      throw new IllegalArgumentException("Given a negative or zero modulus");
    }

    this.modulus = modulus;
    this.phiModulus = Util.phi(modulus);
  }

  /**
   * Computes the power of base to the exponent mod modulus, using the method of
   * successive squaring.
   *
   * @param base     Base of the exponentiation
   * @param exponent Exponent of the exponentiation
   * @return base<sup>exponent</sup> mod modulus
   */
  @Override
  public Integer apply(Integer base, Integer exponent) {
    Objects.requireNonNull(base);
    Objects.requireNonNull(exponent);

    // Trivial
    if (base == 1) {
      return 1;
    }

    // Also trivial
    if (exponent == 1) {
      return base % this.modulus;
    }

    // Equivalent statement. Allows one to express the inverse of a number by giving
    // exponent -1, since it will become phi(modulus) - 1
    if (exponent < 0) {
      exponent %= this.phiModulus;
    } else if (exponent == 0) {
      return 0;
    }

    // We make a copy of the exponent for shifting, at the same time we mod by
    // phi(modulus), since by Euler's theorem the answer will remain unchanged
    int shiftableExponent = exponent % phiModulus;

    // Accumulates the answer
    int prodAns = 1;

    // Holds the base in subsequent successive squares
    int currExp = base % modulus;

    // Used for counting the for loop -- We need to calculate every power of two exponent
    // up to the highest one that is smaller then our given exponent.
    // For example, if we are given an exponent of 327, we need to calculate up to 256
    final int powOf2 = Integer.highestOneBit(exponent);
    // Basically, we loop through each bit in the exponent, noting whether it is zero or one,
    for (int i = 1; i <= powOf2; i *= 2) {
      // If the lowest bit is one then we include it that power of the base in the final product
      if (Integer.lowestOneBit(shiftableExponent) == 1) {
        prodAns = (prodAns * currExp) % modulus;
      }
      currExp = (int) (Math.pow(currExp, 2)) % modulus;
      shiftableExponent >>= 1;
    }
    return prodAns;
  }
}
