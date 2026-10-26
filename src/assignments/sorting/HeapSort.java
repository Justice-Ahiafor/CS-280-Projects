package assignments.sorting;

import assignments.datastructures.MinHeap;

/**
 * HeapSort: Basically involves two startegies.
 * a. Enqueue all items onto a MinHeap. Maintains a runtime of O(N LogN).
 * b. Dequeue all the items from the Heap. Maintains a runtime of )(N Log N).
 * 
 * @param <T> the type of element to be sorted.
 */
public class HeapSort<T extends Comparable<T>> extends SortingAlgorithm<T> {

    /**
     * Sorts the array using HeapSort
     * 
     * @param array the array to be sorted
     */
    public void sort(T[] array) {

        // Initalize an empty MinHeap
        MinHeap<T> heap = new MinHeap<>();

        // Enqueue all the items onto the head
        for (T items: array) {
            heap.enqueue(items);
        }

        // Dequeue all the items from the heap
        for (int i = 0; i < array.length; i ++) {
            array[i] = heap.dequeue();
        }
    }
    /**
     * Run validation tests.
     * @param args command-line args
     */
    public static void main(String[] args) {
        SortingAlgorithm.validate(new HeapSort<Integer>());
        System.out.println("HeapSort has passed all tests.");
        /*
        // Fill an array with random numbers
        int N = 199999;
        Integer[] array = new Integer[N];
        for (int i = 0; i < array.length; i ++) {
            array[i] = (int)(N*Math.random());
        }

        // Measuring runtime.
        SortingAlgorithm<Integer> sorter = new HeapSort<Integer>();
        long start = System.nanoTime();

        sorter.sort(array);
        long end = System.nanoTime();
        double duration = (end - start)/(1e9);

        System.out.println("Array size: "+N);
        System.out.println("Total duration: "+duration);
        */
    } 
    
}
