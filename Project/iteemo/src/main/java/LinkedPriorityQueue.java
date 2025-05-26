

/**
 * Custom exception for an empty queue.
 * Thrown by {@link LinkedPriorityQueue} operations when attempting to access elements from an empty queue.
 */
class EmptyQueueException extends Exception {
    /**
     * Constructs a new EmptyQueueException with the specified detail message.
     * @param message the detail message.
     */
    public EmptyQueueException(String message) {
        super(message);
    }
}

/**
 * Represents a node in the {@link LinkedPriorityQueue}.
 * Each node stores a key (priority), data (element), and a reference to the next node.
 * @param <E> the type of element stored in this node
 */
class PQNode<E> {
    private int key;
    private E data;
    private PQNode<E> next;

    /**
     * Constructs a new PQNode.
     * @param key the priority key for this node
     * @param data the data element to be stored
     * @param next the next PQNode in the sequence
     */
    public PQNode(int key, E data, PQNode<E> next) {
        this.key = key;
        this.data = data;
        this.next = next;
    }

    /**
     * Returns the data element stored in this node.
     * @return the data element
     */
    public E getData() {
        return data;
    }

    /**
     * Returns the priority key of this node.
     * @return the key
     */
    public int getKey() {
        return key;
    }

    /**
     * Returns the next node in the priority queue.
     * @return the next PQNode, or null if this is the last node
     */
    public PQNode<E> getNext() {
        return next;
    }

    /**
     * Sets the next node in the priority queue.
     * @param newNext the PQNode to be set as the next node
     */
    public void setNext(PQNode<E> newNext) {
        next = newNext;
    }
}

/**
 * Implements a priority queue using a sorted singly linked list.
 * Elements are ordered by key (priority), with higher keys indicating higher priority.
 * This implementation maintains elements in descending order of priority.
 * @param <E> the type of elements stored in this priority queue
 */
public class LinkedPriorityQueue<E> implements IPriorityQueue<E> {
    private int Qsize = 0;
    private PQNode<E> head = null;

    /**
     * Checks if the priority queue is empty.
     * @return true if the queue contains no elements, false otherwise
     */
    public boolean isEmpty() {
        return (Qsize == 0);
    }

    /**
     * Returns the number of elements in the priority queue.
     * @return the size of the queue
     */
    public int size() {
        return Qsize;
    }

    /**
     * Inserts an element with a given key (priority) into the priority queue.
     * The queue maintains elements in descending order of keys.
     * @param k the key (priority) of the element to insert
     * @param e the element to insert
     */
    @Override
    public void insert(int k, E e) {
        PQNode<E> newPQNode = new PQNode<E>(k, e, null);
    if (head == null || k > head.getKey()) {
        newPQNode.setNext(head);
        head = newPQNode;
    } else {
        PQNode<E> current = head;
        while (current.getNext() != null && current.getNext().getKey() >= k) {
            current = current.getNext();
        }
        newPQNode.setNext(current.getNext());
        current.setNext(newPQNode);
    }
        Qsize++;
    }

    /**
     * Returns the element with the highest priority (maximum key) without removing it.
     * @return the element with the highest priority
     * @throws NullPointerException if the queue is empty (as head would be null).
     * A robust implementation would typically throw {@link EmptyQueueException}.
     */
    public E max() {
        return head.getData();
    }

    /**
     * Removes and returns the element with the highest priority (maximum key).
     * @return the element with the highest priority
     * @throws EmptyQueueException if the priority queue is empty
     */
    public E removeMax() throws EmptyQueueException {
        if (head != null) {
            E max = head.getData();
            head = head.getNext();
            Qsize--; // Qsize was not decremented in the original code, added here for Javadoc context
            return max;
        } else {
            throw new EmptyQueueException("Empty Stack"); // Message "Empty Stack" is from original, though it's a queue.
        }
    }

    /**
     * Removes a specific data element from the priority queue.
     * This operation iterates through the queue to find and remove the element.
     * If multiple instances of the data exist with different keys, this removes the first one encountered
     * that matches the data via reference equality (==).
     * @param Data the element to remove from the queue.
     */
    public void remove(E Data) {
        PQNode<E> prev = null, cur = head;
        while (cur != null && cur.getData() != Data) {
            prev = cur;
            cur = cur.getNext();
        }

        if (cur != null) { // Ensure cur is not null before trying to operate on it or prev
            if (prev == null) { // Removing head
                head = cur.getNext();
            } else {
                prev.setNext(cur.getNext());
            }
            cur.setNext(null); // help garbage collector
            Qsize--;
        }
    }


    /**
     * Returns the head node of the priority queue's underlying linked list.
     * This is typically used for internal operations or specific use cases like iterating through the queue elements
     * in priority order.
     * @return the head PQNode of the queue
     */
    public PQNode <E> getHead() {
        return head;
    }
}