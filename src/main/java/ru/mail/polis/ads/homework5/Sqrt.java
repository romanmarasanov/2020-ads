package ru.mail.polis.ads.homework5;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Task#1
 */

public final class Sqrt {

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        double c = Double.parseDouble(in.next());

        double l = 0.0; double r = 1e10; double x = (r + l) / 2;
        while(r - l > 0.0000001){
            if(f(x) > c){
                r = x;
            } else {
                l = x;
            }
            x = (r + l) / 2;
        }

        out.println(x);
    }

    static double f(double x){
        return x * x + Math.sqrt(x);
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
