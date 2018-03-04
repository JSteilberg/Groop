package abst.group;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.SortedSet;
import java.util.function.BinaryOperator;

public abstract class FiniteGroup<T> implements Group<T> {
  protected BinaryOperator<T> op;
  protected SortedSet<T> elements;
  protected GroupProps props;
  T identity;
  protected Map<T, T> inverses;

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

  protected FiniteGroup(BinaryOperator<T> operation, SortedSet<T> elements, T identity) {
    this(operation, elements);

    Objects.requireNonNull(identity);
    this.identity = identity;
  }

  protected Map<T, T> findInverses() {
    Map<T, T> ans = new HashMap<>();
    List<T> elems = new ArrayList<>(this.elements);

    for(int i = 0; i < elems.size(); ++i) {
      T currElem = elems.get(i);
      if(ans.containsKey(currElem)) {
        continue;
      }
      for(int j = 0; j < elems.size(); ++j) {
        T compElem = elems.get(j);
        if(this.op.apply(currElem, compElem).equals(this.identity)) {
          if(this.op.apply(compElem, currElem).equals(this.identity)) {
            ans.put(currElem, compElem);
            ans.put(compElem, currElem);
          } else {
            throw new InvalidGroupException(currElem + " * " +  compElem + " = identity, " +
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

  protected boolean testAbelian() {
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

  protected T findIdentity() {
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
