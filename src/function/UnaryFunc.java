package function;

/**
 * Represents a function that takes one input to one output.
 * @param <I> Input type.
 * @param <O> Output type.
 */
@FunctionalInterface
public interface UnaryFunc<I, O> {

  /**
   * Applies the function to the given input.
   * @param in Input to be transformed
   * @return The image of the input
   */
  O apply(I in);


}
