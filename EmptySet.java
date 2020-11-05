/**
 * EmptySet is a class which stores a nothing variable representing an empty set.
 *
 * @author Lucas Phillips
 * @version 15/10/2020
 */
public class EmptySet implements IntSet {
 
    // Static value for an EmptySet.
    private static EmptySet emptySet;

    /**
     * Hidden constructor for use only with the static empty() method.
     */
    private EmptySet() {}

    /**
     * Returns the static instance of EmptySet.
     * @return The static instance of EmptySet.
     */
    static IntSet empty() {
        if (emptySet == null) {
            emptySet = new EmptySet();
        }
        return emptySet;
    }

    /**
     * Adds the passed integer x to the IntSet making the set a Singleton.
     * @param x The integer to be added to the IntSet.
     * @return The singleton set containing the passed value x.
     */
    @Override
    public IntSet add(int x) {
        //System.out.println("EmptySet: Creating new singleton " + x);
        return Singleton.newSingleton(x);
    }

    /**
     * Checks to see if the integer passed is in the set. Always returns false.
     * @param x The integer that is being checked if it exists in the set.
     * @return False.
     */
    @Override
    public boolean contains(int x) {
        return false;
    }

    /**
     * Returns the union of this set and the passed set, which is just the contents of the passed set.
     * @param other The set to be unioned to the EmptySet.
     * @return The IntSet (other) passed.
     */
    @Override
    public IntSet union(IntSet other) {
        return other;
    }
    
    /**
     * Returns the string version of the set which is "{}"
     * @returns The string "{}"
     */
    @Override 
    public String toString() {
        return "{}";
    }
}
