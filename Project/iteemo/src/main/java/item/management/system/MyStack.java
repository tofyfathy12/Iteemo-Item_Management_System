package item.management.system;

import java.util.EmptyStackException;

class SNode<E> {
    private E data;
    private SNode<E> next;

    public SNode(E data, SNode<E> next) {
        this.data = data;
        this.next = next;
    }

    public E getData() {
        return data;
    }

    public SNode<E> getNext() {
        return next;
    }

    public void setData(E newData) {
        data = newData;
    }

    public void setNext(SNode<E> newNext) {
        next = newNext;
    }
}


public class MyStack<E> implements IStack<E> {
    private int size = 0;
    private SNode<E> top = null;
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

    public E peek() {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        return top.getData();
    }

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

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

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
