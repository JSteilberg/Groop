package abst.group.element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DihedralElem {
  private List<Factor> components;
  private int rotationOrder;

  public DihedralElem fromRotation(int rotationOrder, int rotationExponent) {
    List<Factor> comps = new ArrayList<>();
    comps.add(new Factor(Symbol.SIGMA, new Expon(rotationExponent)));
    return new DihedralElem(rotationOrder, comps);
  }

  private DihedralElem() {
  }

  private DihedralElem(int rotationOrder) {
    this.rotationOrder = rotationOrder;
  }

  private DihedralElem(int rotationOrder, List<Factor> components) {
    this.rotationOrder = rotationOrder;
    this.components = components;
  }


  public void simplify() {

  }

  /**
   * Returns the resulting simplified {@code DihedralElem} when this element is appended to the
   * given element.
   *
   * Example: στ.of(τ) -> στ² -> σ.
   *
   * @param other {@code DihedralElem} to append this element to
   * @return Resulting {@code DihedralElem} of composing this element with the given element
   */
  //public DihedralElem of(DihedralElem other) {

//  }


  /**
   * Prints the element as a combination of sigma and tau, where sigma is a rotation and tau
   * is a flip.
   *
   * @return String representation of the element
   */
  @Override
  public String toString() {
    if (components.size() > 2) {
      this.simplify();
    }
    String ans = "";
    for (Factor f : this.components) {
      ans += f.toString();
    }
    return ans;
  }

  /**
   * Quite similar to {@link numtheory.Util.Factor}, this holds a list of elements ({@code Symbol})
   * and their exponents ({@code Expon}).
   */
  public class Factor {
    private final Symbol symb;
    private final Expon expon;

    public Factor(Symbol symbol, Expon exponent) {
      this.symb = symbol;
      this.expon = exponent;
    }



    @Override
    public String toString() {
      return symb.toString() + expon.toString();
    }
  }

  /**
   * Represents the two components of an element in a Dihedral group.
   * Sigma is a rotation counter clockwise, and tau is a flip across the
   * vertical line of symmetry.
   */
  public enum Symbol {
    SIGMA("\u03A3"), TAU("\u03A4");

    private final String symbol;

    Symbol(String symbol) {
      this.symbol = symbol;
    }

    @Override
    public String toString() {
      return this.symbol;
    }
  }

  /**
   * Each component of an element in the dihedral group has some exponent.
   * The exponent represents how many of the functions are composed together.
   */
  public static class Expon {
    /**
     * Holds the unicode representation of numerical exponents 0-9
     */
    private static final Map<Integer, String> representation;

    static {
      representation = new HashMap<>();
      representation.put(0, "\u2070");
      representation.put(1, "\u00B9");
      representation.put(2, "\u00B2");
      representation.put(3, "\u00B3");
      representation.put(4, "\u2074");
      representation.put(5, "\u2075");
      representation.put(6, "\u2076");
      representation.put(7, "\u2077");
      representation.put(8, "\u2078");
      representation.put(9, "\u2079");
    }

    private final int value;

    /**
     * Create a new Expon with specified value.
     *
     * @param value Value of the exponent
     */
    public Expon(int value) {
      this.value = value;
    }

    public int getVal() {
      return this.value;
    }

    @Override
    public String toString() {
      String ans = Integer.toString(this.value);
      for (Integer i = 0; i < 10; ++i) {
        ans = ans.replaceAll(i.toString(), representation.get(i));
      }
      return ans;
    }

    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Expon)) {
        return false;
      }

      Expon otherCast = (Expon) other;
      return otherCast.value == this.value;
    }
  }
}
