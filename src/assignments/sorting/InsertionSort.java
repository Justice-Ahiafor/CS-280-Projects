package assignments.sorting;

public class InsertionSort<T extends Comparable<T>> extends SortingAlgorithm<T> {
    /**
     * Sort an array in-place using InsertionSort.
     * 
     * Post-Condition: "Array" is sorted in ascending order.
     * 
     * @param array
     */

    public void sort(T[] array) {
        // Loop begins at index 1 because the element from the first index is already "sorted"
        for (int i = 1; i < array.length; i ++) {
            T FirstValue = array[i];
            int j = i - 1;

            // Shift elements of all the ones greater than the FirstValue to the right
            while (j >= 0 && array[j].compareTo(FirstValue) > 0) {
                array[j + 1] = array[j];
                j = j - 1;                
            }
            // Put back the FirstValue into its correct sorted spot
            array[j + 1] = FirstValue;
        }
    }

    /**
     * Run validation tests.
     * @param args command-line args
     */

    public static void main(String[] args) {
        SortingAlgorithm.validate(new InsertionSort<Integer>());
        System.out.println("InsertionSort has passed all tests.");

        // Fill an array with random numbers
        int N = 19999;
        Integer[] array = new Integer[N];
        for (int i = 0; i < array.length; i ++) {
            array[i] = (int)(N*Math.random());
        }

        // Measuring runtime.
        SortingAlgorithm<Integer> sorter = new InsertionSort<Integer>();
        long start = System.nanoTime();

        sorter.sort(array);
        long end = System.nanoTime();
        double duration = (end - start)/(1e9);

        System.out.println("Array size: "+N);
        System.out.println("Total duration: "+duration);
    } 
}