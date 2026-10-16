package assignments.datastructures;

import adt.PriorityQueue;

/**
 * PriorityEnquerer: A data structure representing a priority queue.
 * Irrespective of how items are added, the minimum remaining value is always removed next.
 *
 * Strategy: Maintain a vector in enqueue order. When dequeueing,
 *  search the whole vector for the smallest 
 * @param <T>
 */
public class PriorityEnquerer<T extends Comparable<T>> implements PriorityQueue<T> {
    private Vector<T> vector;
    /**
     * Initialize an empty priority queue
     */
    public PriorityEnquerer() {
        this.vector = new Vector<>();
    }
    /**
     * Dequeue remove an item from the queue. 
     * Pre-condition: the queue isn't empty.
     * 
     * @return the smallest item left in the queue
     */
    public  T dequeue() {
        int index = 0;  
        // Find the index with the smallest value
        for (int i = 1; i < vector.length(); i ++) {
            if (vector.at(i).compareTo(vector.at(index)) < 0) {
                index = i;
            }
        }
        return vector.delete(index);
    }
    /**
     * Enqueue adds an item to the queue.
     * 
     * @param value the value to be added
     */
    public void enqueue(T value) {
        vector.push(value);
    }
    /** 
     * IsEmpty checks if the collection is empty.
     * 
     * @return true iff the collection has no more items, otherwise false.
     */
    public boolean isEmpty() {
        return vector.isEmpty();
    }
    /**
     * Peek reports the next item to be removed from the queue
     * 
     * @return the next value to be dequeued
     * 
     */
    public T peek() {
        int index = 0;
        // Find the index with the smallest value
        for (int i = 1; i < vector.length(); i ++) {
            if (vector.at(i).compareTo(vector.at(index)) < 0) {
                index = i;
            }
        }
        return vector.at(index);
    }
    /**
     * Run validation tests.
     * @param args command-line args
     */
    public static void main(String[] args) {
        PriorityQueue.validate(new PriorityEnquerer<Integer>());

        System.out.println("PriorityEnquerer passes all tests without errors.");

    }
    
}
