package ru.mail.polis.ads.homework3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * dynamic median with two heaps. TODO: сделать minHeap и maxHeap детишками Heap с использованием Comparator, сделать отдельную MedianHeap
 */
public final class Task3 {
    private Task3() {
        // Should not be instantiated
    }
    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        HeapMax maxHeap = new HeapMax(1_500_000);
        HeapMin minHeap = new HeapMin(1_500_000);
        maxHeap.insert(Integer.MIN_VALUE);
        minHeap.insert(Integer.MAX_VALUE);
        int sequenceSize = 0;
        while (true){
            try {
                int number = in.nextInt();
                if (sequenceSize++ % 2 == 0) {
                    if (number > minHeap.peek()) {
                        maxHeap.insert(minHeap.extract());
                        minHeap.insert(number);
                    } else {
                        maxHeap.insert(number);
                    }
                    out.println(maxHeap.peek());
                } else {
                    if (number < maxHeap.peek()) {
                        minHeap.insert(maxHeap.extract());
                        maxHeap.insert(number);
                    } else {
                        minHeap.insert(number);
                    }
                    out.println((minHeap.peek() + maxHeap.peek()) / 2);
                }
            }
            catch(Exception ign) {break;}
            }
        }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}


class HeapMax{
    protected int size;
    protected int[] data;
    final protected static double EXPANSION_RATIO = 1.5;
    final protected static int DEFAULT_INITIAL_MEMORY = 1000;

    HeapMax(){
        data = new int[DEFAULT_INITIAL_MEMORY];
        size = 0;
    }
    HeapMax(int initialMemoryCapacity){
        data = new int[initialMemoryCapacity];
        size = 0;
    }

    public int peek(){
        return data[1];
    }

    public void insert(int a){
        if (size == data.length - 1) grow();
        data[++size] = a;
        swim(size);
    }

    public int extract(){
        int a = data[1];
        int tmp = data[size];
        data[size] = data[1];
        data[1] = tmp;
        size--;
        sink(1);
        return a;
    }

    protected void sink(int k){
        while (2 * k <= size){
            int j = 2 * k;
            if (j < size && data[j] < data[j+1]) j++;
            if (data[k] >= data[j]) break;
            int tmp = data[k];
            data[k] = data[j];
            data[j] = tmp;
            k = j;
        }
    }

    protected void swim(int k){
        while(k > 1 && data[k] > data[k/2]){
            int tmp = data[k];
            data[k] = data[k/2];
            data[k/2] = tmp;
            k = k/2;
        }
    }

    protected void grow(){
        int[] newData = new int[(int)(size * EXPANSION_RATIO)];
        if (size >= 0) System.arraycopy(data, 1, newData, 1, size);
        data = newData;
    }
}

class HeapMin extends HeapMax{

    HeapMin(int initialMemoryCapacity){
        super(initialMemoryCapacity);
    }

    @Override
    protected void sink(int k){
        while (2 * k <= size){
            int j = 2 * k;
            if (j < size && data[j] > data[j+1]) j++;
            if (data[k] <= data[j]) break;
            int tmp = data[k];
            data[k] = data[j];
            data[j] = tmp;
            k = j;
        }
    }
    @Override
    protected void swim(int k){
        while(k > 1 && data[k] < data[k/2]){
            int tmp = data[k];
            data[k] = data[k/2];
            data[k/2] = tmp;
            k = k/2;
        }
    }
}