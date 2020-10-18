package ru.mail.polis.ads.homework3;

import java.io.*;
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
        int q = in.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++){
            array[i] = in.nextInt();
        }

        for (int i = 0; i < q; i++){
            if (binarySearch(array, in.nextInt()) == -1) out.println("NO");
            else out.println("YES");
        }
    }

    private static int binarySearch(int[] arr, int x){
        int l = 0;
        int r = arr.length - 1;
        while (l <= r){
            int m = (l + r) / 2;
            if (arr[m] < x){
                l = m + 1;
            } else if (arr[m] > x) {
                r = m - 1;
            } else {
                return m;
            }
        }
        return -1;
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
