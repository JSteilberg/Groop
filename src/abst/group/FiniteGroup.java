package abst.group;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.SortedSet;
import java.util.function.BinaryOperator;

/**
 * Represents a group with a finite order.
 *
 * @param <T> Type of element for the group
 */
public abstract class FiniteGroup<T> implements Group<T> {
  protected BinaryOperator<T> op;
  protected SortedSet<T> elements;
  protected GroupProps props;
  T identity;
  protected Map<T, T> inverses;

  /**
   * Create a new {@code FiniteGroup} with a given operation and set of elements.
   *
   * @param operation Binary function for the group to use as its operation
   * @param elements Set of elements that the given function operates upon
   */
  public FiniteGroup(BinaryOperator<T> operation, SortedSet<T> elements) {
    Objects.requireNonNull(elements);
    Objects.requireNonNull(operation);

    this.props = new GroupProps();
    this.props.setFinite(true);

    this.elements = elements;
    this.op = operation;

    this.identity = this.findIdentity();
    this.inverses = this.findInverses();
  }

  /**
   * Construct a group in a similar fashion to above, but without the need to search for the
   * identity element.
   *
   * @param operation Binary function for the group to use as its operation
   * @param elements Set of elements that the given binary function operates upon
   * @param identity Identity element for the group
   */
  protected FiniteGroup(BinaryOperator<T> operation, SortedSet<T> elements, T identity) {
    Objects.requireNonNull(elements);
    Objects.requireNonNull(operation);
    Objects.requireNonNull(identity);

    this.props = new GroupProps();
    this.props.setFinite(true);

    this.elements = elements;
    this.op = operation;
    this.inverses = this.findInverses();

    this.identity = identity;
  }

  /**
   * Searches for all the inverses of every element in the group. Also does some minor error
   * checking if some group axioms are broken (e.g. commutativity of inverse).
   * Note: This is a general and very slow method; it is highly recommended to override this
   * when subclassing.
   *
   * TODO: This needs to have error checking tested more.
   *
   * @return Map where every element in the group corresponds to its inverse
   * @throws InvalidGroupException if multiple inverse elements are found or an element fails
   * to commute with its inverse
   */
  private Map<T, T> findInverses() {
    Map<T, T> ans = new HashMap<>();
    List<T> elems = new ArrayList<>(this.elements);

    // We are going to loop through every element in the group and product it with every other
    // element in order to find the inverse
    for(int i = 0; i < elems.size(); ++i) {
      T currElem = elems.get(i);
      if(ans.containsKey(currElem)) {
        continue;
      }
      for (T compElem : elems) {
        if (this.op.apply(currElem, compElem).equals(this.identity)) {
          // Check that the inverse commutes
          if (this.op.apply(compElem, currElem).equals(this.identity)) {
            // Check that if we already have the element then the corresponding inverse is
            // correct. Should catch when there are multiple inverses.
            if((ans.containsKey(currElem) && !ans.get(currElem).equals(compElem))
                    || ans.containsKey(compElem) && (!ans.get(compElem).equals(currElem))) {
              throw new InvalidGroupException("Some elements have multiple inverses");
            }
            // Put the element with its inverse
            ans.put(currElem, compElem);
            // And vice versa
            ans.put(compElem, currElem);
          } else {
            throw new InvalidGroupException(currElem + " * " + compElem + " = identity, " +
                    "but" + compElem + " * " + currElem + " =/= identity");
          }
        }
      }
    }
    return ans;
  }

  protected void setPropertiesManual() {
    this.props.setAbelian(this.testAbelian());
    this.props.setOrder(this.elements.size());
  }

  /**
   * Tests whether the group is abelian in a very general and brute forced manner.
   *
   * @return True if the group is abelian, false otherwise.
   */
  private boolean testAbelian() {
    // Loop through every element, compare it with every other element, make sure
    // they commute. Pretty simple.
    for(T elem : this.elements) {
      for(T otherElem : this.elements) {
        if(!this.op.apply(elem, otherElem)
                .equals(this.op.apply(otherElem, elem))) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Uses a general but slow method to find the identity element of the Group.
   * Recommended to override.
   *
   * TODO: Need to break up the single thrown exception into multiple more specific exceptions.
   *
   * @return The identity element of the group
   * @throws InvalidGroupException If multiple identities are found, or some other error that causes
   * the appearance of multiple identities
   */
  private T findIdentity() {
    Iterator<T> it = this.elements.iterator();
    T possibIdentity = null;
    for (T elem : this.elements) {
      if (this.operation(elem, elem).equals(elem)) {
        if (possibIdentity == null) {
          possibIdentity = elem;
        } else {
          throw new InvalidGroupException("Multiple identity elements found");
        }
      }
    }
    if (possibIdentity == null) {
      throw new InvalidGroupException("Group missing an identity element");
    } else {
      // Check that the identity follows correct properties for all elements in the group
      for (T elem : this.elements) {
        // Identity must commute
        if (op.apply(possibIdentity, elem)
                .equals(op.apply(elem, possibIdentity))) {
          // Identity * any element should be the same element
          if (!op.apply(possibIdentity, elem).equals(elem)) {
            throw new InvalidGroupException(elem + " * identity =/= " + elem);
          }
        } else {
          throw new InvalidGroupException("Identity element failed to commute with " + elem);
        }
      }
    }
    return possibIdentity;
  }

  @Override
  public T operation(T left, T right) {
    return this.op.apply(left, right);
  }

  @Override
  public Group extProduct(Group other) {
    return null;
  }

  @Override
  public T getIdentity() {
    return this.identity;
  }

  @Override
  public int order() {
    return elements.size();
  }

  @Override
  public T conjugate(T elem, T conjugateBy) {
    return this.operation(this.operation(elem, conjugateBy), this.inverses.get(conjugateBy));
  }
}