

/**
 * Represents a node in a doubly linked list.
 * Each node stores an element and references to the next and previous nodes in the list.
 * @param <E> the type of element stored in this node
 */
class DLLNode<E> {
    private E element;
    private DLLNode<E> next;
    private DLLNode<E> prev;

    /**
     * Constructs a new DLLNode with the specified data, next node, and previous node.
     * @param Data the element to be stored in this node
     * @param n the next node in the list
     * @param p the previous node in the list
     */
    public DLLNode(E Data, DLLNode<E> n, DLLNode<E> p) {
        element = Data;
        next = n;
        prev = p;
    }

    /**
     * Returns the element stored in this node.
     * @return the element
     */
    public E getElement() {
        return element;
    }

    /**
     * Returns the next node in the list.
     * @return the next node, or null if this is the tail
     */
    public DLLNode<E> getNext() {
        return next;
    }

    /**
     * Returns the previous node in the list.
     * @return the previous node, or null if this is the head
     */
    public DLLNode<E> getPrev() {
        return prev;
    }

    /**
     * Sets the element stored in this node.
     * @param newElem the new element
     */
    public void setElement(E newElem) {
        element = newElem;
    }

    /**
     * Sets the next node in the list.
     * @param newNext the new next node
     */
    public void setNext(DLLNode<E> newNext) {
        next = newNext;
    }

    /**
     * Sets the previous node in the list.
     * @param newPrev the new previous node
     */
    public void setPrev(DLLNode<E> newPrev) {
        prev = newPrev;
    }
}

/**
 * Implements a doubly linked list (DLL).
 * Supports operations such as add, get, set, remove, and others.
 * @param <E> the type of elements stored in this list
 */
public class DLL<E> implements ILinkedList<E> {
    private DLLNode<E> head;
    private DLLNode<E> tail;
    int size;

    /**
     * Constructs an empty doubly linked list.
     */
    public DLL() {
        this.head = this.tail = null;
        this.size = 0;
    }

    /**
     * Returns the head node of the list.
     * @return the head node, or null if the list is empty
     */
    public DLLNode<E> getHead(){
        return head;
    }

    /**
     * Returns the tail node of the list.
     * @return the tail node, or null if the list is empty
     */
    public DLLNode<E> getTail(){
        return tail;
    }

    /**
     * Inserts the specified element at the specified position in this list.
     * Shifts the element currently at that position (if any) and any subsequent elements to the right.
     * @param index the index at which the specified element is to be inserted
     * @param element the element to be inserted
     * @throws Error if the index is out of range (index < 0 || index > size())
     */
    public void add(int index, E element) {
        DLLNode<E> curr = head;
        if (index == 0) {
            DLLNode<E> NewNode = new DLLNode<E>(element, head, null);
            if (curr != null) {
                curr.setPrev(NewNode);
            }
            head = NewNode;
            size++;
        } else if (index >=0 && size > index && head != null) {
            for (int i = 0; i < index - 1; i++) {
                curr = curr.getNext();
            }
            DLLNode<E> NewNode = new DLLNode<E>(element, curr.getNext(), curr);
            curr.setNext(NewNode);
            DLLNode<E> nextNode = NewNode.getNext();
            if (nextNode != null) {
                nextNode.setPrev(NewNode);
            }
            size++;
        } else if (index > 0 &&index == size) {
            add(element);
        } else {
            throw new Error("You Can't add at index " + index + " While The size of the list is " + size);
        }
    }

    /**
     * Appends the specified element to the end of this list.
     * @param element the element to be added to this list
     * @return the newly added DLLNode
     */
    public DLLNode<E> add(E element) {
        if (head == null) {
            DLLNode<E> NewNode = new DLLNode<E>(element, null, null);
            head = NewNode;
            tail = NewNode;
            size++;
            return NewNode;
        } else {
            DLLNode<E> NewNode = new DLLNode<E>(element, null, tail);
            tail.setNext(NewNode);
            tail = NewNode;
            size++;
            return NewNode;
        }
    }

    /**
     * Returns the element at the specified position in this list.
     * @param index the index of the element to return
     * @return the element at the specified position in this list
     * @throws Error if the index is out of range (index < 0 || index >= size())
     */
    public E get(int index) {
        DLLNode<E> curr = head;
        if (index >=0 && size > index && head != null) {
            for (int i = 0; i < index; i++) {
                curr = curr.getNext();
            }
            return curr.getElement();
        } else {
            throw new Error("You Can't get element at index " + index + " While The size of the list is " + size);
        }
    }

    /**
     * Replaces the element at the specified position in this list with the specified element.
     * @param index the index of the element to replace
     * @param element the element to be stored at the specified position
     * @throws Error if the index is out of range (index < 0 || index >= size())
     */
    public void set(int index, E element) {
        DLLNode<E> curr = head;
        if (index >=0 && size > index && head != null) {
            for (int i = 0; i < index; i++) {
                curr = curr.getNext();
            }
            curr.setElement(element);
        } else {
            throw new Error("You Can't set element at index " + index + " While The size of the list is " + size);
        }
    }

    /**
     * Removes all of the elements from this list.
     * The list will be empty after this call returns.
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Returns true if this list contains no elements.
     * @return true if this list is empty, false otherwise
     */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else
            return false;
    }

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their indices).
     * @param index the index of the element to be removed
     * @throws Error if the index is out of range (index < 0 || index >= size())
     */
    public void remove(int index) {
        DLLNode<E> curr = head;
        DLLNode<E> prev = null;
        if (head == null) {
            return;
        }
        if (index == 0) {
            head = head.getNext();
            if (head != null) {
                head.setPrev(null);
            }
            size--;
        } else if (index >= 0 && size > index && head != null) {
            for (int i = 0; i < index; i++) {
                prev = curr;
                curr = curr.getNext();
            }
            prev.setNext(curr.getNext());
            curr = curr.getNext();
            if (curr == null) {
                tail = prev;
            } else {
                curr.setPrev(prev);
            }
            size--;
        } else {
            throw new Error("You Can't remove element at index " + index + " While The size of the list is " + size);
        }
    }

    /**
     * Returns the number of elements in this list.
     * @return the number of elements in this list
     */
    public int size() {
        return size;
    }

    /**
     * Returns a view of the portion of this list between the specified fromIndex, inclusive, and toIndex, inclusive.
     * The returned list is a new DLL containing copies of the elements from the original list.
     * @param fromIndex the low endpoint (inclusive) of the subList
     * @param toIndex the high endpoint (inclusive) of the subList
     * @return a new DLL representing the sublist
     * @throws Error if fromIndex or toIndex are out of range, or if fromIndex > toIndex
     */
    public DLL<E> sublist(int fromIndex, int toIndex) {
        if (toIndex >=0 && fromIndex >=0 && fromIndex < size && toIndex < size && fromIndex <= toIndex) {
            DLL<E> subDll = new DLL<E>();
            DLLNode<E> curr = head;
            for (int i = 0; i < fromIndex; i++) {
                curr = curr.getNext();
            }

            for (int i = fromIndex; i <= toIndex; i++) {
                subDll.add(curr.getElement());
                curr = curr.getNext();
            }
            return subDll;
        } else {
            throw new Error("Can't create sublist");
        }

    }

    /**
     * Returns true if this list contains the specified element.
     * More formally, returns true if and only if this list contains at least one element e such that (o==null ? e==null : o.equals(e)).
     * This implementation uses reference equality (==) for comparison.
     * @param o element whose presence in this list is to be tested
     * @return true if this list contains the specified element
     */
    public boolean contains(E o) {
        DLLNode<E> curr1 = head;
        DLLNode<E> curr2 = tail;
        while (curr1 != null && curr1 != null && curr2 != curr1.getPrev()) {
            if (curr1.getElement() == o || curr2.getElement() == o) {
                return true;
            }
            curr1 = curr1.getNext();
            curr2 = curr2.getPrev();
        }

        return false;
    }

    /**
     * Prints the elements of the list to the console, enclosed in square brackets and separated by commas.
     * Example: [element1, element2, element3]
     */
    public void printList() {

        System.out.print('[');
        DLLNode<E> curr = head;
        while (curr != null) {
            System.out.print(curr.getElement());
            if (curr.getNext() != null) {
                System.out.print(", ");
            }
            curr = curr.getNext();
        }
        System.out.println(']');
    }
}