package ru.mail.polis.ads.homework9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// task: https://www.e-olymp.com/ru/problems/1948

public class Task1{
    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        List<List<Integer>> connectivityList = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            connectivityList.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int b = in.nextInt();
            int e = in.nextInt();
            connectivityList.get(b - 1).add(e - 1);
        }

        DfsRunner dfsRunner = new DfsRunner(connectivityList, n);
        if (dfsRunner.hasLoop) {
            out.write("-1");
        } else {
            for (int i = dfsRunner.sortedGraph.size() - 1; i >= 0; i--) {
                out.write(dfsRunner.sortedGraph.get(i) + " ");
            }
        }
    }

    static class DfsRunner {
        private final int[] colors;
        private final List<List<Integer>> graph;
        public boolean hasLoop;
        public final List<Integer> sortedGraph = new ArrayList<>();

        static final int WHITE = 0;
        static final int GRAY = 1;
        static final int BLACK = 2;

        DfsRunner(List<List<Integer>> graph, int n) {
            this.graph = graph;
            this.colors = new int[n];
            hasLoop = false;
            for (int i = 0; i < n; i++) {
                if (colors[i] == WHITE) {
                    dfs(i);
                }
            }
        }

        public void dfs(int knotIndex) {
            colors[knotIndex] = GRAY;
            for (int nextKnotIndex: graph.get(knotIndex)) {
                if (colors[nextKnotIndex] == WHITE) {
                    dfs(nextKnotIndex);
                } else {
                    if (colors[nextKnotIndex] == GRAY) {
                        hasLoop = true;
                        break;
                    }
                }
            }
            sortedGraph.add(knotIndex + 1);
            colors[knotIndex] = BLACK;
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