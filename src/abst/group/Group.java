package abst.group;

import java.util.List;
import java.util.Set;

/**
 * Represents the mathematical object called a group.
 * <p>Some terminology:
 * <ul>
 *   <li><i>G</i> will be used to refer to this {@code Group}</li>
 *   <li><i>g</i> will be used to refer to some element of <i>G</i> <br>
 *   &nbsp;&nbsp;When multiple elements are needed,
 *   they will be indexed <i>g<sub>1</sub>, g<sub>2</sub>, </i>etc.</li>
 *   <li><i>e</i> will be used to refer to the identity element of G.</li>
 *   <li><i>H</i> and/or <i>K</i> will be used to refer to some subgroup of <i>G</i>.</li>
 *   <li><i>h, k</i>, possibly indexed, will be used to refer to elements of H and K.</li>
 * </ul>
 * <p>A group is defined as a nonempty set endowed with a binary operation, such that <ul>
 *   <li>Multiplying any element by any other element results in an element of the group</li>
 *   <li>There exists an element called the identity, e, such that for any element a
 *       <p><i>a * e = e * a = a.</i></li>
 *   <li>Every element <i>a</i> has a unique inverse
 *       <i>a-1</i> such that <i>a * a<sup>-1</sup>= a<sup>-1</sup> * a = e.</i></li>
 * </ul>
 *
 * @param <E> The type of the elements in this {@code Group}
 */
public interface Group<E> {

  /**
   * Creates a new {@code Group} composed of all combinations between an element
   * from this {@code Group} and the given {@code Group}.
   *
   * @param other Other {@code Group} to product with this {@code Group}
   * @return A new {@code Group} composed of this {@code Group} and the given {@code Group}
   */
  Group extProduct(Group other);

  /**
   * A conjugate of <i>g<sub><sub>1</sub></sub></i> by <i>g<sub><sub>2</sub></sub></i> is defined as
   * <i>g<sub><sub>1</sub></sub> * g<sub><sub>2</sub></sub> * g<sub>1</sub><sup>-1</sup></i>.
   *
   * @param elem Element to conjugate
   * @param conjugateBy Element to conjugate by
   * @return The conjugate of {@code elem} by {@code conjugateBy}
   */
  E conjugate(E elem, E conjugateBy);

  /**
   * Calculates the number of elements in <i>G</i>.
   *
   * @return the order of <i>G</i>
   */
  int order();

  /**
   * Calculates the order of some <i>g</i> in <i>G</i>. That is, the number of times one must
   * multiply <i>g</i> by itself to obtain <i>e</i>.
   *
   * @param elem Element of <i>G</i>
   * @return Order of given element
   */
  int order(E elem);

  /**
   * Returns the set of cosets for a given subgroup.
   * Cosets are not necessarily groups.
   *
   * @param subGroup Subgroup to generate cosets from
   * @return A set of all cosets of the subgroup
   */
  Set<Coset<E>> cosets(Group<E> subGroup);

  /**
   * Returns the elements of <i>G</i>.
   *
   * @return Set containing the elements of <i>G</i>
   */
  Set<E> getElements();

  /**
   * Applies <i>G</i>'s binary operation to two elements.
   *
   * @param elem1 Element on the left hand side of the operation
   * @param elem2 Element on the right hand side of the operation
   * @return Result of the operation
   */
  E operation(E elem1, E elem2);

  /**
   * Returns some string representation of <i>G</i>. May be a Cayley table, may not be, depending
   * on whether <i>G</i> is easily expressed as a Cayley table.
   *
   * @return String representation of G
   */
  String toString();

  /**
   * Returns <i>G</i> represented as a Cayley table. May not work for infinite groups, etc. I don't
   * know yet.
   *
   * @return String containing a Cayley table of G
   */
  String cayleyTable();
}
