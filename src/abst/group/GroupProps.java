package abst.group;

/**
 * Holds the properties that groups have, can be added to.
 */
class GroupProps {
  private boolean isCyclic;
  private boolean isFinite;
  private boolean isAbelian;
  private int order;

  public GroupProps() {}

  /**
   * Set whether the group is cyclic.
   *
   * @param value True if the group is cyclic, false otherwise
   * @return this (for chaining)
   */
  public GroupProps setCyclic(boolean value) {
    this.isCyclic = value;
    // All cyclic groups are Abelian
    if(value) {
      this.setAbelian(true);
    }
    return this;
  }

  public GroupProps setFinite(boolean value) {
    this.isFinite = value;
    return this;
  }

  public GroupProps setAbelian(boolean value) {
    this.isAbelian = value;
    return this;
  }

  public GroupProps setOrder(int value) {
    this.order = value;
    return this;
  }

  /**
   * Returns a string representation of all properties held in state.
   * @return String containing a description of this {@code GroupProps}
   */
  @Override
  public String toString() {
    return "";
  }
}
