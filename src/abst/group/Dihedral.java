package abst.group;

import java.util.SortedSet;

import abst.group.element.DihedralElem;

public class Dihedral extends FiniteGroup<DihedralElem> {
  private final int order;

  public Dihedral(int order)  {
    super(null, null);
    this.order = order;
    this.props.setOperator("\u9702");

  }

  @Override
  public int order(DihedralElem elem) {
    return 0;
  }

  @Override
  public SortedSet<Coset<DihedralElem>> cosets(Group<DihedralElem> subGroup) {
    return null;
  }

  @Override
  public String cayleyTable() {
    return this.props.getOperator();
  }


}
