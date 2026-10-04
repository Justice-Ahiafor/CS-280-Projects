package assignments.sorting;

/**
 * QuickSort: Sort values relative to a pivot.
 * 
 * Post-Condition: "Array" is sorted in ascending order.
 * 
 * @param <T> the type of element to be sorted
 */
public class QuickSort<T extends Comparable<T>> extends SortingAlgorithm<T> {
    /**
     * Sorts the whole array in ascending order
     * 
     * @param array the array to be sorted
     */
    public void sort(T[] array) {
        sort(array, 0, array.length - 1);
    }
    /**
     * Sorts the section of the array recursively
     * 
     * @param array the array to be sorted
     * @param leftHandSide the starting index of the partition
     * @param rightHandSide the ending index of the partition
     */
    private void sort(T[] array, int leftHandSide, int rightHandSide) {
        // Base Case: if the partition has 0 or 1 element, its already sorted
        if (leftHandSide < rightHandSide) {
            // Choose a pivot to partition the array 
            int pivot = mySort(array, leftHandSide, rightHandSide);

            //sort the left partition and the right partition
            sort(array, leftHandSide, pivot - 1);
            sort(array, pivot + 1, rightHandSide);
        }
    }
    /**
     * sorts the array around the first element as the pivot
     * 
     * @param array the array to be partitioned
     * @param leftHandSide the starting index
     * @param rightHandSide the ending index
     * @return the final spot of the pivot
     */
    private int mySort(T[] array, int leftHandSide, int rightHandSide) {
        T pivot = array[leftHandSide];        // Let the first index be the pivot
        int known = leftHandSide;

        // Assign values to the left and right of the partition
        for (int i = leftHandSide + 1; i <= rightHandSide; i ++) {
            if (array[i].compareTo(pivot) < 0) {
                known ++;
                
                //swap the arrays of i and known!
                T temp = array[known];
                array[known] = array[i];
                array[i] = temp;
            }
        }
        // Now put pivot in the right place by switching into its final place
        T temp = array[leftHandSide];
        array[leftHandSide] = array[known];
        array[known] = temp;

        return known;
    }
    /* 
     * Steps:
     * a. Choose a pivot, usually first element
     * b. Assign values to a left / right partition based on the pivot
     * c. Pivot is in the right place
     *      i. Now sort left partition and right partition
     *          * Make use of a recursive function
     */ 

    /**
     * Run validation tests.
     * @param args command-line args
     */
    public static void main(String[] args) {
        SortingAlgorithm.validate(new QuickSort<Integer>());
        System.out.println("QuickSort has passed all tests.");

        /*
        // Fill an array with random numbers
        int N = 19999;
        Integer[] array = new Integer[N];
        for (int i = 0; i < array.length; i ++) {
            array[i] = (int)(N*Math.random());
        }

        // Measuring runtime.
        SortingAlgorithm<Integer> sorter = new QuickSort<Integer>();
        long start = System.nanoTime();

        sorter.sort(array);
        long end = System.nanoTime();
        double duration = (end - start)/(1e9);

        System.out.println("Array size: "+N);
        System.out.println("Total duration: "+duration);
        */
    }    

}
