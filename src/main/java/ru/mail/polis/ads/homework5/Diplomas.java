package ru.mail.polis.ads.homework5;

import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.io.PrintWriter;
        import java.util.StringTokenizer;

/**
 * Task #2
 */

public final class Diplomas {

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        long w = in.nextInt();
        long h = in.nextInt();
        long n = in.nextInt();
        long answerMax = Math.min(h, w) * n;
        long answerMin = Math.max(h, w);
        while (answerMax > answerMin) {
            long answer = (answerMax + answerMin) / 2;
            if ((answer / w) * (answer / h) >= n) {
                answerMax = answer;
            } else {
                answerMin = answer + 1;
            }
        }
        out.println(answerMin);
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