package assignments.datastructures;

import java.util.Iterator;

import adt.Set;

/**
 * HashSet: A data structure representing a mathematical set.
 * The set is intrinsically unordered, and every item is unique.
 * 
 * @param <T> the type of each element
 */
public class HashSet<T> implements Set<T> {
    private Vector<VectorSet<T>> buckets;
    private int N;
    private static final int D = 5;

    /**
     * Initialize the HashSet
     */
    public HashSet() {
        N = 0;

        buckets = new Vector<>();

        for (int i = 0; i < 10; i ++) {
            buckets.push(new VectorSet<>());
        }
    }
    
    /**
     * Hash function: takes any object and spits out array index
     * 
     * @param value the value to be hashed
     * @param tableLength the current length of bucket array
     * @return hashed index between 0 and (tableLength - 1)
     */
    private int hash(T value, int tableLength) {
        return Math.abs(value.hashCode()) % tableLength;
    }

    /**
     * Rehashes with twice as many buckets.
     */
    private void rehash() {
        Vector<VectorSet<T>> newBuckets = new Vector<>();

        int newSize = buckets.length() * 2;

        for (int i = 0; i < newSize; i ++) {
            newBuckets.push(new VectorSet<>());
        }

        for (int i = 0; i < buckets.length(); i ++) {
            for (T item: buckets.at(i)) {
                int newIndex = hash(item, newSize);
                newBuckets.at(newIndex).add(item);
            }
        }
        buckets = newBuckets;
    }

    /**
     * Creates an iterator that goes step by step
     * 
     * @return an iterator for everything in the set
     */
    public Iterator<T> iterator() {
        Vector<T> vector = new Vector<>();

        for (int i = 0; i < buckets.length(); i ++) {
            for (T item: buckets.at(i)) {
                vector.push(item);
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
        int currentIndex = hash(value, buckets.length());

        while (buckets.at(currentIndex).length() >= D) {
            rehash();
            currentIndex = hash(value, buckets.length());
        }
        buckets.at(currentIndex).add(value);
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
        int currentIndex = hash(value, buckets.length());
        return buckets.at(currentIndex).contains(value);
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
        int currentIndex = hash(value, buckets.length());

        if (buckets.at(currentIndex).remove(value)) {
            N --;
            return true;
        }
        
        return false;
    }

    /**
     * Run validation tests
     * @param args command-line args
     */
    public static void main(String[] args) {
        Set.validate(new HashSet<Integer>());

        HashSet<Integer> testHashSet = new HashSet<>();

        for (int i = 0; i < 100; i += 1) {
            assert testHashSet.add(i);
        }

        assert testHashSet.length() == 100;

        assert testHashSet.contains(25);
        assert testHashSet.contains(37);
        assert testHashSet.contains(59);
        assert testHashSet.contains(62);
        assert testHashSet.contains(79);
        assert testHashSet.contains(92);

        assert !testHashSet.contains(100);

        assert testHashSet.remove(37);

        assert testHashSet.length() == 99;
        
        System.out.println("HashSet passes all tests without errors.");
    }
    
}
