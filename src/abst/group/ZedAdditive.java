package abst.group;

import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Represents an integer group under addition modulo <i>n</i>.
 */
public class ZedAdditive extends Zed {

  /**
   * Create a new additive integer group modulo n. Size of the group will be n.
   *
   * @param modulus Modulo for the group to be generated with.
   */
  public ZedAdditive(int modulus) {
    super((x, y) -> (x + y) % modulus, makeElements(modulus));

    this.props.setOperator("+");
  }

  /**
   * Creates a set of integers modulo n.
   *
   * @param modulus Modulus
   * @return Sorted set of integers
   */
  private static SortedSet<Integer> makeElements(int modulus) {
    return IntStream.range(0, modulus).boxed()
            .collect(Collectors.toCollection(TreeSet::new));
  }
}
