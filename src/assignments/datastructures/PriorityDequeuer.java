package assignments.datastructures;

import adt.PriorityQueue;

/**
 * PriorityDequeuer: A data structure representing a priority queue.
 * Irrespective of how items are added, the minimum remaining value is always removed next.
 *  
 * Strategy: Maintain an array in sorted order. Then, when dequeueing, 
 *  just pop off the last element in your array.
 * @param <T>
 */
public class PriorityDequeuer<T extends Comparable<T>> implements PriorityQueue<T>{
    private Vector<T> vector;
    /**
     * Initialize an empty priority queue
     */
    public PriorityDequeuer() {
        this.vector = new Vector<>();
    }
    /**
     * Dequeue remove an item from the queue. 
     * Pre-condition: the queue isn't empty.
     * 
     * @return the smallest item left in the queue
     */
    public  T dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
        }
        return vector.pop();
    }
    /**
     * Enqueue adds an item to the queue.
     * 
     * @param value the value to be added
     */
    public void enqueue(T value) {
        int index = 0;
        // Loop around to find where the value fits in
        while (index < vector.length() && value.compareTo(vector.at(index)) < 0) {
            index ++;
        }
        vector.insert(index, value);
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
     */
    public T peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
        }
        return vector.peek();
    }
    /**
     * Run validation tests.
     * @param args command-line args
     */
    public static void main(String[] args) {
        PriorityQueue.validate(new PriorityDequeuer<Integer>());

        System.out.println("PriorityDequeuer passes all tests without errors.");
    }
    
}
