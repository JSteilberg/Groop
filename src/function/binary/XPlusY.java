package function.binary;

import function.BinaryFunc;

public class XPlusY implements BinaryFunc<Double, Double, Double> {
  @Override
  public Double apply(Double arg1, Double arg2) {
    return arg1 + arg2;
  }
}
