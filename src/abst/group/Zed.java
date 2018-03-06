package abst.group;

import java.util.Comparator;
import java.util.Optional;
import java.util.SortedSet;
import java.util.function.BinaryOperator;

/**
 * Represents a group with integer elements. Common use cases are multiplicative and additive
 * groups.
 *
 * TODO: Make specific subclasses for multiplicative and additive groups.
 */
public class Zed extends FiniteGroup<Integer> {

  /**
   * Create a new integer group with given binary operation and elements.
   *
   * @param operation Operation between the integers
   * @param elements  Set of integers to operate upon
   */
  public Zed(BinaryOperator<Integer> operation, SortedSet<Integer> elements) {
    super(operation, elements);
  }

  @Override
  public SortedSet<Coset<Integer>> cosets(Group subGroup) {
    return null;
  }

  @Override
  public int order(Integer elem) {
    return 0;
  }

  //TODO: This needs to be cleaned up.. a lot... I was just excited to have it working
  @Override
  public String cayleyTable() {

    // Java is just so pretty, isn't it...
    Optional<Integer> maxelemp = this.elements.stream().max(Comparator.naturalOrder());

    int maxelem;
    if (maxelemp.isPresent()) {
      maxelem = maxelemp.get();
    } else {
      throw new InvalidGroupException("Group seems to have no max element");
    }
    if (maxelem == 0) {
      maxelem = 1;
    }

    // Floor of log base 10 is the number of digits in a number
    int amtExSpace = (int) Math.log10(maxelem) + 1;
    String sp = "                  ";
    //amtExSpace += 2;

    // Put down the operator
    StringBuilder ans =
            new StringBuilder(sp.substring(1, amtExSpace) +
                    this.props.getOperator() + " \u2503");

    int rowLength = 0;

    // Create key at top of table
    for (Integer elem : this.elements) {
      rowLength += (amtExSpace + 1);
      int elemLen;
      if (elem < 1) {
        elemLen = 1;
      } else {
        elemLen = (int) Math.log10(elem) + 1;
      }

      ans.append(sp.substring(elemLen, amtExSpace + 1)).append(elem);
    }


    // Create horizontal line before listing elements of group
    ans.append("\n");
    for (int i = 0; i <= amtExSpace; ++i) {
      ans.append("\u2501");
    }
    ans.append("\u254B");
    for (int i = 0; i < rowLength; ++i) {
      ans.append("\u2501");
    }


    for (Integer row : this.elements) {
      int elemLen;
      if (row < 1) {
        elemLen = 1;
      } else {
        elemLen = (int) Math.log10(row) + 1;
      }
      ans.append("\n").append(sp.substring(elemLen, amtExSpace)).append(row).append(" \u2503");

      for (Integer col : this.elements) {
        Integer prod = this.operation(row, col);

        int colLen;
        if (prod < 1) {
          colLen = 1;
        } else {
          colLen = (int) Math.log10(prod) + 1;
        }

        if (colLen > amtExSpace) {
          amtExSpace = colLen + 1;
        }
        ans.append(sp.substring(colLen, amtExSpace + 1)).append(prod);
      }
    }
    return ans.toString();
  }
}
