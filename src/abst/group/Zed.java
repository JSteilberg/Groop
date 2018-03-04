package abst.group;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.function.BinaryOperator;

/**
 * Represents
 */
public class Zed extends FiniteGroup<Integer> {

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

  @Override
  public SortedSet<Integer> getElements() {
    return null;
  }

  @Override
  public String cayleyTable() {

    int maxelem = this.elements.stream().max(Comparator.naturalOrder()).get();
    int amtExtraSpace = (int)Math.log10(maxelem);
    amtExtraSpace++;
    String sp = "";
    for(int i = 0; i < amtExtraSpace; ++i) {
      sp += " ";
    }

    StringBuilder ans = new StringBuilder("\n" + sp + "+ \u2503");
    int rowLength = 0;

    // Create key at top of table
    for (Integer elem : this.elements) {
      rowLength += (amtExtraSpace + 2);
      if (elem < 10) {
        ans.append(sp).append(elem).append(" ");
      } else if (elem < 100) {
        ans.append(sp).append("\b").append(elem).append(" ");
      } else {
        ans.append(sp).append("\b\b").append(elem).append(" ");
      }
    }

    // Create line before listing elements of group
    ans.append("\n").append(sp).append("\u2501\u2501\u254B");
    for (int i = 0; i < rowLength; ++i) {
      ans.append("\u2501");
    }



    for (Integer i : this.elements) {
      if (i < 10) {
        ans.append("\n").append(sp).append(i).append(" \u2503");
      } else if (i < 100){
        ans.append("\n").append(sp).append("\b").append(i).append(" \u2503");
       // ans += "\n" + i + " \u2503";
      } else {
        ans.append("\n").append(sp).append("\b\b").append(i).append(" \u2503");
      }
      for (Integer col : this.elements) {
        Integer prod = this.operation(i, col);
        if (prod < 10) {
          ans.append(sp).append(prod).append(" ");
        } else if (prod < 100) {
          ans.append(sp).append("\b").append(prod).append(" ");
         // ans += prod + " ";
        } else {
          ans.append(sp).append("\b\b").append(prod).append(" ");
        }
      }
    }
    return ans.toString();
  }
}
