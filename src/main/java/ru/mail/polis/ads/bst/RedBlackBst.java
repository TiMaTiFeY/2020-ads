package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * LLRB implementation of binary search tree.
 */
public class RedBlackBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private static final boolean BLACK = false;
    private static final boolean RED = true;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        boolean color;

        Node(Key key, Value value, boolean color) {
            this.key = key;
            this.value = value;
            this.color = color;
        }
    }

    private Node root = null;
    private int size = 0;

    private boolean isRed(Node x) {
        return x != null && x.color == RED;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        y.color = x.color;
        x.color = RED;
        return y;
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        y.left = x.right;
        x.right = y;
        x.color = y.color;
        y.color = RED;
        return x;
    }

    private Node flipColors(Node x) {
        x.color = !x.color;
        x.left.color = !x.left.color;
        x.right.color = !x.right.color;
        return x;
    }

    private Node fixUp(Node x) {
        if (isRed(x.right) && !isRed(x.left)) {
            x = rotateLeft(x);
        }
        if (isRed(x.left) && isRed(x.left.left)) {
            x = rotateRight(x);
        }
        if (isRed(x.left) && isRed(x.right)) {
            flipColors(x);
        }
        return x;
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            size++;
            return new Node(key, value, RED);
        }
        int comparisonResult = key.compareTo(x.key);
        if (comparisonResult < 0) {
            x.left = put(x.left, key, value);
        } else {
            if (comparisonResult > 0) {
                x.right = put(x.right, key, value);
            } else {
                x.value = value;
            }
        }
        return fixUp(x);
    }

    private Node moveRedLeft(Node x) {
        flipColors(x);
        if (isRed(x.right.left)) {
            x.right = rotateRight(x.right);
            x = rotateLeft(x);
            flipColors(x);
        }
        return x;
    }

    private Node moveRedRight(Node x) {
        flipColors(x);
        if (isRed(x.left.left)) {
            x = rotateRight(x);
            flipColors(x);
        }
        return x;
    }

    private Node removeMax(Node x) {
        if (isRed(x.left)) {
            x = rotateRight(x);
        }
        if (x.right == null) {
            return null;
        }
        if (!isRed(x.right) && !isRed(x.right.left)) {
            x = moveRedRight(x);
        }
        x.right = removeMax(x.right);
        return fixUp(x);
    }

    private void removeMax() {
        root = removeMax(root);
        if (root != null) {
            root.color = BLACK;
        }
    }

    private Node removeMin(Node x) {
        if (x.left == null) {
            return null;
        }
        if (!isRed(x.left) && !isRed(x.left.left)) {
            x = moveRedLeft(x);
        }
        x.left = removeMin(x.left);
        return fixUp(x);
    }

    private void removeMin() {
        root = removeMin(root);
        if (root != null) {
            root.color = BLACK;
        }
    }

    private Node get(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int comparisonResult = key.compareTo(x.key);
        if (comparisonResult < 0) {
            return get(x.left, key);
        }
        if (comparisonResult > 0) {
            return get(x.right, key);
        }
        return x;
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        Node node = get(root, key);
        return node != null ? node.value : null;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node minNode(Node x) {
        if (x == null) {
            return null;
        }
        if (x.left == null) {
            return x;
        }
        return minNode(x.left);
    }

    private Node remove(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int comparisonResult = key.compareTo(x.key);
        if (comparisonResult < 0) {
            // delete left
            if (x.left != null) {
                if (!isRed(x.left) && !isRed(x.left.left)) {
                    x = moveRedLeft(x);
                }
                x.left = remove(x.left, key);
            }
        } else {
            // delete right
            if (isRed(x.left)) {
                x = rotateRight(x);
                x.right = remove(x.right, key);
            } else if (comparisonResult == 0 && x.right == null) {
                return null; // at the bottom
            } else {
                if (x.right != null && !isRed(x.right) && !isRed(x.right.left)) {
                    x = moveRedRight(x); // preserve invariant
                }
                // delete under invariant
                if (key == x.key) {
                    Node min = minNode(x.right);
                    x.key = min.key;
                    x.value = min.value;
                    x.right = removeMin(x.right);
                } else {
                    x.right = remove(x.right, key);
                }
            }
        }
        return fixUp(x);
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        Value removeNode = get(key);
        if (removeNode == null) {
            return null;
        }
        root = remove(root, key);
        size--;
        return removeNode;
    }

    @Nullable
    @Override
    public Key min() {
        Node minNode = minNode(root);
        return minNode != null ? minNode.key : null;
    }

    @Nullable
    @Override
    public Value minValue() {
        Node minNode = minNode(root);
        return minNode != null ? minNode.value : null;
    }

    private Node maxNode(Node x) {
        if (x == null) {
            return null;
        }
        if (x.right == null) {
            return x;
        }
        return maxNode(x.right);
    }

    @Nullable
    @Override
    public Key max() {
        Node maxNode = maxNode(root);
        return maxNode != null ? maxNode.key : null;
    }

    @Nullable
    @Override
    public Value maxValue() {
        Node maxNode = maxNode(root);
        return maxNode != null ? maxNode.value : null;
    }

    private Node floor(Node current, Key key) {
        if (current == null) {
            return null;
        }
        int comparisonResult = current.key.compareTo(key);
        if (comparisonResult == 0) {
            return current;
        }
        if (comparisonResult > 0) {
            return floor(current.left, key);
        }
        Node floorNode = floor(current.right, key);
        if (floorNode != null) {
            return floorNode.key.compareTo(key) <= 0 ? floorNode : current;
        }
        return current;
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        Node floorNode = floor(root, key);
        return floorNode != null ? floorNode.key : null;
    }

    private Node ceil(Node current, Key key) {
        if (current == null) {
            return null;
        }
        int comparisonResult = current.key.compareTo(key);
        if (comparisonResult == 0) {
            return current;
        }
        if (comparisonResult < 0) {
            return ceil(current.right, key);
        }
        Node ceilNode = ceil(current.left, key);
        if (ceilNode != null) {
            return ceilNode.key.compareTo(key) >= 0 ? ceilNode : current;
        }
        return current;
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        Node ceilNode = ceil(root, key);
        return ceilNode != null ? ceilNode.key : null;
    }

    @Override
    public int size() {
        return size;
    }
}
