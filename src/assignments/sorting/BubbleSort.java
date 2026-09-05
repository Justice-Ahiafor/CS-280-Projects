package assignments.sorting;

/**
 * BubbleSort swaps adjacent elements over and over until the whole array is sorted.
 */
public class BubbleSort<T extends Comparable<T>> extends SortingAlgorithm<T> {
    /**
     * Sort an array in-place using BubbleSort.
     * 
     * Post-Condition: "Array" is sorted in ascending order.
     * 
     * @param array an array of integers
     */
    public void sort(T[] array) {
        // k is the length of the sub-array we are looping through in the inner loop 
        for (int k = array.length; k >= 2; k --) {
            // Go up to k - 1 because we don't need to compare the last index against its neighbors
            for (int i = 0; i < k - 1; i ++) {
                if (array[i].compareTo(array[i + 1]) > 0) {
                    // Swap adjacent items (i and i + 1).
                    swap(array, i, i +  1);
                } 
            }
        }
    }

    /**
     * Swap two elements within an array
     * @param array the array to swap values in
     * @param i the first index to swap
     * @param j the second index to swap
     */

    private void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp; 
    }

    /**
     * Run validation tests.
     * @param args command-line args
     */

    public static void main(String[] args) {
        SortingAlgorithm.validate(new BubbleSort<Integer>());
        System.out.println("BubbleSort has passed all tests.");


        
        // Fill an array with random numbers
        int N = 19999;
        Integer[] array = new Integer[N];
        for (int i = 0; i < array.length; i ++) {
            array[i] = (int)(N*Math.random());
        }

        // Measuring runtime.
        SortingAlgorithm<Integer> sorter = new BubbleSort<Integer>();
        long start = System.nanoTime();

        sorter.sort(array);
        long end = System.nanoTime();
        double duration = (end - start)/(1e9);

        System.out.println("Array size: "+N);
        System.out.println("Total duration: "+duration);
    }
}

// java -cp lib/* -ea src/assignments/sorting/BubbleSort.java 
// Used to run the file!
