package assignments.datastructures;

import java.util.Iterator;
import java.util.Vector;

import adt.Queue;
import adt.Tree;

/// A data structure where data is organized into a mathematical tree structure,
///  comprised of nodes which have at most two children.
/// 
/// The binary search tree satisfies the binary search tree condition:
///  every node has a value greater than that of its less child,
///  and less than or equal to that of its right child.
/// 
/// @param <T> the type of each element, which must have a natural ordering
public class BinarySearchTree<T extends Comparable<T>> implements Tree<T> {
    private Node root;

    /**
     * Initialize an empty tree.
     */
    public BinarySearchTree() {
        this.root = null;
    }

    /*
    /**
     * Manually construct a tree so we can test iterator methods.
     * 
     * NOTE: You will remove this method once we've learned how to add.
     * 
     * @param demo a specific sequence of numbers defined in the main method
     */
    /* public void fillForDay1Tests(T[] demo) {
        this.root = new Node(demo[0]);
        this.root.left = new Node(demo[1]);
        this.root.left.left = new Node(demo[2]);
        this.root.left.right = new Node(demo[3]);
        this.root.left.left.right = new Node(demo[4]);
        this.root.left.right.left = new Node(demo[5]);
        this.root.left.left.right.right = new Node(demo[6]);
        this.root.right = new Node(demo[7]);
        this.root.left.left.right.right.left = new Node(demo[8]);
        this.root.left.right.right = new Node(demo[9]);
        this.root.left.right.left.right = new Node(demo[10]);
        this.root.left.right.left.left = new Node(demo[11]);
        this.root.left.right.right.right = new Node(demo[12]);
        this.root.left.right.left.right.right = new Node(demo[13]);
        this.root.left.left.right.right.right = new Node(demo[14]);
    }
        */
    

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
     * Compute the degree of the tree, i.e. the largest number of children any single node has.
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
     * Compute the height of the tree, i.e. the longest path to descend from the root to a leaf.
     * @return the height of the tree
     */
    public int height() {
            return height(this.root);
    }
    /**
     * Used recursion to compute the height of the subtree
     * @param root the root of the subtree
     * @return the height of the subtree
     */
    private int height(Node root) {
        // Base Case for when only the current node has no height
        if (root == null) return 0;
                            
        // Count the left and right side then take the biggest among them and add 1
        int left = height(root.left);
        int right = height(root.right);
        
        return Math.max(left, right) + 1;  
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

    // NOTE: You're going to add more public methods here on the second day of trees.
    /**
     * 
     * Asks the question, did I change the tree?
     * 
     * @param value value to be added to the Binary Search Tree
     * @return true if the value is found, otherwise return false
     */
    public boolean add(T value) {
        if (root == null) {
            root = new Node(value);
            return true;
        }
        return add(root,value);
    }
    /**
     * Used recursion to add a value into the tree
     * @param node the root of the subtree
     * @param value value to be added
     * @return return true if its successful, otherwise false.
     */
    private boolean add(Node node, T value) {
        int comparison = value.compareTo(node.data);

        // Left side
        if (comparison < 0) {
            if (node.left == null) {
                node.left = new Node(value);
                return true;
            }
            else {
                return add(node.left, value);
            }
        }
        // Right side
        else {
            if (node.right == null) {
                node.right = new Node(value);
                return true;
            }
            else {
                return add(node.right, value);
            }
        }
    }
    /**
     * Asks the question, did I change the tree?
     * 
     * @param value value to be removed from the Binary Search Tree
     * @return true if the value is found, otherwise return false
     */
    public boolean remove(T value) {
        if (!contains(value)) return false;

        root = remove(root, value);
        return true;
    }
    /**
     * Used recursion to remove a value from the tree
     * @param node the root of the subtree
     * @param value value to be removed
     * @return return true if its successful, otherwise false.
     */
    private Node remove(Node rootNode, T value) {
        // Base case
        if (rootNode == null) return null;

        int comparison = rootNode.data.compareTo(value);

        // Left
        if (comparison > 0) {
            rootNode.left = remove(rootNode.left, value);
            return rootNode;
        }
        // Right
        if (comparison < 0) {
            rootNode.right = remove(rootNode.right, value);
            return rootNode;
        }
        // If the Node has 1 or 0 child
        if (rootNode.left == null) return rootNode.right;
        if (rootNode.right == null) return rootNode.left;

        // 2 children
        Node succ = rootNode.right;
        while (succ.left != null) succ = succ.left;
        // Replace the current node.data with the succ data
        rootNode.data = succ.data;
        // Remove the succ node from the right
        rootNode.right = remove(rootNode.right, succ.data);
        
        return rootNode;
    }
    /**
     * Asks the question, is the value in the Binary Search Tree?
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
     * An encapsulation of a value with two pointers, suitable for a binary tree.
     */
    private class Node {
        T data;
        Node left;      // Left child.
        Node right;     // Right child.

        /**
         * Initialize a node with no children.
         * @param data the data value
         */
        Node(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    /**
     * Run validation tests.
     * @param args command-line args
     */
    public static void main(String[] args) {
        Tree.validate(new BinarySearchTree<>());

        // Build a sample tree.
        Integer[] numbers = {4, -4, -6, 0, -6, -3, -5, 5, -6, 0, -3, -4, 3, -3, -5};
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        // tree.fillForDay1Tests(numbers);     // NOTE: To be replaced once we learn how to add.
        for (Integer n: numbers) tree.add(n);

        // Check structure.
        assert tree.length() == 15;
        assert tree.degree() == 2;
        assert tree.height() == 6;

        // Check traversals.
        assert testTraversal(tree.preorder(),   new int[]{4, -4, -6, -6, -5, -6, -5, 0, -3, -4, -3, -3, 0, 3, 5});
        assert testTraversal(tree.inorder(),    new int[]{-6, -6, -6, -5, -5, -4, -4, -3, -3, -3, 0, 0, 3, 4, 5});
        assert testTraversal(tree.postorder(),  new int[]{-6, -5, -5, -6, -6, -4, -3, -3, -3, 3, 0, 0, -4, 5, 4});
        assert testTraversal(tree.levelorder(), new int[]{4, -4, 5, -6, 0, -6, -3, 0, -5, -4, -3, 3, -6, -5, -3});
        assert testTraversal(tree.iterator(),   new int[]{-6, -6, -6, -5, -5, -4, -4, -3, -3, -3, 0, 0, 3, 4, 5});

        System.out.println("BinarySearchTree passes all tests.");

        // My tests
        BinarySearchTree<Integer> Tree = new BinarySearchTree<>();
        // Tests for add
        assert Tree.add(3) == true;
        assert Tree.add(10) == true;
        assert Tree.add(2) == true;
        assert Tree.add(9) == true;
        assert Tree.add(1) == true;

        // Tests for contains
        assert Tree.contains(5) == false;
        assert Tree.contains(3) == true;
        assert Tree.contains(7) == false;
        assert Tree.contains(11) == false;
        assert Tree.contains(2) == true;

        // Tests for remove
        assert Tree.remove(10) == true;
        assert Tree.remove(1) == true;
        assert Tree.remove(5) == false;
        assert Tree.remove(11) == false;
        assert Tree.remove(2) == true;
        


        System.out.println("BinarySearchTree passes all tests for add, contains, and remove tests.");
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