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

        // Measuring Runtime
        /*for (int N = 100; N <= 1000000; N *= 10) {

            PriorityEnquerer<Integer> PriorityEnquerer1 = new PriorityEnquerer<>();
            
            for (int j = 0; j < N; j ++) {
                int randomValue = (int)(Math.random() * N + 1);
                PriorityEnquerer1.enqueue(randomValue);
            }

            long start = System.nanoTime();

            PriorityEnquerer1.dequeue();

            long end = System.nanoTime();

            double duration = (end - start) / 1e9;

            System.out.println(N + "\t" + duration);
        }*/

        System.out.println("PriorityEnquerer passes all tests without errors.");
    }
    
}
// To run a file and add it to CSV
// java -cp lib/* -ea src/assignments/algorithms/ERADemo.java >> dat/InsertionData.csv
// To create a csv file
// mkdir dat, meaning make directory!