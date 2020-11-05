/**
 * The interface for all IntSets
 * Implemented by the EmptySet, Singleton and TreeSet classes.
 *
 * @author Lucas Phillips
 * @version 15/10/2020
 */
public interface IntSet {
    
    // Adds an integer to the set returning a new set.
    IntSet add(int x);
    
    // Checks to see if integer x is in the set.
    boolean contains(int x);
    
    // Adds two sets together to create a new set.
    IntSet union(IntSet other);
    
    // Shows all of the values of the tree in a readable string.
    @Override
    String toString(); // not sure if needed but kept in just incase it causes weirdness without it.
}