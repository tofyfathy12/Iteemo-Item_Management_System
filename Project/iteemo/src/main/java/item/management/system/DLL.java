package item.management.system;

class DLLNode<E> {
    private E element;
    private DLLNode<E> next;
    private DLLNode<E> prev;

    public DLLNode(E Data, DLLNode<E> n, DLLNode<E> p) {
        element = Data;
        next = n;
        prev = p;
    }

    public E getElement() {
        return element;
    }

    public DLLNode<E> getNext() {
        return next;
    }

    public DLLNode<E> getPrev() {
        return prev;
    }

    public void setElement(E newElem) {
        element = newElem;
    }

    public void setNext(DLLNode<E> newNext) {
        next = newNext;
    }

    public void setPrev(DLLNode<E> newPrev) {
        prev = newPrev;
    }

}

public class DLL<E> implements ILinkedList<E> {
    private DLLNode<E> head;
    private DLLNode<E> tail;
    int size;

    public DLL() {
        this.head = this.tail = null;
        this.size = 0;
    }

    public DLLNode<E> getHead(){
        return head;
    }
    public DLLNode<E> getTail(){
        return tail;
    }
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

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else
            return false;
    }

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

    public int size() {
        return size;
    }

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
