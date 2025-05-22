package item.management.system;

import java.util.EmptyStackException;

class Node<E> {
    private E data;
    private Node<E> next;

    public Node(E data, Node<E> next) {
        this.data = data;
        this.next = next;
    }

    public E getData() {
        return data;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setData(E newData) {
        data = newData;
    }

    public void setNext(Node<E> newNext) {
        next = newNext;
    }
}


public class MyStack<E> implements IStack<E> {
    private int size = 0;
    private Node<E> top = null;
    public E pop() {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        Node<E> poppedNode = top;
        top = top.getNext();
        poppedNode.setNext(null);
        size--;
        return poppedNode.getData();
    }

    public E peek() {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        return top.getData();
    }

    public void push(E element) {
        if (top == null) {
            top = new Node<E>(element, null);
        }
        else {
            Node<E> newNode = new Node<E>(element, top);
            top = newNode;
        }
        size++;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public void printStack() {
        Node<E> head = top;
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
