package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private Node root = null;
    private int size = 0;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int height;

        Node(Key key, Value value, int height) {
            this.key = key;
            this.value = value;
            this.height = height;
        }
    }

    private void fixHeight(Node x) {
        x.height = 1 + Math.max(height(x.left), height(x.right));
    }

    private int factor(Node x) {
        return height(x.left) - height(x.right);
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        y.left = x.right;
        x.right = y;
        fixHeight(y);
        fixHeight(x);
        return x;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        fixHeight(x);
        fixHeight(y);
        return y;
    }

    private Node balance(Node x) {
        if (factor(x) == 2) {
            if (factor(x.left) < 0) {
                x.left = rotateLeft(x.left);
            }
            return rotateRight(x);
        }
        if (factor(x) == -2) {
            if (factor(x.right) > 0) {
                x.right = rotateRight(x.right);
            }
            return rotateLeft(x);
        }
        return x;
    }

    private Node get(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (key.compareTo(x.key) < 0) {
            return get(x.left, key);
        }
        if (key.compareTo(x.key) > 0) {
            return get(x.right, key);
        }
        return x;
    }

    @Override
    public Value get(@NotNull Key key) {
        Node node = get(root, key);
        return node != null ? node.value : null;
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            size++;
            return new Node(key, value, 1);
        }
        if (key.compareTo(x.key) < 0) {
            x.left = put(x.left, key, value);
        } else {
            if (key.compareTo(x.key) > 0) {
                x.right = put(x.right, key, value);
            } else {
                x.value = value;
            }
        }
        fixHeight(x);
        x = balance(x);
        return x;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
    }

    private Node removeMin(Node x) {
        if (x.left == null) {
            return x.right;
        }
        x.left = removeMin(x.left);
        return x;
    }

    private void removeMin() {
        root = removeMin(root);
    }

    Node innerRemove(Node x) {
        if (x.right == null) {
            return x.left;
        }
        if (x.left == null) {
            return x.right;
        }
        Node t = x;
        x = min(t.right);
        x.right = removeMin(t.right);
        x.left = t.left;
        return x;
    }

    private Node remove(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (key.compareTo(x.key) < 0) {
            x.left = remove(x.left, key);
        }
        if (key.compareTo(x.key) > 0) {
            x.right = remove(x.right, key);
        }
        if (key.compareTo(x.key) == 0) {
            x = innerRemove(x);
        }
        return x;
    }

    @Override
    public Value remove(@NotNull Key key) {
        Node removeNode = get(root, key);
        if (removeNode == null) {
            return null;
        }
        root = remove(root, key);
        size--;
        return removeNode.value;
    }

    private Node min(Node x) {
        if (x == null) {
            return null;
        }
        if (x.left == null) {
            return x;
        }
        return min(x.left);
    }

    @Override
    public Key min() {
        Node minNode = min(root);
        return minNode != null ? minNode.key : null;
    }

    @Override
    public Value minValue() {
        Node minNode = min(root);
        return minNode != null ? minNode.value : null;
    }

    private Node max(Node x) {
        if (x == null) {
            return null;
        }
        if (x.right == null) {
            return x;
        }
        return max(x.right);
    }

    @Override
    public Key max() {
        Node maxNode = max(root);
        return maxNode != null ? maxNode.key : null;
    }

    @Override
    public Value maxValue() {
        Node maxNode = max(root);
        return maxNode != null ? maxNode.value : null;
    }

    private Node floor(Node current, Key key) {
        if (current == null) {
            return null;
        }
        if (current.key == key) {
            return current;
        }
        if (current.key.compareTo(key) > 0) {
            return floor(current.left, key);
        }
        Node floorNode = floor(current.right, key);
        if (floorNode != null) {
            return floorNode.key.compareTo(key) <= 0 ? floorNode : current;
        }
        return current;
    }

    @Override
    public Key floor(@NotNull Key key) {
        Node floorNode = floor(root, key);
        return floorNode != null ? floorNode.key : null;
    }

    private Node ceil(Node current, Key key) {
        if (current == null) {
            return null;
        }
        if (current.key == key) {
            return current;
        }
        if (current.key.compareTo(key) < 0) {
            return ceil(current.right, key);
        }
        Node ceilNode = ceil(current.left, key);
        if (ceilNode != null) {
            return ceilNode.key.compareTo(key) >= 0 ? ceilNode : current;
        }
        return current;
    }

    @Override
    public Key ceil(@NotNull Key key) {
        Node ceilNode = ceil(root, key);
        return ceilNode != null ? ceilNode.key : null;
    }

    @Override
    public int size() {
        return size;
    }

    private int height(Node x) {
        return x == null ? 0 : x.height;
    }

    @Override
    public int height() {
        return height(root);
    }
}
