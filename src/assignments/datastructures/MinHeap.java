package assignments.datastructures;

import adt.PriorityQueue;

/**
 * MinHeap: A binary tree with a structural condition.
 * a. No parent is greater than its children.
 * b. Tree is always filled in levelorder traversal.
 * 
 * @param <T> the type of each element, which must have natural ordering.
 */
public class MinHeap<T extends Comparable<T>> implements PriorityQueue<T> {
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
            vector.set(0, lastItem);

            dequeue(0);
        }
        return root;
    }

    /**
     * Used recursion to move a parent down the tree
     * 
     * @param parent the index of the item to be moved
     */
    private void dequeue(int parent) {
        // Base case for when the parent has no children
        if (leftChild(parent) >= vector.length()) return;

        // Chooses the smaller child or the left if they are the same
        int child = leftChild(parent);
        int right = rightChild(parent);

        if (right < vector.length() 
            && vector.at(right).compareTo(vector.at(child)) < 0) {
                child = right;
            }
        // Stop once the parent is no bigger than its smaller child
        if (vector.at(parent).compareTo(vector.at(child)) <= 0) return;

        T temp = vector.at(parent);
        vector.set(parent, vector.at(child));
        vector.set(child, temp);

        dequeue(child);
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
        vector.push(value);

        enqueue(vector.length() - 1);
    }

    /**
     * Used recursion to move a child up the tree
     * 
     * @param child the index of the item to be moved
     */
    private void enqueue(int child) {
        // Base case for when the root has no parent
        if (child == 0) return;

        int parent = parentIndex(child);
        // Stop once the child is no smaller than its parent
        if (vector.at(child).compareTo(vector.at(parent)) >= 0) return;

        T temp = vector.at(child);
        vector.set(child, vector.at(parent));
        vector.set(parent, temp);

        enqueue(parent);
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
     * Run validation tests.
     * @param args command-line args
     */
    public static void main(String[] args) {
        PriorityQueue.validate(new MinHeap<>());

        System.out.println("MinHeap passes all tests without error.");
    }
}
