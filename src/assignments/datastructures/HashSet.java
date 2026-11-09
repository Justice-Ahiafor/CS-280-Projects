package assignments.datastructures;

import java.util.Iterator;

import adt.Set;

/**
 * HashSet: A data structure representing a mathematical set.
 * The set is intrinsically unordered, and every item is unique.
 * 
 * @param <T> the type of each element
 */
public class HashSet<T extends Comparable<T>> implements Set<T> {
    private Vector<T> [] M;     // # of buckets in array or the size of the hash table
    private int N;              // # of items in the HashSet
    private int D;              // # of items in any one bucket

    /**
     * Initialize the HashSet
     */
    @SuppressWarnings("unchecked")
    public HashSet() {
        N = 0;
        D = 5;

        M = new Vector[10];
        for (int i = 0; i < M.length; i ++) {
            M[i] = new Vector<>();
        }
    }
    
    /**
     * Hash function: takes any object and spits out array index
     * 
     * @param value the value to be hashed
     * @param tableLength the current length of the array M
     * @return hashed index between 0 and (tableLength - 1)
     */
    private int hash(T value, int tableLength) {
        return Math.abs(value.hashCode()) % tableLength;
    }

    @SuppressWarnings("unchecked")
    private void rehash() {
        int newD = M.length * 2;
        Vector<T>[] newHashVectors = new Vector[newD];

        for (int i = 0; i < newD; i ++) {
            newHashVectors[i] = new Vector<>();
        }

        for (int i = 0; i < M.length; i ++) {
            for (int j = 0; j < M[i].length(); j ++) {
                T item = M[i].at(j);
                int newIndex = hash(item, newD);
                newHashVectors[newIndex].push(item);
            }
        }
        M = newHashVectors;
    }
    /**
     * Creates an iterator that goes step by step
     * 
     * The iterator visit items in forward order, starting with element at index 0 and
     *  continues until all the elements have been done
     *
     * @return an iterator that produces items in a vector from first to last
     */
    public Iterator<T> iterator() {
        Vector<T> vector = new Vector<>();

        for (int i = 0; i < M.length; i ++) {
            for (int j = 0; j < M[i].length(); j ++) {
                vector.push(M[i].at(j));
            }
        }
        return vector.iterator();
    }

    /**
     * Add an item to the set
     * 
     * @param value the value to be added
     * @return true iff the set changed(ie value was not ready in the set)
     */
    public boolean add(T value) {
        if (this.contains(value)) return false;
        int currentIndex = hash(value, M.length);

        if (M[currentIndex].length() >= D) {
            rehash();
            currentIndex = hash(value, M.length);
        }
        M[currentIndex].push(value);
        N ++;
        return true;
    }

    /**
     * Checks if the collection contains a given value.
     * 
     * @param value the value to look for
     * @return true iff the collection contains value
     */
    public boolean contains(T value) {
        int currentIndex = hash(value, M.length);
        return M[currentIndex].contains(value);
    }

    /**
     * Compute the number of items in this collection.
     * Often referred to as Cardinality
     * 
     * @return the number of items in the set
     */
    public int length() {
        return N;
    }

    /**
     * Remove an item from the set.
     * 
     * @param value the value to be removed
     */
    public boolean remove(T value) {
        int currentIndex = hash(value, M.length);
        Vector<T> vector = M[currentIndex];

        for (int i = 0; i < vector.length(); i ++) {
            if (value.equals(vector.at(i))) {
                vector.delete(i);
                N --;
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
        Set.validate(new HashSet<Integer>());         
        
        System.out.println("HashSet passes all tests without errors.");
    }
    
}
