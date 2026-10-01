package assignments.sorting;

import java.util.ArrayList;
import java.util.List;

/**
 * MergeSort: split data into subgroups and recursively merge them.
 * 
 * Post-Condition: "List" is sorted in ascending order.
 * 
 */
public class MergeSort<T extends Comparable<T>> extends SortingAlgorithm<T> {
    public void sort(T[] array) {
        if (array == null || array.length <= 1) return;

        // Convert the array to an ArrayList that way I can use
        List<T> list = new ArrayList<>(List.of(array));

        // Call recursive list sorting method
        sort(list);

        // Write the sorted elemnts back into the array
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
        }
    }

    public void sort(List<T> list) {
        // Base case: If the list has 0 or 1 elements
        if (list.size() <= 1) return;

        // Split the list into two
        int middle = list.size() / 2;

        // Split into 2 and create a new ArrayList
        List<T> leftHandSide = new ArrayList<>(list.subList(0, middle));
        List<T> rightHandSide = new ArrayList<>(list.subList(middle, list.size()));

        // Now you can sort it recursively
        sort(leftHandSide);
        sort(rightHandSide);
        
        // Merge both list together now
        merge(list, leftHandSide, rightHandSide);
    }
    private void merge(List<T> complete, List<T>leftHandSide, List<T>rightHandSide) {
        int i = 0;
        int j = 0;
        int k = 0;

        // Compare elements from the left and right hand sides
        while (i < leftHandSide.size() && j < rightHandSide.size()) {
            if (leftHandSide.get(i).compareTo(rightHandSide.get(j)) <= 0) {
                complete.set(k ++, leftHandSide.get(i ++));
            }
            else {
                complete.set(k ++, rightHandSide.get(j ++));
            }
        }
        // Now copy the remaining elements from the left and the right hand sides
        while (i < leftHandSide.size()) {
            complete.set(k ++, leftHandSide.get(i ++));
        }
        while (j < rightHandSide.size()) {
            complete.set(k ++, rightHandSide.get(j ++));
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
     * 
     * g. Overwrite the array with the sorted elements from the list
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
