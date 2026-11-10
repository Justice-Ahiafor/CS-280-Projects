package assignments.sorting;

/**
 * RadixSort: Sometimes called card sort is a much trivial algorithm
 *  useful for large gaps of bucketsort. It sorts digit by digit.
 *  The runtime analysis for Radixsort is O(d(M + N)).
 */
public class RadixSort extends SortingAlgorithm<Integer> {
    /**
     * Sorts the array using radix sort.
     * 
     * @param array the array to be sorted
     */
    public void sort(Integer[] array) {
        if (array == null || array.length <= 1) return;

        int max = getMax(array);

        Integer [] results = new Integer[array.length];

        for (int exponent = 1; max / exponent > 0; exponent *= 10) {
            int [] count = new int[10];

            // Count how many times it appears
            for (int i = 0; i < array.length; i ++) {
                count[(Math.abs(array[i]) / exponent) % 10] ++;
            }

            for (int i = 1; i < 10; i ++) {
                count[i] += count[i - 1];
            }

            // Make sure elements are in the sorted position
            for (int i = array.length - 1; i >= 0; i --) {
                results[count[Math.abs(array[i] / exponent) % 10] - 1] = array[i];
                count[Math.abs(array[i] / exponent) % 10] --;
            }

            // Copy results back to array
            for (int i = 0; i < array.length; i ++) {
                array[i] = results[i];
            }
        }

        // Handle negative
        int index = 0;

        // negative in reverse
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] < 0) results[index++] = array[i];
        }

        // positive in normal
        for (int i = 0; i < array.length; i++) {
            if (array[i] >= 0) results[index++] = array[i];
        }

        // Copy into final order
        for (int i = 0; i < array.length; i++) array[i] = results[i];
    }

    /**
     * Finds the maximum in the array.
     * 
     * @param array the array to be sorted
     * @return the maximum absolute value
     */
    private int getMax(Integer[] array) {
        int max = Math.abs(array[0]);
        for (int i = 0; i < array.length; i ++) {
            if (Math.abs(array[i]) > max) max = Math.abs(array[i]);
        }
        return max;
    }

    /**
     * Run validation tests.
     * @param args command-line args
     */
    public static void main(String[] args) {
        SortingAlgorithm.validate(new RadixSort());
        System.out.println("RadixSort has passed all tests.");
        /*
        // Fill an array with random numbers
        int N = 199999;
        Integer[] array = new Integer[N];
        for (int i = 0; i < array.length; i ++) {
            array[i] = (int)(N*Math.random());
        }

        // Measuring runtime.
        SortingAlgorithm<Integer> sorter = new RadixSort();
        long start = System.nanoTime();

        sorter.sort(array);
        long end = System.nanoTime();
        double duration = (end - start)/(1e9);

        System.out.println("Array size: "+N);
        System.out.println("Total duration: "+duration);
        */
    }    
}
