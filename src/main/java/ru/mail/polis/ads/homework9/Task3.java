package ru.mail.polis.ads.homework9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Task3 {

    private static class Edge {
        int a;
        int b;
        int weight;

        Edge(int a, int b, int weight) {
            this.a = a;
            this.b = b;
            this.weight = weight;
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        Edge[] edges = new Edge[m + 1];
        int[] path = new int[n + 1];

        path[1] = 0;
        for (int i = 2; i <= n; i++) path[i] = 30000;

        for (int i = 1; i <= m; i++) edges[i] = new Edge(in.nextInt(), in.nextInt(), in.nextInt());

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= m; j++) {
                if (path[edges[j].a] == 30000) continue;
                if (path[edges[j].b] > path[edges[j].a] + edges[j].weight)
                    path[edges[j].b] = path[edges[j].a] + edges[j].weight;
            }
        }
        for (int i = 1; i <= n; i++) out.print(path[i] + " ");
        out.flush();
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

