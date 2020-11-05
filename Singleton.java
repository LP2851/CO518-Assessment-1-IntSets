/**
 * The Singleton class stores one integer value.
 *
 * @author Lucas Phillips
 * @version 15/10/2020
 */
public class Singleton implements IntSet {

    // The value stored in the Singleton
    public int value;
    
    // The static instances of Singleton for values 0-7 inclusive.
    private static Singleton[] singletons0To7;

    /**
     * Constructor for the Singleton class
     */
    private Singleton(int x) {
        value = x;
    }

    /**
     * Smart constructor for Singletons. Takes an integer and 
     * returns an instance of the class which is either a new
     * Singleton object (for values not in 0-7 inclusive)
     * or a pointer to a static instance for the corresponding value.
     * @param x The value to be stored by the Singleton.
     * @return A Singleton containing one integer value.
     */
    public static IntSet newSingleton(int x){
        if(singletons0To7 == null) {
            singletons0To7 = new Singleton[8];
            for(int i = 0; i < 8; i ++) {
                singletons0To7[i] = new Singleton(i);
            }
        }

        if (x >= 0 && x <= 7) {
            return singletons0To7[x];
        } else {
            return new Singleton(x);
        }
    }

    /**
     * Adds an integer to the set, making the set a TreeSet if the integer passed is not the same as the one stored by the class
     * @param x The integer to be added to the IntSet.
     * @return A IntSet containing the current value stored and the passed value x, or just the value stored.
     */
    @Override
    public IntSet add(int x) {
        if (x == value) {
            return this;
        } else {
            return new TreeSet().add(value).add(x);
        }
        
    }
    
    /**
     * Returns a boolean value if the number passed is the value stored by this Singleton instance.
     * @param x Value to compare to the stored value.
     * @return If the value, x, is stored currently by the Singleton instance.
     */
    @Override
    public boolean contains(int x) {
        return (value == x);
    }

    /**
     * Returns a new IntSet containing both this set's value and the other IntSet's values with no duplicates.
     * @param other IntSet to get unioned to current IntSet
     * @return Union of both this Singleton and the passed IntSet.
     */
    @Override
    public IntSet union(IntSet other) {       
        if(other instanceof EmptySet) { // empty set
            return this;
        } else { // tree set or singleton
            return other.add(value);
        }
    }
    
    /**
     * Returns a string representing the value stored by the Singleton
     * @return String representing the value stored by the Singleton ( "{" + value + "}" ).
     */
    @Override 
    public String toString() {
        return "{" + value + "}";
    }
}
