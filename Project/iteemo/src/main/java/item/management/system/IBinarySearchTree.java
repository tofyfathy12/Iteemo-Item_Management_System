package item.management.system;

/**
 * Interface for a Binary Search Tree (BST).
 * Defines the standard operations that a BST should support.
 * @param <TKey> the type of keys maintained by this tree, must be comparable
 * @param <Value> the type of mapped values
 */
public interface IBinarySearchTree<TKey extends Comparable<TKey>, Value> {
    /**
     * Checks if the tree is empty.
     * @return true if the tree contains no elements, false otherwise
     */
    boolean isEmpty();

    /**
     * Returns the number of key-value mappings in this tree.
     * @return the size of the tree
     */
    int size();

    /**
     * Inserts a new key-value pair into the tree.
     * If the key already exists, its corresponding value may be updated.
     * @param key the key to insert
     * @param value the value associated with the key
     */
    void insert(TKey key, Value value);

    /**
     * Deletes the mapping for a key from this tree if it is present.
     * @param key the key whose mapping is to be removed from the tree
     */
    void delete(TKey key);

    /**
     * Searches for a key in the tree.
     * @param key the key to search for
     * @return true if the tree contains a mapping for the specified key, false otherwise
     */
    boolean contains(TKey key);

    /**
     * Retrieves the value to which the specified key is mapped,
     * or null if this tree contains no mapping for the key.
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or null if this tree contains no mapping for the key
     */
    Value get(TKey key);

    /**
     * Updates the value associated with a specified key.
     * If the key does not exist, this operation may or may not insert it, depending on the implementation.
     * @param key the key whose associated value is to be updated
     * @param value the new value to associate with the key
     */
    void update(TKey key, Value value);

    // /**
    //  * Performs an in-order traversal of the tree and adds the values to the provided collection.
    //  * @param result a collection to store the values in in-order
    //  */
    // void inOrderTraversal(Collection<Value> result);

    // /**
    //  * Performs a pre-order traversal of the tree and adds the values to the provided collection.
    //  * @param result a collection to store the values in pre-order
    //  */
    // void preOrderTraversal(Collection<Value> result);

    // /**
    //  * Performs a post-order traversal of the tree and adds the values to the provided collection.
    //  * @param result a collection to store the values in post-order
    //  */
    // void postOrderTraversal(Collection<Value> result);

    /**
     * Returns the height of the tree.
     * The height of an empty tree is typically defined as -1, and a tree with one node as 0.
     * @return the height of the tree
     */
    int height();
}