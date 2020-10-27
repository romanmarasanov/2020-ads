package ru.mail.polis.ads.homework4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Task3 {
    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] firstSeq = new int[n];
        for (int i = 0; i < n; i++) {
            firstSeq[i] = in.nextInt();
        }
        int m = in.nextInt();
        int[] secondSeq = new int[m];
        for (int i = 0; i < m; i++) {
            secondSeq[i] = in.nextInt();
        }
        out.println(findLongestCommonSequence(firstSeq, secondSeq, n, m));
    }

    private static int findLongestCommonSequence(int[] firstSeq, int[] secondSeq, int n, int m) {
        m++; n++;
        int[] lens = new int[m * n];
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (firstSeq[i - 1] == secondSeq[j - 1]) {
                    lens[i * m + j] = lens[(i - 1) * m + j - 1] + 1;
                } else {
                    lens[i * m + j] = Math.max(lens[i * m + j - 1], lens[(i - 1) * m + j]);
                }
            }
        }
        return lens[lens.length - 1];
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
