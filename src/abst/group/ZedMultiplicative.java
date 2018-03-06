package abst.group;

import numtheory.Util;

/**
 * Represents a group with integer elements under multiplication modulo <i>n</i>.
 */
public class ZedMultiplicative extends Zed {
  /**
   * Create a new multiplicative integer group modulo n. Size of the group will be phi(n).
   *
   * @param modulus Modulo for the group to be generated with.
   */
  public ZedMultiplicative(int modulus) {
    super((x, y) -> (x * y) % modulus, Util.getReducedResidueSystem(modulus));
    this.props.setOperator("*");
  }
}
