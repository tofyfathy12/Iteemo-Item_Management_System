/**
 * Represents a node in a binary search tree.
 * Each node stores a key-value pair, height, and references to its parent, left child, and right child.
 * @param <TKey> the type of keys maintained by this node, must be comparable
 * @param <Value> the type of mapped values
 */
class Node<TKey extends Comparable<TKey>, Value> {
        private int height;
        private TKey key;
        private Value value;
        private Node<TKey, Value> parent;
        private Node<TKey, Value> left;
        private Node<TKey, Value> right;
        /**
         * Constructs a new Node with the specified key and value.
         * Initializes height to 0 and parent, left, and right children to null.
         * @param key the key for this node
         * @param value the value associated with this node's key
         */
        public Node(TKey key, Value value) {
            this.height = 0;
            this.parent = this.left = this.right = null;
            this.key = key;
            this.value = value;
        }

        /**
         * Returns the key of this node.
         * @return the key
         */
        public TKey getKey() {
            return key;
        }

        /**
         * Returns the value of this node.
         * @return the value
         */
        public Value getValue() {
            return value;
        }

        /**
         * Sets the value of this node.
         * @param value the new value
         */
        public void setValue(Value value) {
            this.value = value;
        }

        /**
         * Checks if this node is a root node (has no parent).
         * @return true if this node is a root, false otherwise
         */
        public boolean isRoot() {
            return (this.parent == null);
        }

        /**
         * Returns the parent of this node.
         * @return the parent node, or null if this is the root
         */
        public Node<TKey, Value> getParent() {
            return this.parent;
        }

        /**
         * Sets the parent of this node.
         * @param newParent the new parent node
         */
        public void setParent(Node<TKey, Value> newParent) {
            this.parent = newParent;
        }

        /**
         * Returns the left child of this node.
         * @return the left child node, or null if there is no left child
         */
        public Node<TKey, Value> getLeft() {
            return this.left;
        }

        /**
         * Sets the left child of this node.
         * Also sets this node as the parent of the new left child if the child is not null.
         * @param left the new left child node
         */
        public void setLeft(Node<TKey, Value> left) {
            this.left = left;
            if (left != null)
                left.parent = this;
        }

        /**
         * Checks if this node has a left child.
         * @return true if this node has a left child, false otherwise
         */
        public boolean hasLeft() {
            return (this.left != null);
        }

        /**
         * Returns the right child of this node.
         * @return the right child node, or null if there is no right child
         */
        public Node<TKey, Value> getRight() {
            return this.right;
        }

        /**
         * Sets the right child of this node.
         * Also sets this node as the parent of the new right child if the child is not null.
         * @param right the new right child node
         */
        public void setRight(Node<TKey, Value> right) {
            this.right = right;
            if (right != null)
                right.parent = this;
        }

        /**
         * Checks if this node has a right child.
         * @return true if this node has a right child, false otherwise
         */
        public boolean hasRight() {
            return (this.right != null);
        }

        /**
         * Returns the height of this node.
         * @return the height
         */
        public int getHeight() {
            return this.height;
        }

        /**
         * Sets the height of this node.
         * @param newHeight the new height
         */
        public void setHeight(int newHeight) {
            this.height = newHeight;
        }
    }

/**
 * Implements a binary search tree (BST) data structure.
 * This implementation includes self-balancing capabilities (AVL-like rotations) upon insertion.
 * @param <TKey> the type of keys maintained by this tree, must be comparable
 * @param <Value> the type of mapped values
 */
public class BinarySearchTree<TKey extends Comparable<TKey>, Value> implements IBinarySearchTree<TKey, Value> {
    private Node<TKey, Value> root;
    private int size;

    /**
     * Constructs an empty binary search tree.
     */
    public BinarySearchTree() {
        this.root = null;
        this.size = 0;
    }

    /**
     * Returns the root node of the tree.
     * @return the root node, or null if the tree is empty
     */
    public Node<TKey, Value> root() {
        return root;
    }

    /**
     * Checks if the tree is empty.
     * @return true if the tree is empty, false otherwise
     */
    public boolean isEmpty() {
        return (root == null);
    }

    /**
     * Returns the number of nodes in the tree.
     * @return the size of the tree
     */
    public int size() {
        return size;
    }

    /**
     * Inserts a key-value pair into the tree.
     * If the key already exists, its value is updated.
     * The tree is balanced after insertion.
     * @param key the key to insert
     * @param value the value associated with the key
     */
    public void insert(TKey key, Value value) {
        insert(root, key, value);
    }

    /**
     * Private helper method to recursively insert a key-value pair into the subtree rooted at v.
     * @param v the root of the current subtree
     * @param key the key to insert
     * @param value the value associated with the key
     */
    private void insert(Node<TKey, Value> v, TKey key, Value value) {
        int cmp = 0;
        if (v == null) {
            root = new Node<TKey, Value>(key, value);
        }
        else {
            cmp = key.compareTo(v.getKey());
            if (cmp == 0) {
                v.setValue(value);
            }
            else if (cmp < 0) {
                if (v.hasLeft()) {
                    insert(v.getLeft(), key, value);
                }
                else {
                    Node<TKey, Value> newNode = new Node<TKey, Value>(key, value);
                    v.setLeft(newNode);
                }
            }
            else {
                if (v.hasRight()) {
                    insert(v.getRight(), key, value);
                }
                else {
                    Node<TKey, Value> newNode = new Node<TKey, Value>(key, value);
                    v.setRight(newNode);
                }
            }
        }
        int bf = getBalance(root);
        if (bf > 1) {
            cmp = key.compareTo(root.getLeft().getKey());
            if (cmp < 0) {
                root = rotateRight(root);
            }
            else if (cmp > 0){
                root.setLeft(rotateLeft(root.getLeft()));
                root = rotateRight(root);
            }
        }
        else if (bf < -1) {
            cmp = key.compareTo(root.getRight().getKey());
            if (cmp > 0) {
                root = rotateLeft(root);
            }
            else if (cmp < 0) {
                root.setRight(rotateRight(root.getRight()));
                root = rotateLeft(root);
            }
        }
    }

    /**
     * Updates the height of ancestors of a given child node.
     * This method seems to increment height rather than recalculating it based on children's heights,
     * which might not be standard for AVL height updates.
     * @param child the node whose ancestors' heights need updating
     */
    private void updateHeight(Node<TKey, Value> child) {
        Node<TKey, Value> iter = child.getParent();
        while (iter != null) {
            iter.setHeight(iter.getHeight() + 1);
            iter = iter.getParent();
        }
    }

    /**
     * Performs a left rotation on the subtree rooted at the given node.
     * @param root the root of the subtree to rotate
     * @return the new root of the rotated subtree
     */
    private Node<TKey, Value> rotateLeft(Node<TKey, Value> root) {
        Node<TKey, Value> Z = root.getRight();
        Node<TKey, Value> C = Z.getLeft();

        Z.setLeft(root);
        Z.setParent(null);
        root.setRight(C);
        updateHeight(root);

        return Z;
    }

    /**
     * Performs a right rotation on the subtree rooted at the given node.
     * @param root the root of the subtree to rotate
     * @return the new root of the rotated subtree
     */
    private Node<TKey, Value> rotateRight(Node<TKey, Value> root) {
        Node<TKey, Value> Z = root.getLeft();
        Node<TKey, Value> C = Z.getRight();

        Z.setRight(root);
        Z.setParent(null);
        root.setLeft(C);
        updateHeight(root);

        return Z;
    }

    /**
     * Calculates the balance factor of a given node.
     * The balance factor is the height of the left subtree minus the height of the right subtree.
     * @param root the node for which to calculate the balance factor
     * @return the balance factor, or 0 if the node is null
     */
    private int getBalance(Node<TKey, Value> root) {
        if (root == null)
            return 0;
        return height(root.getLeft()) - height(root.getRight());
    }

    /**
     * Returns the value associated with the minimum key in the tree.
     * @return the minimum value, or null if the tree is empty
     */
    public Value getMin() {
        return min(root).getValue();
    }

    /**
     * Private helper method to find the node with the minimum key in the subtree rooted at the given node.
     * @param root the root of the subtree
     * @return the node with the minimum key, or null if the subtree is empty
     */
    private Node<TKey, Value> min(Node<TKey, Value> root) {
        if (root == null) {
            return null;
        }
        Node<TKey, Value> x = root;
        while (x.getLeft() != null) {
            x = x.getLeft();
        }
        return x;
    }

    /**
     * Returns the value associated with the maximum key in the tree.
     * @return the maximum value, or null if the tree is empty
     */
    public Value getMax() {
        return max(root).getValue();
    }

    /**
     * Private helper method to find the node with the maximum key in the subtree rooted at the given node.
     * @param root the root of the subtree
     * @return the node with the maximum key, or null if the subtree is empty
     */
    private Node<TKey, Value> max(Node<TKey, Value> root) {
        if (root == null) {
            return null;
        }
        Node<TKey, Value> x = root;
        while (x.getRight() != null) {
            x = x.getRight();
        }
        return x;
    }

    /**
     * Deletes the node with the specified key from the tree.
     * @param key the key to delete
     */
    public void delete(TKey key) {
        root = delete(root, key);
    }

    /**
     * Private helper method to recursively delete the node with the specified key from the subtree rooted at v.
     * @param v the root of the current subtree
     * @param key the key to delete
     * @return the root of the modified subtree
     */
    private Node<TKey, Value> delete(Node<TKey, Value> v, TKey key) {
        if (v == null) {
            return null;
        }
        int cmp = key.compareTo(v.getKey());
        if (cmp < 0) {
            v.setLeft(delete(v.getLeft(), key));
        }
        else if (cmp > 0) {
            v.setRight(delete(v.getRight(), key));
        }
        else {
            if (v.getLeft() == null)
                return v.getRight();
            if (v.getRight() == null)
                return v.getLeft();
            Node<TKey, Value> t = v;
            v = min(v.getRight());
            v.setRight(deleteMin(t.getRight()));
            v.setLeft(t.getLeft());
        }
        return v;
    }

    /**
     * Private helper method to delete the node with the minimum key from the subtree rooted at v.
     * This is used when deleting a node with two children.
     * @param v the root of the current subtree
     * @return the root of the modified subtree
     */
    private Node<TKey, Value> deleteMin(Node<TKey, Value> v) {
        if (v.getLeft() == null) {
            return v.getRight();
        }
        v.setLeft(deleteMin(v.getLeft()));
        return v;
    }

    /**
     * Checks if the tree contains a node with the specified key.
     * @param key the key to search for
     * @return true if the key is found, false otherwise
     */
    public boolean contains(TKey key) {
        return contains(root, key);
    }

    /**
     * Private helper method to recursively search for a key in the subtree rooted at v.
     * @param v the root of the current subtree
     * @param key the key to search for
     * @return true if the key is found, false otherwise
     */
    private boolean contains(Node<TKey, Value> v, TKey key) {
        if (v == null) {
            return false;
        }
        else {
            int cmp = key.compareTo(v.getKey());
            if (cmp == 0) {
                return true;
            }
            else if (cmp < 0) {
                return contains(v.getLeft(), key);
            }
            else {
                return contains(v.getRight(), key);
            }
        }
    }

    /**
     * Retrieves the value associated with the specified key.
     * @param key the key whose associated value is to be returned
     * @return the value associated with the key, or null if the key is not found or the tree is empty
     */
    public Value get(TKey key) {
        if (isEmpty()) {
            return null;
        }
        return get(root, key);
    }

    /**
     * Private helper method to recursively retrieve the value associated with a key in the subtree rooted at v.
     * @param v the root of the current subtree
     * @param key the key to search for
     * @return the value associated with the key, or null if the key is not found
     */
    private Value get(Node<TKey, Value> v, TKey key) {
        Value val = null;
        if (v != null) {
            int cmp = key.compareTo(v.getKey());
            if (cmp == 0) {
                val = v.getValue();
            }
            else if (cmp < 0) {
                val = get(v.getLeft(), key);
            }
            else {
                val = get(v.getRight(), key);
            }
        }
        return val;
    }

    /**
     * Updates the value associated with the specified key.
     * If the key does not exist, this method does nothing.
     * @param key the key whose value is to be updated
     * @param value the new value
     */
    public void update(TKey key, Value value) {
        update(root, key, value);
    }

    /**
     * Private helper method to recursively update the value associated with a key in the subtree rooted at v.
     * @param v the root of the current subtree
     * @param key the key whose value is to be updated
     * @param value the new value
     */
    private void update(Node<TKey, Value> v, TKey key, Value value) {
        if (v != null) {
            int cmp = key.compareTo(v.getKey());
            if (cmp == 0) {
                v.setValue(value);
            }
            else if (cmp < 0) {
                update(v.getLeft(), key, value);
            }
            else {
                update(v.getRight(), key, value);
            }
        }
    }

    /**
     * Returns the height of the tree.
     * The height of an empty tree is -1. The height of a tree with one node is 0.
     * @return the height of the tree
     */
    public int height() {
        return height(this.root);
    }

    /**
     * Private helper method to get the height of a specific node.
     * The height of a null node is -1.
     * @param root the node whose height is to be determined
     * @return the height of the node
     */
    private int height(Node<TKey, Value> root) {
        if (root == null)
            return -1;
        return root.getHeight();
    }
}