package assignments.datastructures;

import java.util.Iterator;
import adt.List;

/// An extensible list backed by a chain of nodes.
/// 
/// The idea here is to wrap each datum in a larger structure, a *node*,
///  which also contains a pointer to the node containing the *next* element in the list.
/// This structure permits efficient insertion and deletion,
///  in the sense that it only requires rearranging pointers nearby where the change takes place.
/// 
/// However, this structure foregoes *random access*, i.e. easy access to arbitrary locations in the list.
/// In order to make any changes to a location in the middle of the list,
///  one must first traverse through the chain of nodes from the beginning of the list.
/// 
/// @param <T> the type of each element
public class LinkedList<T> implements List<T>, Iterable<T> {
    private Node head;
    private int size;

    /**
     * Initialize an empty linked list.
     */
    public LinkedList() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Compute the number of items in this list.
     * @return the number of items
     */
    public int length() {
        // TODO implement this method
        return this.size;
    }
    
    /**
     * Fetch an item from the list.
     * @param index the location of the item - a nonnegative integer less than the length of the list
     * @return the value stored at the given location
     */
    public T at(int index) {
        assert 0 <= index && index < this.size;
        // TODO implement this method

        // To get to the specific index you need,
        //  you have to start from the beginning and go one step at a time.
        Node initialNode = this.head;

        // Use a loop to iterate through
        for (int i = 0; i < index; i ++) {
            initialNode = initialNode.link;
        }
        // Return value at this index
        return initialNode.data;
    }
    
    /**
     * Change an item in the list.
     * @param index the location of the item - a nonnegative integer less than the length of the list
     * @param value the new value to assign at the given location
     */
    public void set(int index, T value) {
        assert 0 <= index && index < this.size;
        // TODO implement this method
        
        // To get to the specific index you need to be changed,
        //  you have to start from the beginning and go one step at a time.
        Node initialNode = this.head;

        // Use a loop to iterate through
        for (int i = 0; i < index; i ++) {
            initialNode = initialNode.link;
        }
        // Once found, set the index at the element equal to the value
        initialNode.data = value;
    }
    
    /**
     * Check if the list contains a given value.
     * @param value the value to look for
     * @return true iff the collection contains value
     */
    public boolean contains(T value) {
        // TODO implement this method

        // To make sure the list contains the value,
        //  you have to start from the beginning and go one step at a time.
        Node initialNode = head;

        // Using a while loop to loop through until it reaches the end, 
        //  because without the while loop, it will just look at the initialNode and quit
        while (initialNode != null) {    
            // this will take control of when what you are looking for doesn't exist 
            if (value == null) {
                if (initialNode.data == null) {
                    // found the node that has null data
                    return true;
                }
            }
            else {
                if (value.equals(initialNode.data)) {
                    // found the correct node I want
                    return true;
                }
            }
            // move to the next node
            initialNode = initialNode.link;
        }
        // if for every check node its not true, then return false
        return false; 
    }
    
    /**
     * Insert an item into the list.
     * @param index the location of where to put the item - a nonnegative integer less than or equal to the length of the list
     * @param value the new valu e to put at the given location
     */
    public void insert(int index, T value) {
        assert 0 <= index && index <= this.size;
        // TODO implement this method

        // If you have to insert the value at index 0
        if (index == 0) {
            this.head = new Node(value, this.head); // new node points to old head

            this.size ++;
        }
        else {
            // For a given index, i want to loop and stop one step befor the index it wants
            Node initialNode = this.head;

            for (int i = 0; i < index - 1; i ++) {
                initialNode = initialNode.link;
            }
            // Insert the new node in the right spot and increase the size by 1
            initialNode.link = new Node(value, initialNode.link);

            this.size ++;
        }
    }

    
    /**
     * Remove an item from the list.
     * @param index the location to delete from - a nonnegative integer less than the length of the list
     * @return the value which was removed
     */
    public T delete(int index) {
        assert 0 <= index && index < this.size;
        // TODO implement this method

        T removedValue;         // This is just to hold what I'm deleting
        
        // If the item is at the first index 
        if (index == 0) {
            removedValue = this.head.data;      // Take the data at the first index
            this.head = this.head.link;         // Moves the head forward and neglects the first node
        }
        // Otherwise if your taking the node from elsewhere
        else {
        Node initialNode = this.head;          // Start from the head

            for (int i = 0; i < index - 1; i ++) {
                initialNode = initialNode.link;     // Move the tracker to the next node
            }
            removedValue = initialNode.link.data;   
            initialNode.link = initialNode.link.link;
        }
        // Reduce the size of the vector
        this.size --;

        return removedValue;     // Spits back the deleted elements
        
    }

    /**
     * An encapsulation of a value with a pointer, allowing us to chain to another value.
     */
    private class Node {
        T data;
        Node link;

        /**
         * Initialize a node with no children.
         * @param data the data value
         * @param link the next node in the chain
         */
        Node(T data, Node link) {
            this.data = data;
            this.link = link;
        }
    }
    /** 
     * Create an iterator that walks through the LinkedList for one node at a time
     * The iterator starts at the head of the list and moves forward by following each node
     * The hasNext() checks if there is a node next
     * The next() returns the value of the initialNode then moves to the next
     */
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node initialNode = head;    // Starts at the head

            public boolean hasNext() {  // Checks if there is still a node
                return initialNode != null;
            }
            public T next() {           // Returns the node's data then moves to the next one
                T value = initialNode.data;
                initialNode = initialNode.link;
                return value;
            }
        };
    }

    /**
     * Run validation tests.
     * @param args command-line args
     */
     public static void main(String[] args) {
        List.validate(new LinkedList<>());

        // Test iterator.
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < 5; i ++) list.insert(0, i);
        Iterator<Integer> iter = list.iterator();
        for (int i = 5; i > 0; i --) assert iter.next().equals(i-1);
        assert !iter.hasNext();

        System.out.println("LinkedList passes all tests.");
    }
}
