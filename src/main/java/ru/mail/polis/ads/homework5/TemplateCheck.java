package ru.mail.polis.ads.homework5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Task #4
 */

public final class TemplateCheck {

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {

        String template = in.next();
        String sequence = in.next();

        if (template.equals(sequence)) {
            out.print("YES");
            return;
        }

        String word, pattern;
        if (sequence.contains("?") || sequence.contains("*")) {
            word = template;
            pattern = sequence;
        } else {
            word = sequence;
            pattern = template;
        }
        int n = word.length();
        int m = pattern.length();

        boolean[][] d = new boolean[m + 1][n + 1];
        d[0][0] = true;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if  (pattern.charAt(i - 1) == word.charAt(j - 1) || pattern.charAt(i - 1) == '?') {
                    d[i][j] = d[i - 1][j - 1];
                } else if (pattern.charAt(i - 1) == '*') {
                    d[i][j] = d[i - 1][j - 1] || d[i][j - 1] || d[i - 1][j];
                } else {
                    d[i][j] = false;
                }
            }
        }

        System.out.print(d[m][n] ? "YES" : "NO");
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
