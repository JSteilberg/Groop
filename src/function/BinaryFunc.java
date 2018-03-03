package function;

/**
 * Represents a function that takes in two arguments and returns one output.
 *
 * @param <I1> Type of the first input to the function
 * @param <I2> Type of the second input to the function
 * @param <O>  Type of the image of the function
 */
@FunctionalInterface
public interface BinaryFunc<I1, I2, O> {
  /**
   * Apply the binary function to the given arguments, returning the image.
   *
   * @return The image of the binary function
   */
  O apply(I1 arg1, I2 arg2);
}
