# Groop
## What is Group
Groop is an ever changing library that allows one to easily explore group and number theory using Java 8.
Groop makes use of the new lambda features in Java 8 to allow one to easily create their own binary operations and thus groups.

## Installation, Dependencies
To install Groop, simply clone the repository. Groop is programmed in IntelliJ IDEA, so your life would be easiest if you also did so.

Groop makes use of JUnit 4 and the Java 8 standard library. No other dependencies are planned.

## Basic usage
Groop currently provides three main packages, *abst*, *function* and *numtheory*
### abst
Abst provides classes that represent basic groups (and possibly rings someday). 
### function
This simply provides some default binary functions that can either be used as is or serve as examples.
### numtheory
Numtheory provides a host of static functions for basic number theory purposes. Simply call Util.*functionname* to use.
Note: Many functions, mainly those that specify they throw an IllegalStateException, require a sufficient prime sieve to be built. This can be done by calling *Util.generateSieve(number)*.

## To-Do
- Add basic number theory functions (incl ~~sigma~~, ~~phi~~, pi, various identities)
  - About 50% done
- Add the following elementary group types:
  - Dihedral groups
  - ~~Integers under addition, multiplication~~
  - Infinite groups
  - Symmetric groups
- Add parser for basic REPL interaction
  - Far in the future
- Add basic Ring/Field theory structures
  - Also far in the future
