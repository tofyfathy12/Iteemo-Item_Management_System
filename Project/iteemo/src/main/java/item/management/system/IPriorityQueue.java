package item.management.system;

/**
 * Interface for a Priority Queue data structure.
 * Elements are typically ordered based on a key (priority), and operations
 * allow for insertion, retrieval of the maximum (or minimum) element,
 * and removal of the maximum (or minimum) element. This interface assumes a max-priority queue.
 * @param <E> the type of elements held in this priority queue
 */
public interface IPriorityQueue<E> {
    
    /** * Return the number of elements in the priority queue.
     * @return number of elements
     */
    int size();

    /** * Test whether the priority queue is empty.
     * @return true if empty, false otherwise
     */
    boolean isEmpty();

    /** * Insert a new element with key k and element e into the priority queue.
     * @param k the key (priority) associated with the element
     * @param e the element to insert
     */
    void insert(int k, E e);

    /** * Return, but do not remove, the element with the max key (highest priority).
     * @return the element with the max key
     * @throws IllegalStateException if the priority queue is empty (actual exception may vary by implementation)
     */
    E max();

    /** * Remove and return the element with the largest key (highest priority).
     * @return the removed element with the largest key
     * @throws EmptyQueueException if the priority queue is empty
     */
    E removeMax() throws EmptyQueueException;

    /**
     * Removes a specific element from the priority queue.
     * The means of identifying and removing the 'Data' element will depend on the implementation.
     * @param Data the element to remove
     */
    void remove(E Data);
}