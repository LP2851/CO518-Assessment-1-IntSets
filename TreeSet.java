/**
 * This is the TreeSet class that handles multiple ints in a set.
 *
 * @author Lucas Phillips
 * @version 15/10/2020
 */
public class TreeSet implements IntSet {

    // Links to the IntSet for both left and right directions from this node.
    IntSet[] tree;
    
    // Static constants that make the code slightly easier to read.
    private static final int LEFT = 0;
    private static final int RIGHT = 1;
    
    /**
     * Constructor for the TreeSet class.
     * @param left  IntSet going into the left side of the tree.
     * @param right IntSet going into the right side of the tree.
     */
    public TreeSet(IntSet left, IntSet right) {
        tree = new IntSet[] {left, right};
    }
    
    /**
     * Constructor for the TreeSet class creating an empty tree.
     */
    public TreeSet() {
        tree = new IntSet[] {EmptySet.empty(), EmptySet.empty()};
    }
    
    /**
     * Returns if the integer x is an even number.
     * @param x Integer to be checked to see if it is even. 
     * @return Boolean value depending on if x is even or odd.
     */
    private boolean isEven(int x) {
        return (x % 2 == 0);
    }
    
    /**
     * Adds an integer to the TreeSet and returns the new TreeSet.
     * If the integer is already in the tree set then it is not added and the same tree is returned.
     * @param x The integer to be added to the TreeSet.
     * @return A new TreeSet containing x.
     */
    @Override
    public IntSet add(int x) {
        IntSet left = tree[LEFT]; IntSet right = tree[RIGHT];
        if (!contains(x)) { // if not in set
            if (isEven(x)) { // if even add to left side of tree
                left = left.add(x/2);
            } else { // if odd add to right side of tree
                right = right.add((x-1)/2);
            }
        }
        return new TreeSet(left, right); // create new tree with generated branches
    }

    /**
     * Returns a boolean value representing if the passed value is in the TreeSet.
     * @param x The value being searched for in the tree.
     * @return A boolean value representing if the passed value is in the TreeSet.
     */
    @Override
    public boolean contains(int x) {
        // Working out if in left or right side of tree (halves search size)
        int leftOrRight = (isEven(x)) ? LEFT : RIGHT;
        return tree[leftOrRight].contains((x-leftOrRight)/2);
    }
    
    /**
     * Returns a side of the tree set it is called on. This is only used in union on the passed other set.
     * @param i Integer containing 0 or 1 representing left or right side of the tree.
     * @return The side of the tree requested (can be EmptySet, Singleton or TreeSet).
     */
    private IntSet getTreeSide(int i) {
        return tree[i];
    }
    
    /**
     * Returns a new set containing all of the elements from this set and from the passed set (no duplicates as this is a set).
     * @param other The set to be unioned to the set this has been called on. 
     * @return A new set containing all of the elements from this set and from the passed set.
     */
    @Override
    public IntSet union(IntSet other) {
        
        if (other instanceof TreeSet) { // if other is a TreeSet
            // Returning a new TreeSet made up of the left sides of the two trees unioned and the same for the right. 
            return new TreeSet(((TreeSet)other).getTreeSide(LEFT).union(tree[LEFT]), ((TreeSet)other).getTreeSide(RIGHT).union(tree[RIGHT]));
        } else { // if other is Singleton or EmptySet
            // Letting the other handle the union.
            return other.union(this);
        }
    }
    
    /**
     * Extracts all of the integers from the strings in the string array that it is passed and upscales them to new values.
     * @param strings String[] containing all of the integers to be parsed to ints. 
     * @param leftOrRight Either 0 or 1 depending on if the information is coming from the left or the right of the tree.
     * @return An int[] containing all of the numbers (upscaled).
     */
    private int[] getIntsFromStrings(String[] strings, int leftOrRight) {
        int[] out = (tree[leftOrRight] instanceof EmptySet) ? new int[0] : new int[strings.length];
        if(!(tree[leftOrRight] instanceof EmptySet)) {
            int n = 0;
            for(String s : strings) {
                if(!s.equals("")) {
                    out[n] = Integer.parseInt(s) * 2 + leftOrRight;
                    n++;
                }
            }
        }
        return out;
    }
    
    /**
     * Creates a string containing all of the numbers from the two arrays it is passed.
     * E.g. Returns "2,4,6,8,10,100,101,1,5,7,9"
     * @param left An int[] for all of the numbers taken from the left side of the tree - side doesnt really matter.
     * @param right An int[] for all of the numbers taken from the right side of the tree - side doesnt really matter.
     * @return A string of integers separated by commas with all of the numbers from both passed arrays. 
     */
    private String createIntString(int[] left, int[] right) {
        String out = "";
        for (int i : left) {
            if (out.length() != 0) out += ",";
            out += i;
        }
        for (int i : right) {
            if (out.length() != 0) out += ",";
            out += i;
        }
        return out;
    }
    
    /**
     * Returns a string representing the TreeSet with all of the values it represents split up with commas and surrounded by
     * curly brackets.
     * E.g. "{1,2,3,4,5,6,7,8,9}"
     * @return A string representing the TreeSet with all of the values it represents.
     */
    @Override 
    public String toString() {
        String[] leftString = tree[LEFT].toString().replace("{", "").replace("}", "").split(",");
        String[] rightString = tree[RIGHT].toString().replace("{", "").replace("}", "").split(",");
        int[] left, right;
        
        left = getIntsFromStrings(leftString, LEFT);
        right = getIntsFromStrings(rightString, RIGHT);
        
        return "{" + createIntString(left, right) + "}";
    }
}