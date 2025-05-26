

import java.util.EmptyStackException;

/**
 * Represents a node in a singly linked list used to implement a stack.
 * Each node stores data and a reference to the next node.
 * @param <E> the type of element stored in this node
 */
class SNode<E> {
    private E data;
    private SNode<E> next;

    /**
     * Constructs a new SNode with the specified data and next node.
     * @param data the element to be stored in this node
     * @param next the next node in the list
     */
    public SNode(E data, SNode<E> next) {
        this.data = data;
        this.next = next;
    }

    /**
     * Returns the data stored in this node.
     * @return the data element
     */
    public E getData() {
        return data;
    }

    /**
     * Returns the next node in the list.
     * @return the next node, or null if this is the last node
     */
    public SNode<E> getNext() {
        return next;
    }

    /**
     * Sets the data stored in this node.
     * @param newData the new data element
     */
    public void setData(E newData) {
        data = newData;
    }

    /**
     * Sets the next node in the list.
     * @param newNext the new next node
     */
    public void setNext(SNode<E> newNext) {
        next = newNext;
    }
}


/**
 * Implements a generic stack data structure using a singly linked list.
 * Supports standard stack operations: push, pop, peek, isEmpty, and size.
 * @param <E> the type of elements stored in this stack
 */
public class MyStack<E> implements IStack<E> {
    private int size = 0;
    private SNode<E> top = null;
    /**
     * Removes and returns the element at the top of this stack.
     * @return the element at the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    public E pop() {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        SNode<E> poppedNode = top;
        top = top.getNext();
        poppedNode.setNext(null);
        size--;
        return poppedNode.getData();
    }

    /**
     * Returns the element at the top of this stack without removing it.
     * @return the element at the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    public E peek() {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        return top.getData();
    }

    /**
     * Pushes an element onto the top of this stack.
     * @param element the element to be pushed onto the stack
     */
    public void push(E element) {
        if (top == null) {
            top = new SNode<E>(element, null);
        }
        else {
            SNode<E> newNode = new SNode<E>(element, top);
            top = newNode;
        }
        size++;
    }

    /**
     * Tests if this stack is empty.
     * @return true if the stack contains no elements, false otherwise
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Returns the number of elements in this stack.
     * @return the current size of the stack
     */
    public int size() {
        return size;
    }

    /**
     * Prints the elements of the stack from top to bottom, enclosed in square brackets and separated by commas.
     * Example: [topElement, nextElement, ..., bottomElement]
     */
    public void printStack() {
        SNode<E> head = top;
        System.out.print("[");
        while (head != null) {
            System.out.print(head.getData());
            if (head.getNext() != null) {
                System.out.print(", ");
            }
            head = head.getNext();
        }
        System.out.println("]");
    }
}