package item.management.system;

public interface IBinarySearchTree<TKey extends Comparable<TKey>, Value> {
    // Checks if the tree is empty
    boolean isEmpty();

    // Returns the size of the tree
    int size();

    // Inserts a new node into the tree
    void insert(TKey key, Value value);

    // Deletes a node from the tree
    void delete(TKey key, Value deletedNode);

    // Searches for a key in the tree
    boolean contains(TKey key);

    // Retrieves the value associated with a key
    Value get(TKey key);

    // Updates the value associated with a key
    void update(TKey key, Value value);

    // Performs an in-order traversal of the tree
    // void inOrderTraversal(Collection<Value> result);

    // Performs a pre-order traversal of the tree
    // void preOrderTraversal(Collection<Value> result);

    // Performs a post-order traversal of the tree
    // void postOrderTraversal(Collection<Value> result);

    // Returns the height of the tree
    int height();
}
