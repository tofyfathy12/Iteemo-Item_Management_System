package item.management.system;

public interface IPriorityQueue<E> {
    
    /** 
     * Return the number of elements in the priority queue.
     * @return number of elements
     */
    int size();

    /** 
     * Test whether the priority queue is empty.
     * @return true if empty, false otherwise
     */
    boolean isEmpty();

    /** 
     * Insert a new element with key k and element e into the priority queue.
     * @param k the key associated with the element
     * @param e the element to insert
     */
    void insert(int k, E e);

    /** 
     * Return, but do not remove, the element with the max key.
     * @return the element with the max key
     * @throws IllegalStateException if the priority queue is empty
     */
    E max();

    /** 
     * Remove and return the element with the largest key.
     * @return the removed element with the largest key
     * @throws IllegalStateException if the priority queue is empty
     */
    E removeMax() throws EmptyQueueException;

    void remove(E Data);
}