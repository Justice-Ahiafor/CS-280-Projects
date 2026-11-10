package assignments.sorting;

/**
 * ShellSort works by comparing elements that are distant; the distance between
 *  comparisons decreases as the algorithm runs until the last phase, in which
 *  adjacents elements are compared.
 * Also called diminishing increment sort.
 * Strategy;
 * a. Create a big gap so there is big jumps, so that the items can get close
 *  to their final places quickly.
 * b. Do an insertion sort with the gaps.
 * 
 * @param <T> the type of element to be sorted
 */
public class ShellSort<T extends Comparable<T>> extends SortingAlgorithm<T> {

    /** 
     * Sorts the array using ShellSort
     * 
     * @param array the array to be sorted
     */
    public void sort(T[] array) {
        int totalLength = array.length;
        
        // Start with a big gap
        for (int gap = totalLength / 2; gap > 0; gap /= 2) {
            
            // Do an insertion sort but with gap
            for (int i = gap; i < totalLength; i ++) {
                T temp = array[i];
                int j;
            
                for (j = i; j >= gap && temp.compareTo(array[j - gap]) < 0; j -= gap) {
                        array[j] = array[j - gap];
                    }

                    array[j] = temp;
            }
        }
    }
    /**
     * Run validation tests.
     * @param args command-line args
     */
    public static void main(String[] args) {
        SortingAlgorithm.validate(new ShellSort<Integer>());
        System.out.println("ShellSort has passed all tests.");
        /*
        // Fill an array with random numbers
        int N = 199999;
        Integer[] array = new Integer[N];
        for (int i = 0; i < array.length; i ++) {
            array[i] = (int)(N*Math.random());
        }

        // Measuring runtime.
        SortingAlgorithm<Integer> sorter = new ShellSort<Integer>();
        long start = System.nanoTime();

        sorter.sort(array);
        long end = System.nanoTime();
        double duration = (end - start)/(1e9);

        System.out.println("Array size: "+N);
        System.out.println("Total duration: "+duration);
        */
    } 
    
}
