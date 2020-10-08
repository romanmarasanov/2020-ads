package ru.mail.polis.ads.homework2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public final class Task5 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int size = in.nextInt();

        Number[] array = new Number[size];
        for (int i = 0; i < size; i++){
            int a = in.nextInt();
            int b = in.nextInt();
            array[i] = new Number(a, b);
        }

        mergeSort(array, size);

        for (int i = 0; i < size; i++) {
            out.println(array[i].firstNumber + " " + array[i].secondNumber);
        }
    }


    static class Number{
        public int firstNumber;
        public int secondNumber;

        public Number(int firstNumber, int secondNumber) {
            this.firstNumber = firstNumber;
            this.secondNumber = secondNumber;
        }
    }

    public static void mergeSort(Number[] a, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        Number[] l = new Number[mid];
        Number[] r = new Number[n - mid];

        System.arraycopy(a, 0, l, 0, mid);
        System.arraycopy(a, mid, r, 0, n - mid);
        mergeSort(l, mid);
        mergeSort(r, n - mid);

        merge(a, l, r, mid, n - mid);
    }

    public static void merge(Number[] a, Number[] l, Number[] r, int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i].firstNumber <= r[j].firstNumber) {
                a[k++] = l[i++];
            }
            else {
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
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