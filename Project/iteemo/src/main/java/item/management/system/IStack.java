package item.management.system;

interface IStack<E> {
  
    /*** Removes the element at the top of stack and returnsthat element.
    * @return top of stack element, or through exception if empty
    */
    
    public E pop();
    
    /*** Get the element at the top of stack without removing it from stack.
    * @return top of stack element, or through exception if empty
    */
    
    public E peek();
    
    /*** Pushes an item onto the top of this stack.
    * @param E to insert*
    */
    
    public void push(E element);
    
    /*** Tests if this stack is empty
    * @return true if stack empty
    */
    public boolean isEmpty();
    
    public int size();
  }
