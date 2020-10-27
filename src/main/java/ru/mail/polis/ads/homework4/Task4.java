package ru.mail.polis.ads.homework4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public final class Task4 {
    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] stepsPriceArray = new int[n + 2];
        for (int i = 1; i <= n; i++){
            stepsPriceArray[i] = in.nextInt();
        }
        int k = in.nextInt();

        out.print(findMaxPrice(stepsPriceArray, n, k));
    }

    private static int findMaxPrice(int[] stepsPriceArray, int n, int k) {
        int[] maxCost = new int[n + 2];
        maxCost[0] = 0;
        for (int i = 1; i < n + 2; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i >= k ? i - k : 0; j < i; j++) {
                if (maxCost[j] > max) {
                    max = maxCost[j];
                }
            }
            maxCost[i] = stepsPriceArray[i] + max;
        }
        return maxCost[n + 1];
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
