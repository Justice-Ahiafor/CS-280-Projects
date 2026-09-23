package assignments.datastructures;

import adt.List;
import java.util.Iterator;

/**
 * CircularLinkedList stores data in a node that goes around in a circle,
 *  it has refernece to the tail node that link in turn to the head node and makes it go in circles.
 * 
 * @param <T> the type of each element
 */
public class CircularLinkedList<T> implements List<T>, Iterable<T> {
    private  Node tail;             // Last node, tail.links is the head
    private int size;

    /**
     * Initialize an empty CircularLinkedList
     */
    public CircularLinkedList() {
        this.tail = null;
        this.size = 0;
    }

    /**
     * Compute the number of items in this list
     * @return the number of items 
     */
    public int length() {
        return this.size;
    }

    /**
     * Fetch an item from the list.
     * @param index the location of the item
     * @return the value stored at the given location
     */
    public T at(int index) {
        assert 0 <= index && index < this.size;
        // TODO implement this method

        Node initialNode = tail.link;

        for (int i = 0; i < index; i ++) {
            initialNode = initialNode.link;
        }
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
        
        Node initialNode = tail.link;

        for (int i = 0; i < index; i ++) {
            initialNode = initialNode.link;
        }
        initialNode.data = value;
    }
    /**
     * Check if the list contains a given value.
     * @param value the value to look for
     * @return true iff the collection contains value
     */
    public boolean contains(T value) {
        // TODO implement this method

        if (tail == null) {
            return false;
        }
        Node initialNode = tail.link;

        for (int i = 0; i < size; i ++) {    
            if (value == null) {
                if (initialNode.data == null) {
                    return true;
                }
            }
            else {
                if (value.equals(initialNode.data)) {
                    return true;
                }
            }
            initialNode = initialNode.link;
        }
        return false; 
    }
    /**
     * Remove an item from the list.
     * @param index the location to delete from - a nonnegative integer less than the length of the list
     * @return the value which was removed
     */
    public T delete(int index) {
        assert 0 <= index && index < this.size;
        // TODO implement this method

        T removedValue;
        
        if (index == 0) {
            Node head = this.tail.link;
            removedValue = head.data;
            this.tail.link = head.link;
        }
        else {
        Node initialNode = this.tail.link;

            for (int i = 0; i < index - 1; i ++) {
                initialNode = initialNode.link;
            }
            removedValue = initialNode.link.data;  
            
            // Deleting the tail
            
            if (initialNode.link == this.tail) {
                initialNode.link = this.tail.link;
                this.tail = initialNode;
            }
            else {
                initialNode.link = initialNode.link.link;
            }
        }
        this.size --;

        return removedValue;
        
    }
    /**
     * Insert an item into the list.
     * @param index the location of where to put the item - a nonnegative integer less than or equal to the length of the list
     * @param value the new valu e to put at the given location
     */
    public void insert(int index, T value) {
        assert 0 <= index && index <= this.size;
        // TODO implement this method

        // Empty List
        if (this.size == 0) {
            Node newNode = new Node(value, null);
            newNode.link = newNode;
            this.tail = newNode;
            this.size ++;
            return;
        }
        // Prepend, index 0
        if (index == 0) {
            Node head = this.tail.link;
            Node newNode = new Node(value, head);
            this.tail.link = newNode;
            this.size ++;
            return;
        }
        // Append, index at end
        if (index == this.size) {
            Node head = this.tail.link;
            Node newNode = new Node(value, head);
            this.tail.link = newNode;
            this.tail = newNode;
            this.size ++;
            return;
        }
        // Insert anywhere else
        else {
            Node initialNode = this.tail.link;

            for (int i = 0; i < index - 1; i ++) {
                initialNode = initialNode.link;
            }
            initialNode.link = new Node(value, initialNode.link);

            this.size ++;
        }
    }
    /**
     * Iterator for CircularLinkedList
     * @return an iterator that produces items in a list from first to last
     */
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node initialNode;
            int count = 0;
        
        {
            if (tail == null) {
                initialNode = null;         // Empty List
            }
            else {
                initialNode = tail.link;    // head
            }
        }
            /**
             * @returns true if the iteration has more elements.
             *  (In other words, returns true if next would return an element rather than throwing an exception.)
             */
            public boolean hasNext() {  
                return count < size;
            }
            /** 
             * @returns the next element in the iteration.
             */
            public T next() {
                T value = initialNode.data;
                initialNode = initialNode.link;
                count ++;
                return value;
            }
        };
    }
    /**
     * Node class for CircularLinkedList
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
     * Run validation tests.
     * @param args command-line args
     */
    public static void main(String[] args) {
        List.validate(new CircularLinkedList<>());

        // Test iterator.
        CircularLinkedList<Integer> list = new CircularLinkedList<>();
        for (int i = 0; i < 5; i ++) list.insert(0, i);
        Iterator<Integer> iter = list.iterator();
        for (int i = 5; i > 0; i --) assert iter.next().equals(i-1);
        assert !iter.hasNext();

        System.out.println("CircularLinkedList passes all tests.");
    }
}
