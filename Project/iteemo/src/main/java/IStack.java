

/**
 * Interface for a generic Stack data structure.
 * Defines the standard LIFO (Last-In, First-Out) operations for a stack.
 * @param <E> the type of elements held in this stack
 */
public interface IStack<E> {
  
    /**
    * Removes the element at the top of this stack and returns that element.
    * @return top of stack element, or throws exception if empty
    */
    
    public E pop();
    
    /**
    * Get the element at the top of stack without removing it from stack.
    * @return top of stack element, or throws exception if empty
    */
    
    public E peek();
    
    /**
    * Pushes an item onto the top of this stack.
    * @param element to insert
    */
    
    public void push(E element);
    
    /**
    * Tests if this stack is empty
    * @return true if stack empty
    */
    public boolean isEmpty();
    
    /**
     * Returns the number of elements in this stack.
     * @return the size of the stack
     */
    public int size();
}