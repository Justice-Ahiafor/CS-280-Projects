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
    private T[] queue;
    private int size;

    /**
     * This is just an empty PriorityDequeuer with space 10 initially
     */
    @SuppressWarnings("unchecked") 
    public PriorityDequeuer() {
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
        if (isEmpty()) {
            System.out.println("Queue is empty.");
        }
        size --;
        T value = queue[size];
        queue[size] = null;
        return value;
    }
    /**
     * Enqueue adds an item to the queue.
     * 
     * @param value the value to be added
     */
    public void enqueue(T value) {
        int index = 0;
        // Loop around to find where the value fits in
        while (index < size && value.compareTo(queue[index]) < 0) {
            index ++;
        }
        // Shifting the elements to make room for the insertion value
        for (int i = size; i > index; i --) {
            queue[i] = queue[i - 1];
        }
        queue[index] = value;
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
     */
    public T peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
        }
        return queue[size - 1];
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
