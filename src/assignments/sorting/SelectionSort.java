package assignments.sorting;

public class SelectionSort<T extends Comparable<T>> extends SortingAlgorithm<T> {
    /**
     * Sort an array in-place using SelectionSort.
     * 
     * Post-Condition: "Array" is sorted in ascending order.
     * 
     * @param array
     */
    
    public void sort(T[] array) {
        
        for (int k = 0; k < array.length - 1; k ++ ) {
            int Smallest_Element = k;        // Assume the smallest element is at index k.
           
            for (int i = k + 1; i < array.length; i ++) {
                if (array[i].compareTo(array[Smallest_Element]) < 0) {
                    Smallest_Element = i;    // Updates the array to the smallest element as it goes.
                }
            }
            //Swap with the smallest element
            swap(array, k, Smallest_Element);
        }

    }

    /**
     * Picks out the smallest element one-by-one
     * a. It identifies the smallest element in the unsorted group
     * b. Then, you move that element as far to the left of the unsorted group as possible
     * 
     * @param array an array of integers
     * 
     */
    private void swap(T[] array, int k, int i) {
        T temp = array[k];
        array[k] = array[i];
        array[i] = temp; 
    }
    /**
     * Run validation tests.
     * @param args command-line args
     */
    
    public static void main(String[] args) {
        SortingAlgorithm.validate(new SelectionSort<Integer>());
        System.out.println("SelectionSort has passed all tests.");

        // Fill an array with random numbers
        int N = 19999;
        Integer[] array = new Integer[N];
        for (int i = 0; i < array.length; i ++) {
            array[i] = (int)(N*Math.random());
        }
        
        // Measuring runtime.
        SortingAlgorithm<Integer> sorter = new SelectionSort<Integer>();
        long start = System.nanoTime();

        sorter.sort(array);
        long end = System.nanoTime();
        double duration = (end - start)/(1e9);

        System.out.println("Array size: "+N);
        System.out.println("Total duration: "+duration);
    }

     
}

