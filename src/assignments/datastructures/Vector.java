package assignments.datastructures;

import adt.List;

/// An extensible list backed by an array buffer.
/// 
/// The idea here is to store your data in an array larger than it has to be.
/// This gives you room to add more items to the end, without having to reallocate memory every time.
/// You simply need to keep track of which parts of the array are currently in use.
/// 
/// Eventually, the array buffer *will* run out of space.
/// Then you need to allocate an even larger buffer, and copy the present buffer to the new one.
/// This is a very expensive operation, so you want to make sure it occurs very infrequently.
/// 
/// @param <T> the type of each element
public class Vector<T> implements List<T> {
    /** The initial amount of buffer space in a newly-created vector. */
    public static final int INITIAL_BUFFER_SIZE = 10;
    private T[] array;
    private int size;

    /**
     * Initialize an empty vector.
     */
    @SuppressWarnings("unchecked")
    public Vector() {
        // Generic types (i.e. `T`) don't technically exist at runtime, so you have to allocate arrays generically and then cast them.
        // This is normally bad practice and generates a warning, hence the @SuppressWarnings tag before the method.
        this.array = (T[])(new Object[INITIAL_BUFFER_SIZE]);
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
        // You may use assert statements to enforce pre-conditions at runtime.
        assert 0 <= index && index < this.size;

        // TODO implement this method
        return this.array[index];
    }
    
    /**
     * Change an item in the list.
     * @param index the location of the item - a nonnegative integer less than the length of the list
     * @param value the new value to assign at the given location
     */
    public void set(int index, T value) {
        // You may use assert statements to enforce pre-conditions at runtime.
        assert 0 <= index && index < this.size;

        // TODO implement this method
        this.array[index] = value;
    }
    
    /**
     * Check if the list contains a given value.
     * @param value the value to look for
     * @return true iff the collection contains value
     */
    public boolean contains(T value) {
        // TODO implement this method
        /*
         * Loop around all the elements used in the vector
         * compare it with the required value
         * If it matches, return True.
         * If there is no match, return false.
         */
        for (int i = 0; i < this.size; i++) {
            if (this.array[i].equals(value)) {
                return true;
            }
        }
            return false;
        }
    
    /**
     * Insert an item into the list.
     * @param index the location of where to put the item - a nonnegative integer less than or equal to the length of the list
     * @param value the new value to put at the given location
     */
    public void insert(int index, T value) {
        // You may use assert statements to enforce pre-conditions at runtime.
        // Note this function has a somewhat different pre-condition!
        assert 0 <= index && index <= this.size;

        // TODO implement this method
        // If in case the array buffer gets full, to resize it, I'm just going to double its capacity
        if (this.size == this.array.length) {
            resize(this.array.length * 2);
        }
        /*
         * Shift all the members one spot to the right
         * and insert the new value in that spot
         */
        for (int i = this.size; i > index; i -- ) {     // Shifts element to the right
            this.array[i] = this.array[i - 1];
        }
        // inserts the new value
        this.array[index] = value;
        // Increases the size of the buffer
        this.size ++;
    }
    
    /**
     * Remove an item from the list.
     * @param index the location to delete from - a nonnegative integer less than the length of the list
     * @return the value which was removed
     */
    public T delete(int index) {
        // You may use assert statements to enforce pre-conditions at runtime.
        assert 0 <= index && index < this.size;

        // TODO implement this method
        /*
         * I saved the element thats is being removed so that later on I can return it
         * Shift all the elements to the left when you start from the index you deleted
         * Reduce the size because an element has been deleted
         */
        T removed = this.array[index];
        for (int i = index; i < this.size - 1; i ++) {  // Shift elements to the left
            this.array[i] = this.array[i + 1];
        }
        // Reduce the size of the vector
        this.size --;
        return removed;     // Spits back the deleted elements
        
    }

    /**
     * Resize the internal buffer array.
     * 
     * This method involves copying from the current buffer to a newly allocated one.
     * 
     * @param newSize the new size of the internal buffer array
     */
    @SuppressWarnings("unchecked")
    private void resize(int newSize) {
        // TODO implement this method. Take inspiration from the constructor implementation.
        T[] newArray = (T[])(new Object[newSize]);
        // Copy elements already there into the new buffer
        for (int i = 0; i < this.size; i++) {
            newArray[i] = this.array[i];
        }
        // Replace the old buffer
        this.array = newArray;
    }

    /**
     * Run validation tests.
     * @param args command-line args
     */
    public static void main(String[] args) {
        List.validate(new Vector<>());
        System.out.println("Vector passes all tests.");
    }
    
}
