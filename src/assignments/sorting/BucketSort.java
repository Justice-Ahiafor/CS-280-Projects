package assignments.sorting;

/**
 * BucketSort 
 * Prepare an array of "buckets" that maintains a runtime complexity of O(M)
 * Put each item in its bucket, maintains a runtime of O(N)
 * Take each item out of the bucket, maintains a runtime complexity of O(M + N)
 */
public class BucketSort extends SortingAlgorithm<Integer> {
    /**
     * Sorts the array using BucketSort
     * 
     * @param array the array to be sorted
     */
    public void sort(Integer[] array) {
        // Pretend the first index is both the max and the min
        int minimumIndex = array[0];
        int maximumIndex = array[0];

        // Now find the max and min values in the array
        for (int i = 1; i < array.length; i ++) {
            if (array[i] < minimumIndex) minimumIndex = array[i];
            if (array[i] > maximumIndex) maximumIndex = array[i];
        }

        // How many buckets I need
        int totalBuckets = maximumIndex - minimumIndex + 1;

        // Initialize an array of buckets
        int[] bucket = new int[totalBuckets];

        // Count whenever a value comes up
        for (int i = 0; i < array.length; i ++) {
            int bucketIndex = array[i] - minimumIndex;
            bucket[bucketIndex] ++;
        }

        int arrayIndex = 0;
        
        // Visit every bucket from small to big
        for (int bucketIndex = 0; bucketIndex < totalBuckets; bucketIndex ++) {
            
            // Put the elements back in the array
            while (bucket[bucketIndex] > 0) {
                array[arrayIndex] = bucketIndex + minimumIndex;
                arrayIndex ++;
                bucket[bucketIndex] --;
            }
        }
    }

    /**
     * Run validation tests.
     * @param args command-line args
     */
    public static void main(String[] args) {
        SortingAlgorithm.validate(new BucketSort());
        System.out.println("BucketSort has passed all tests.");
        /*
        // Fill an array with random numbers
        int N = 199999;
        Integer[] array = new Integer[N];
        for (int i = 0; i < array.length; i ++) {
            array[i] = (int)(N*Math.random());
        }

        // Measuring runtime.
        SortingAlgorithm<Integer> sorter = new BucketSort();
        long start = System.nanoTime();

        sorter.sort(array);
        long end = System.nanoTime();
        double duration = (end - start)/(1e9);

        System.out.println("Array size: "+N);
        System.out.println("Total duration: "+duration);
        */
    } 
    
}
