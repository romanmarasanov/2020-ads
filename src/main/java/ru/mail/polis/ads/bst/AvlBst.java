package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private int size;
    private Node root;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int height;

        public Node(Key key, Value value, int height) {
            this.key = key;
            this.value = value;
            this.height = height;
        }
    }

    AvlBst(){
        size = 0;
    }

    @Override
    public Value get(@NotNull Key key) {
        Node res = get(root, key);
        return res == null ? null : res.value;
    }

    private Node get(Node x, Key key){
        if (x == null) return null;
        if (key.compareTo(x.key) < 0) return get(x.left, key);
        if (key.compareTo(x.key) > 0) return get(x.right, key);
        return x;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
    }

    private Node put(Node x, Key key, Value value){
        if (x == null) {
            size++;
            x = new Node(key, value, 1);
            return x;
        }
        if (key.compareTo(x.key) < 0) x.left = put(x.left, key, value);
        else if (key.compareTo(x.key) > 0) x.right = put(x.right, key, value);
        else x.value = value;
        fixHeight(x);
        x = balance(x);
        return x;
    }

    @Override
    public Value remove(@NotNull Key key) {
        Node res = get(root, key);
        if (res == null) return null;
        else {
            size--;
            root = delete(root, key);
            return res.value;
        }
    }

    private Node delete(Node x, Key key){
        if (x == null) return null;
        if (key.compareTo(x.key) < 0) x.left = delete(x.left, key);
        else if (key.compareTo(x.key) > 0) x.right = delete(x.right, key);
        else x = innerDelete(x);
        return x;
    }

    private Node innerDelete(Node x){
        if (x.right == null) return x.left;
        if (x.left == null) return x.right;
        Node t = x;
        x = min(t.right);
        x.right = deleteMin(t.right);
        x.left = t.left;
        return x;
    }

    private Node deleteMin(Node x){
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        return x;
    }

    private Node deleteMax(Node x){
        if (x.right == null) return x.left;
        x.right = deleteMin(x.right);
        return x;
    }

    @Override
    public Key min() {
        Node res = min(root);
        return res == null ? null : res.key;
    }

    private Node min(Node x){
        if (x == null) return null;
        return x.left == null ? x : min(x.left);
    }

    @Override
    public Value minValue() {
        Node res = min(root);
        return res == null ? null : res.value;
    }

    @Override
    public Key max() {
        Node res = max(root);
        return res == null ? null : res.key;
    }

    private Node max(Node x){
        if (x == null) return null;
        return x.right == null ? x : max(x.right);
    }

    @Override
    public Value maxValue() {
        Node res = max(root);
        return res == null ? null : res.value;
    }

    @Override
    public Key floor(@NotNull Key key) {
        Node res = floor(root, key);
        return res == null ? null : res.key;
    }

    @Override
    public Key ceil(@NotNull Key key) {
        Node res = ceil(root, key);
        return res == null ? null : res.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) return null;
        if (key.compareTo(x.key) == 0) return x;
        if (key.compareTo(x.key) < 0) return floor(x.left, key);
        Node right = floor(x.right, key);
        return right == null ? x : right;
    }

    private Node ceil(Node x, Key key) {
        if (x == null) return null;
        if (key == x.key) return x;
        if (key.compareTo(x.key) > 0) return ceil(x.right, key);
        Node left = ceil(x.left, key);
        return left == null ? x : left;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return height(root);
    }

    private int height(Node x) {
        return x == null ? 0 : x.height;
    }

    /**
     * далее идут внутренние методы АВЛ-дерева (балансные)
     */

    private Node balance(Node x) {
        if (factor(x) == 2) {
            if (factor(x.left) < 0)
                x.left = rotateLeft(x.left);
            return rotateRight(x);
        }
        if (factor(x) == -2) {
            if (factor(x.right) > 0)
                x.right = rotateRight(x.right);
            return rotateLeft(x);
        }
        return x;
    }

    private Node rotateLeft(Node x) {
        Node r = x.right;
        x.right = r.left;
        r.left = x;
        fixHeight(x);
        fixHeight(r);
        return r;
    }

    private Node rotateRight(Node x) {
        Node l = x.left;
        x.left = l.right;
        l.right = x;
        fixHeight(x);
        fixHeight(l);
        return l;
    }

    private void fixHeight(Node node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    private int factor(Node x) {
        return height(x.left) - height(x.right);
    }
}
