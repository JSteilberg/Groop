package function.binary;

import java.util.function.BinaryOperator;

public class XPlusY implements BinaryOperator<Double> {
  @Override
  public Double apply(Double left, Double right) {
    return left + right;
  }
}
