package ru.mail.polis.ads.homework5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Task #5
 */

public final class Permutations {

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }
        for (int i1 : arr) {
            out.print(i1 + " ");
        }
        out.println();
        int i = n - 1;
        while (i > 0) {
            Arrays.sort(arr, i, n);
            int swapI = i;
            while (arr[swapI] < arr[i - 1]) {
                swapI++;
            }
            int temp = arr[swapI];
            arr[swapI] = arr[i - 1];
            arr[i - 1] = temp;
            i = n - 1;
            while (i > 0 && arr[i] < arr[i - 1]) {
                i--;
            }
            for (int i1 : arr) {
                out.print(i1 + " ");
            }
            out.println();
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