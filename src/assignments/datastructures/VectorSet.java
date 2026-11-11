package assignments.datastructures;

import java.util.Iterator;

import adt.Set;

/**
 * VectorSet: A data structure representing a mathematical set.
 * The set is intrinsically unordered, and every item is unique.
 * 
 * @param <T> the type of each element
 */
public class VectorSet<T> implements Set<T> {
    private Vector<T> vector;
    /**
     * Initialize an empty vector
     */
    public VectorSet() {
        this.vector = new Vector<>();
    }
    /**
     * Creates an iterator that goes step by step through the vector
     * 
     * The iterator visit items in forward order, starting with element at index 0 and
     *  continues until all the elements have been done
     *
     * @return an iterator that produces items in a vector from first to last
     */
    public Iterator<T> iterator() {
        return this.vector.iterator();
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
        this.vector.push(value);
        return true;
    }

    /**
     * Checks if the collection contains a given value.
     * 
     * @param value the value to look for
     * @return true iff the collection contains value
     */
    public boolean contains(T value) {
        return this.vector.contains(value);
    }

    /**
     * Compute the number of items in this collection.
     * Often referred to as Cardinality
     * 
     * @return the number of items in the set
     */
    public int length() {
        return this.vector.length();
    }

    /**
     * Remove an item from the set.
     * 
     * @param value the value to be removed
     */
    public boolean remove(T value) {
        for (int i = 0; i < this.vector.length(); i ++) {
            if (value.equals(this.vector.at(i))) {
                this.vector.delete(i);
                return true;
            }
        }
        return false;
    }
    /**
     * Run validation tests
     * 
     * @param args command-line args
     */
    public static void main(String[] args) {
        Set.validate(new VectorSet<Integer>());         
        
        System.out.println("VectorSet passes all tests without errors.");
    }
    
}
