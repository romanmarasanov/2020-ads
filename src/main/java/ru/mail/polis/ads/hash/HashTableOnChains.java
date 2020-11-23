package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HashTableOnChains<K, V> implements HashTable<K, V> {

    private static final int START_SIZE = 16;
    private static final int EXPANSION_RATIO = 2;
    private static final double LOAD_FACTOR = 0.75;

    private int size = 0; /** all elements quantity */
    private Node<K, V>[] table = new Node[START_SIZE];

    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    @Override
    public @Nullable V get(@NotNull K k) {
        int index = getIndex(k.hashCode());
        Node<K, V> res = table[index];
        if (res == null) return null;
        while (res.next != null){
            res = res.next;
        }
        return res.value;
    }

    @Override
    public void put(@NotNull K k, @NotNull V v) {
        int index = getIndex(k.hashCode());
        Node<K, V> res = table[index];
        if (res == null) {
            table[index] = new Node<>(k, v);
            size++;
            if ((double)(size / table.length) > LOAD_FACTOR) resize();
            return;
        }
        while (res.key != k && res.next != null){
            res = res.next;
        }
        if (res.key.equals(k)) res.value = v;
        else {
            res.next = new Node<>(k, v);
            size++;
            if ((double)(size / table.length) > LOAD_FACTOR) resize();
        }
    }

    private void resize() {
        int tableSizeBefore = table.length;
        Node<K, V>[] tmp = table;
        table = new Node[tableSizeBefore*EXPANSION_RATIO];

        for (int i = 0; i < tableSizeBefore; i++) {
            Node<K, V> node = tmp[i];
            if (node != null) {
                while (node != null) {
                    put(node.key, node.value);
                    node = node.next;
                }
            }
        }
    }

    @Override
    public @Nullable V remove(@NotNull K k) {
        int index = getIndex(k.hashCode());
        Node<K, V> res = table[index];

        if (res == null) return null;

        if (res.next == null) {
            V val = res.value;
            table[index] = null;
            size--;
            return val;
        }

        while (!res.next.key.equals(k)) res = res.next;
        V val = res.next.value;
        res.next = res.next.next;
        return val;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private int getIndex(int hashCode){
        return (hashCode & 0x7fffffff) % table.length;
    }
}
