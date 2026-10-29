package assignments.datastructures;

import java.util.Iterator;

import adt.PriorityQueue;
import adt.Stack;
import adt.Tree;
/**
 * MinHeap: A binary tree with a structural condition.
 * a. No parent is greater than its children.
 * b. Tree is always filled in levelorder traversal.
 * 
 * No Recursive functions exist.
 * 
 * @param <T> the type of each element, which must have natural ordering.
 */
public class MinHeap<T extends Comparable<T>> implements PriorityQueue<T>, Tree<T> {
    private Vector<T> vector;
    /**
     * Initialize an empty priority queue
     */
    public MinHeap() {
        this.vector = new Vector<>();
    }
    /**
     * To dequeue;
     * a. Remove the root.
     * b. Take the last levelorder item and put it in the root.
     * c. Swap the problem parent down, percolate down, until you restore minHeap.
     * 
     * Maintains O(log N) runtime.
     * 
     * @return the item to be returned.
     */
    public T dequeue() {
        T root = vector.at(0);
        T lastItem = vector.pop();

        if (!vector.isEmpty()) {
            // LastItem goes in the root
            vector.set(0, lastItem);

            // Now percolate down
            int parent = 0;
            while (leftChild(parent) < vector.length()) {
                // Choose the smaller child, and the left one if they are equal
                int child = leftChild(parent);
                int right = rightChild(parent);

                if (right < vector.length() && vector.at(right).compareTo(vector.at(child)) < 0) {
                    child = right;
                }
                // Stop once the parent is no smaller than child
                if (vector.at(parent).compareTo(vector.at(child)) <= 0) {
                    break;
                }
                // Percolate down
                T temp = vector.at(parent);
                vector.set(parent, vector.at(child));
                vector.set(child, temp);

                parent = child;
            }
        }
        return root;
    }
    /**
     * To enqueue,
     * a. Start by putting the new item in the next available slot in levelorder.
     * b. Swap the problem child with its parents until all conditions are satisfied.
     * 
     * Maintain O(log N) runtime
     * 
     * @param value the value to be added.
     */
    public void enqueue(T value) {
        // Insert inlevel order
        vector.push(value);

        // Now percolate up
        int child = vector.length() - 1;
        while (child > 0) {
            int parent = parentIndex(child);
            // Stops once the child is no smaller than parent
            if (vector.at(child).compareTo(vector.at(parent)) >= 0) {
                break;
            }
            // Percolate up
            T temp = vector.at(child);
            vector.set(child, vector.at(parent));
            vector.set(parent, temp);

            child = parent;
        }
    }
    /** 
     * IsEmpty checks if the collection is empty.
     * 
     * @return true iff the collection has no more items, otherwise false.
     */
    public boolean isEmpty() {
        return vector.isEmpty();
    }
    /**
     * Peek reports the next item to be removed from the minHeap
     * 
     * @return the next value to be dequeued which is the root.
     */
    public T peek() {
        return vector.at(0);
    }
    /**
     * Compute the index of the parent 
     * 
     * @param childIndex the index of the child
     * @return the index of its parent
     */
    private int parentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }
    /**
     * Compute the index of the left child
     * 
     * @param parentIndex the index of the parent
     * @return the index of the left child
     */
    private int leftChild(int parentIndex) {
        return (parentIndex * 2) + 1;
    }
    /**
     * Compute the index of the right child
     * 
     * @param parentIndex the index of the parent
     * @return the index of the right child
     */
    private int rightChild(int parentIndex) {
        return leftChild(parentIndex) + 1;
    }

    /**
     * Compute the number of items in this tree.
     * @return the number of items
     */    
    public int length() {
        return vector.length();
    }
    /**
     * Compute the degree of the tree, i.e. the largest number of children any single node has.
     * @return the degree of the tree
     */    
    public int degree() {
        if (vector.length() <= 1) return 0;
        if (vector.length() == 2) return 1;
        return 2;
    }

    /**
     * Compute the height of the tree, i.e. the longest path to descend from the root to a leaf.
     * @return the height of the tree
     */    
    public int height() {
        if (vector.isEmpty()) return 0;

        int height = 1;
        int index = vector.length() - 1;

        while (index > 0) {
            index = parentIndex(index);
            height ++;
        }
        return height;
    }

    /**
     * Iterate over all elements in-order.
     * @return an iterator
     */
    public Iterator<T> iterator() {
        return this.inorder();
    }
    /**
     * Perform a pre-order traversal of the tree.
     * Traverse root, traverse left, traverse right
     * @return an iterator
     */
    public Iterator<T> preorder() {
        Vector<T> vectorResult = new Vector<>();
        // Base case for an empty subtree
        if (vector.isEmpty()) return vectorResult.iterator();

        // Use Stack instead of recursion as mentioned in class
        Stack<Integer> stack = new LinkedList<>();
        stack.push(0);

        while (!stack.isEmpty()) {
            // Top of the stack
            int currentIndex = stack.pop();

            vectorResult.push(vector.at(currentIndex));

            // I put the right child first because for a stack the left
            //  child is on top and it can come out first.
            if (rightChild(currentIndex) < vector.length()) stack.push(rightChild(currentIndex));
            if (leftChild(currentIndex) < vector.length()) stack.push(leftChild(currentIndex));

        }
        return vectorResult.iterator();
    }
    
    /**
     * Perform a level order traversal of the tree.
     * Its already in levelorder, so just read from front to back.
     * @return an iterator
     */
    public Iterator<T> levelorder() {
        return vector.iterator();
    }
    /**
     * Perform a post-order traversal of the tree.
     * Traverse left, traverse right, traverse root
     * @return an iterator
     */
    public Iterator<T> postorder() {
        Vector<T> vectorResult = new Vector<>();
        Stack<Integer> stack = new LinkedList<>();

        int currentIndex = 0;
        int lastIndex = -1;

        while (currentIndex < vector.length() || !stack.isEmpty()) {
            if (currentIndex < vector.length()) {
                stack.push(currentIndex);
                currentIndex = leftChild(currentIndex);
            }
            else {
                // Look on the top of the stack
                int topmost = stack.peek();
                if (rightChild(topmost) < vector.length() && lastIndex != rightChild(topmost)) {
                    currentIndex = rightChild(topmost);
                }
                else {
                    vectorResult.push(vector.at(topmost));
                    lastIndex = stack.pop();
                }
            }
        }
        return vectorResult.iterator();
    }
    /**
     * Perform an in-order traversal of the tree.
     * Traverse left, traverse root, traverse right
     * @return an iterator
     */
    public Iterator<T> inorder() {
        Vector<T> vectorResult = new Vector<>();
        Stack<Integer> stack = new LinkedList<>();

        int currentIndex = 0;

        while (currentIndex < vector.length() || !stack.isEmpty()) {
            // Go left
            while (currentIndex < vector.length()) {
                stack.push(currentIndex);
                currentIndex = leftChild(currentIndex);
            }
            // No more items on the left and so the item on top is next
            currentIndex = stack.pop();
            vectorResult.push(vector.at(currentIndex));

            currentIndex = rightChild(currentIndex);
        }
        return vectorResult.iterator();
    }

    /**
     * Run validation tests.
     * @param args command-line args
     */
    public static void main(String[] args) {
        PriorityQueue.validate(new MinHeap<Integer>());
        Tree.validate(new MinHeap<Integer>());

        System.out.println("MinHeap passes all tests without errors.");
    }
    
}
