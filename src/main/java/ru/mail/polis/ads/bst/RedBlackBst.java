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

    private int size = 0;
    Node root = null;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        boolean color;

        public Node(Key key, Value value, boolean color) {
            this.key = key;
            this.value = value;
            this.color = color;
        }
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        Node res = get(root, key);
        return res == null ? null : res.value;
    }

    private Node get(Node x, Key key){
        if (x == null) return null;
        int compareResult = key.compareTo(x.key);
        if (compareResult < 0) return get(x.left, key);
        if (compareResult > 0) return get(x.right, key);
        return x;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node x, Key key, Value value){
        if (x == null) {
            size++;
            return new Node(key, value, RED);
        }
        int compareResult = key.compareTo(x.key);
        if (compareResult < 0) x.left = put(x.left, key, value);
        else if (compareResult > 0) x.right = put(x.right, key, value);
        else x.value = value;

        return fixUp(x);
    }

    private Node fixUp(Node x){
        if (isRed(x.right) && !isRed(x.left)) x = rotateLeft(x);
        if (isRed(x.left) && isRed(x.left.left)) x = rotateRight(x);
        if (isRed(x.left) && isRed(x.right)) flipColors(x);

        return x;
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        Value valueToRemove = get(key);
        if (valueToRemove == null) return null;
        root = remove(root, key);
        size--;
        return valueToRemove;
    }

    private Node remove(Node x, Key key){
        if (x == null) return null;
        if (key.compareTo(x.key) < 0) {
            if (x.left != null) {
                if (!isRed(x.left) && !isRed(x.left.left)) x = moveRedLeft(x);
                x.left = remove(x.left, key);
            }
        } else {
            if (isRed(x.left)) {
                x = rotateRight(x);
                x.right = remove(x.right, key);
            } else if (key.compareTo(x.key) == 0 && x.right == null) {
                return null;
            } else {
                if (x.right != null && !isRed(x.right) && !isRed(x.right.left)){
                    x = moveRedRight(x);
                }
                if (key.compareTo(x.key) == 0){
                    Node min = minNode(x.right);
                    x.key = min.key;
                    x.value = min.value;
                    x.right = deleteMin(x.right);
                } else {
                    x.right = remove(x.right, key);
                }
            }
        }
        return fixUp(x);
    }

    @Nullable
    @Override
    public Key min() {
        Node res = min(root);
        return res == null ? null : res.key;
    }

    @Nullable
    @Override
    public Value minValue() {
        Node res = min(root);
        return res == null ? null : res.value;
    }

    private Node min(Node x){
        if (x == null) return null;
        return x.left == null ? x : min(x.left);
    }

    @Nullable
    @Override
    public Key max() {
        Node res = max(root);
        return res == null ? null : res.key;
    }

    @Nullable
    @Override
    public Value maxValue() {
        Node res = max(root);
        return res == null ? null : res.value;
    }

    private Node max(Node x){
        if (x == null) return null;
        return x.right == null ? x : max(x.right);
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        Node res = floor(root, key);
        return res == null ? null : res.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int compareResult = key.compareTo(x.key);
        if (compareResult == 0) return x;
        if (compareResult < 0) return floor(x.left, key);
        Node right = floor(x.right, key);
        return right == null ? x : right;
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        Node res = ceil(root, key);
        return res == null ? null : res.key;
    }

    private Node ceil(Node x, Key key) {
        if (x == null) return null;
        int compareResult = key.compareTo(x.key);
        if (compareResult == 0) return x;
        if (compareResult > 0) return ceil(x.right, key);
        Node left = ceil(x.left, key);
        return left == null ? x : left;
    }

    @Override
    public int size() {
        return size;
    }

    /** helper methods for fixUp */

    private Node rotateLeft(Node x){
        Node r = x.right;
        x.right = r.left;
        r.left = x;
        r.color = x.color;
        x.color = RED;
        return r;
    }

    private Node rotateRight(Node x){
        Node l = x.left;
        x.left = l.right;
        l.right = x;
        l.color = x.color;
        x.color = RED;
        return l;
    }

    private void flipColors(Node x){
        x.color = !x.color;
        x.right.color = !x.right.color;
        x.left.color = !x.left.color;
    }

    private boolean isRed(Node x){
        return x != null && x.color == RED;
    }

    /** helper methods for delete */

    Node moveRedLeft(Node x){
        flipColors(x);
        if (isRed(x.right.left)){
            x.right = rotateRight(x.right);
            x = rotateLeft(x);
            flipColors(x);
        }
        return x;
    }

    Node moveRedRight(Node x){
        flipColors(x);
        if (isRed(x.left.left)){
            x = rotateRight(x);
            flipColors(x);
        }
        return x;
    }

    private Node deleteMin(Node x){
        if (x.left == null) return null;
        if (!isRed(x.left) && !isRed(x.left.left)) x = moveRedLeft(x);
        x.left = deleteMin(x.left);
        return fixUp(x);
    }

    private Node minNode(Node x){
        if (x == null) return null;
        if (x.left == null) return x;
        return minNode(x.left);
    }

    /** useless method :) */
    private Node deleteMax(Node x){
        if (isRed(x.left)) x = rotateRight(x);
        if (x.right == null) return null;
        if (!isRed(x.right) && !isRed(x.right.left)) x = moveRedRight(x);
        x.right = deleteMax(x.right);
        return fixUp(x);
    }

}

