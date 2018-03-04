package abst.group;

import org.junit.Test;

import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;

import numtheory.Util;

public class TestGroups {

  @Test
  public void testZed() {
    SortedSet<Integer> s = new TreeSet<>(Arrays.asList(0,1,2,3,4,5,6,7,8,9,10,11,12));

    Group<Integer> ze = new Zed((x, y) -> (x + y) % 13, s);
    System.out.println(ze.cayleyTable());
    System.out.println(ze.getIdentity());

    SortedSet<Integer> mulset = new TreeSet<>(Arrays.asList(1,2,4,5,7,8));

    Group<Integer> mul = new Zed((x, y) -> (x * y) % 31, Util.getReducedResidueSystem(31));
    System.out.println(mul.cayleyTable());

    Util.generateSieve(5000);
    Group<Integer> sfree = new Zed( (x, y) ->
            Util.factor(x * y).stream().reduce(1,
                    (acc, fac) -> acc * (int)Math.pow(fac.prime, fac.exponent%2),
                    (z, w) -> 1),
            new TreeSet<>(Arrays.asList(1,2,3,5,6,7,10,11,13,14,15,17,19,21,22,23,26,29,30,31)));

    System.out.println(sfree.cayleyTable());
  }
}
