

/**
 * Interface for a generic List data structure.
 * Defines common operations for lists, such as adding, getting, setting, and removing elements.
 * The {@code DLLNode<E> add(E element)} method is specific to an implementation that returns the node.
 * @param <E> the type of elements in this list
 */
public interface ILinkedList<E> {
    /**
    * Inserts a specified element at the specified position in the list.
    * Shifts the element currently at that position (if any) and any subsequent elements to the right.
    * @param index index at which the specified element is to be inserted
    * @param element element to be inserted
    */
    public void add(int index, E element);
    /**
    * Inserts the specified element at the end of the list.
    * @param element element to be appended to this list
    * @return DLLNode the node that was added to the list (specific to DLL implementation)
    */
    public DLLNode<E> add(E element);
    /**
    * Returns the element at the specified position in this list.
    * @param index index of the element to return
    * @return the element at the specified position in this list.
    */
    public E get(int index);
    /**
    * Replaces the element at the specified position in this list with the
    * specified element.
    * @param index index of the element to replace
    * @param element element to be stored at the specified position
    */
    public void set(int index, E element);
    /**
    * Removes all of the elements from this list.
    */
    public void clear();
    /**
    * Returns true if this list contains no elements.
    * @return true if this list contains no elements.
    */
    public boolean isEmpty();
    /**
    * Removes the element at the specified position in this list.
    * @param index the index of the element to be removed
    */
    public void remove(int index);
    /**
    * Returns the number of elements in this list.
    * @return the number of elements in this list.
    */
    public int size();
    /**
    * Returns a view of the portion of this list between the specified fromIndex
    * and toIndex, inclusively.
    * @param fromIndex low endpoint (inclusive) of the subList
    * @param toIndex high endpoint (inclusive) of the subList
    * @return a new ILinkedList representing the specified sublist
    */
    public ILinkedList<E> sublist(int fromIndex, int toIndex);
    /**
    * Returns true if this list contains an element with the same value as the
    * specified element.
    * @param o element whose presence in this list is to be tested
    * @return true if this list contains the specified element.
    */
    public boolean contains(E o);
}