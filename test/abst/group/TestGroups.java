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

    Util.generateSieve(5000);
    Group<Integer> sfree = new Zed( (x, y) ->
            Util.factor(x * y).stream().reduce(1,
                    (acc, fac) -> acc * (int)Math.pow(fac.prime, fac.exponent%2),
                    (z, w) -> 1),
            new TreeSet<>(Arrays.asList(1,2, 3, 5, 6, 7, 10, 11, 13, 14, 15, 17, 19, 21, 22, 23, 26, 29, 30, 31, 33, 34, 35, 37, 38, 39, 41, 42, 43, 46, 47, 51, 53, 55, 57, 58, 59, 61, 62, 65, 66, 67, 69, 70, 71, 73, 74, 77, 78, 79, 82, 83, 85, 86, 87, 89, 91, 93, 94, 95, 97, 101, 102, 103, 105, 106, 107, 109, 110, 111, 113, 114, 115, 118, 119, 122, 123, 127, 129, 130, 131, 133, 134, 137, 138, 139, 141, 142, 143, 145, 146, 149, 151, 154, 155, 157, 158, 159, 161, 163, 165, 166, 167, 170, 173, 174, 177, 178, 179, 181, 182, 183, 185, 186, 187, 190, 191, 193, 194, 195, 197, 199, 201, 202, 203, 205, 206, 209, 210, 211, 213, 214, 215, 217, 218, 219, 221, 222, 223, 226, 227, 229, 230, 231, 233, 235, 237, 238, 239, 241, 246, 247, 249, 251, 253, 254, 255, 257, 258, 259, 262, 263, 265, 266, 267, 269, 271, 273, 274, 277, 278, 281, 282, 283, 285, 286, 287, 290, 291, 293, 295, 298, 299, 301, 302, 303, 305, 307, 309, 310, 311, 313, 314, 317, 318, 319, 321, 322, 323, 326, 327, 329, 330, 331, 334, 335, 337, 339, 341, 345, 346, 347, 349, 353, 354, 355, 357, 358, 359, 362, 365, 366, 367, 370, 371, 373, 374, 377, 379, 381, 382, 383, 385, 386, 389, 390, 391, 393, 394, 395, 397, 398, 399, 401, 402, 403, 406, 407, 409, 410, 411, 413, 415, 417, 418, 419, 421, 422, 426, 427, 429, 430, 431, 433, 434, 435, 437, 438, 439, 442, 443, 445, 446, 447, 449, 451, 453, 454, 455, 457, 458, 461, 462, 463, 465, 466, 467, 469, 470, 471, 473, 474, 478, 479, 481, 482, 483, 485, 487, 489, 491, 493, 494, 497, 498, 499)));

    System.out.println(sfree.cayleyTable());
  }
}
