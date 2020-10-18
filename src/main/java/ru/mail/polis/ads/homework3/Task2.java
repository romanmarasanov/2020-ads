package ru.mail.polis.ads.homework3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task2 {
    private Task2() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int commandCount = in.nextInt();
        MaxHeap heap = new MaxHeap();
        for (int i = 0; i < commandCount; i++){
             int command = in.nextInt();
            switch (command){
                case 0:
                    heap.insert(in.nextInt());
                    break;
                case 1:
                    out.println(heap.extract());
            }
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

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}

class MaxHeap{
    private int size;
    private int[] data;
    final private static double EXPANSION_RATIO = 1.5;

    MaxHeap(){
        data = new int[1001];
        size = 0;
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

    private void sink(int k){
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

    private void swim(int k){
        while(k > 1 && data[k] > data[k/2]){
            int tmp = data[k];
            data[k] = data[k/2];
            data[k/2] = tmp;
            k = k/2;
        }
    }

    private void grow(){
        int[] newData = new int[(int)(size*EXPANSION_RATIO)];
        if (size >= 0) System.arraycopy(data, 1, newData, 1, size);
        data = newData;
    }
}
