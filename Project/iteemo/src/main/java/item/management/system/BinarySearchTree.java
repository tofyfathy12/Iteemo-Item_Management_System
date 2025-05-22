package item.management.system;

class Node<TKey extends Comparable<TKey>, Value> {
        private TKey key;
        private Value value;
        private Node<TKey, Value> parent;
        private Node<TKey, Value> left;
        private Node<TKey, Value> right;
        public Node(TKey key, Value value) {
            this.parent = this.left = this.right = null;
            this.key = key;
            this.value = value;
        }

        public TKey getKey() {
            return key;
        }

        public Value getValue() {
            return value;
        }

        public void setValue(Value value) {
            this.value = value;
        }

        public boolean isRoot() {
            return (this.parent == null);
        }

        public Node<TKey, Value> getParent() {
            return this.parent;
        }

        public Node<TKey, Value> getLeft() {
            return this.left;
        }

        public void setLeft(Node<TKey, Value> left) {
            this.left = left;
            left.parent = this;
        }

        public boolean hasLeft() {
            return (this.left != null);
        }

        public Node<TKey, Value> getRight() {
            return this.right;
        }

        public void setRight(Node<TKey, Value> right) {
            this.right = right;
            right.parent = this;
        }

        public boolean hasRight() {
            return (this.right != null);
        }
    }

public class BinarySearchTree<TKey extends Comparable<TKey>, Value> implements IBinarySearchTree<TKey, Value> {
    private Node<TKey, Value> root;
    private int size;

    public BinarySearchTree() {
        this.root = null;
        this.size = 0;
    }

    public Node<TKey, Value> root() {
        return root;
    }

    public boolean isEmpty() {
        return (root == null);
    }

    public int size() {
        return size;
    }

    public void insert(TKey key, Value value) {
        insert(root, key, value);
    }

    private void insert(Node<TKey, Value> v, TKey key, Value value) {
        if (v == null) {
            root = new Node<TKey, Value>(key, value);
        }
        else {
            int cmp = key.compareTo(v.getKey());
            if (cmp == 0) {
                v.setValue(value);
            }
            else if (cmp < 0) {
                if (v.hasLeft()) {
                    insert(v.getLeft(), key, value);
                }
                else {
                    v.setLeft(new Node<TKey, Value>(key, value));
                }
            }
            else {
                if (v.hasRight()) {
                    insert(v.getRight(), key, value);
                }
                else {
                    v.setRight(new Node<TKey, Value>(key, value));
                }
            }
        }
    }

    public Value getMin() {
        return min(root).getValue();
    }

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

    public Value getMax() {
        return max(root).getValue();
    }

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

    public void delete(TKey key) {
        root = delete(root, key);
    }

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

    private Node<TKey, Value> deleteMin(Node<TKey, Value> v) {
        if (v.getLeft() == null) {
            return v.getRight();
        }
        v.setLeft(deleteMin(v.getLeft()));
        return v;
    }

    public boolean contains(TKey key) {
        return contains(root, key);
    }

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

    public Value get(TKey key) {
        if (isEmpty()) {
            return null;
        }
        return get(root, key);
    }

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

    public void update(TKey key, Value value) {
        update(root, key, value);
    }

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

    public int height() {
        return height(this.root);
    }

    private int height(Node<TKey, Value> root) {
        if (root == null)
            return 0;
        return 1 + Math.max(height(root.getLeft()), height(root.getRight()));
    }
}
