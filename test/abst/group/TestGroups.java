package abst.group;

import org.junit.Test;

import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;

import numtheory.Util;

import static org.junit.Assert.assertEquals;

public class TestGroups {

  @Test
  public void testZed() {
    Group<Integer> two = new ZedAdditive(2);
    assertEquals("Size two group should look like this",
             "+ \u2503 0 1\n\u2501\u2501\u254B\u2501\u2501" +
                     "\u2501\u2501\n0 \u2503 0 1\n1 \u2503 1 0",
            two.cayleyTable());

    SortedSet<Integer> expSet = new TreeSet<>(Arrays.asList(0,1));
    assertEquals("Generated additive group of size 2 should have 0..1 as its set",
            expSet, two.getElements());

    Group<Integer> ze = new ZedAdditive(7);
    assertEquals("Identity of additive group should be 0", 0, (int)ze.getIdentity());

    expSet = new TreeSet<>(Arrays.asList(0,1,2,3,4,5,6));
    assertEquals("Generated additive group of size 7 should have 0..6 as its set",
            expSet, ze.getElements());

    System.out.println(ze.cayleyTable());

    Group<Integer> mul = new ZedMultiplicative(7);
    assertEquals("Identity of multiplicative group should be 1",
            1, (int)mul.getIdentity());

    expSet = new TreeSet<>(Arrays.asList(1,2,3,4,5,6));
    assertEquals("Generated multiplicative group modulo 7 should have 1..6 as its set",
            expSet, mul.getElements());
    System.out.println(mul.cayleyTable());

    Group<Integer> nonprime = new ZedMultiplicative(27);
    System.out.println(nonprime.cayleyTable());

    Util.generateSieve(5000);/*
    Group<Integer> sfree = new Zed( (x, y) ->
            Util.factor(x * y).stream().reduce(1,
                    (acc, fac) -> acc * (int)Math.pow(fac.prime, fac.exponent%2),
                    (z, w) -> 1),
            new TreeSet<>(Arrays.asList(1,2,3,5,6,7,10,11,13,14,15,17,19,21,22,23,26,29,30,
                    31,33,34,35,37,38,39,41,42,43,46,47,51,53,55)));

    System.out.println(sfree.cayleyTable());*/
  }
}
