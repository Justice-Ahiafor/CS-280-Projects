package assignments.sorting;

import java.util.Iterator;

import assignments.datastructures.BinarySearchTree;

/**
 * TreeSort: Basically involves two strategies.
 * a. Put all items into a BinarySearchTree.
 * b. Traverse the tree using Inorder traversal.
 * 
 * @param <T> the type of element to be sorted.
 */
public class TreeSort<T extends Comparable<T>> extends SortingAlgorithm<T> {
    /**
     * Sorts the array using TreeSort
     * 
     * @param array the array to be sorted
     */
    public void sort(T[] array) {
        // Initialize an empty BinarySearchTree
        BinarySearchTree<T> tree = new BinarySearchTree<>();

        // Put all the items onto a BST
        for (T items: array) {
            tree.add(items);
        }

        // Traverse through using Inorder traversal.
        int i = 0;
        Iterator<T> iterator = tree.inorder();
        while (iterator.hasNext()) {
            array[i] = iterator.next();
            i ++;
        }
    }
    
    /**
     * Run validation tests.
     * @param args command-line args
     */
    public static void main(String[] args) {
        SortingAlgorithm.validate(new TreeSort<Integer>());
        System.out.println("TreeSort has passed all tests.");
        /*
        // Fill an array with random numbers
        int N = 199999;
        Integer[] array = new Integer[N];
        for (int i = 0; i < array.length; i ++) {
            array[i] = (int)(N*Math.random());
        }

        // Measuring runtime.
        SortingAlgorithm<Integer> sorter = new TreeSort<Integer>();
        long start = System.nanoTime();

        sorter.sort(array);
        long end = System.nanoTime();
        double duration = (end - start)/(1e9);

        System.out.println("Array size: "+N);
        System.out.println("Total duration: "+duration);
        */
    } 
}
