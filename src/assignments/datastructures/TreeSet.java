package assignments.datastructures;

import java.util.Iterator;

import adt.Set;
import adt.Tree;

/**
 * TreeSet: A data structure representing a mathematical set.
 * The set is intrinsically unordered, and every item is unique.
 * 
 * @param <T> the type of each element
 */
public class TreeSet<T extends Comparable<T>> implements Set<T> {

    private BinarySearchTree<T> tree;
    /**
     * Initialize an empty tree
     */
    public TreeSet() {
        this.tree = new BinarySearchTree<>();
    }
    /**
     * Creates an iterator that goes step by step through the tree
     * 
     * @return an iterator that produces items in a vector from first to last
     */
    public Iterator<T> iterator() {
        return this.tree.iterator();
    }

    /**
     * Add an item to the set
     * 
     * @param value the value to be added
     * @return true iff the set changed(ie value was not ready in the set)
     */
    public boolean add(T value) {
        if (this.contains(value)) {
            return false;
        }
        this.tree.add(value);
        return true;
    }

    /**
     * Checks if the collection contains a given value.
     * 
     * @param value the value to look for
     * @return true iff the collection contains value
     */
    public boolean contains(T value) {
        return this.tree.contains(value);
    }

    /**
     * Compute the number of items in this collection.
     * Often referred to as Cardinality
     * 
     * @return the number of items in the set
     */
    public int length() {
        return this.tree.length();
    }

    /**
     * Remove an item from the set.
     * 
     * @param value the value to be removed
     */
    public boolean remove(T value) {
        return this.tree.remove(value);
    }
    /**
     * Run validation tests
     * 
     * @param args command-line args
     */
    public static void main(String[] args) {
        // Set.validate(new TreeSet<Integer>());         
        
        // System.out.println("TreeSet passes all tests without errors.");

        // Fill an array with random numbers
        for (int N = 10000; N <= 100000; N += 10000) {
            TreeSet<Integer> set = new TreeSet<>();

            while (set.length() < N) {
                set.add(1 + (int)(N*Math.random()));
            }

            // Measuring runtime.
            long start = System.nanoTime();

            set.contains(N + 1);

            long end = System.nanoTime();

            double duration = (end - start)/(1e9);

            System.out.println(N + "\t" + duration);
        }
    }    
}
