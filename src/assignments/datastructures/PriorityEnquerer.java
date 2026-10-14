package assignments.datastructures;

import adt.PriorityQueue;

/**
 * PriorityEnquerer: A data structure representing a priority queue.
 * Irrespective of how items are added, the minimum remaining value is always removed next.
 *
 * Strategy: Maintain an array in enqueue order. When dequeueing,
 *  search the whole array for the smallest 
 * @param <T>
 */
public class PriorityEnquerer<T extends Comparable<T>> implements PriorityQueue<T> {
    private T[] queue;
    private int size;

    /**
     * This is just an empty PriorityEnqueuer with space 10 initially
     */
    @SuppressWarnings("unchecked")
    public PriorityEnquerer() {
        this.queue = (T[]) new Comparable[10];
        this.size = 0;
    }
    /**
     * Dequeue remove an item from the queue. 
     * Pre-condition: the queue isn't empty.
     * 
     * @return the smallest item left in the queue
     */
    public  T dequeue() {
        int index = 0;
        //finding the index with the smallest value
        for (int i = 1; i < size; i ++) {
            if (queue[i].compareTo(queue[index]) < 0) {
                index = i;
            }
        }
        T value = queue[index];
        // Shifting the remaining items to fill that hole removed
        for (int i = index; i < size - 1; i ++) {
            queue[i] = queue[i + 1];
        }
        size --;
        return value;
    }
    /**
     * Enqueue adds an item to the queue.
     * 
     * @param value the value to be added
     */
    public void enqueue(T value) {
        queue[size] = value;
        size ++;
    }
    /** 
     * IsEmpty checks if the collection is empty.
     * 
     * @return true iff the collection has no more items, otherwise false.
     */
    public boolean isEmpty() {
        return size == 0;
    }
    /**
     * Peek reports the next item to be removed from the queue
     * 
     * @return the next value to be dequeued
     * 
     */
    public T peek() {
        int index = 0;
        for (int i = 1; i < size; i ++) {
            if (queue[i].compareTo(queue[index]) < 0) {
                index = i;
            }
        }
        return queue[index];
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
