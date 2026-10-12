package assignments.datastructures;

import java.util.Iterator;
import java.util.Vector;

import adt.Tree;
import adt.Queue;

/**
 * AVLTree: An AVL (Adelson-Velsky and Landis) tree is a binary search tree with a balance condition.
 * The balance condition must be easy to maintain, and it ensures the depth of the tree is O(logN).
 * 
 * Each node stores its own height, and the tree automatically performs rotations,
 *  after insertions and deletions to maintain the AVL height-balance property:
 *  for every node, the height difference between its left and right subtrees
 *  is at most 1.
 * 
 * @param <T> the type of each element, which must have a natural ordering
 */
public class AVLTree<T extends Comparable<T>> implements Tree<T> {
    private Node root;

    /**
     * Initialize an empty AVL tree.
     */
    public AVLTree() {
        this.root = null;
    }

    /**
     * Compute the number of items in this tree.
     * @return the number of items
     */
    public int length() {
        return length(this.root);
    }
    /**
     * Used recursion to count the number of nodes in the subtree
     * @param root the root of the subtree
     * @return the number of nodes in the subtree
     */
    private int length(Node root) {
        // Base case for when only the current node is empty
        if (root == null) return 0;

        // Count the left and right side then add 1
        int left = length(root.left);
        int right = length(root.right);

        return left + right + 1;
    }
    /**
     * Compute the degree of the AVL tree, i.e. the largest number of children any single node has.
     * @return the degree of the tree
     */
    public int degree() {
        return degree(this.root);
    }
    /**
     * Used recursion to determine the max degree of the subtree
     * @param root the root of the subtree
     * @return the max degree found in the subtree
     */
    private int degree(Node root) {
        // Base Case for when only the current node has no degree
        if (root == null) return 0;
        
        // Initialize the degree of the current node
        int currentDegree = 0;
        
        // Count the left and right degree
        if (root.left != null) {
            currentDegree += 1;            
        }
        if (root.right != null) {
            currentDegree += 1;            
        }
                            
        // Count the left and right side then take the biggest among them
        int left = degree(root.left);
        int right = degree(root.right);
        
        return Math.max(currentDegree, Math.max(left, right));  
    }
    
    /**
     * Compute the height of the AVL tree, ie, each node stores its own height.
     * @return the height of node root, or 0, if null
     */
    public int height() {
        return height(root);
    }
    /**
     * AVL trees doesn't compute the height using recursion
     *  
     * @param root the node whose height I want
     * @return the height of the node, or 0, if null.
     */
    private int height(Node root) {
        return root == null ? 0: root.height; 
    }
    /**
     * Perform a pre-order traversal of the tree.
     * @return an iterator
     */
    public Iterator<T> preorder() {
        Vector<T> vector = new Vector<>();
        preorder(this.root, vector);
        return vector.iterator();
    }
    /**
     * Used recursion to perform preorder traversal
     * @param root the root of the subtree
     * @param vector is what receives the traversed output
     */
    private void preorder(Node root, Vector<T> vector) {
        // Base case for empty subtree
        if (root == null) return;
        // Traverse root, traverse left, traverse right
        vector.add(root.data);
        preorder(root.left, vector);
        preorder(root.right, vector);
    }
    
    /**
     * Perform an in-order traversal of the tree.
     * @return an iterator
     */
    public Iterator<T> inorder() {
        Vector<T> vector = new Vector<>();
        inorder(this.root, vector);
        return vector.iterator();
    }
    /**
     * Used recursion to perform inorder traversal
     * @param root the root of the subtree
     * @param vector is what receives the traversed output
     */
    private void inorder(Node root, Vector<T> vector) {
        // Base case for empty subtree
        if (root == null) return;
        // Traverse left, traverse root, traverse right
        inorder(root.left, vector);
        vector.add(root.data);
        inorder(root.right, vector);
    }
    
    /**
     * Perform a post-order traversal of the tree.
     * @return an iterator
     */
    public Iterator<T> postorder() {
        Vector<T> vector = new Vector<>();
        postorder(this.root, vector);
        return vector.iterator();
    }
    /**
     * Used recursion to perform postorder traversal
     * @param root the root of the subtree
     * @param vector is what receives the traversed output
     */
    private void postorder(Node root, Vector<T> vector) {
        // Base case for empty subtree
        if (root == null) return;
        // Traverse left, traverse right, traverse root
        postorder(root.left, vector);
        postorder(root.right, vector);
        vector.add(root.data);
    }
    
    /**
     * Perform a level order traversal of the tree.
     * @return an iterator
     */
    public Iterator<T> levelorder() {
        Vector<T> vector = new Vector<>();
        if (root == null) return vector.iterator();
        
        Queue<Node> queue = new CircularLinkedList<>();
        queue.enqueue(root);

        while (!queue.isEmpty()) {
            Node currentNode = queue.dequeue();

            vector.add(currentNode.data);

            if (currentNode.left != null) {
                queue.enqueue(currentNode.left);
            }
            if (currentNode.right != null) {
                queue.enqueue(currentNode.right);
            }
        }
        return vector.iterator();
    }
    /**
     * Iterate over all elements in-order.
     * @return an iterator
     */
    public Iterator<T> iterator() {
        return this.inorder();
    }
    /**
     * Compute the balance factor of the node.
     * 
     * @param node the node whose balance factor I want
     * @return the balance factor
     */
    private int balanceFactor(Node node) {
        return height(node.left) - height(node.right);
    }
    /**
     * Update the height stored in a node.
     *
     * @param node the node whose height I'm  updating
     */
    private void updateHeight(Node node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }
    /**
     * Rotate binary tree node with left, update the height,
     *  and then return the new root
     * 
     * @param node the node of the unbalanced subtree
     * @return the new node after the rotation has happened
     */
    private Node rotateLeft(Node node) {
        Node node2 = node.right;
        Node tempNode = node2.left;

        node2.left = node;
        node.right = tempNode;

        node.height = Math.max(height(node.left), height(node.right)) + 1;
        node2.height = Math.max(height(node2.left), height(node2.right)) + 1;

        return node2;
    }
    /**
     * Rotate binary tree node with right, update the height,
     *  and then return the new root
     * 
     * @param node the node of the unbalanced subtree
     * @return the new node after the rotation has happened
     */
    private Node rotateRight(Node node) {
        Node node2 = node.left;
        Node tempNode = node2.right;

        node2.right = node;
        node.left = tempNode;

        node.height = Math.max(height(node.left), height(node.right)) + 1;
        node2.height = Math.max(height(node2.left), height(node2.right)) + 1;

        return node2;
    }
    /**
     * Restores AVL balance at the given node by performing rotations, 
     *  when the balance factor is outside the range of -1 and 1.
     *
     * @param node the root of the subtree
     * @return the new root of the subtree
     */
    private Node rebalance(Node node) {
        // First update the height
        updateHeight(node);

        int balance = balanceFactor(node);

        // If the left subtree is more
        if (balance > 1) {
            // Left-right
            if (balanceFactor(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        }
        // If the left side is heavy
        if (balance < -1) {
            // Right-left
            if (balanceFactor(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }
        // Now its balanced
        return node;
    }
    /**
     * Inserts a value into the AVL tree, if the value is there, nothing happens.
     * 
     * @param value value to be added to the AVL tree
     * @return true if the value is added, otherwise false
     */
    public boolean add(T value) {
        root = insert(root, value);
        return true;
    }
    /**
     * Insert a value in AVL tree using recursion.
     * 
     * @param node the current node
     * @param value the value to be inserted
     * @return the root of the subtree
     */
    private Node insert(Node node, T value) {
        // Base case
        if (node == null) return new Node(value);

        int comparison = value.compareTo(node.data);
        // Left side
        if (comparison <= 0) {
            node.left = insert(node.left, value);
        }
        // Right side
        else if (comparison > 0) {
            node.right = insert(node.right, value);
        }
        else {
            return node;
        }
        return rebalance(node);
    }
    /**
     * Removes a value from the AVL tree if its there.
     * 
     * @param value the value to be removed from the AVL tree
     * @return true is the value is found, otherwise false
     */
    public boolean remove(T value) {
        if (!contains(value)) return false;
        root = remove(root, value);
        return true;
    }
    /**
     * Remove a value in AVL tree using recursion
     * 
     * @param node the current node
     * @param value the value to be removed
     * @return the root of the subtree
     */
    private Node remove(Node node, T value) {
        // Base case
        if (node == null) return null;

        int comparison = value.compareTo(node.data);

        // Left side
        if (comparison < 0) {
            node.left = remove(node.left, value);
        }
        // Right side
        else if (comparison > 0) {
            node.right = remove(node.right, value);
        }
        else {
            // If the Node has 1 or 0 child
            if (node.left == null) return node.right;
            else if (node.right == null) return node.left;
            // 2 children
            else {
                Node succ = node.right;
                while (succ.left != null) succ = succ.left;
    
                // Replace the current node.data with the succ data
                node.data = succ.data;
                // Remove the succ node from the right
                node.right = remove(node.right, succ.data); 
            }
        }
        return rebalance(node);
    }
    /**
     * Determines whether a value in the AVL tree.
     * 
     * @param value value to check if it exists in the Binary Search Tree
     * @return true if the value is found, otherwise return false
     */
    public boolean contains(T value) {
        return this.contains(root, value);
    }

    /**
     * Used recursion to see whether a value in the tree exist
     * @param node the root of the subtree
     * @param value value to be searched for
     * @return return true if its successful, otherwise false.
     */
    private boolean contains(Node node, T value) {
        // Base case reaches a null node
        if (node == null) return false;

        if (node.data.equals(value)) return true;

        if (value.compareTo(node.data) < 0) {
            return contains(node.left, value);
        } 
        else {
            return contains(node.right, value);
        }
    }
    /**
     * An encapsulation of a value with two pointers, suitable for a AVL tree.
     */
    private class Node {
        T data;
        Node left;      // Left child
        Node right;     // Right Child
        int height;

        /**
         * Initialize a node with no children
         * @param data the data value
         */
        Node(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
            this.height = 1;
        }
    }

    /**
     * Run validation tests.
     * @param args command-line args
     */
    public static void main(String[] args) {
         Tree.validate(new AVLTree<>());

        // Build a sample tree.
        Integer[] numbers = {4, -4, -6, 0, -6, -3, -5, 5, -6, 0, -3, -4, 3, -3, -5};
        AVLTree<Integer> tree = new AVLTree<>();
        for (Integer n: numbers) tree.add(n);

        // Check structure.
        assert tree.length() == 15;
        assert tree.degree() == 2;
        assert tree.height() == 5;

        // Check traversals.
        assert testTraversal(tree.preorder(),   new int[]{-4, -6, -6, -6, -5, -5, -4, 0, -3, -3, -3, 0, 4, 3, 5});
        assert testTraversal(tree.inorder(),    new int[]{-6, -6, -6, -5, -5, -4, -4, -3, -3, -3, 0, 0, 3, 4, 5});
        assert testTraversal(tree.postorder(),  new int[]{-6, -6, -5, -4, -5, -6, -3, -3, 0, -3, 3, 5, 4, 0, -4});
        assert testTraversal(tree.levelorder(), new int[]{-4, -6, 0, -6, -5, -3, 4, -6, -5, -4, -3, 0, 3, 5, -3});
        assert testTraversal(tree.iterator(),   new int[]{-6, -6, -6, -5, -5, -4, -4, -3, -3, -3, 0, 0, 3, 4, 5});
        System.out.println("AVL Tree passes all tests.");

        // My tests
        AVLTree<Integer> small = new AVLTree<>();
        // Tests for add
        assert small.add(3) == true;
        assert small.add(10) == true;
        assert small.add(2) == true;
        assert small.add(9) == true;
        assert small.add(1) == true;

        // Tests for contains
        assert small.contains(5) == false;
        assert small.contains(3) == true;
        assert small.contains(7) == false;
        assert small.contains(11) == false;
        assert small.contains(2) == true;

        // Tests for remove
        assert small.remove(10) == true;
        assert small.remove(1) == true;
        assert small.remove(5) == false;
        assert small.remove(11) == false;
        assert small.remove(2) == true;

        System.out.println("AVL Tree passes all tests for add, contains, and remove tests.");
    }
    
    /**
     * Convenience method to test tree traversals.
     * @param iterator newly-constructed iterator
     * @param array expected values
     */
    private static boolean testTraversal(Iterator<Integer> iterator, int[] array) {
        boolean expected = true;
        for (int i = 0; i < array.length; i ++) {
            Integer next = iterator.next();
            expected = expected && next.equals(array[i]);
        }
        return expected && !iterator.hasNext();
    }
}
