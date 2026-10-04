package assignments.sorting;

/**
 * MergeSort: split data into subgroups and recursively merge them.
 * 
 * Post-Condition: "Array" is sorted in ascending order.
 * 
 * @param <T> the type of element to be sorted
 */
public class MergeSort<T extends Comparable<T>> extends SortingAlgorithm<T>{
    /**
     * sorts the whole array in ascending order
     * 
     * @param array the array to be sorted
     */
    public void sort(T[] array) {
        // Allocate a working array
        @SuppressWarnings("unchecked")
        T[] workingArray = (T[]) new Comparable[array.length];

        sort(array, workingArray, 0, array.length - 1);
    }
    /**
     * Sorts the section of the array recursively
     * 
     * @param array the array to be sorted
     * @param workingArray the temporary buffer array
     * @param leftHandSide the starting index of the partition
     * @param rightHandSide the ending index of the partition
     */
    private void sort(T[] array, T[] workingArray, int leftHandSide, int rightHandSide) {
        // Base case: If there is only one element in the array, its already sorted
        if (leftHandSide >= rightHandSide) return;

        // Determining the midde
        int middle = (leftHandSide + rightHandSide) / 2;

        // Sort the left half and the right half
        sort(array, workingArray, leftHandSide, middle);          // Left
        sort(array, workingArray, middle + 1, rightHandSide);     // Right

        //Merge the sorted halves
        merge(array, workingArray, leftHandSide, middle, rightHandSide);
    }
    /**
     * Merges the two sorted partitions back together
     * 
     * @param array the array to be sorted
     * @param workingArray the temporary buffer array
     * @param leftHandSide the starting index of the left half
     * @param midde the ending index of the left half
     * @param rightHandSide the ending index of the right half
     */
    private void merge(T[] array, T[] workingArray, int leftHandSide, int midde, int rightHandSide) {

        int i = leftHandSide;
        int j = midde + 1;
        int k = leftHandSide;

        // Now I get to compare the elements from both halves and copy the smaller one
        while (i <= midde && j <= rightHandSide) {
            if (array[i].compareTo(array[j]) <= 0) {
                workingArray[k ++] = array[i ++];
            }
            else {
                workingArray[k ++] = array[j ++];
            }
        }
        // Now copy the remaining elements from the left half
        while (i <= midde) {
            workingArray[k ++] = array[i ++];            
        }
        // Now copy the remaining elements from the right half
        while (j <= rightHandSide) {
            workingArray[k ++] = array[j ++];
        }
        // Now copy the merged elements back into the original array
        for (int x = leftHandSide; x <= rightHandSide; x ++) {
            array[x] = workingArray[x];
        }
    }
    /*
     * Steps: 
     * a. Split list in half
     * b. Sort left / right half(recursively)
     * c. Keep track of the smallest(leftmost) item in each half.
     * d. The smaller of the 2 numbers is taken as the leftmost in a newList(move 
     *     cursor to next item in the half you just took from)
     * e. Until complete one half is totally used up or has been added to our newList
     * f. Bring in other half in other
     */
    
    /**
     * Run validation tests.
     * @param args command-line args
     */
    public static void main(String[] args) {
        SortingAlgorithm.validate(new MergeSort<Integer>());
        System.out.println("MergeSort has passed all tests.");
    }  
}
