# CO518-Assessment-1-IntSets
Code of my solution to the IntSet assessment that was set in CO518: Algorithms, Correctness and Efficiency.
All objects for this project are to be immutable.

## IntSet
IntSet is an interface with four methods all of which are implemented in the EmptySet, Singleton and TreeSet classes (classes that implement IntSet).

## EmptySet
EmptySet represents an empty integer set. This class only ever has one instance, so all uses of EmptySets all point to the same obejct. 

## Singleton
Singleton represents a singleton integer set (set containing one integer). This class uses a smart constructor, to use the same objects for singletons containing the numbers 0-7 inclusive.  All other numbers create new instances of the class. All uses of singletons containing a value 0-7 all point to the same singleton objects. E.g. all uses of singleton sets that contain 4 are all pointing to the same object.

## TreeSet
TreeSet represents a set containing mutliple values. This class uses a tree to store these values. Even numbers are stored in the left branch of the tree and odd numbers are stored in the right side of the tree. Each branch is an IntSet (can be EmptySet, Singleton or TreeSet). When adding to a branch of the tree, the integers being stored are changed slightly to make them smaller:
- Even numbers are divided by 2.
- Odd numbers have 1 subtracted and then the number is divided by 2. 
